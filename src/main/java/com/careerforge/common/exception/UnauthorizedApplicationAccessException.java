package com.careerforge.common.exception;

public class UnauthorizedApplicationAccessException extends RuntimeException {

    public UnauthorizedApplicationAccessException() {
        super("You are not allowed to modify this application.");
    }
}