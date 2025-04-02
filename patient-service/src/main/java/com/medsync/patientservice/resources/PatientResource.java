package com.medsync.patientservice.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medsync.patientservice.dtos.PatientRequestDTO;
import com.medsync.patientservice.dtos.PatientResponseDTO;
import com.medsync.patientservice.dtos.validators.CreatePatientValidationGroup;
import com.medsync.patientservice.exceptions.EmailAlreadyExistsException;
import com.medsync.patientservice.exceptions.PatientNotFoundException;
import com.medsync.patientservice.services.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;


@Tag(name = "Patients", description = "API for managing patients")
@RestController
@RequestMapping("/api/v1/patients")
public class PatientResource {

    private final PatientService patientService;

    public PatientResource(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Get all patients", description = "Retrieves a list of all patients")
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok().body(patientService.getPatients());
    }

    @Operation(summary = "Get a patient by ID", description = "Retrieves patient details by unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) 
            throws PatientNotFoundException {
        return ResponseEntity.ok().body(patientService.getPatientById(id));
    }

    @Operation(summary = "Create a new patient", description = "Registers a new patient with the provided details")
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody PatientRequestDTO patientRequestDTO)
            throws EmailAlreadyExistsException {
        return ResponseEntity.ok().body(
            patientService.createPatient(patientRequestDTO)
        );
    }

    @Operation(summary = "Update an existing patient", description = "Updates patient details based on the provided ID")
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> putMethodName(@PathVariable UUID id,
        @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO)
        throws PatientNotFoundException, EmailAlreadyExistsException {
        return ResponseEntity.ok().body(patientService.updatePatient(id, patientRequestDTO));
    }

    @Operation(summary = "Delete a patient", description = "Deletes a patient based on the provided ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
