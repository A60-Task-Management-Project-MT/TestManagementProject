package com.company.oop.test.menagement.exceptions;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException() {
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

}