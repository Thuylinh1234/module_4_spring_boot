package com.sqc.academy.bai4.controller;

import com.sqc.academy.bai4.exception.ApiException;
import com.sqc.academy.bai4.model.Department;
import com.sqc.academy.bai4.exception.ErrorCode;
import com.sqc.academy.bai4.JsonResponse;
import com.sqc.academy.bai4.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    @GetMapping
    public Object getAll() {
        return JsonResponse.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Integer id) {
        return JsonResponse.ok(service.getById(id));
    }

    @PostMapping
    public Object create(@RequestBody Department department) {
        return JsonResponse.created(service.create(department));
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody Department dep) {
        return JsonResponse.ok(service.update(id, dep));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        service.delete(id);
        return JsonResponse.noContent();
    }
}
