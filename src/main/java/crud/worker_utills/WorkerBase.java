package crud.worker_utills;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerBase {
    private static final ArrayList<Worker> workers = new ArrayList<>();
    private WorkerBase() {}

    public static void save(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Worker worker : workers) {
            writer.write(worker.toString() + "\n");
        }
        writer.close();
    }

    public static void load(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while (reader.ready()) {
            workers.add(Worker.parseString(reader.readLine()));
        }
        reader.close();
    }

    public static void deleteWorker(String id) {
        workers.removeIf(e -> e.getId().equals(id));
    }

    public static void addWorker(Worker worker) {
        workers.add(worker);
    }

    public static ArrayList<Worker> getWorkers() {
        return workers;
    }

    public static Worker getWorker(String id) {
        List<Worker> el = workers.stream().filter(e -> e.getId().equals(id)).toList();
        return el.size() == 1 ? el.get(0) : null;
    }
}
