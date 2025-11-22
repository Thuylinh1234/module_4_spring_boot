package com.sqc.academy.bai4.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sqc.academy.bai4.JsonResponse;
import com.sqc.academy.bai4.dto.ApiResponse;
import com.sqc.academy.bai4.exception.ApiException;
import com.sqc.academy.bai4.exception.ErrorCode;
import com.sqc.academy.bai4.exception.Gender;
import com.sqc.academy.bai4.model.Employee;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class EmployeeRepository implements IEmployeeRepository {
    private static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(UUID.randomUUID().toString(), "Nguyễn Văn A", LocalDate.of(1995, 5, 12), Gender.MALE, 1000.0, "0901234567", 1));
        employees.add(new Employee(UUID.randomUUID().toString(), "Trần Thị B", LocalDate.of(1998, 8, 22), Gender.FEMALE, 1200.0, "0907654321", 2));
        employees.add(new Employee(UUID.randomUUID().toString(), "Lê Văn C", LocalDate.of(1992, 3, 5), Gender.MALE, 1500.0, "0901122334",1));
        employees.add(new Employee(UUID.randomUUID().toString(), "Phạm Thị D", LocalDate.of(2000, 7, 19), Gender.FEMALE, 1100.0, "0909988776",1));
        employees.add(new Employee(UUID.randomUUID().toString(), "Ngô Văn E", LocalDate.of(1997, 11, 30), Gender.MALE, 1300.0, "0905566778",2));
        employees.add(new Employee(UUID.randomUUID().toString(), "Đặng Thị F", LocalDate.of(1999, 2, 14), Gender.FEMALE, 1400.0, "0903344556",1));
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

    @GetMapping("/search")
    public Object search(
            // Tìm theo tên
            @RequestParam(required = false) String name,

            // Tìm theo ngày sinh
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,

            // Lọc theo giới tính
            @RequestParam(required = false) Gender gender,

            // Lọc theo lương
            @RequestParam(required = false) String salaryRange,

            // Tìm theo số điện thoại
            @RequestParam(required = false) String phone,

            // Lọc theo bộ phận
            @RequestParam(required = false) Integer departmentId
    ) {
        List<Employee> result = new ArrayList<>();

        for (Employee employee: employees) {

            //Lọc theo tên
            if (name != null && !name.isEmpty()) {
                String nameLower = name.toLowerCase();
                if (!employee.getName().toLowerCase().contains(nameLower)) {
                    continue;
                }
            }

            //Lọc theo ngày sinh từ
            if (dobFrom != null) {
                if (employee.getDob().isBefore(dobFrom)) {
                    continue;
                }
            }

            //Lọc theo ngày sinh đến
            if (dobTo != null) {
                if (employee.getDob().isAfter(dobTo)) {
                    continue;
                }
            }

            //Lọc theo giới tính
            if (gender != null) {
                if (employee.getGender() != gender) {
                    continue;
                }
            }

            //Lọc theo số điện thoại
            if (phone != null && !phone.isEmpty()) {
                if (!employee.getPhone().contains(phone)) {
                    continue;
                }
            }

            //Lọc theo phòng ban
            if (departmentId != null) {
                if (!employee.getDepartmentId().equals(departmentId)) {
                    continue;
                }
            }

            // Lọc theo lương
            if (salaryRange != null && !salaryRange.isEmpty()) {
                if (!filterSalary(employee.getSalary(), salaryRange)) {
                    continue;
                }
            }

            result.add(employee);
        }

        return JsonResponse.ok(result);
    }

    // kiểm tra
    private boolean filterSalary(double salary, String range) {
        if (range == null) {
            return true; // Không lọc lương
        }

        switch (range) {
            case "lt5":
                return salary < 5;

            case "5-10":
                return salary >= 5 && salary <= 10;

            case "10-20":
                return salary > 10 && salary <= 20;

            case "gt20":
                return salary > 20;

            default:
                return true;
        }
    }

}
