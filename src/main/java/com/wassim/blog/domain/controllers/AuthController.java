package com.wassim.blog.domain.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wassim.blog.domain.dto.AuthResponse;
import com.wassim.blog.domain.dto.LoginRequest;
import com.wassim.blog.domain.services.intefaces.IAuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthenticationService authenticationService;

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        var userDetails = authenticationService.authenticate(request.getEmail(), request.getPassword());
        var token = authenticationService.generateToken(userDetails);
        AuthResponse response = AuthResponse.builder() // Example: token valid for 1 hour
                .token(token)
                .expiresIn(3600)
                .build();
        return ResponseEntity.ok(response); // Example: token valid for 1 hour
    }

}
