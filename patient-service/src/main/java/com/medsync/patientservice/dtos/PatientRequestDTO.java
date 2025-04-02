package com.medsync.patientservice.dtos;

import com.medsync.patientservice.dtos.validators.CreatePatientValidationGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {
    
    @NotBlank(message="Name cannot be blank")
    @Size(max=100, message="Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message="Email cannot be blank")
    @Email(message="Email should be valid")
    private String email;

    @NotBlank(message="Address cannot be blank")
    private String address;

    @NotBlank(message="Date of birth cannot be blank")
    private String dateOfBirth;

    @NotBlank(groups=CreatePatientValidationGroup.class, message="Register Date cannot be blank")
    private String registerDate;

    public PatientRequestDTO() {}

    public PatientRequestDTO(String name, String email, String address, String dateOfBirth, String registerDate) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.registerDate = registerDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

}
