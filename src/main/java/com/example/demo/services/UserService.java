package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserWithRoleDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Transactional(readOnly = true)
    public UserWithRoleDTO getUser(String username) {

        return mapToUserDTO(Objects.requireNonNull(userRepository.findByUsername(username).orElse(null)));
    }

    public String login(UserDTO user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()));
            log.warn(authentication.getPrincipal());
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtUtils.generateToken(userDetails);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Transactional
    public List<UserWithRoleDTO> getAllUsers() {
        /*List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserDTO userDto = mapToUserDTO(user);
                    userDto.setPassword("********");
                    log.info(user.getUsername());
                    return userDto;
                })
                .toList();*/
        return userRepository.findAll().stream()
                .map(this::mapToUserDTO)
                .toList();
    }

    @Transactional
    public void registerUser(UserWithRoleDTO userWithRoleDTO) {
        userRepository.save(mapUserToEntity(userWithRoleDTO));
    }

    private User mapUserToEntity(UserWithRoleDTO userWithRoleDTO) {
        return User.builder()
                .username(userWithRoleDTO.getUsername())
                .password(passwordEncoder.encode(userWithRoleDTO.getPassword()))
                .role(rolesRepository.findByRoleName(userWithRoleDTO.getRole()).orElseThrow())
                .build();
    }

    private UserWithRoleDTO mapToUserDTO(User user) {
        return UserWithRoleDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole().getRoleName())
                .build();
    }
}
