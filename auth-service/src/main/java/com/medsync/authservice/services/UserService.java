package com.medsync.authservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsync.authservice.models.User;
import com.medsync.authservice.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
