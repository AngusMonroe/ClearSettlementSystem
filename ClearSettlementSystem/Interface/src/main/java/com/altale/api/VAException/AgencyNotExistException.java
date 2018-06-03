package com.altale.api.VAException;

public class AgencyNotExistException extends RegistryException {
    public AgencyNotExistException() {
        super();
    }

    public AgencyNotExistException(String message) {
        super(message);
    }
}
