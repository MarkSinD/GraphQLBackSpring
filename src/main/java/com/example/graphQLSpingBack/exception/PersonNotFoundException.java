package com.example.graphQLSpingBack.exception;

import java.text.MessageFormat;

public class PersonNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4624121128954970095L;
    private final int id;

    public PersonNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format("Person with ID ''{0}'' isn''t available", id);
    }
}