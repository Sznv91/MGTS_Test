package test.mgts.utils;

public class ValidationFormatException extends ValidationException {
    public ValidationFormatException(int position, String passNumber) {
        super("Неверный формат ввода номера пропуска ошибка в " + position + " символе", passNumber);
    }
}
