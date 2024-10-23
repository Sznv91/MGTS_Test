package test.mgts;

import test.mgts.model.Contact;
import test.mgts.service.StorageService;
import test.mgts.storage.ListStorage;
import test.mgts.storage.MapStorage;
import test.mgts.utils.CharsetDetector;

import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Main {
    public static final String STATE_LOCATION = ".\\saveState.txt";

    public static void main(String[] args) throws IOException {
        Application application;
        StorageService service;
        if (args.length > 0 && args[0].equals("map")) {
            service = new StorageService(new MapStorage());
        } else service = new StorageService(new ListStorage());

        try{
            Path path = Paths.get(STATE_LOCATION);
            List<String> allLines = Files.readAllLines(path, Charset.forName(CharsetDetector.getCharsetFile()));
            allLines.forEach(line -> {
                String[] tmp = line.split(" ");
                Contact contact = new Contact(tmp[0], tmp[1], tmp[2]);
                service.save(contact);
            });
        } catch (NoSuchFileException e){
            //Не является ошибкой. Если файл не найден, то при завершении работы приложения - будет создан новый.
        }

        application = new Application(service);

        application.work();
    }

}
