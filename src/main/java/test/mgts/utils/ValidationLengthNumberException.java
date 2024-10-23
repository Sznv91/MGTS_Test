package test.mgts.utils;

public class ValidationLengthNumberException extends ValidationException {

    public ValidationLengthNumberException(String passNumber) {
        super("Длина номера пропуска не соответствует 8 ", passNumber);
    }
}
