package com.blazejherzog.jobcandidatemanager.shared.infrastructure.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
