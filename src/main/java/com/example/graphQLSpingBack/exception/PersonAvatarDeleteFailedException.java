package com.example.graphQLSpingBack.exception;

public class PersonAvatarDeleteFailedException extends RuntimeException {
    private static final long serialVersionUID = -2609753792931437565L;

    public PersonAvatarDeleteFailedException(Throwable cause) {
        super(cause);
    }
}