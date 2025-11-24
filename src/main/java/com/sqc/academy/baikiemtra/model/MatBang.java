package com.sqc.academy.baikiemtra.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder // thiết lập giá trị cho từng thuộc tính 1 cách dễ dàng
@NoArgsConstructor // ko tham số
@AllArgsConstructor // có tham có
public class MatBang {
    private String maMatBang;
    private String tenMatBang;
    private String diaChi;
    private double dienTich;
    private LoaiMatBang loaiMatBang;
    private double giaThue;
    private LocalDate ngayBatDau;
}
