package com.sqc.academy.bai4.repository;

import com.sqc.academy.bai4.dto.ApiResponse;
import com.sqc.academy.bai4.dto.EmployeeSearchRequest;
import com.sqc.academy.bai4.exception.ApiException;
import com.sqc.academy.bai4.exception.ErrorCode;
import com.sqc.academy.bai4.exception.Gender;
import com.sqc.academy.bai4.model.Employee;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class EmployeeRepository implements IEmployeeRepository {

    private static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(UUID.randomUUID().toString(), "Nguyễn Văn A", LocalDate.of(1995, 5, 12), Gender.MALE, 1000.0, "0901234567", 1));
        employees.add(new Employee(UUID.randomUUID().toString(), "Trần Thị B", LocalDate.of(1998, 8, 22), Gender.FEMALE, 1200.0, "0907654321", 2));
        employees.add(new Employee(UUID.randomUUID().toString(), "Lê Văn C", LocalDate.of(1992, 3, 5), Gender.MALE, 1500.0, "0901122334", 1));
        employees.add(new Employee(UUID.randomUUID().toString(), "Phạm Thị D", LocalDate.of(2000, 7, 19), Gender.FEMALE, 1100.0, "0909988776", 1));
        employees.add(new Employee(UUID.randomUUID().toString(), "Ngô Văn E", LocalDate.of(1997, 11, 30), Gender.MALE, 1300.0, "0905566778", 2));
        employees.add(new Employee(UUID.randomUUID().toString(), "Đặng Thị F", LocalDate.of(1999, 2, 14), Gender.FEMALE, 1400.0, "0903344556", 1));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {
        return ResponseEntity.ok(ApiResponse.<List<Employee>>builder().data(employees).build());
    }


    @GetMapping("/{id}")


//    @PutMapping("/{id}")
//    public Object updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmp) {
//        Employee empToUpdate = null; // biến tạm
//
//        for (Employee e : employees) {
//            if (e.getId().equals(id)) {
//                empToUpdate = e;
//                break;
//            }
//        }
//
//        if (empToUpdate == null) { // Kiểm tra nhân viên
//            throw new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
//        }
//        // cập nhật
//        empToUpdate.setName(updatedEmp.getName());
//        empToUpdate.setDob(updatedEmp.getDob());
//        empToUpdate.setGender(updatedEmp.getGender());
//        empToUpdate.setSalary(updatedEmp.getSalary());
//        empToUpdate.setPhone(updatedEmp.getPhone());
//
//        return JsonResponse.ok(empToUpdate); // Trả về response
//    }

//    @DeleteMapping("/{id}")
//    public Object deleteEmployee(@PathVariable String id) {
//        Employee empToDelete = null;
//
//        for (Employee e : employees) {
//            if (e.getId().equals(id)) {
//                empToDelete = e;
//                break;
//            }
//        }
//
//        if (empToDelete == null) {
//            throw new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
//        }
//
//        employees.remove(empToDelete);
//        return JsonResponse.noContent();
//    }


    @Override
    public List<Employee> findAll() {
        return employees;
    }


    @Override
    public Employee findById(String id) {
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

        return found;
    }


    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public boolean delete(String id) {
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
        return true;
    }


    @Override
    public List<Employee> search(EmployeeSearchRequest request) {
        List<Employee> result = new ArrayList<>();

        for (Employee employee : employees) {

            //Lọc theo tên
            if (request.getName() != null && !request.getName().isEmpty()) {
                String nameLower = request.getName().toLowerCase();
                if (!employee.getName().toLowerCase().contains(nameLower)) {
                    continue;
                }
            }

            //Lọc theo ngày sinh từ
            if (request.getDobFrom() != null) {
                if (employee.getDob().isBefore(request.getDobFrom())) {
                    continue;
                }
            }

            //Lọc theo ngày sinh đến
            if (request.getDobTo() != null) {
                if (employee.getDob().isAfter(request.getDobTo())) {
                    continue;
                }
            }

            //Lọc theo giới tính
            if (request.getGender() != null) {
                if (employee.getGender() != request.getGender()) {
                    continue;
                }
            }

            //Lọc theo số điện thoại
            if (request.getPhone() != null && !request.getPhone().isEmpty()) {
                if (!employee.getPhone().contains(request.getPhone())) {
                    continue;
                }
            }

            //Lọc theo phòng ban
            if (request.getDepartmentId() != null) {
                if (!employee.getDepartmentId().equals(request.getDepartmentId())) {
                    continue;
                }
            }

            // Lọc theo lương
            if (request.getSalaryRange() != null && !request.getSalaryRange().isEmpty()) {
                if (!filterSalary(employee.getSalary(), request.getSalaryRange())) {
                    continue;
                }
            }

            result.add(employee);
        }

        return result;
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
