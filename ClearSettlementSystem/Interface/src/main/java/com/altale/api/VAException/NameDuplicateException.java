package com.altale.api.VAException;

public class NameDuplicateException extends RegistryException {
    public NameDuplicateException() {
        super();
    }

    public NameDuplicateException(String message) {
        super(message);
    }
}
