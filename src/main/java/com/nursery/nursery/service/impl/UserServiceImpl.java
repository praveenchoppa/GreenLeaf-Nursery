package com.nursery.nursery.service.impl;

import com.nursery.nursery.dto.UserResponse;
import com.nursery.nursery.entity.User;
import com.nursery.nursery.exception.ResourceNotFoundException;
import com.nursery.nursery.repository.UserRepository;
import com.nursery.nursery.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository.findByUsername(
                        username
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}