package com.sqc.academy.bai4.repository;

import com.sqc.academy.bai4.dto.EmployeeSearchRequest;
import com.sqc.academy.bai4.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmployeeRepository {
    List<Employee> findAll();
    Employee findById(String id);
    Employee save(Employee employee);
    boolean delete(String id);
    List<Employee> search(EmployeeSearchRequest request);
}
