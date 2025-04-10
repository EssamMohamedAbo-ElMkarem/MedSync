package com.medsync.authservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

    @Email(message="Email should be valid")
    @NotBlank(message="Email should be provided")
    private String email;

    @NotBlank(message="Password should be provided")
    @Size(min=8, message="Password shouldn't be less than 8 characters")
    private String password;

    public LoginRequestDTO() { }

    public LoginRequestDTO(String email, String password){
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
