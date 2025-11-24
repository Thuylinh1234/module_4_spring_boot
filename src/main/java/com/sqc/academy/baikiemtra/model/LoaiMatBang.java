package com.sqc.academy.baikiemtra.model;

import lombok.*;

@Getter
@Setter
@Builder // thiết lập giá trị cho từng thuộc tính 1 cách dễ dàng
@NoArgsConstructor // ko tham số
@AllArgsConstructor // có tham có
public class LoaiMatBang {
    private int id;
    private String tenLoai;
}
