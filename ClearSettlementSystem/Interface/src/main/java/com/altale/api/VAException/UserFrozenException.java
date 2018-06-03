package com.altale.api.VAException;

public class UserFrozenException extends AccountServiceException {
    public UserFrozenException() {
        super();
    }

    public UserFrozenException(String message) {
        super(message);
    }
}
