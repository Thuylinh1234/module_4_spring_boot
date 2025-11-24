package com.sqc.academy.baikiemtra.service;

import com.sqc.academy.baikiemtra.model.MatBang;
import com.sqc.academy.baikiemtra.repository.IdMatBangRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Service
public class MatBangService implements IMatBangService {
    IdMatBangRepository repository;

    @Override
    public List<MatBang> findAll(String ten, String diaChi, Integer loaiId, Double giaMin, Double giaMax, Double dtMin, Double dtMax, Date ngayStart, Date ngayEnd) {
        return repository.findAll(ten, diaChi, loaiId, giaMin, giaMax, dtMin, dtMax, ngayStart, ngayEnd);
    }

    @Override
    public MatBang findById(int id) {
        return repository.findById(id);
    }

    @Override
    public MatBang save(MatBang matBang) {
        return repository.save(matBang);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }
}
