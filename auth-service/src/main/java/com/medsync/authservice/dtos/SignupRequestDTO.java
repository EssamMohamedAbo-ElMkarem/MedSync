package com.medsync.authservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupRequestDTO {

    @NotBlank(message="Email can't be empty")
    @Email(message="Email has to be valid")
    private String email;

    @NotBlank(message="Password can't be empty")
    private String password;

    public SignupRequestDTO(){ }

    public SignupRequestDTO(String email, String password, String role) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
