/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Config.SQLServerConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import views.HoaDonView;

/**
 *
 * @author Admin
 */
public class HoaDonController {
    HoaDonView view;
    SQLServerConnect serverConnect;
    Connection conn;
    
    public HoaDonController(String makh){
        view = new HoaDonView();
        serverConnect = new SQLServerConnect();
        conn = serverConnect.connect();
        setText(makh);
        view.btnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }
        });
    }

    private void setText(String makh) {
        try {
            String sql = "select hoadon.maKH,maHD,hoTen,diaChi,loaiDien,maThang,chisomoi,chisocu,ldtt,tien from HOTIEUTHU \n" +
                            "join HOADON on HOTIEUTHU.maKH=HOADON.maKH\n" +
                            "join CHISODIEN on HOTIEUTHU.maKH=CHISODIEN.maKH where hoadon.makh = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, makh);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                view.setMaHD(rs.getString("mahd"));
                view.setMaKH(rs.getString("makh"));
                view.setMaThang(rs.getString("mathang"));
                view.setTen(rs.getString("hoten"));
                view.setLoaiDien(rs.getString("loaidien"));
                view.setDiaChi(rs.getString("diachi"));
                view.getDtm().addRow(new Object[]{rs.getString("chisocu"),rs.getString("chisomoi"),rs.getString("ldtt"),rs.getInt("tien")});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
