package com.google.dao.impl;

import com.google.dao.StudentDao;
import com.google.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/trial_schema";
    private final String username = "root";
    private final String password = "";

    private Connection connection;
    private Statement statement;
    private static final String CREATE_STUDENT = "insert into students value (default, ?, ?, ?);";
    private static final String UPDATE_STUDENT = "update students set first_name = ?, sur_name = ?, major = ? where id = ?;";
    private static final String DELETE_STUDENT = "delete from students where id = ?";
    private static final String FIND_STUDENT_BY_ID = "select * from students where id = ";
    private static final String FIND_ALL = "select * from students";

    public StudentDaoImpl() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll() {
        ArrayList<Student> students = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                students.add(getStudentByResultSet(resultSet));
            }
        } catch (Exception e) {
            return students;
        }
        return students;
    }

    @Override
    public Optional<Student> findById(long id) {
        try (ResultSet resultSet = statement.executeQuery(FIND_STUDENT_BY_ID + id)) {
            if (resultSet.next()) {
                return Optional.of(getStudentByResultSet(resultSet));
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private Student getStudentByResultSet(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String surname = resultSet.getString("sur_name");
        String majority = resultSet.getString("major");
        Long id = resultSet.getLong("id");
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(surname);
        student.setMajority(majority);
        student.setId(id);
        return student;
    }

    @Override
    public void deleteById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upgrade(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getMajority());
            preparedStatement.setLong(4, student.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STUDENT)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getMajority());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
