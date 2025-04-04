package com.medsync.patientservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.medsync.patientservice.models.EventType;
import com.medsync.patientservice.models.Patient;

import patient.events.PatientEvent;

@Component
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient, EventType eventType) {
        PatientEvent patientEvent = PatientEvent.newBuilder().setPatientId(patient.getId().toString())
                .setName(patient.getName()).setEmail(patient.getEmail()).setEventType(eventType.toString()).build();

        try {
            kafkaTemplate.send("patient", patientEvent.toByteArray());
        } catch (Exception ex) {
            log.error("Error while producing event to topic patient {}", eventType.toString());
        }
    }

}
