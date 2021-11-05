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
public class ChiSoDienModel {
    private String maKH;
    private String maThang;
    private int chiSoCu;
    private int chiSoMoi;

    public int getChiSoCu() {
        return chiSoCu;
    }

    public void setChiSoCu(int chiSoCu) {
        this.chiSoCu = chiSoCu;
    }

    public int getChiSoMoi() {
        return chiSoMoi;
    }

    public void setChiSoMoi(int chiSoMoi) {
        this.chiSoMoi = chiSoMoi;
    }

    public ChiSoDienModel(String maKH, String maThang, int chiSoCu, int chiSoMoi) {
        this.maKH = maKH;
        this.maThang = maThang;
        this.chiSoCu = chiSoCu;
        this.chiSoMoi = chiSoMoi;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaThang() {
        return maThang;
    }

    public void setMaThang(String maThang) {
        this.maThang = maThang;
    }

    public String[] toStringArray()
    {
        String [] stringArray= {maKH, maThang,String.valueOf(chiSoCu), String.valueOf(chiSoMoi)};
        return stringArray;
    }
}
