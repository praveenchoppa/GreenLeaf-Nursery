package com.nursery.nursery.controller;

import com.nursery.nursery.dto.UserResponse;
import com.nursery.nursery.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser() {

        return userService.getCurrentUser();
    }
}