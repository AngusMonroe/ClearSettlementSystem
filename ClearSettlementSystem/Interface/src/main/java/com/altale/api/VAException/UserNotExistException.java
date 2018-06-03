package com.altale.api.VAException;

public class UserNotExistException extends AccountServiceException {
    public UserNotExistException() {
        super();
    }

    public UserNotExistException(String message) {
        super(message);
    }
}
