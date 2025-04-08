package com.medsync.doctorservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medsync.doctorservice.models.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

}
