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
public class HoaDonModel {
        private String maKH;
        private String maHoaDon;
        private String loaiDien;
        private String hoten;
        private int luongDienTieuThu;
        private int thanhTien;

    public HoaDonModel() {
    }
        
    public HoaDonModel(String maKH, String hoten, String maHoaDon, String loaiDien, int luongDienTieuThu, int thanhTien) {
        this.maKH = maKH;
        this.maHoaDon = maHoaDon;
        this.hoten = hoten;
        this.loaiDien = loaiDien;
        this.luongDienTieuThu = luongDienTieuThu;
        this.thanhTien = thanhTien;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getLoaiDien() {
        return loaiDien;
    }

    public void setLoaiDien(String loaiDien) {
        this.loaiDien = loaiDien;
    }

    public int getLuongDienTieuThu() {
        return luongDienTieuThu;
    }

    public void setLuongDienTieuThu(int luongDienTieuThu) {
        this.luongDienTieuThu = luongDienTieuThu;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    
    
    public String[] toStringArray(){
        String[] arr = {maHoaDon,hoten,maKH,loaiDien,""+luongDienTieuThu,""+thanhTien};
        return arr;
    }

    @Override
    public String toString() {
        return "HoaDonModel{" + "maKH=" + maKH + ", maHoaDon=" + maHoaDon + ", loaiDien=" + loaiDien + ", luongDienTieuThu=" + luongDienTieuThu + ", thanhTien=" + thanhTien + '}';
    }
        
        
}
