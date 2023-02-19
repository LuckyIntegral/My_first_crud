package com.google.dao;

import com.google.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    void create(Student student);
    void upgrade(Student student);
    void deleteById(long id);
    Optional<Student> findById(long id);
    List<Student> findAll();
}
