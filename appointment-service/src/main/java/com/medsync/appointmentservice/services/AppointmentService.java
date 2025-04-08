package com.medsync.appointmentservice.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.medsync.appointmentservice.models.Appointment;
import com.medsync.appointmentservice.repositories.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getAppointmentById(UUID id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void deleteAppointment(UUID id) {
        appointmentRepository.deleteById(id);
    }

    public Appointment updateAppointment(UUID id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setDate(updatedAppointment.getDate());
            appointment.setStartTime(updatedAppointment.getStartTime());
            appointment.setPatientId(updatedAppointment.getPatientId());
            appointment.setDoctorId(updatedAppointment.getDoctorId());
            return appointmentRepository.save(appointment);
        }).orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }
}
