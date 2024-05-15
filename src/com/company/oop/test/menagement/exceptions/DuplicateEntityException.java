package com.company.oop.test.menagement.exceptions;

public class DuplicateEntityException extends RuntimeException{

    public DuplicateEntityException(String message) {
        super(message);
    }
}
