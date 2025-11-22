package com.sqc.academy.bai4.dto;

import com.sqc.academy.bai4.exception.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder // thiết lập giá trị cho từng thuộc tính 1 cách dễ dàng
@NoArgsConstructor // ko tham số
@AllArgsConstructor // có tham có
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchRequest {
    String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dobFrom; // Tìm từ ngày

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dobTo;   // Tìm đến ngày

    Gender gender;
    String phone;
    Integer departmentId;
    String salaryRange; // Để String vì giá trị là "lt5", "5-10"...

}
