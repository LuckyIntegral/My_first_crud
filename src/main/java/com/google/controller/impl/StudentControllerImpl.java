package com.google.controller.impl;

import com.google.controller.StudentController;
import com.google.entity.Student;
import com.google.service.StudentService;
import com.google.service.impl.StudentServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StudentControllerImpl implements StudentController {
    private final StudentService service = new StudentServiceImpl();
    @Override
    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Select your option");
            String option;
            menu();
            while ((option = reader.readLine()) != null) {
                crud(reader, option);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong, please try again");
        }
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> create(reader);
            case "2" -> findById(reader);
            case "3" -> findAll();
            case "4" -> update(reader);
            case "5" -> delete(reader);
            case "6" -> stop();
        }
        menu();
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want to create a student, please enter 1");
        System.out.println("If you want to find the student, please enter 2");
        System.out.println("If you want to find all students, please enter 3");
        System.out.println("If you want to update the student, please enter 4");
        System.out.println("If you want to delete the student, please enter 5");
        System.out.println("If you want to close application, please enter 6");
        System.out.println();
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Update Student");
        System.out.println("Please enter id");
        String id = reader.readLine();
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        System.out.println("Please enter major");
        String major = reader.readLine();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMajority(major);
        service.upgrade(Long.parseLong(id), student);
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Create Student");
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        System.out.println("Please enter the major");
        String major = reader.readLine();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMajority(major);
        service.create(student);
    }

    private void findById(BufferedReader reader) throws IOException {
        System.out.println("Find Student by id");
        String id = reader.readLine();
        Student student = service.findById(Long.parseLong(id));
        System.out.println(student);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Delete Student by id");
        String id = reader.readLine();
        service.deleteById(Long.parseLong(id));
    }

    private void findAll() {
        System.out.println("Find all Students");
        List<Student> students = service.findAll();
        students.forEach(System.out::println);
    }

    private void stop() {
        System.exit(0);
    }
}
