package test.mgts;

import test.mgts.model.Contact;
import test.mgts.service.StorageService;
import test.mgts.utils.CharsetDetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static test.mgts.Main.STATE_LOCATION;

public class Application {
    private final StorageService storageService;
    private static final String MESSAGE_ERROR = "Неверный формат ввода данных. Пример использования: ";

    public Application(StorageService service) {
        this.storageService = service;
    }

    public void work() throws IOException {

        String greetingMessage = String.format("Добрый день!%nДля добавление контакта используйте команду \"add {Имя} {Фамилия} {Номер пропуска}\"" +
                "%nДля просмотра контактов используйте команду \"show-all\"" +
                "%nДля просмотра только номеров контактов используйте команду \"show-all-only-number\"" +
                "%nДля поиска контакта по номера пропуска используйте команду \"search\"" +
                "%nДля удаления контакта по номеру пропуска используйте команду \"delete\"" +
                "%nДля выхода и сохранения изменений используйте команду \"exit\"%n" +
                "%nДля повторного отображения списка команд используйте \"help\"%n");

        System.out.printf(greetingMessage);

        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream, CharsetDetector.getCharsetConsole());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            String input = bufferedReader.readLine();
            if (input.equals("exit")) {
                OutputStreamWriter fileWriter = new OutputStreamWriter(Files.newOutputStream(Paths.get(STATE_LOCATION)), CharsetDetector.getCharsetFile());
                for(Contact contact: storageService.getAll()) {
                    fileWriter.write(String.format("%s %s %s" + System.lineSeparator(), contact.getFirstName(), contact.getSecondName(), contact.getPassNumber()));
                }
                //закрываем потоки и освобождаем ресурсы
                fileWriter.close();
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();

                System.exit(0);
            }
            if (input.equals("help")) {
                System.out.printf(greetingMessage);
                continue;
            }
            List<String> commands = Arrays.asList("add", "show-all", "show-all-only-number", "search", "delete", "help");
            String[] splitInput = input.split(" ");
            if (splitInput.length > 0 &&
                    !commands.contains(splitInput[0])) {
                System.out.println("Команда не распознана");
                continue;
            }
            String command = splitInput[0];

            // Обработка добавления контакта.
            if (command.equals("add") && splitInput.length == 4) {
                Contact contact = new Contact(splitInput[1], splitInput[2], splitInput[3]);
                storageService.save(contact);
            } else if (command.equals("add")) {
                System.out.printf(MESSAGE_ERROR + "\"add Михаил Сазонов 00F1C313\"%n");
            }

            // Обработка отображения контактов с отображением ФИО.
            if (command.equals("show-all") && splitInput.length == 1) {
                showContacts(true);
            } else if (command.equals("show-all")) {
                System.out.printf(MESSAGE_ERROR + "\"show-all\"%n");
            }

            // Обработка отображения контактов с отображением только номеров пропусков.
            if (command.equals("show-all-only-number") && splitInput.length == 1) {
                showContacts(false);
            } else if (command.equals("show-all-only-number")) {
                System.out.printf(MESSAGE_ERROR + "\"show-all-only-number\"%n");
            }

            // Обработка поиска контакта.
            if (command.equals("search") && splitInput.length == 2) {
                System.out.println(storageService.get(splitInput[1]));
            } else if (command.equals("search")) {
                System.out.printf(MESSAGE_ERROR + "\"search 00F1C313\"%n");
            }

            // Обработка удаления контакта.
            if (command.equals("delete") && splitInput.length == 2) {
                storageService.delete(splitInput[1]);
            } else if (command.equals("delete")) {
                System.out.printf(MESSAGE_ERROR + "\"delete 00F1C313\"%n");
            }
        }
    }

    /**
     * Метод для отображения контактов находящихся в хранилище.
     *
     * @param showWithFio Необходимо ли отображать Фамилию Имя Отчество или только номер пропуска.
     */
    private void showContacts(boolean showWithFio) {
        String emptyListMessage = "Список контактов пуст.";
        List<Contact> contacts = storageService.getAll();
        if (contacts.size() > 0) {
            if (showWithFio) {
                contacts.forEach(contact -> System.out.println(contact.toString()));
            } else {
                contacts.forEach(contact -> System.out.printf("%s%n", contact.getPassNumber()));
            }
        } else {
            System.out.println(emptyListMessage);
        }
    }
}
