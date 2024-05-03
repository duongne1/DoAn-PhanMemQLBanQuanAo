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
public class HoaDon {

    private String maHD;
    private Date ngayLapHD;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private float tongTien;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public HoaDon(String maHD, Date ngayLapHD, NhanVien nhanVien, KhachHang khachHang, float tongTien) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
    }

    public HoaDon(Date ngayLapHD, NhanVien nhanVien, KhachHang khachHang) {
        this.ngayLapHD = ngayLapHD;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
    }

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }



    public HoaDon() {
    }

}
