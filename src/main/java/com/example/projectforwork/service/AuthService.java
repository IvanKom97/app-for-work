package com.example.projectforwork.service;

import com.example.projectforwork.config.JwtTokenProvider;
import com.example.projectforwork.dto.JwtResponse;
import com.example.projectforwork.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public JwtResponse auth(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            String token = jwtTokenProvider.generateToken(authentication.getName());
            return new JwtResponse(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("wrong credentials");
        }
    }

}
