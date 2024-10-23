package test.mgts.utils;

public class StorageException extends RuntimeException {

    private final String id;

    public StorageException(String message, String uuid) {
        super(message);
        this.id = uuid;
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.id = uuid;
    }

    public String getId() {
        return id;
    }
}
