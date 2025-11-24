package com.sqc.academy.bai4.repository;

import com.sqc.academy.bai4.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository implements IDepartmentRepository {
    @Autowired
    private static final List<Department> departments = new ArrayList<>();

    static {
        departments.add(new Department(1, "Kế Toán"));
        departments.add(new Department(2, "Nhân Sự"));
        departments.add(new Department(3, "Kỹ Thuật"));
    }

    @Override
    public List<Department> findAll() {
        return departments;
    }

    @Override
    public Department findById(Integer id) {

        for (Department d : departments) {
            if (d.getId().equals(id)) {
                return d;
            }
        }

        return null;
    }

    @Override
    public Department save(Department department) {

        Department existing = findById(department.getId());

        if (existing == null) {
            departments.add(department);
        } else {
            existing.setName(department.getName());
        }

        return department;
    }

    @Override
    public boolean delete(Integer id) {

        Department d = findById(id);

        if (d == null) {
            return false;
        }

        departments.remove(d);
        return true;
    }
}

