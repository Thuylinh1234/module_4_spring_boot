package com.sqc.academy.service;

import com.sqc.academy.model.Student;

import java.util.List;

public interface IStudentService {
     List<Student> findAll();

     Student findById(Integer id);

     Student save(Student student);
}
