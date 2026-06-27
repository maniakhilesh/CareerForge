package com.careerforge.common.exception;

public class DuplicateApplicationException extends RuntimeException {

    public DuplicateApplicationException() {
        super("You have already applied to this opportunity");
    }
}