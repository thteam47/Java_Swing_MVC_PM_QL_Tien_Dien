/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Config.SQLServerConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import views.DangKiView;

/**
 *
 * @author Admin
 */
public class DangKiController {
    DangKiView dangKiView;
    SQLServerConnect sqlServerConnect;
    Connection connection;
    public DangKiController(QuanLyTaiKhoanController qltkController){
        dangKiView = new DangKiView(qltkController);
        sqlServerConnect = new SQLServerConnect();
        connection = sqlServerConnect.connect();
        dangKiView.getBtnTaoTK().addActionListener(al -> btnDK());
        dangKiView.getBtnThoat().addActionListener(al->btnThoat());
    }

    public DangKiController() {
        dangKiView = new DangKiView();

        sqlServerConnect = new SQLServerConnect();
        connection = sqlServerConnect.connect();
        dangKiView.getBtnTaoTK().addActionListener(al -> btnDK());
        dangKiView.getBtnThoat().addActionListener(al->btnThoat());
    }
    
    private void btnDK() {
        try {
            if (!dangKiView.checkNull()) {
                String sql = "insert TAIKHOAN\n"
                        + "values(?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, dangKiView.getTxtTaiKhoan().getText());
                ps.setString(2, dangKiView.getTxtMK().getText());
                ps.setString(3, dangKiView.getCboHoi().getSelectedItem().toString());
                ps.setString(4, dangKiView.getTxtTraLoi().getText());
                int i = ps.executeUpdate();
                if(i>0)
                    JOptionPane.showMessageDialog(dangKiView, "Đăng kí thành công!");
            }

        } catch (SQLException ex) {
            if(ex.toString().indexOf("PRIMARY KEY")>0){
                JOptionPane.showMessageDialog(dangKiView, "Tài khoản đã tồn tại!");
            } else
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnThoat() {
        dangKiView.dispose();
    }
}
