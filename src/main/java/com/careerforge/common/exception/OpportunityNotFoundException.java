package com.careerforge.common.exception;

public class OpportunityNotFoundException extends RuntimeException {

    public OpportunityNotFoundException() {
        super("Opportunity not found");
    }
}