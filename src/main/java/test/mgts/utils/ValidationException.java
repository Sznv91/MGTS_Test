package test.mgts.utils;

import lombok.Getter;

public class ValidationException extends RuntimeException {
    @Getter
    private final String passNumber;


    public ValidationException(String message, String passNumber) {
        super(message);
        this.passNumber = passNumber;
    }
}
