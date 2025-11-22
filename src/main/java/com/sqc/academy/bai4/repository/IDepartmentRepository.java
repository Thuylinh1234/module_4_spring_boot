package com.sqc.academy.bai4.repository;

import com.sqc.academy.bai4.model.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
    Department findById(Integer id);
    Department save(Department department);
    boolean delete(Integer id);
}
