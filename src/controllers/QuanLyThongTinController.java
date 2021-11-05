/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Config.SQLServerConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.HoTieuThuModel;
import views.QuanLyThongTinView;
import views.TimKiemView;

/**
 *
 * @author Admin
 */
public class QuanLyThongTinController {

    QuanLyThongTinView view;
    HoTieuThuModel model;
    private final String[] tableHeaders = {"Mã KH", "Họ tên", "Giới tính", "Ngày sinh", "CMND", "SĐT", "Ngày đăng ký", "Địa chỉ","Loại điện"};
    SQLServerConnect sqlServerConnect;
    Connection connection;
    ListSelectionModel listSelectionModel;
    String maKHSelected = "";
    static boolean isViewAddedListener = false;
    public QuanLyThongTinController(QuanLyThongTinView view) {
        this.view = view;
        sqlServerConnect = new SQLServerConnect();
        connection = sqlServerConnect.connect();
        setHeaderForTable();
        if(!isViewAddedListener)
        {
            
            view.getBtnThem().addActionListener(al -> btnThemPerformed());
            view.getBtnReset().addActionListener(al -> btnResetPerformed());
            view.getBtnSua().addActionListener(al -> btnSuaPerformed());
            listSelectionModel = view.getTblBang().getSelectionModel();
            listSelectionModel.addListSelectionListener(listSelectionListener());
            view.getBtnTimKiem().addActionListener(al -> btnTimKiemPerformed());
            isViewAddedListener=true;

        }
        getDataFromDB();
        
    }

    ListSelectionListener listSelectionListener() {
        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                try {
                    int selectedRow = listSelectionModel.getMinSelectionIndex();
                    System.out.println("hàng đc chọn: " + selectedRow);
                    maKHSelected = (String) view.getTblBang().getValueAt(selectedRow, 0); // dòng i cột 0
                    System.out.println("'" + maKHSelected + "'");
                    showKhachHangModel(maKHSelected);
                } catch (Exception e) {
                }

            }
        };
        return lsl;
    }

    public void showKhachHangModel(String maKH) {

        try {
            String sqlQuery = "Select * from HoTieuThu where HoTieuThu.maKH like ?";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoTieuThuModel hoTieuThuModel = getDataFromResultSet(rs);
                view.setModel(hoTieuThuModel);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnThemPerformed() {
        model = view.getModel();
        if (model.getNgayDK() != null && model.getNgaySinh() != null) {
            view.getDtm().addRow(model.toStringArray());
            insertDataToDB();
            getDataFromDB();
        }

    }

    public HoTieuThuModel getDataFromResultSet(ResultSet rs) {
        HoTieuThuModel hoTieuThuModel = null;
        try {
            String maKH = rs.getString("maKH");
            String hoTen = rs.getString("hoTen");
            String CMND = rs.getString("CMND");
            String diaChi = rs.getString("diaChi");
            String gioiTinh = rs.getString("gioiTinh");
            Date ngaySinh = new Date(rs.getDate("ngaySinh").getTime());
            String sdt = rs.getString("sdt");
            Date ngayDangKi = new Date(rs.getDate("ngayDangKi").getTime());
            String loaiDien = rs.getString("loaiDien");
            hoTieuThuModel = new HoTieuThuModel(maKH, hoTen, gioiTinh, ngaySinh, CMND, sdt, ngayDangKi, diaChi, loaiDien);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hoTieuThuModel;
    }

    public void getDataFromDB() {
        try {
            view.getDtm().setRowCount(0);
            String sqlQuery = "Select * from HoTieuThu";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoTieuThuModel hoTieuThuModel = getDataFromResultSet(rs);
                view.getDtm().addRow(hoTieuThuModel.toStringArray());
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertDataToDB() {
        try {
            String sqlQueryInsert = "INSERT HOTIEUTHU "
                    + "VALUES (?, ? ,? ,? ,? ,? ,? ,? ,?)";
            PreparedStatement ps = connection.prepareStatement(sqlQueryInsert);
            ps.setString(1, model.getMaKH());
            ps.setString(2, model.getTen());
            ps.setString(3, model.getCmnd());
            ps.setString(4, model.getDiaChi());
            ps.setString(5, model.getGioiTinh());
            ps.setObject(6, model.utilDateToSQLDate(model.getNgaySinh()));
            ps.setString(7, model.getSdt());
            ps.setObject(8, model.utilDateToSQLDate(model.getNgayDK()));
            ps.setString(9, model.getLoaiDien());
            int result = ps.executeUpdate();
            if (result == 1) {
                JOptionPane.showMessageDialog(view, "Thêm thành công!");
            } else {
                JOptionPane.showMessageDialog(view, "Thêm thất bại!");
            }
            ps.close();
        } catch (SQLException ex) {
            if (ex.toString().contains("PRIMARY KEY")) {
                JOptionPane.showMessageDialog(view, "Trùng khoá chính!");
            } else if (ex.toString().contains("String or binary data would be truncated")) {
                JOptionPane.showMessageDialog(view, "Không thể để 1 trường quá dài!");
            } else {
                Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setHeaderForTable() {
        view.getDtm().setColumnIdentifiers(tableHeaders);
    }

    private void btnResetPerformed() {
        view.reset();
        getDataFromDB();

    }

    public void insertDataToTableView(ResultSet rs) {
        view.getDtm().setRowCount(0);
        try {
            while (rs.next()) {
                model = getDataFromResultSet(rs);
                view.getDtm().addRow(model.toStringArray());
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnTimKiemPerformed() {
        TimKiemThongTinController timKiemController = new TimKiemThongTinController(this);

    }

    private void btnSuaPerformed() {
        try {
            model = view.getModel();
            if (model.getNgayDK() != null && model.getNgaySinh() != null) {
                System.out.println("ma kh cần sửa : " + maKHSelected);
                System.out.println("Data mới: " + model.toString());
                String sql = "UPDATE [dbo].[HOTIEUTHU]"
                        + "       SET"
                        + "       [hoTen] = ?"
                        + "      ,[CMND] = ?"
                        + "      ,[diaChi] = ?"
                        + "      ,[gioiTinh] = ?"
                        + "      ,[ngaySinh] = ?"
                        + "      ,[sdt] = ?"
                        + "      ,[ngayDangKi] = ?"
                        + "      ,loaidien = ?"
                        + " WHERE maKH like ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, model.getTen());
                ps.setString(2, model.getCmnd());
                ps.setString(3, model.getDiaChi());
                ps.setString(4, model.getGioiTinh());
                ps.setObject(5, model.utilDateToSQLDate(model.getNgaySinh()));
                ps.setString(6, model.getSdt());
                ps.setObject(7, model.utilDateToSQLDate(model.getNgayDK()));
                ps.setString(8, model.getLoaiDien());
                ps.setString(9, model.getMaKH());
                int result = ps.executeUpdate();
                if (result == 1) {
                    JOptionPane.showMessageDialog(view, "Thay đổi giá trị thành công!");
                }
                getDataFromDB();
                ps.close();
            }
        } catch (SQLException ex) {
            if (ex.toString().contains("String or binary data would be truncated")) {
                JOptionPane.showMessageDialog(view, "Không thể để 1 trường quá dài!");
            } else {
                Logger.getLogger(QuanLyChiSoDienController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(view, "Không thể cập nhật giá trị! \nCó lỗi xảy ra! ");
            }
        }
    }

}
