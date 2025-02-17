package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO getUser(String username) {
        return mapToUserDTO(Objects.requireNonNull(userRepository.findByUsername(username).orElse(null)));
    }

    @Transactional
    public void registerUser(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(userRepository.findRoleByRoleName(userDTO.getRole()).orElse(null))
                .build();
        userRepository.save(user);
    }

    private User mapUserToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getRole());
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole().getRoleName())
                .build();
    }
}
