package com.sqc.academy.bai4.service;

import com.sqc.academy.bai4.exception.ApiException;
import com.sqc.academy.bai4.exception.ErrorCode;
import com.sqc.academy.bai4.model.Department;
import com.sqc.academy.bai4.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository repository;

    @Override
    public List<Department> getAll() {
        return repository.findAll();
    }

    @Override
    public Department getById(Integer id) {
        Department d = repository.findById(id);
        if (d == null) throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXISTED);
        return d;
    }

    @Override
    public Department create(Department department) {
        return repository.save(department);
    }

    @Override
    public Department update(Integer id, Department department) {
        getById(id);
        department.setId(id);
        return repository.save(department);
    }

    @Override
    public void delete(Integer id) {
        getById(id);
        repository.delete(id);
    }
}

