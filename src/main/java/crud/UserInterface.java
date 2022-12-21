package crud;

import crud.factory_utills.Factory;
import crud.factory_utills.FactoryBase;
import crud.worker_utills.Worker;
import crud.worker_utills.WorkerBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {

    private static final String filePath = "Your_file_path.txt";
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose your option");
        String option;
        WorkerBase.load(filePath);
        options();
        while ((option = bufferedReader.readLine()) != null) {
            crud(bufferedReader, option);
        }
    }

    private void crud(BufferedReader reader, String option) throws IOException {
        switch (option) {
            case "1" -> createWorker(reader);
            case "2" -> findWorker(reader);
            case "3" -> findAllWorkers();
            case "4" -> upgradeWorker(reader);
            case "5" -> removeWorker(reader);
            case "6" -> createFactory(reader);
            case "7" -> findFactory(reader);
            case "8" -> findAllFactories();
            case "9" -> upgradeFactory(reader);
            case "10" -> removeFactory(reader);
            case "11" -> exit();
        }
        options();
    }

    private void createFactory(BufferedReader reader) throws IOException {
        Factory factory = new Factory();
        System.out.println("Please enter the name");
        factory.setName(reader.readLine().trim());
        System.out.println("Please enter the country of factory");
        factory.setCountry(reader.readLine().trim());
        System.out.println(Factory.FIELDS_NAMES);
        System.out.println(factory);
        FactoryBase.addFactory(factory);
    }

    private void findFactory(BufferedReader reader) throws IOException {
        System.out.println("Please enter the id of factory");
        Factory factory = FactoryBase.getFactory(reader.readLine());
        if (factory == null) {
            System.out.println("This id is incorrect");
        } else {
            System.out.println(Worker.FIELD_NAMES);
            System.out.println(factory);
        }
    }

    private void findAllFactories() {
        System.out.println(Factory.FIELDS_NAMES);
        for (Factory factory : FactoryBase.getValues()) {
            System.out.println(factory);
        }
    }

    private void upgradeFactory(BufferedReader reader) throws IOException {
        System.out.println("Please enter id of factory");
        Factory factory = FactoryBase.getFactory(reader.readLine().trim());
        if (factory == null) {
            System.out.println("This id doesn't exist");
            return;
        }
        System.out.println(factory);
        upgradeFactoryMenu();
        switch (reader.readLine()) {
            case "1" -> factory.setName(reader.readLine());
            case "2" -> factory.setCountry(reader.readLine());
        }
    }

    private void upgradeFactoryMenu() {
        System.out.println("For upgrade the name, please enter 1");
        System.out.println("For upgrade the country, please enter 2");
    }

    private void removeFactory(BufferedReader reader) throws IOException {
        System.out.println("Please enter id of factory");
        String id = reader.readLine();
        FactoryBase.removeFactory(id);
    }

    private void exit() throws IOException {
        WorkerBase.save(filePath);
        System.exit(0);
    }

    private void removeWorker(BufferedReader reader) throws IOException {
        System.out.println("Please enter worker's id");
        String id = reader.readLine();
        WorkerBase.deleteWorker(id);
    }

    private void upgradeWorker(BufferedReader reader) throws IOException {
        System.out.println("Please enter worker's id");
        String id = reader.readLine();
        Worker worker = WorkerBase.getWorker(id);
        if (worker == null) {
            System.out.println("This id doesn't exist");
            return;
        }
        upgradeWorkerMenu();
        String option = reader.readLine();
        switch (option) {
            case "1" -> worker.setName(reader.readLine());
            case "2" -> worker.setSurname(reader.readLine());
            case "3" -> worker.setPosition(reader.readLine());
            case "4" -> worker.setMobileNumber(reader.readLine());
        }
    }

    private void upgradeWorkerMenu() {
        System.out.println();
        System.out.println("If you want to upgrade the name, please enter 1");
        System.out.println("If you want to upgrade the surname, please enter 2");
        System.out.println("If you want to upgrade the position, please enter 3");
        System.out.println("If you want to upgrade the phone number, please enter 4");
    }

    private void options() {
        System.out.println();
        System.out.println("For create new worker, please enter 1");
        System.out.println("For find a worker, please enter 2");
        System.out.println("For find all workers, please enter 3");
        System.out.println("For upgrade the worker, please enter 4");
        System.out.println("For delete worker, please enter 5");
        System.out.println("For create new factory, please enter 6");
        System.out.println("For find the factory, please enter 7");
        System.out.println("For find all factory, please enter 8");
        System.out.println("For upgrade the factory, please enter 9");
        System.out.println("For delete factory, please enter 10");
        System.out.println("For save + exit the system, please enter 11");
        System.out.println();
    }

    private void findWorker(BufferedReader reader) throws IOException {
        System.out.println("Please, enter worker's id code");
        String code = reader.readLine();
        Worker worker = WorkerBase.getWorker(code);
        if (worker == null) {
            System.out.println("This id doesn't exist");
        } else {
            System.out.println(Worker.FIELD_NAMES);
            System.out.println(worker);
        }
    }

    private void findAllWorkers() {
        System.out.println("All workers:");
        System.out.println(Worker.FIELD_NAMES);
        for (Worker worker: WorkerBase.getWorkers()) {
            System.out.println(worker);
        }
    }

    private void createWorker(BufferedReader reader) throws IOException {
        Worker worker = new Worker();
        System.out.println("Please enter the name");
        worker.setName(reader.readLine());
        System.out.println("Please enter the surname");
        worker.setSurname(reader.readLine());
        System.out.println("Please enter the position");
        worker.setPosition(reader.readLine());
        System.out.println("Please enter the phone number");
        worker.setMobileNumber(reader.readLine());
        System.out.println("\nNew worker has been added:");
        System.out.println(Worker.FIELD_NAMES);
        System.out.println(worker);
        WorkerBase.addWorker(worker);
    }
}