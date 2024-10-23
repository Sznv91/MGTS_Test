package test.mgts.utils;

public class NotFoundStorageException extends StorageException {

    public NotFoundStorageException(String id) {
        super("Контакт " + id + " не найден", id);
    }

}
