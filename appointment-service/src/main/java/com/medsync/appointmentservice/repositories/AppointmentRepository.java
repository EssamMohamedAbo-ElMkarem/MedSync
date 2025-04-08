package com.medsync.appointmentservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medsync.appointmentservice.models.Appointment;

@Repository
public interface  AppointmentRepository extends JpaRepository<Appointment, UUID> {
    
}
