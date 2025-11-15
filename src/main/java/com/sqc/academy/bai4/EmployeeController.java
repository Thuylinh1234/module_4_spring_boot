package com.sqc.academy.bai4;
import com.sqc.academy.bai4.ApiException;
import com.sqc.academy.bai4.ErrorCode;
import com.sqc.academy.bai4.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Hãy xây dựng API CRUD nhân viên với các thông tin cơ bản sau:
 *
 * name
 * dob (ngày sinh)
 * gender (MALE / FEMALE / OTHER)
 * salary
 * phone
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(UUID.randomUUID().toString(), "Nguyễn Văn A", LocalDate.of(1995, 5, 12), Gender.MALE, 1000.0, "0901234567"));
        employees.add(new Employee(UUID.randomUUID().toString(), "Trần Thị B", LocalDate.of(1998, 8, 22), Gender.FEMALE, 1200.0, "0907654321"));
        employees.add(new Employee(UUID.randomUUID().toString(), "Lê Văn C", LocalDate.of(1992, 3, 5), Gender.MALE, 1500.0, "0901122334"));
        employees.add(new Employee(UUID.randomUUID().toString(), "Phạm Thị D", LocalDate.of(2000, 7, 19), Gender.FEMALE, 1100.0, "0909988776"));
        employees.add(new Employee(UUID.randomUUID().toString(), "Ngô Văn E", LocalDate.of(1997, 11, 30), Gender.MALE, 1300.0, "0905566778"));
        employees.add(new Employee(UUID.randomUUID().toString(), "Đặng Thị F", LocalDate.of(1999, 2, 14), Gender.FEMALE, 1400.0, "0903344556"));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {
        return ResponseEntity.ok(ApiResponse.<List<Employee>>builder().data(employees).build());
    }


    @GetMapping("/{id}")
    public Object getEmployeeById(@PathVariable String id) {
        Employee found = null;
        for (Employee e : employees) {
            if (e.getId().equals(id)) {   // so sánh ID
                found = e;
                break;
            }
        }

        if (found == null) {
            throw new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }

        return JsonResponse.ok(found); // Trả về response
    }


    @PutMapping("/{id}")
    public Object updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmp) {
        Employee empToUpdate = null; // biến tạm

        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                empToUpdate = e;
                break;
            }
        }

        if (empToUpdate == null) { // Kiểm tra nhân viên
            throw new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }
        // cập nhật
        empToUpdate.setName(updatedEmp.getName());
        empToUpdate.setDob(updatedEmp.getDob());
        empToUpdate.setGender(updatedEmp.getGender());
        empToUpdate.setSalary(updatedEmp.getSalary());
        empToUpdate.setPhone(updatedEmp.getPhone());

        return JsonResponse.ok(empToUpdate); // Trả về response
    }

    @DeleteMapping("/{id}")
    public Object deleteEmployee(@PathVariable String id) {
        Employee empToDelete = null;

        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                empToDelete = e;
                break;
            }
        }

        if (empToDelete == null) {
            throw new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }

        employees.remove(empToDelete);
        return JsonResponse.noContent();
    }
}