package com.sqc.academy.bai4.model;

import com.sqc.academy.bai4.exception.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder // thiết lập giá trị cho từng thuộc tính 1 cách dễ dàng
@NoArgsConstructor // ko tham số
@AllArgsConstructor // có tham có
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    private String id;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private double salary;
    private String phone;
    Integer departmentId;



}
