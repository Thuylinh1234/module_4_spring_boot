package com.sqc.academy;

import com.sqc.academy.exception.ApiException;
import com.sqc.academy.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    Student.builder().id(1).name("Linh").score(2.0).build(),
                    Student.builder().id(2).name("Lợi").score(3.0).build()

            ));

    // @RequestMapping(value = "/students", method = RequestMethod.GET)
    @GetMapping
//    public ResponseEntity<?>getStudents(){
//        return ResponseEntity.ok(ApiRespone.builder().data(students).build());
//    } // thích trả về cái chi thì trả

    public ResponseEntity<ApiRespone<List<Student>>>getStudents() {
        return ResponseEntity.ok(ApiRespone.<List<Student>>builder().data(students).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<Student>> getById(@PathVariable("id") Integer id) {
        for (Student student : students) {
            if (student.getId() == id) {
                //return ResponseEntity.status(HttpStatus.OK).body(student);
                return ResponseEntity.ok(ApiRespone.<Student>builder()
                        .data(student)
                        .build());
            }
        }
        throw new ApiException(ErrorCode.STUDENT_NOT_FOUND);
       // return ResponseEntity.notFound().build();
//        return ResponseEntity.status(ErrorCode.STUDENT_NOT_FOUND.getStatus()).body(
//                ApiRespone.<Student>builder()
//                .code(ErrorCode.STUDENT_NOT_FOUND.getCode())
//                .message(ErrorCode.STUDENT_NOT_FOUND.getMessage())
//                .build());
    }

    //@RequestMapping(value = "/students", method =  RequestMethod.POST)
    @PostMapping
    public ResponseEntity<ApiRespone<Student>> save(@RequestBody Student student) {
        student.setId((int) (Math.random() * 1000000) + 1);
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiRespone.<Student>builder().data(student).build());
    }

}
