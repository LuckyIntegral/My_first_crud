package my_first_crud;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WorkersInterface workersInterface = new WorkersInterface();
        workersInterface.start();
    }
}
