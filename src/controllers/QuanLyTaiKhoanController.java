/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Config.SQLServerConnect;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.HoTieuThuModel;
import views.QuanLyTaiKhoanView;

/**
 *
 * @author Admin
 */
public class QuanLyTaiKhoanController {

    QuanLyTaiKhoanView view;
    SQLServerConnect sqlServerConnect;
    Connection connection;
    ListSelectionModel listSelectionModel;
    String tenTK;
    static boolean isViewAddedListener = false;

    public QuanLyTaiKhoanController(QuanLyTaiKhoanView view) {
        this.view = view;
        sqlServerConnect = new SQLServerConnect();
        connection = sqlServerConnect.connect();
        getDataFromDB();
        listSelectionModel = view.getTblBang().getSelectionModel();
        view.getBtnThem().addActionListener(al -> BtnThem());
            view.getBtnSua().addActionListener(al -> BtnSua());
            view.getBtnXoa().addActionListener(al -> btnXoa());
//        if (!isViewAddedListener) {
//            
//            isViewAddedListener = true;
//
//        }

    }

    public void getDataFromDB() {
        try {
            view.getDtm().setRowCount(0);
            String sqlQuery = "Select * from taikhoan";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                view.getDtm().addRow(new Object[]{rs.getString("tai_khoan"), "******", "*******", "********"});
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BtnThem() {
        DangKiController c = new DangKiController(this);
    }

    private void BtnSua() {
        int selectedRow = view.getTblBang().getSelectedRow();
        if (selectedRow > -1) {
            tenTK = (String) view.getTblBang().getValueAt(selectedRow, 0);
            new QuenMKController(tenTK);
        } else {
            JOptionPane.showMessageDialog(view, "Chưa chọn hàng!");
        }

    }

    private void btnXoa() {
        int selectedRow = view.getTblBang().getSelectedRow();
        if (selectedRow > -1) {
            tenTK = (String) view.getTblBang().getValueAt(selectedRow, 0);
            if (!tenTK.equals("admin")) {
                try {
                    int question = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xoá tài khoản này?");
                    if (question == 0) {
                        String sql = "delete from taikhoan where tai_khoan = ?";
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setString(1, tenTK);
                        int i = ps.executeUpdate();
                        if (i > 0) {
                            JOptionPane.showMessageDialog(view, "Xoá thành công!");
                            
                        }
                    }
                    getDataFromDB();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyTaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Không được xoá tài khoản admin!!");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Chưa chọn hàng!");
        }

    }

}
