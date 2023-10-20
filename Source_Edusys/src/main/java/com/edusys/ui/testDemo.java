package com.edusys.ui;

import com.edusys.entity.NhanVien;
import com.edusys.dao.NhanVienDAO;
import com.edusys.dao.ThongKeDAO;
import java.util.List;

public class testDemo {
    public static void main(String[] args) {
        ThongKeDAO tkDAO = new ThongKeDAO();
        List<Object[]> list = tkDAO.getDiemChuyenDe();
        for(Object[] obj : list)
        {
            System.out.println("=> " + obj[0] + "- "+obj[1] + "- "+obj[2]);
        }
//        NhanVienDAO dao = new NhanVienDAO();
//        //dao.insert(new NhanVien("PD03", "Gii", "123", true));
//        List<NhanVien> ls = dao.selectAll();
//        for(NhanVien nv : ls)
//        {
//            System.out.println("=>" + nv.toString());
//        }

    }
}
