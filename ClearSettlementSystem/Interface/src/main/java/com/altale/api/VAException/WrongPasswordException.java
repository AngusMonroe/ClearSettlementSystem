package com.altale.api.VAException;

public class WrongPasswordException extends AccountServiceException {
    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
