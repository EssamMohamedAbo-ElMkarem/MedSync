package com.medsync.authservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medsync.authservice.dtos.SignupRequestDTO;
import com.medsync.authservice.models.User;
import com.medsync.authservice.models.UserRole;
import com.medsync.authservice.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User createUser(SignupRequestDTO signupRequestDTO){
        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        user.setRole(UserRole.VIEWER.name());
        return userRepository.save(user);
    }
}
