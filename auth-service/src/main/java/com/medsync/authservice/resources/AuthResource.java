package com.medsync.authservice.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medsync.authservice.dtos.LoginRequestDTO;
import com.medsync.authservice.dtos.LoginResponseDTO;
import com.medsync.authservice.services.AuthService;

@RestController
@RequestMapping("/")
public class AuthResource {

    @Autowired
    private AuthService authService;

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO){
        Optional<String> token = authService.authenticate(loginRequestDTO);
        if(token.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new LoginResponseDTO(token.get()));
    }
}
