package com.example.demo.controllers;

import com.example.demo.dto.TokenDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserWithRoleDTO;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(new TokenDTO(userService.login(user)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/create-password")
    public ResponseEntity<String> createPassword(@RequestParam String password) {
        try {
            return ResponseEntity.ok(encoder.encode(password));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserWithRoleDTO user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get-user/{username}")
    public ResponseEntity<UserWithRoleDTO> getUser(@PathVariable("username") String username, Principal principal) {
        try {
            if (!principal.getName().equals(username)) {
                return ResponseEntity.status(403).build();
            }
            return ResponseEntity.ok(userService.getUser(username));
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserWithRoleDTO>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
}
