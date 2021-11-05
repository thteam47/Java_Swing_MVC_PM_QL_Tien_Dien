/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.DanhMucModel;
import models.LoginModel;
import views.HomeMainView;
import views.LoginView;

/**
 *
 * @author Admin
 */
public class HomeMainController {
    
    private HomeMainView view;
    final String taikhoan;
    
    public HomeMainController(String taiKhoan) {
        this.taikhoan = taiKhoan;
        
        view = new HomeMainView();
        ChuyenManHinhController chuyenManHinhController = new ChuyenManHinhController(view.getJpnView());
        chuyenManHinhController.setDashboard(view.getJpnThongTin(), view.getLabThongTin()); //gọi nút mặc định khi mở view
        
        List<DanhMucModel> listDanhMuc = new ArrayList<>();
        listDanhMuc.add(new DanhMucModel("QuanLyThongTin", view.getJpnThongTin(), view.getLabThongTin()));
        listDanhMuc.add(new DanhMucModel("QuanLyChiSoDien", view.getJpnChiSoDien(), view.getLabChiSoDien()));
        listDanhMuc.add(new DanhMucModel("DanhSachThanhToan", view.getJpnThanhToan(), view.getLabThanhToan()));
        listDanhMuc.add(new DanhMucModel("QuanLyHoaDon", view.getJpnHoaDon(), view.getLabHoaDon()));
        listDanhMuc.add(new DanhMucModel("ThongKeDoanhThu", view.getJpnThongKe(), view.getLabThongKe()));
        listDanhMuc.add(new DanhMucModel("QuanLyTaiKhoan", view.getJpnTaiKhoan(), view.getLabTaiKhoan()));
        chuyenManHinhController.setEvent(listDanhMuc);
        checkAdmin(taiKhoan);
        
        view.getLabLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(view, "Đăng xuất?");
                if (i == 0) {
                    view.dispose();
                    LoginController loginController = new LoginController();
                }
            }
        });
        
    }

    public void checkAdmin(String taikhoan) {
        if (taikhoan.compareTo("admin") != 0) {
            view.getJpnTaiKhoan().setVisible(false);
        }
        
    }

    public static void main(String[] args) {
        HomeMainController homeMainController = new HomeMainController("admin");
    }
    
}
