package com.sqc.academy.bai4.service;

import com.sqc.academy.bai4.model.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAll();
    Department getById(Integer id);
    Department create(Department department);
    Department update(Integer id, Department department);
    void delete(Integer id);
}
