package com.google.service.impl;

import com.google.dao.StudentDao;
import com.google.dao.impl.StudentDaoImpl;
import com.google.entity.Student;
import com.google.service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private final StudentDao dao = new StudentDaoImpl();
    @Override
    public void create(Student student) {
        dao.create(student);
    }

    @Override
    public void upgrade(long id, Student student) {
        Optional<Student> optional = dao.findById(id);
        if (optional.isPresent()) {
            student.setId(id);
            dao.upgrade(student);
        }
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public Student findById(long id) {
        if (dao.findById(id).isEmpty()) {
            throw new SecurityException();
        }
        return dao.findById(id).get();
    }

    @Override
    public List<Student> findAll() {
        return dao.findAll();
    }
}
