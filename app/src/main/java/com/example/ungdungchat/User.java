package com.example.ungdungchat;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int viTri;
    private String email;
    private String mesage;
    private String hienThi;
    public User() {
    }

    public User(int viTri, String email, String mesage, String hienThi) {
        this.viTri = viTri;
        this.email = email;
        this.mesage = mesage;
        this.hienThi = hienThi;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public String getHienThi() {
        return hienThi;
    }

    public void setHienThi(String hienThi) {
        this.hienThi = hienThi;
    }
}
