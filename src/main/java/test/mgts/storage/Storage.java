package test.mgts.storage;

import test.mgts.model.Contact;

import java.util.List;

public interface Storage {
    void save(Contact contact);
    Contact get(String passNumber);
    List<Contact> getAll();
    void delete(String passNumber);
}
