package test.mgts.utils;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String id) {
        super("Контакт " + id + " уже существует", id);
    }

}
