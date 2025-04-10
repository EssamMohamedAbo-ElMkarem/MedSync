package com.medsync.authservice.dtos;

public class SignupResponseDTO {

    private String message;

    public SignupResponseDTO() { }

    public SignupResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
