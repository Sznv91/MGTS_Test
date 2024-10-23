package test.mgts.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.mgts.model.Contact;
import test.mgts.utils.ExistStorageException;
import test.mgts.utils.NotFoundStorageException;


abstract class AbstractStorageTest {
    private final Storage storage;
    private static final Contact CONTACT_1 = new Contact("Михаил", "Сазонов", "00F1C313");
    private static final Contact CONTACT_2 = new Contact("Иван", "Иванов", "00F1C314");
    private static final Contact CONTACT_3 = new Contact("Абрам", "Абрамов", "00F1C315");
    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    void save() {
        storage.save(CONTACT_1);
        int expectStorageSize = 1;
        int actualStorageSize = storage.getAll().size();
        Contact expectContact = new Contact(CONTACT_1);
        Contact actualContact = storage.get(CONTACT_1.getPassNumber());
        Assertions.assertEquals(expectStorageSize, actualStorageSize);
        Assertions.assertEquals(expectContact, actualContact);
        Assertions.assertEquals(expectContact.getFirstName(), actualContact.getFirstName());
        Assertions.assertEquals(expectContact.getSecondName(), actualContact.getSecondName());
        Assertions.assertEquals(expectContact.getPassNumber(), actualContact.getPassNumber());
    }


    @Test
    void saveDuplicate() {
        storage.save(CONTACT_1);
        Assertions.assertThrows(ExistStorageException.class, this::save);
    }

    @Test
    void get() {
        storage.save(CONTACT_1);
        Contact expectContact = new Contact(CONTACT_1);
        Contact actualContact = storage.get(CONTACT_1.getPassNumber());
        Assertions.assertEquals(expectContact, actualContact);
        Assertions.assertEquals(expectContact.getFirstName(), actualContact.getFirstName());
        Assertions.assertEquals(expectContact.getSecondName(), actualContact.getSecondName());
        Assertions.assertEquals(expectContact.getPassNumber(), actualContact.getPassNumber());
    }


    @Test
    void getNotExist() {
        Assertions.assertThrows(NotFoundStorageException.class, () -> storage.delete(CONTACT_1.getPassNumber()));
    }

    @Test
    void getAll() {
        storage.save(CONTACT_1);
        storage.save(CONTACT_2);
        storage.save(CONTACT_3);
        int expectStorageSize = 3;
        int actualStorageSize = storage.getAll().size();
        Contact actualContact_1 = new Contact(storage.get(CONTACT_1.getPassNumber()));
        Contact actualContact_2 = new Contact(storage.get(CONTACT_2.getPassNumber()));
        Contact actualContact_3 = new Contact(storage.get(CONTACT_3.getPassNumber()));
        Assertions.assertEquals(expectStorageSize, actualStorageSize);
        Assertions.assertEquals(CONTACT_1, actualContact_1);
        Assertions.assertEquals(CONTACT_2, actualContact_2);
        Assertions.assertEquals(CONTACT_3, actualContact_3);
    }

    @Test
    void getAllWhenZeroStorageSize() {
        int expectStorageSize = 0;
        int actualStorageSize = storage.getAll().size();
        Assertions.assertEquals(expectStorageSize, actualStorageSize);
    }

    @Test
    void delete() {
        storage.save(CONTACT_1);
        int expectStorageSize = 1;
        int actualStorageSize = storage.getAll().size();
        Assertions.assertEquals(expectStorageSize, actualStorageSize);

        storage.delete(CONTACT_1.getPassNumber());

        expectStorageSize = 0;
        actualStorageSize = storage.getAll().size();
        Assertions.assertEquals(expectStorageSize, actualStorageSize);
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotFoundStorageException.class, () -> storage.delete(CONTACT_1.getPassNumber()));
    }
}