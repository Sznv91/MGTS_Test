package test.mgts;

import test.mgts.service.StorageService;
import test.mgts.storage.ListStorage;
import test.mgts.storage.MapStorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.ObjectInputStream;


public class Main {
    public static final String STATE_LOCATION = ".\\saveState.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Application application;

        try {
            FileInputStream fileInputStream = new FileInputStream(STATE_LOCATION);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            application = (Application) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            StorageService service;
            if (args.length > 0 && args[0].equals("map")) {
                service = new StorageService(new MapStorage());
            } else service = new StorageService(new ListStorage());
            application = new Application(service);
        }

        /*StorageService service;
        if (args.length > 0 && args[0].equals("map")) {
            service = new StorageService(new MapStorage());
        } else service = new StorageService(new ListStorage());
        application = new Application(service);*/

        application.work();
    }

}
