/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Config.SQLServerConnect;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.ChiSoDienModel;
import views.QuanLyChiSoDienView;

/**
 *
 * @author Admin
 */
public class QuanLyChiSoDienController {

    ChiSoDienModel model;
    QuanLyChiSoDienView view;
    private final String[] tableHeaders = {"Mã Khách Hàng", "Mã Tháng", "Chỉ Số Cũ", "Chỉ Số Mới"};
    SQLServerConnect sqlServerConnect;
    Connection connection;
    ListSelectionModel listSelectionModel;
    String maKHSelected = "";
    static boolean isViewAddedListener = false;

    public QuanLyChiSoDienController(QuanLyChiSoDienView view) {
        this.view = view;
        sqlServerConnect = new SQLServerConnect();
        connection = sqlServerConnect.connect();
        setHeaderForTable();
        if (!isViewAddedListener) {
            view.getBtnThem().addActionListener(al -> btnThemPerformed());
            view.getBtnSua().addActionListener(al -> btnSuaPerfomed());
            view.getBtnReset().addActionListener(al -> btnResetPerformed());
            listSelectionModel = view.getTblBang().getSelectionModel();
            listSelectionModel.addListSelectionListener(listSelectionListener());

            isViewAddedListener = true;

        }
        getDataFromDB();
    }

    private void setHeaderForTable() {
        view.getDtm().setColumnIdentifiers(tableHeaders);
    }

    public void btnThemPerformed() {
        model = view.getModel();
        if (model != null) {
            view.getDtm().addRow(model.toStringArray());
            insertDataDB();
            createHD_TK(); //khi cập nhật xong chỉ số điện thì auto tạo hoá đơn
            getDataFromDB();
        }
    }

    private ListSelectionListener listSelectionListener() {
        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                try {
                    int selectedRow = listSelectionModel.getMinSelectionIndex();
                    System.out.println("hàng đc chọn: " + selectedRow);
                    maKHSelected = (String) view.getTblBang().getValueAt(selectedRow, 0); // dòng i cột 0
                    showModel(maKHSelected);
                } catch (Exception e) {
                }

            }
        };
        return listSelectionListener;
    }

    public void showModel(String maKH) {

        try {
            String sqlQuery = "Select * from ChiSoDien where maKH = ?";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiSoDienModel model = getDataFromResultSet(rs);
                view.getTxtMaKH().setText(model.getMaKH());
                view.getTxtMaKH().setEditable(false);
                view.getTxtMaThang().setText(model.getMaThang());
                view.getTxtMaThang().setEditable(false);
                view.getTxtCSC().setText(String.valueOf(model.getChiSoCu()));
                view.getTxtCSM().setText(String.valueOf(model.getChiSoMoi()));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ChiSoDienModel getDataFromResultSet(ResultSet rs) {
        ChiSoDienModel capNhatChiSoDienModel = null;
        try {
            String maKH = rs.getString("maKH");
            String maThang = rs.getString("maThang");
            String chiSoMoi = rs.getString("chiSoMoi");
            String chiSoCu = rs.getString("chiSoCu");
            capNhatChiSoDienModel = new ChiSoDienModel(maKH, maThang, Integer.parseInt(chiSoCu), Integer.parseInt(chiSoMoi));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return capNhatChiSoDienModel;
    }

    public void getDataFromDB() {
        try {
            view.getDtm().setRowCount(0);
            String sqlQuery = "Select * from chisodien";
            try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ChiSoDienModel modell = getDataFromResultSet(rs);
                    view.getDtm().addRow(modell.toStringArray());
                }
                view.getTblBang().repaint();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertDataDB() {
        try {
            String sql = "insert CHISODIEN\n" + "values(?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, model.getMaKH());
                ps.setString(2, model.getMaThang());
                ps.setString(3, String.valueOf(model.getChiSoCu()));
                ps.setString(4, String.valueOf(model.getChiSoMoi()));
                int result = ps.executeUpdate();
                if (result == 1) {
                    JOptionPane.showMessageDialog(view, "Thêm thành công!");
                }
            }
        } catch (HeadlessException | SQLException e) {
            if (e.toString().contains("PRIMARY KEY")) {
                JOptionPane.showMessageDialog(view, "Trùng khoá chính!");
            } else if (e.toString().contains("String or binary data would be truncated")) {
                JOptionPane.showMessageDialog(view, "Không thể để 1 trường quá dài!");
            } else if (e.toString().contains("For input string")) {

            } else {
                Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private void btnResetPerformed() {
        view.reset();
        getDataFromDB();

    }

    private void btnSuaPerfomed() {
        try {
            model = view.getModel();
            if (model != null) {
                System.out.println("ma kh cần sửa : " + maKHSelected);
                System.out.println("Data mới: " + model.toString());
                String sql = "UPDATE [dbo].[CHISODIEN]"
                        + "   SET [chisocu] = ?"
                        + "      ,[chisomoi] = ?"
                        + " WHERE maKH = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, model.getChiSoCu());
                ps.setInt(2, model.getChiSoMoi());
                ps.setString(3, maKHSelected);
                int result = ps.executeUpdate();
                if (result == 1) {
                    JOptionPane.showMessageDialog(view, "Thay đổi giá trị thành công!");
                } else {
                    JOptionPane.showMessageDialog(view, "Thay đổi thất bại!");
                }
                getDataFromDB();
                ps.close();
            }
        } catch (SQLException ex) {
            if (ex.toString().contains("duplicate key")) {
                JOptionPane.showMessageDialog(view, "Trùng khoá chính!");
            } else if (ex.toString().contains("String or binary data would be truncated")) {
                JOptionPane.showMessageDialog(view, "Không thể để 1 trường quá dài!");
            } else if (ex.toString().contains("For input string")) {
                JOptionPane.showMessageDialog(view, "Nhập sai chỉ số!");
            } else {
                Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createHD(String maKh, String mathang) {
        try {
            String sql = "select mahd from hoadon";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String maHD_end = "";
            while (rs.next()) {
                maHD_end = rs.getString("mahd");
            }
            String maHD_next = String.valueOf(maHD_end.charAt(2)) + maHD_end.charAt(3) + maHD_end.charAt(4);//lấy mãHD cuối cùng rồi +1
            int newMaHD = Integer.parseInt(maHD_next) + 1;
            DecimalFormat df = new DecimalFormat("#000");// định dạng 3 số sau của maHD
            String newmaHD = df.format(newMaHD);
            String maHD = "HD" + newmaHD;
            String sql2 = "insert hoadon values(?,?,null,null)";// tạo hoá đơn
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1, maHD);
            ps2.setString(2, maKh);
            ps2.executeUpdate();
            String sql3 = "updateHoaDon ?,? "; // gọi thủ tục tính lđtt, tiền
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            ps3.setString(1, maKh);
            ps3.setString(2, mathang);
            ps3.executeUpdate();
            System.out.println("Đã thêm hoá đơn " + maHD + "/" + maKh);
            String sql4 = "insert THONGKE\n"
                    + "values(?,?,?,0)";
            PreparedStatement ps4 = connection.prepareStatement(sql4);
            ps4.setString(1, maKh);
            ps4.setString(2, mathang);
            ps4.setString(3, maHD);
            ps4.executeUpdate();
            System.out.println("Đã thêm thống kê " + maHD);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChiSoDienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createHD_TK() {
        try {
            String sql = "select maKH, mathang from CHISODIEN \n"
                    + "	where maKH not in (select (maKH) from HOADON)";
            PreparedStatement ps = connection.prepareStatement(sql);// tìm xem có chỉ số điện mới được tạo nhưng chưa tạo hoá đơn
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                createHD(rs.getString("makh"), rs.getString("mathang"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChiSoDienController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
