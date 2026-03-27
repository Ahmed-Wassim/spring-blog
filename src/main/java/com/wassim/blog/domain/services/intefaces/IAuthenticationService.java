package com.wassim.blog.domain.services.intefaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationService {
    UserDetails authenticate(String email, String password);

    String generateToken(UserDetails userDetails);

    UserDetails validateToken(String token);
}
