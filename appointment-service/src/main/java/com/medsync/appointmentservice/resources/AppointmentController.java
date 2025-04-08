package com.medsync.appointmentservice.resources;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medsync.appointmentservice.models.Appointment;
import com.medsync.appointmentservice.services.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointment API", description = "Operations related to appointment booking, retrieval, and deletion")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Create a new appointment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Appointment successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment created = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get an appointment by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the appointment"),
        @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable UUID id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all appointments")
    @ApiResponse(responseCode = "200", description = "List of all appointments")
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @Operation(summary = "Delete an appointment by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Appointment deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable UUID id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update an existing appointment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Appointment updated successfully"),
        @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable UUID id, @RequestBody Appointment appointment) {
        Appointment updated = appointmentService.updateAppointment(id, appointment);
        return ResponseEntity.ok(updated);
    }
}
