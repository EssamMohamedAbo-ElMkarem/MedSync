package com.medsync.doctorservice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsync.doctorservice.models.Doctor;
import com.medsync.doctorservice.repositories.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor getDoctorById(UUID id){
        return doctorRepository.findById(id).orElseThrow();
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(UUID id, Doctor doctor) throws Exception{
        if(!doctorRepository.existsById(id)){
            throw new Exception("Doctor with id " + id.toString() + " doesn't exist!");
        }
        else{
            Doctor updatedDoctor = doctorRepository.findById(id).orElseThrow();
            updatedDoctor.setName(doctor.getName());
            updatedDoctor.setEmail(doctor.getEmail());
            updatedDoctor.setAddress(doctor.getAddress());
            updatedDoctor.setSpecialization(doctor.getSpecialization());
            updatedDoctor.setJoiningDate(doctor.getJoiningDate());
            Doctor savedDoctor = doctorRepository.save(updatedDoctor);
            return savedDoctor;
        }
    }

    public void deleteDoctor(UUID id){
        doctorRepository.deleteById(id);
    }

}
