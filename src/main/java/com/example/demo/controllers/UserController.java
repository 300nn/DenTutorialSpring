package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username) {
        try {
            return ResponseEntity.ok(userService.getUser(username));
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
}
