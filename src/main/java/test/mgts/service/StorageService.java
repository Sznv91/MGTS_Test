package test.mgts.service;

import test.mgts.model.Contact;
import test.mgts.storage.Storage;
import test.mgts.utils.ExistStorageException;
import test.mgts.utils.NotFoundStorageException;
import test.mgts.utils.ValidationException;

import java.io.Serializable;
import java.util.List;

import static test.mgts.utils.Validator.*;

public class StorageService implements Storage {
    Storage storage;

    public StorageService(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void save(Contact contact) {
        try {
            validatePassNumber(contact.getPassNumber());
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            storage.save(contact);
        } catch (ExistStorageException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.printf("Выполнено добавление контакта%n");
    }

    @Override
    public Contact get(String passNumber) {
        try {
            validatePassNumber(passNumber);
        } catch (NotFoundStorageException | ValidationException e) {
            System.out.println(e.getMessage());
        }
        return storage.get(passNumber);
    }

    @Override
    public List<Contact> getAll() {
        return storage.getAll();
    }

    @Override
    public void delete(String passNumber) {
        try {
            validatePassNumber(passNumber);
            storage.delete(passNumber);
            System.out.printf("Контакт '%s' успешно удалён%n", passNumber);
        } catch (ValidationException | NotFoundStorageException e) {
            System.out.println(e.getMessage());
        }
    }
}
