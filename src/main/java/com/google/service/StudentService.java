package com.google.service;

import com.google.entity.Student;

import java.util.List;

public interface StudentService {
    void create(Student student);
    void upgrade(long id, Student student);
    void deleteById(long id);
    Student findById(long id);
    List<Student> findAll();
}
