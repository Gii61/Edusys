package com.edusys.dao;

import com.edusys.entity.KhoaHoc;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocDAO extends EdusysDAO<KhoaHoc, String>{
    final String insert = "INSERT INTO KHOAHOC(MAKH, MACD, HOCPHI, THOILUONG, NGAYKG, GHICHU, MANV, NGAYTAO)\n" +
                          "VALUES (?,?,?,3,?,?,?,?)";
    final String update = "UPDATE KHOAHOC\n" +
                          "SET MACD =?,HOCPHI = ?, THOILUONG = ?, NGAYKG =?,GHICHU =?, MANV =?, NGAYTAO =?\n" +
                          "WHERE MAKH = ?";
    final String delete = "DELETE FROM KHOAHOC WHERE MAKH = ?";
    final String selectAll = "SELECT * FROM KHOAHOC";
    final String select_byID = "SELECT * FROM KHOAHOC WHERE MAKH = ?";
    final String select_by_MaCD = "SELECT * FROM KHOAHOC WHERE MACD = ?";
    @Override
    public void insert(KhoaHoc entity) {
        JdbcHelper.update(insert, entity.getMaKH(),entity.getMaCD(), entity.getHocPhi(),entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(),entity.getMaNV(), entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
        JdbcHelper.update(update, entity.getMaCD(), entity.getHocPhi(),entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(),entity.getMaNV(), entity.getNgayTao(), entity.getMaKH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(delete, id);
    }

    @Override
    public List<KhoaHoc> selectAll() {
         return selectBySql(selectAll);
    }

    @Override
    public KhoaHoc selectById(String id) {
        List<KhoaHoc> list = selectBySql(select_byID, id);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next())
            {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getString("MaKH"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                entity.setMaCD(rs.getString("MaCD"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
         
    public List<KhoaHoc> selectKhoaHocByChuyenDe(String maCD) {
         return selectBySql(select_by_MaCD,maCD);
    } 
    public List<Integer> selectYears()
    {
        String sql = "SELECT DISTINCT YEAR(NGAYKG) YEAR FROM KHOAHOC ORDER BY YEAR DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            while(rs.next())
            {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }

  
