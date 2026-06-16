package com.nursery.nursery.controller;

import com.nursery.nursery.dto.LoginRequest;
import com.nursery.nursery.dto.LoginResponse;
import com.nursery.nursery.dto.RegisterRequest;
import com.nursery.nursery.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/create-admin")
    public String createAdmin() {

        return authService.createAdmin();
    }

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequest request
    ) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }
}