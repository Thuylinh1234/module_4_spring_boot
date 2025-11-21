package com.sqc.academy.bai4;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    // Danh sách bộ phận lưu tạm trong bộ nhớ
    private static final List<Department> departments = new ArrayList<>();

    // Dữ liệu mẫu
    static {
        departments.add(new Department(1, "Kế Toán"));
        departments.add(new Department(2, "Nhân Sự"));
        departments.add(new Department(3, "Kỹ Thuật"));
    }

    @GetMapping
    public Object getAll() {
        return JsonResponse.ok(departments);
    }

    // Lấy theo id
    @GetMapping("/{id}")
    public Object getById(@PathVariable Integer id) {
        Department found = findById(id);

        if (found == null) {
            throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXISTED);
        }

        return JsonResponse.ok(found);
    }


    // Thêm mới
    @PostMapping
    public Object create(@RequestBody Department department) {
        departments.add(department);
        return JsonResponse.created(department);
    }


    // Cập nhật
    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody Department updateData) {

        Department found = findById(id);

        if (found == null) {
            throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXISTED);
        }

        // Cập nhật tên
        found.setName(updateData.getName());
        return JsonResponse.ok(found);
    }


    // Xóa
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        Department found = findById(id);

        if (found == null) {
            throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXISTED);
        }

        departments.remove(found);

        return JsonResponse.noContent();
    }


    private Department findById(Integer id) {
        for (Department department : departments) {
            if (department.getId().equals(id)) {
                return department;
            }
        }
        return null;
    }
}
