package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NguoiHocDAO extends EdusysDAO<NguoiHoc, String>{
    final String insert = "INSERT INTO NGUOIHOC( MANH, HOTEN, NGAYSINH, GIOITINH, DIENTHOAI, EMAIL, GHICHU, MANV, NGAYDK )\n" +
                           "VALUES (?,?,?,?,?,?,?,?,?)";
    final String update = "UPDATE NGUOIHOC \n" +
                          "SET HOTEN = ?, NGAYSINH = ?,GIOITINH = ?, DIENTHOAI = ?,EMAIL = ?,GHICHU =?, MANV = ?, NGAYDK = ?\n" +
                          "WHERE MANH = ?";
    
    final String delete = "DELETE FROM NGUOIHOC WHERE MANH =?";
    final String selectAll = "SELECT * FROM NGUOIHOC";
    final String select_byID = "SELECT * FROM NGUOIHOC WHERE MANH = ?";
    final String select_not_in_course = "SELECT * FROM NGUOIHOC WHERE HOTEN LIKE ? AND MANH NOT IN (SELECT MANH FROM HOCVIEN WHERE MAKH = ?) ";
    final String select_by_keyword = "SELECT *FROM NGUOHOC WHERE HOTEN LIKE ?";
    @Override
    public void insert(NguoiHoc entity) {
        JdbcHelper.update(insert, entity.getMaNH(), entity.getHoTen(),entity.getNgaySinh(), entity.isGioiTinh(), entity.getDienThoai(), entity.getEmail(),entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK());
    }

    @Override
    public void update(NguoiHoc entity) {
        JdbcHelper.update(update, entity.getHoTen(),entity.getNgaySinh(), entity.isGioiTinh(), entity.getDienThoai(), entity.getEmail(),entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK(), entity.getMaNH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(delete, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return (List<NguoiHoc>) selectById(selectAll);
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = selectBySql(select_byID, id);
        if(list.isEmpty())
        {
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    public List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next())
            {
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<NguoiHoc> selectNotInCourse(String maKH, String keyword) {
        return selectBySql(select_not_in_course,"%"+keyword+"%",maKH);
    }
    public List<NguoiHoc>selectByKeyWord(String keyword)
    {
        return selectBySql(select_by_keyword, "%"+keyword+"%");
    }
    
}
