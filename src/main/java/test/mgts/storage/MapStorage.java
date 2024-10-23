package test.mgts.storage;

import test.mgts.model.Contact;
import test.mgts.utils.ExistStorageException;
import test.mgts.utils.NotFoundStorageException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<String, Contact> storage = new HashMap<>();

    @Override
    public void save(Contact contact) {
        Contact tmp = storage.get(contact.getPassNumber());
        if (tmp != null) {
            throw new ExistStorageException(contact.getPassNumber());
        } else storage.put(contact.getPassNumber(), contact);
    }

    @Override
    public Contact get(String passNumber) {
        Contact tmp = storage.get(passNumber);
        if (tmp == null){
            throw new NotFoundStorageException(passNumber);
        }
        return tmp;
    }

    @Override
    public List<Contact> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String passNumber) {
        Contact tmp = storage.get(passNumber);
        if (tmp == null){
            throw new NotFoundStorageException(passNumber);
        } else storage.remove(passNumber);
    }
}
