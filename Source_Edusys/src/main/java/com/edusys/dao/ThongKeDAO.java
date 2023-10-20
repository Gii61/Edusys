package com.edusys.dao;

import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private List<Object[]>getListOfArray(String sql, String[] cols, Object...args)
    {
        try {
           List<Object[]> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next())
            {
                Object[] vals = new Object[cols.length];
                for(int i = 0; i < cols.length; i++)
                {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    public List<Object[]>getBangDiem(String maKH)
        {
            String sql = "{CALL SP_BANGDIEM(?)}";
            String[] cols = {"MaNH","HoTen","Diem"};
            return getListOfArray(sql, cols, maKH);
        }
     public List<Object[]>getLuongNguoiHoc()
        {
            String sql = "{CALL SP_THONGKENGUOIHOC}";
            String[] cols = {"Nam","SoLuong","DauTien","CuoiCung"};
            return getListOfArray(sql, cols);
        }
     public List<Object[]>getDiemChuyenDe()
        {
            String sql = "{CALL SP_THONGKEDIEM}";
            String[] cols = {"ChuyenDe","SoHV","ThapNhat","CaoNhat","TrungBinh"};
            return getListOfArray(sql, cols);
        }
      public List<Object[]>getDoanhThu(Integer nam)
        {
            String sql = "{CALL SP_THONGKEDOANHTHU(?)}";
            String[] cols = {"ChuyenDe","SoKH","SoHV","DoanhThu","ThapNhat","CaoNhat","TrungBinh"};
            return getListOfArray(sql, cols,nam);
        }
    
}
