package com.sqc.academy.bai4;

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


    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employees;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Không tìm thấy nhân viên với ID: " + id));
    }


    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee newEmp) {
        newEmp.setId(UUID.randomUUID().toString());
        employees.add(newEmp);
        return ResponseEntity.ok("Thêm nhân viên thành công!");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmp) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                e.setName(updatedEmp.getName());
                e.setDob(updatedEmp.getDob());
                e.setGender(updatedEmp.getGender());
                e.setSalary(updatedEmp.getSalary());
                e.setPhone(updatedEmp.getPhone());
                return ResponseEntity.ok("Cập nhật nhân viên thành công!");
            }
        }
        return ResponseEntity.status(404).body("Không tìm thấy nhân viên với ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        boolean removed = employees.removeIf(e -> e.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok("Xóa nhân viên thành công!");
        } else {
            return ResponseEntity.status(404).body("Không tìm thấy nhân viên với ID: " + id);
        }
    }
}