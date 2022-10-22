package com.example.library.Exeption;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
