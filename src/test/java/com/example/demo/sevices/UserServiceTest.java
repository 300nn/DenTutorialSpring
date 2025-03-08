package com.example.demo.sevices;

import com.example.demo.dto.UserWithRoleDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RolesRepository rolesRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldGetAllUsersEmpty() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        List<UserWithRoleDTO> result = userService.getAllUsers();

        log.info("Response: {}", result);

        assertEquals(0, result.size());
    }

    @Test
    void shouldGetAllUsers() {
        List<User> testUsers = createTestUsers();

        when(userRepository.findAll()).thenReturn(testUsers);

        log.info("Returned data from jpa {}", testUsers);

        List<UserWithRoleDTO> result = userService.getAllUsers();

        log.info("Response: {}", result);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testUsers.get(1).getUsername(), result.get(1).getUsername());
        assertEquals(testUsers.get(1).getRole().getRoleName(), result.get(1).getRole());
    }

    private List<User> createTestUsers() {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .username("1")
                .password("pas")
                .role(Role.builder()
                        .roleName("Admin")
                        .build())
                .build());
        users.add(User.builder()
                .username("2")
                .password("pas2223w")
                .role(Role.builder()
                        .roleName("User")
                        .build())
                .build());
        return users;
    }

}
