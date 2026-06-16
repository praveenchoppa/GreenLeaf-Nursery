package com.nursery.nursery.service;

import com.nursery.nursery.dto.LoginRequest;
import com.nursery.nursery.dto.LoginResponse;
import com.nursery.nursery.dto.RegisterRequest;

public interface AuthService {

    LoginResponse login(
            LoginRequest request
    );

    String createAdmin();

    String register(
            RegisterRequest request
    );
}