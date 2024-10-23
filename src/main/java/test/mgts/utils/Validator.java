package test.mgts.utils;

public class Validator {
    /**
     * Метод проверки правильности ввода номера пропуска
     *
     * @param passNumber Строка которую необходимо проверить
     */
    public static void validatePassNumber(String passNumber) {
        if (passNumber.length() != 8) {
            throw new ValidationLengthNumberException(passNumber);
        }
        String[] array = passNumber.split("[0-9A-F]");
        if (array.length != 0) {
            throw new ValidationFormatException(array.length, passNumber);
        }
    }
}
