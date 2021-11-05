/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Config.SQLServerConnect;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.LoginModel;
import views.DangKiView;
import views.HomeMainView;
import views.LoginView;

/**
 *
 * @author Admin
 */
public class LoginController {

    LoginView loginView;
    LoginModel loginModel;
    SQLServerConnect sqlServerConnect;
    Connection connection;
    HomeMainView homeView;
    HomeMainController homeMainController;

    public LoginController() {
        this.loginView = new LoginView();
        sqlServerConnect = new SQLServerConnect();
        connection = sqlServerConnect.connect();
        loginView.getBtnDangNhap().addActionListener((ae) -> btnDangNhapPerformed());
        loginView.getLabDK().addMouseListener(labDKMouseListener());
        loginView.getLabQuenMK().addMouseListener(labQuenMKMouseListener());
    }

    public void btnDangNhapPerformed() {
        loginModel = loginView.getModel();
        if (!loginModel.isEmpty()) {
            if (loginValidator(loginModel.getTaiKhoan(), loginModel.getMatKhau())) {
                String tenTK = loginModel.getTaiKhoan();
                HomeMainController homeMainController1 = new HomeMainController(tenTK);
                loginView.dispose();
                JOptionPane.showMessageDialog(homeView, "Xin chào " + tenTK);
            } else {
                JOptionPane.showMessageDialog(loginView, "Đăng nhập thất bại!");
            }
        } else {
            JOptionPane.showMessageDialog(loginView, "Chưa điền tài khoản!");
        }
    }

    boolean loginValidator(String username, String password) {
        boolean loginValidate = false;
        try {
            System.out.println("login...");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from TAIKHOAN");
            while (rs.next()) {
                if (rs.getString("tai_khoan").equals(username) && rs.getString("mat_khau").equals(password)) {
                    System.out.println("login success");
                    loginValidate = true;
                    break;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("loi sql");
        }
        return loginValidate;
    }

    private MouseListener labDKMouseListener() {
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                DangKiController dangKiController = new DangKiController();

            }

            @Override
            public void mousePressed(MouseEvent me) {

            }

            @Override
            public void mouseReleased(MouseEvent me) {

            }

            @Override
            public void mouseEntered(MouseEvent me) {

            }

            @Override
            public void mouseExited(MouseEvent me) {

            }
        };
        return ml;
    }

    private MouseListener labQuenMKMouseListener() {
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                QuenMKController quenMKController = new QuenMKController();

            }

            @Override
            public void mousePressed(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        return ml;
    }

}
