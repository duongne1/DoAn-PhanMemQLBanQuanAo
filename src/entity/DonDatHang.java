/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class DonDatHang {

    private String maDH;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private Date ngayDatHang;
    private float tongTien;

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(Date ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public DonDatHang(String maDH, KhachHang khachHang, NhanVien nhanVien, Date ngayDatHang, float tongTien) {
        this.maDH = maDH;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayDatHang = ngayDatHang;
        this.tongTien = tongTien;
    }


    public DonDatHang() {
    }

    public DonDatHang(String maDH) {
        this.maDH = maDH;
    }

    
}
