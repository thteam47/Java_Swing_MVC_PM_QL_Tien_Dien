/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.TimKiemModel;
import views.TimKiemView;

/**
 *
 * @author Admin
 */
public class TimKiemThongTinController {
    TimKiemView view;
    TimKiemModel model;
    QuanLyThongTinController quanLyThongTinController;  
    
    public TimKiemThongTinController(QuanLyThongTinController quanLyThongTinController) {
        this.quanLyThongTinController = quanLyThongTinController;
        view = new TimKiemView();
        view.getBtnTimKiem().addActionListener(al-> btnTimKiemPerformed());
    }

    private void btnTimKiemPerformed() {
        ResultSet rs= null;
        try { 
            model = view.getModel();
            String sqlQuery = "Select * from HOTIEUTHU where maKH + hoTen like ?";
            PreparedStatement ps = quanLyThongTinController.connection.prepareStatement(sqlQuery);
            ps.setString(1, "%" + model.getKey() + "%");
            rs = ps.executeQuery();
            quanLyThongTinController.insertDataToTableView(rs);
            view.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(TimKiemThongTinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
