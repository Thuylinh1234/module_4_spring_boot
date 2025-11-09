package com.sqc.academy;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Linh", 2.0),
                    new Student(2, "Vy", 3.0),
                    new Student(3, "Lợi", 4.0)
            ));

    // @RequestMapping(value = "/students", method = RequestMethod.GET)
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Integer id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    //@RequestMapping(value = "/students", method =  RequestMethod.POST)
    @PostMapping
    public List<Student> save(@RequestBody Student student) {
        student.setId((int) (Math.random() * 1000000) + 1);
        students.add(student);
        return students;
    }

}
