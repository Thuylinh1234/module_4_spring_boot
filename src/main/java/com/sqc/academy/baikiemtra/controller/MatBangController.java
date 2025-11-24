package com.sqc.academy.baikiemtra.controller;

import com.sqc.academy.ApiRespone;
import com.sqc.academy.baikiemtra.exception.ApiException;
import com.sqc.academy.baikiemtra.exception.ErrorCode;
import com.sqc.academy.baikiemtra.model.MatBang;
import com.sqc.academy.baikiemtra.service.IMatBangService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/matbang")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatBangController {
    IMatBangService service;

    @GetMapping
    public ResponseEntity<ApiRespone<List<MatBang>>> search(
            @RequestParam(required = false) String ten,
            @RequestParam(required = false) String diaChi,
            @RequestParam(required = false) Integer loaiId,
            @RequestParam(required = false) Double giaMin,
            @RequestParam(required = false) Double giaMax,
            @RequestParam(required = false) Double dtMin,
            @RequestParam(required = false) Double dtMax,
            @RequestParam(required = false) Date ngayStart,
            @RequestParam(required = false) Date ngayEnd
    ) {
        List<MatBang> list = service.findAll(ten, diaChi, loaiId, giaMin, giaMax, dtMin, dtMax, ngayStart, ngayEnd);
        return ResponseEntity.ok(ApiRespone.<List<MatBang>>builder().data(list).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<MatBang>> getById(@PathVariable int id) {
        MatBang mb = service.findById(id);
        if (mb == null) throw new ApiException(ErrorCode.MATBANG_NOT_FOUND);
        return ResponseEntity.ok(ApiRespone.<MatBang>builder()
                .data(mb)
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiRespone<MatBang>> create(@RequestBody MatBang matBang) {
        matBang = service.save(matBang);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiRespone.<MatBang>builder()
                        .data(matBang)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRespone<String>> delete(@PathVariable int id) {
        boolean deleted = service.delete(id);
        if (!deleted) throw new ApiException(ErrorCode.MATBANG_NOT_FOUND);
        return ResponseEntity.ok(ApiRespone.<String>builder()
                .data("Xóa thành công")
                .build());
    }
}
