package com.sqc.academy.baikiemtra.repository;

import com.sqc.academy.baikiemtra.model.LoaiMatBang;
import com.sqc.academy.baikiemtra.model.MatBang;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MatBangRepository implements IdMatBangRepository {

    @Override
    public List<MatBang> findAll(String ten, String diaChi, Integer loaiId, Double giaMin, Double giaMax, Double dtMin, Double dtMax, java.util.Date ngayStart, java.util.Date ngayEnd) {
        List<MatBang> list = new ArrayList<>();
        String sql = "SELECT mb.ma_matbang, mb.ten_matbang, mb.dia_chi, mb.dien_tich, " +
                "mb.gia_thue, mb.ngay_bat_dau, l.id AS loai_id, l.ten_loai " +
                "FROM MatBang mb JOIN LoaiMatBang l ON mb.loai_id = l.id";

        try (PreparedStatement ps = BaseRepository.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MatBang mb = MatBang.builder()
                        .maMatBang(rs.getString("ma_matbang"))
                        .tenMatBang(rs.getString("ten_matbang"))
                        .diaChi(rs.getString("dia_chi"))
                        .dienTich(rs.getDouble("dien_tich"))
                        .giaThue(rs.getDouble("gia_thue"))
                        .ngayBatDau(rs.getDate("ngay_bat_dau").toLocalDate())
                        .loaiMatBang(new LoaiMatBang(
                                rs.getInt("loai_id"),
                                rs.getString("ten_loai")
                        ))
                        .build();
                list.add(mb);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách mặt bằng: " + e.getMessage());
        }

        return list;
    }


    @Override
    public MatBang findById(int id) {
        String sql = "SELECT mb.ma_matbang, mb.ten_matbang, mb.dia_chi, mb.dien_tich, " +
                "mb.gia_thue, mb.ngay_bat_dau, l.id AS loai_id, l.ten_loai " +
                "FROM MatBang mb JOIN LoaiMatBang l ON mb.loai_id = l.id " +
                "WHERE mb.id = ?";

        try (PreparedStatement ps = BaseRepository.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return MatBang.builder()
                        .maMatBang(rs.getString("ma_matbang"))
                        .tenMatBang(rs.getString("ten_matbang"))
                        .diaChi(rs.getString("dia_chi"))
                        .dienTich(rs.getDouble("dien_tich"))
                        .giaThue(rs.getDouble("gia_thue"))
                        .ngayBatDau(rs.getDate("ngay_bat_dau").toLocalDate())
                        .loaiMatBang(new LoaiMatBang(
                                rs.getInt("loai_id"),
                                rs.getString("ten_loai")
                        ))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm mặt bằng: " + e.getMessage());
        }

        return null;
    }

    @Override
    public MatBang save(MatBang matBang) {
        String sql = "INSERT INTO MatBang (ma_matbang, ten_matbang, dia_chi, dien_tich, loai_id, gia_thue, ngay_bat_dau) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = BaseRepository.getConnection().prepareStatement(sql)) {
            ps.setString(1, matBang.getMaMatBang());
            ps.setString(2, matBang.getTenMatBang());
            ps.setString(3, matBang.getDiaChi());
            ps.setDouble(4, matBang.getDienTich());
            ps.setInt(5, matBang.getLoaiMatBang().getId());
            ps.setDouble(6, matBang.getGiaThue());
            ps.setDate(7, Date.valueOf(matBang.getNgayBatDau()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm mặt bằng: " + e.getMessage());
        }

        return matBang;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM MatBang WHERE id = ?";

        try (PreparedStatement ps = BaseRepository.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa mặt bằng: " + e.getMessage());
        }
    }
}
