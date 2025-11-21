package com.sqc.academy.repository;

import com.sqc.academy.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {
    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    Student.builder().id(1).name("Linh").score(2.0).build(),
                    Student.builder().id(2).name("Lợi").score(3.0).build()

            )
    );

    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();

        try (PreparedStatement preparedStatement = BaseRepository
                .getConnection()
                .prepareStatement("select id, name, score from student");
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {


            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .score(resultSet.getDouble("score"))
                        .build();
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students; // lấy ra tất cả
    }

    public Student findById(Integer id) {

        try (PreparedStatement preparedStatement = BaseRepository
                .getConnection()
                .prepareStatement("select id, name, score from student where  id = ?;")

        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .score(resultSet.getDouble("score"))
                        .build();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Student save(Student student) {
        try (PreparedStatement preparedStatement = BaseRepository
                .getConnection()
                .prepareStatement("insert into student (name, score) values (?, ?);")

        ) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setDouble(2, student.getScore());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return student;
        }
}

