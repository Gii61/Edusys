package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChuyenDeDAO extends EdusysDAO<ChuyenDe, String>{
    final String insert = "INSERT INTO CHUYENDE(MACD, TENCD, HOCPHI, THOILUONG, HINH, MOTA)\n" +
                           "VALUES (?,?,?,?,?,?)";
    final String update = "UPDATE CHUYENDE\n" +
                           "SET TENCD =?, HOCPHI =?, THOILUONG =?, HINH = ?, MOTA =? WHERE MACD = ?";
    final String delete = "DELETE FROM CHUYENDE WHERE MACD = ?";
    final String selectAll = "SELECT * FROM CHUYENDE";
    final String select_byID = "SELECT * FROM CHUYENDE WHERE MACD = ?";
    
    
    
    
    @Override
    public void insert(ChuyenDe entity) {
             JdbcHelper.update(insert, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        JdbcHelper.update(update, entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(delete, id);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = selectBySql(select_byID, id);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next())
            {
                ChuyenDe entity = new ChuyenDe();
                entity.setMaCD(rs.getString("MaCD"));
                entity.setTenCD(rs.getString("TenCD"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt(4));
                entity.setHinh(rs.getString("Hinh"));
                entity.setMoTa(rs.getString("MoTa"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
