package com.medsync.appointmentservice.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    private UUID patientId;
    private UUID doctorId;
    private LocalDate date;
    private LocalTime startTime;

    public Appointment() {
        this.id = UUID.randomUUID();
    }

    public Appointment(UUID patientId, UUID doctorId, LocalDate date, LocalTime startTime) {
        this.id = UUID.randomUUID();
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.startTime = startTime;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return startTime.plusHours(1);
    }

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(date, startTime);
    }

    public LocalDateTime getEndDateTime() {
        return getStartDateTime().plusHours(1);
    }
}
