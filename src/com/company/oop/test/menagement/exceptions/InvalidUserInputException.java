package com.company.oop.test.menagement.exceptions;

public class InvalidUserInputException extends RuntimeException {

    public InvalidUserInputException() {
    }

    public InvalidUserInputException(String message) {
        super(message);
    }

}
