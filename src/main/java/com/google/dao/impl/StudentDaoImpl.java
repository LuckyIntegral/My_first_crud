package com.google.dao.impl;

import com.google.dao.StudentDao;
import com.google.entity.Student;

import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {
    @Override
    public void create(Student student) {

    }

    @Override
    public void upgrade(Student student) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Optional<Student> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}
