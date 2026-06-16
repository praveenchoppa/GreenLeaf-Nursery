package com.nursery.nursery.service.impl;

import com.nursery.nursery.dto.LoginRequest;
import com.nursery.nursery.dto.LoginResponse;
import com.nursery.nursery.dto.RegisterRequest;
import com.nursery.nursery.entity.Role;
import com.nursery.nursery.entity.User;
import com.nursery.nursery.exception.BadRequestException;
import com.nursery.nursery.repository.UserRepository;
import com.nursery.nursery.security.JwtUtil;
import com.nursery.nursery.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl
        implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String createAdmin() {

        if (userRepository.findByUsername("admin").isPresent()) {
            return "Admin already exists";
        }

        User admin = new User();

        admin.setUsername("admin");
        admin.setEmail("admin@nursery.com");

        admin.setPassword(
                passwordEncoder.encode("admin123")
        );

        admin.setRole(
                Role.ROLE_ADMIN
        );

        userRepository.save(admin);

        return "Admin created successfully";
    }

    @Override
    public String register(
            RegisterRequest request
    ) {

        if (
                userRepository.existsByUsername(
                        request.getUsername()
                )
        ) {
            throw new BadRequestException(
                    "Username already exists"
            );
        }

        if (
                userRepository.existsByEmail(
                        request.getEmail()
                )
        ) {
            throw new BadRequestException(
                    "Email already exists"
            );
        }

        User user = new User();

        user.setUsername(
                request.getUsername()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(
                Role.ROLE_USER
        );

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public LoginResponse login(
            LoginRequest request
    ) {

        User user =
                userRepository.findByUsername(
                        request.getUsername()
                ).orElseThrow(() ->
                        new BadRequestException(
                                "Invalid username/password"
                        )
                );

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {

            throw new BadRequestException(
                    "Invalid username/password"
            );
        }

        String token =
                jwtUtil.generateToken(
                        user.getUsername()
                );

        return new LoginResponse(token,user.getRole().name());
    }
}