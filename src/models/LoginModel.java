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
public class LoginModel {
    private String taiKhoan;
    private String matKhau;

    public LoginModel(String taiKhoan, String matKhau) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "LoginModel{" + "taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + '}';
    }

    public boolean isEmpty() {
        if(taiKhoan.isEmpty()||matKhau.isEmpty()){
            return true;
        } else
            return false;
    }
    
}
