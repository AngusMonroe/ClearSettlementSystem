package com.altale.api.VAException;

public class AccountServiceException extends RuntimeException {
    public AccountServiceException() {
        super();
    }

    public AccountServiceException(String message) {
        super(message);
    }
}
