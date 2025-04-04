package com.medsync.patientservice.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsync.patientservice.dtos.PatientRequestDTO;
import com.medsync.patientservice.dtos.PatientResponseDTO;
import com.medsync.patientservice.exceptions.EmailAlreadyExistsException;
import com.medsync.patientservice.exceptions.PatientNotFoundException;
import com.medsync.patientservice.grpc.BillingGrpcClient;
import com.medsync.patientservice.kafka.KafkaProducer;
import com.medsync.patientservice.mappers.PatientMapper;
import com.medsync.patientservice.models.EventType;
import com.medsync.patientservice.models.Patient;
import com.medsync.patientservice.repositories.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BillingGrpcClient billingGrpcClient;

    @Autowired
    private KafkaProducer kafkaProducer;

    public List<PatientResponseDTO> getPatients() {
        return patientRepository.findAll()
                .stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO getPatientById(UUID id) throws PatientNotFoundException {
        return PatientMapper.toDTO(patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id.toString())));
    }

    public PatientResponseDTO createPatient(
            PatientRequestDTO patientRequestDTO) throws EmailAlreadyExistsException {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email"
                    + " already exists " + patientRequestDTO.getEmail());
        } else {
            Patient newPatient = PatientMapper.toModel(patientRequestDTO);
            patientRepository.save(newPatient);
            billingGrpcClient.createBillingAccount(newPatient.getId().toString(), newPatient.getName(),
                    newPatient.getEmail());
            // Send kafka PATIENT_CREATED event
            kafkaProducer.sendEvent(newPatient, EventType.PATIENT_CREATED);
            return PatientMapper.toDTO(newPatient);
        }
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO)
            throws PatientNotFoundException, EmailAlreadyExistsException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id.toString()));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email"
                    + " already exists " + patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);

        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id.toString()));
        kafkaProducer.sendEvent(patient, EventType.PATIENT_DELETED);
        patientRepository.deleteById(id);
    }

}
