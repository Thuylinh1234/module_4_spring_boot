package com.sqc.academy.baikiemtra.repository;

import com.sqc.academy.baikiemtra.model.MatBang;

import java.util.Date;
import java.util.List;

public interface IdMatBangRepository {
    List<MatBang> findAll(String ten, String diaChi, Integer loaiId, Double giaMin, Double giaMax, Double dtMin, Double dtMax, Date ngayStart, Date ngayEnd);
    MatBang findById(int id);
    MatBang save(MatBang matBang);
    boolean delete(int id);
}
