package com.sqc.academy.bai4.controller;
import com.sqc.academy.bai4.*;
import com.sqc.academy.bai4.dto.ApiResponse;
import com.sqc.academy.bai4.dto.EmployeeSearchRequest;
import com.sqc.academy.bai4.exception.ApiException;
import com.sqc.academy.bai4.exception.ErrorCode;
import com.sqc.academy.bai4.exception.Gender;
import com.sqc.academy.bai4.model.Employee;
import com.sqc.academy.bai4.repository.IEmployeeRepository;
import com.sqc.academy.bai4.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    @Autowired
    private IEmployeeService employeeService; // Chỉ gọi Service, không gọi Repository

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {
        return ResponseEntity.ok(ApiResponse.<List<Employee>>builder()
                .data(employeeService.getAllEmployees())
                .build());
    }

    @GetMapping("/{id}")
    public Object getEmployeeById(@PathVariable String id) {
        // Service đã lo việc check lỗi, Controller chỉ cần trả về
        return JsonResponse.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public Object createEmployee(@RequestBody Employee employee) {
        return JsonResponse.ok(employeeService.createEmployee(employee));
    }

    @PutMapping("/{id}")
    public Object updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmp) {
        return JsonResponse.ok(employeeService.updateEmployee(id, updatedEmp));
    }

    @DeleteMapping("/{id}")
    public Object deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return JsonResponse.noContent();
    }

    @GetMapping("/search")
    public Object search(@ModelAttribute EmployeeSearchRequest request) {
        List<Employee> result = employeeService.search(request);
        return JsonResponse.ok(result);
    }
}
