/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class ChiTietDonHang {
    private DonDatHang donHang;
    private SanPham sanPham;
    private int soLuong;
    private float tongTien;

    public DonDatHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonDatHang donHang) {
        this.donHang = donHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public ChiTietDonHang(DonDatHang donHang, SanPham sanPham, int soLuong, float tongTien) {
        this.donHang = donHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }



    public ChiTietDonHang() {
    }
    
}
