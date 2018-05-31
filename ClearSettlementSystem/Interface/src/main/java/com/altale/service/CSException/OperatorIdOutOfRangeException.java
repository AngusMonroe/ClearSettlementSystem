package com.altale.service.CSException;

@SuppressWarnings("serial")
public class OperatorIdOutOfRangeException extends RuntimeException{

    public OperatorIdOutOfRangeException() {

    }

    public OperatorIdOutOfRangeException(String message) {
        super(message);
    }
}

