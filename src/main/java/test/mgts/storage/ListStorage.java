package test.mgts.storage;

import test.mgts.model.Contact;
import test.mgts.utils.ExistStorageException;
import test.mgts.utils.NotFoundStorageException;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<String> {
    private final List<Contact> storage = new ArrayList<>();

    @Override
    public void save(Contact contact) {
        storage.forEach(element -> {
            if (element.getPassNumber().equals(contact.getPassNumber())) {
                throw new ExistStorageException(contact.getPassNumber());
            }
        });
        storage.add(contact);
    }

    @Override
    public Contact get(String passNumber) {
        for (Contact contact : storage) {
            if (contact.getPassNumber().equals(passNumber)) {
                return contact;
            }
        }
        throw new NotFoundStorageException(passNumber);
    }

    @Override
    public List<Contact> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public void delete(String passNumber) {
        if (storage.size() > 0){
            for (int i = 0; i < passNumber.length(); i++){
                if (storage.get(i).getPassNumber().equals(passNumber)){
                    storage.remove(i);
                    return;
                }
            }
        }
        throw new NotFoundStorageException(passNumber);
    }
}
