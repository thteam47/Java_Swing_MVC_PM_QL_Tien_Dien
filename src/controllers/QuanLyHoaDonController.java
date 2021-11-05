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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.HoaDonModel;
import views.QuanLyHoaDonView;
import views.TimKiemView;
import views.TimKiemmaHDView;

/**
 *
 * @author Admin
 */
public class QuanLyHoaDonController {

    QuanLyHoaDonView view;
    TimKiemView viewTimKiem;
    SQLServerConnect serverConnect;
    Connection conn;
    ListSelectionModel listSelectionModel;
    static boolean             isViewAddedListener=false;

    
    public QuanLyHoaDonController(QuanLyHoaDonView view) {
        this.view = view;
        serverConnect = new SQLServerConnect();
        conn = serverConnect.connect();
        getDataDB();
        if(!isViewAddedListener)
        {
            view.getBtnInHD().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int select = view.getTbl().getSelectedRow();
                if (select > -1) {
                    String makh = (String) view.getTbl().getValueAt(select, 2);
                    new HoaDonController(makh);
                } else {
                    JOptionPane.showMessageDialog(view, "Chưa chọn hàng!");
                }
            }
        });
        view.getBtnThanhToan().addActionListener(al -> setThanhToan());
        view.getBtnTimKiem().addActionListener(al -> TimKiem());
        listSelectionModel = view.getTbl().getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int select = view.getTbl().getSelectedRow();
                setLabel(select);
            }
        });
            isViewAddedListener=true;

        }
        
    }

    private void showData(ResultSet rs) throws SQLException {
        view.getDtm().setRowCount(0);
        while (rs.next()) {
            try {
                HoaDonModel model = new HoaDonModel();
                model.setMaKH(rs.getString("makh"));
                model.setHoten(rs.getString("hoten"));
                model.setMaHoaDon(rs.getString("mahd"));
                model.setLoaiDien(rs.getString("loaidien"));
                model.setLuongDienTieuThu(rs.getInt("ldtt"));
                model.setThanhTien(rs.getInt("tien"));
                view.getDtm().addRow(model.toStringArray());
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(QuanLyHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void getDataDB() {

        try {
            String sql = "select HOADON.maKH, hoTen, maHD, ldtt, loaiDien, tien from HOADON\n"
                    + "join HOTIEUTHU on HOADON.maKH = HOTIEUTHU.maKH";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            showData(rs);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setThanhToan() {
        try {
            int select = view.getTbl().getSelectedRow();
            if (select > -1) {
                String makh = (String) view.getTbl().getValueAt(select, 2);
                String sql = "update thongke set payment = 1 where makh = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, makh);
                int i = ps.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(view, "Đã thanh toán thành công!");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Chưa chọn hàng!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setLabel(int i) {

        try {
            String maKH = (String) view.getTbl().getValueAt(i, 2);
            String sql = "select  hoTen, mathang, ldtt, tien from HOADON\n"
                    + "join HOTIEUTHU on HOADON.maKH = HOTIEUTHU.maKH\n"
                    + "join CHISODIEN on CHISODIEN.maKH = HOADON.maKH\n"
                    + "where HOADON.maKH = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                view.getTen().setText(rs.getString("hoten"));
                view.getTien().setText("" + rs.getInt("tien"));
                view.getThang().setText(rs.getString("mathang"));
                view.getLDTT().setText("" + rs.getInt("ldtt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch(ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("QuanLyHoaDonController.java: Lỗi id label set =-1");
        }
    }

    private void TimKiem() {
        viewTimKiem = new TimKiemView();
        viewTimKiem.getBtnTimKiem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String maKH = viewTimKiem.getTxtKey().getText();
                    String sql = "select HOADON.maKH, hoTen, maHD, ldtt, loaiDien, tien from HOADON\n"
                            + "join HOTIEUTHU on HOADON.maKH = HOTIEUTHU.maKH\n"
                            + "where HOADON.maKH+HoaDon.maHD+hoTen like ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, "%" + maKH + "%");
                    ResultSet rs = ps.executeQuery();
                    showData(rs);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                viewTimKiem.dispose();
            }
        });

    }
//public void insertDataToTableView(ResultSet rs) {
//        view.getDtm().setRowCount(0);
//        try {
//            while (rs.next()) {
//                model = getDataFromResultSet(rs);
//                view.getDtm().addRow(model.toStringArray());
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(QuanLyThongTinController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void buildTableData(DefaultTableModel dtm, ResultSet rs) {
        //Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                dtm.addRow(vector);
                System.out.println(vector);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
