package com.medsync.authservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medsync.authservice.dtos.LoginRequestDTO;
import com.medsync.authservice.dtos.SignupRequestDTO;
import com.medsync.authservice.dtos.SignupResponseDTO;
import com.medsync.authservice.models.User;
import com.medsync.authservice.utils.JWTUtil;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        return userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));
    }

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO){
        Optional<User> existingUser = userService.findByEmail(signupRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists.");
        }
        try {
            userService.createUser(signupRequestDTO);
            return new SignupResponseDTO("User created successfully");
        } catch (Exception e) {
            return new SignupResponseDTO("User wasn't created successfully");
        }
    }
}
