package my_first_crud.worker_utills;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Worker {
    private String name;
    private String surname;
    private Position position;
    private String mobileNumber;
    private String id;

    private static final String defaultValue = "Unspecified";
    private static final Set<String> passwords = new HashSet<>();
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != Worker.class) return false;
        Worker worker = (Worker) obj;
        return this.id.equals(worker.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, position, mobileNumber, id);
    }

    public static Worker parseString(String line) {
        Worker worker = new Worker();
        worker.name = line.substring(0, 12).trim();
        worker.surname = line.substring(12, 24).trim();
        worker.mobileNumber = line.substring(24, 42).trim();
        worker.setPosition(line.substring(42, 51).trim());
        worker.id = line.substring(51, 59);
        return worker;
    }

    @Override
    public String toString() {
        return name + " ".repeat(12 - name.length()) +
                surname + " ".repeat(12 - surname.length()) +
                mobileNumber + " ".repeat(18 - mobileNumber.length()) +
                position + " ".repeat(9 - position.toString().length()) + this.getId();
    }

    public void setName(String name) {
        if (name.length() > 12) {
            System.out.println("This name is too long");
            this.name = defaultValue;
        } else if (!name.matches("[A-zА-яІіЇїҐґЄє]+")) {
            System.out.println("This name is incorrect");
            this.name = defaultValue;
        } else {
            this.name = name;
        }
    }

    public void setSurname(String surname) {
        if (surname.length() > 12) {
            System.out.println("This surname is too long");
            this.surname = defaultValue;
        } else if (!surname.matches("[A-zА-яІіЇїҐґЄє]+")) {
            System.out.println("This surname is incorrect");
            this.surname = defaultValue;
        } else {
            this.surname = surname;
        }
    }

    public void setPosition(String position) {
        switch (position.toLowerCase()) {
            case "trainee" -> setPosition(Position.TRAINEE);
            case "junior" -> setPosition(Position.JUNIOR);
            case "middle" -> setPosition(Position.MIDDLE);
            case "senior" -> setPosition(Position.SENIOR);
            default -> setPosition(Position.NS);
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setMobileNumber(String mobileNumber) {
        if (!mobileNumber.matches("[!\\d-+ ]+")) {
            System.out.println("This number is incorrect");
            this.mobileNumber = defaultValue;
        } else if (mobileNumber.length() > 18) {
            System.out.println("This number is too long");
            this.mobileNumber = defaultValue;
        } else {
            this.mobileNumber = mobileNumber.trim();
        }
    }

    public String getId() {
        if (this.id == null) {
            String newId = UUID.randomUUID().toString().substring(0, 8);
            while (passwords.contains(newId)) {
                newId = UUID.randomUUID().toString().substring(0, 8);
            }
            this.id = newId;
            passwords.add(this.id);
        }
        return id;
    }
}