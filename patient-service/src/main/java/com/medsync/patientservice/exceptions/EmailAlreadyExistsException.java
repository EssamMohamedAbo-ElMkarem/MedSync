package com.medsync.patientservice.exceptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String message){ super(message); }
}
