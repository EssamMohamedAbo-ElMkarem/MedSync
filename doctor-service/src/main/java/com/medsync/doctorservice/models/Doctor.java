package com.medsync.doctorservice.models;

import java.time.LocalDate;
import java.util.UUID;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NonNull
    private String name;

    @Email
    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String specialization;

    @NonNull
    private String address;

    @NonNull
    private LocalDate joiningDate;

    public Doctor(){ }

    public Doctor(UUID id, String name, String email, String specialization, String address, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.specialization = specialization;
        this.address = address;
        this.joiningDate = joiningDate;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getJoiningDate() {
        return this.joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

}
