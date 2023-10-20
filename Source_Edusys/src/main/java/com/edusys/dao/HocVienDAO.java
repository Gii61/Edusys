package com.edusys.dao;

import com.edusys.entity.HocVien;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HocVienDAO extends EdusysDAO<HocVien, String>{
    final String insert = "INSERT INTO HOCVIEN (MAKH, MANH, DIEM)\n" +
                           "VALUES (?,?,?)";
    final String update = "UPDATE HOCVIEN\n" +
                           "SET MAKH = ?,MANH =?, DIEM = ?\n" +
                           "WHERE MAHV = ?";
    final String delete = "DELETE FROM HOCVIEN WHERE MAHV = ?";
    final String selectAll = "SELECT * FROM HOCVIEN";
    final String select_byID = "SELECT * FROM HOCVIEN WHERE MAHV = ?";
    final String select_byKhoaHoc = "SELECT * FROM HOCVIEN WHERE MAKH = ?";
    @Override
    public void insert(HocVien entity) {
        JdbcHelper.update(insert,entity.getMaKH(),entity.getMaNH(), entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.update(update, entity.getMaKH(),entity.getMaNH(),entity.getDiem(),entity.getMaHV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(delete, id);
    }

    @Override
    public List<HocVien> selectAll() {
    return selectBySql(selectAll);
    }

    @Override
    public HocVien selectById(String id) {
        List<HocVien> list = selectBySql(select_byID, id);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
       
    }

    @Override
    public List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next())
            {
                HocVien entity = new HocVien();
                entity.setMaHV(rs.getString("MaHV"));
                entity.setMaKH(rs.getString("MaKH"));
                entity.setMaNH(rs.getString("MaNH"));
                entity.setDiem(rs.getDouble("Diem"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<HocVien> selectByKhoaHoc(String maKH) {
    return selectBySql(select_byKhoaHoc,maKH);
    }

    
}
