package com.nursery.nursery.controller;

import com.nursery.nursery.dto.LoginRequest;
import com.nursery.nursery.dto.LoginResponse;
import com.nursery.nursery.entity.Role;
import com.nursery.nursery.entity.User;
import com.nursery.nursery.repository.UserRepository;
import com.nursery.nursery.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create-admin")
    public String createAdmin() {

        if (userRepository.findByUsername("admin").isPresent()) {
            return "Admin already exists";
        }

        User admin = new User();

        admin.setUsername("admin");

        admin.setPassword(passwordEncoder.encode("admin123"));

        admin.setRole(Role.ROLE_ADMIN);

        userRepository.save(admin);

        return "Admin created successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {

        User user = userRepository.findByUsername(
                        request.getUsername()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid username/password"
                        )
                );

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new RuntimeException(
                    "Invalid username/password"
            );
        }

        String token =
                jwtUtil.generateToken(
                        user.getUsername()
                );

        return new LoginResponse(token);
    }
}