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
import views.QuenMatKhauView;

/**
 *
 * @author Admin
 */
public class QuenMKController {
    QuenMatKhauView quenMatKhauView;
    SQLServerConnect sqlServerConnect;
    Connection connection;
    
    public QuenMKController(){
        quenMatKhauView = new QuenMatKhauView();
        sqlServerConnect = new SQLServerConnect();
        connection = sqlServerConnect.connect();
        quenMatKhauView.getBtnThayDoi().addActionListener(al -> btnThayDoi());
        quenMatKhauView.getBtnThoat().addActionListener(al->btnThoat());
    }

    QuenMKController(String tenTK) {
        quenMatKhauView = new QuenMatKhauView();
        quenMatKhauView.getTxtTaiKhoan().setText(tenTK);
        quenMatKhauView.getTxtTaiKhoan().setEnabled(false);
        sqlServerConnect = new SQLServerConnect();
        connection = sqlServerConnect.connect();
        quenMatKhauView.getBtnThayDoi().addActionListener(al -> btnThayDoi());
    }

    private void btnThayDoi() {
        if(!quenMatKhauView.checkNull()){
            try {
                String sql = "update TAIKHOAN\n" +
                        " set mat_khau = ?, cauhoi = ? , traloi = ? \n" +
                        " where tai_khoan = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, quenMatKhauView.getTxtMK().getText());
                ps.setString(2, quenMatKhauView.getCboHoi().getSelectedItem().toString());
                ps.setString(3, quenMatKhauView.getTxtTraLoi().getText());
                ps.setString(4, quenMatKhauView.getTxtTaiKhoan().getText());

                int i = ps.executeUpdate();
                if(i>0)
                    JOptionPane.showMessageDialog(quenMatKhauView, "Thay đổi thành công!");
                else
                    JOptionPane.showMessageDialog(quenMatKhauView, "Thay đổi thất bại!");
            } catch (SQLException ex) {
                Logger.getLogger(QuenMKController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void btnThoat() {
        quenMatKhauView.dispose();
    }
}
