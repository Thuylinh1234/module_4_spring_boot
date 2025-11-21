package com.sqc.academy.controller;

import com.sqc.academy.ApiRespone;
import com.sqc.academy.exception.ApiException;
import com.sqc.academy.exception.ErrorCode;
import com.sqc.academy.model.Student;
import com.sqc.academy.service.IStudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class StudentController {

    IStudentService studentService;


    // @RequestMapping(value = "/students", method = RequestMethod.GET)
    @GetMapping
//    public ResponseEntity<?>getStudents(){
//        return ResponseEntity.ok(ApiRespone.builder().data(students).build());
//    } // thích trả về cái chi thì trả

    public ResponseEntity<ApiRespone<List<Student>>>getStudents() {
        return ResponseEntity.ok(ApiRespone.<List<Student>>builder().data(studentService.findAll()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<Student>> getById(@PathVariable("id") Integer id) {
       Student student = studentService.findById(id);

       if (student == null) {
           throw new ApiException(ErrorCode.STUDENT_NOT_FOUND);
       }

       return ResponseEntity.ok(ApiRespone.<Student>builder()
               .data(student)
               .build());


       // return ResponseEntity.notFound().build();
//        return ResponseEntity.status(ErrorCode.STUDENT_NOT_FOUND.getStatus()).body(
//                ApiRespone.<Student>builder()
//                .code(ErrorCode.STUDENT_NOT_FOUND.getCode())
//                .message(ErrorCode.STUDENT_NOT_FOUND.getMessage())
//                .build());
    }
//
//    //@RequestMapping(value = "/students", method =  RequestMethod.POST)
    @PostMapping
    public ResponseEntity<ApiRespone<Student>> save(@RequestBody Student student) {
        student =  studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiRespone.<Student>builder()
                        .data(student)
                        .build());
    }

}
