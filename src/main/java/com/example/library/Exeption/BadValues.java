package com.example.library.Exeption;

public class BadValues extends RuntimeException{
    public BadValues(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
