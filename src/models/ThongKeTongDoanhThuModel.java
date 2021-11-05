/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author PhamDoanHieu
 */
public class ThongKeTongDoanhThuModel {
    private String maThang;
    private String maKH;
    private String maHD;
    private int tien;
    private double tongDoanhThu;

    public ThongKeTongDoanhThuModel(String maThang, String maKH, String maHD, int tien) {
        this.maThang = maThang;
        this.maKH = maKH;
        this.maHD = maHD;
        this.tien = tien;
    }

    public String getMaThang() {
        return maThang;
    }

    public void setMaThang(String maThang) {
        this.maThang = maThang;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }
    
    public String[] toStringAraay(){
        String[] model ={maKH, maThang, maHD, ""+tien};
        return model;
    }

    @Override
    public String toString() {
        return "ThongKeTongDoanhThuModel{" + "maThang=" + maThang + ", maKH=" + maKH + ", maHD=" + maHD + ", tien=" + tien + '}';
    }
    
    
    
}
