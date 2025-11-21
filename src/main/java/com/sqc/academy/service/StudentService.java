package com.sqc.academy.service;

import com.sqc.academy.model.Student;
import com.sqc.academy.repository.IStudentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor // tiêm thông qua Contructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService implements IStudentService {

    // C1: Tiêm vào thuộc tính
//    @Autowired
//    private IStudentRepository studentRepository ;

    // C2: Tiêm vào constructor
//    private IStudentService studentService;
//    public StudentRepository(IStudentRepository studentRepository){
//        this.studentRepository = studentRepository;
//    }

    //C3: Tiêm vào setter
     IStudentRepository studentRepository;
//    @Autowired
//    public StudentService(IStudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }




    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
