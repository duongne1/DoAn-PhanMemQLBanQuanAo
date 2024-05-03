/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Blob;
import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class SanPham {

    private String maSP, tenSP, nhanHieu;
    private KichThuoc kichThuoc;
    private ChatLieu chatLieu;
    private LoaiSP loaiSP;
    private MauSac mauSac;
    private int soLuong;
    private String trangThai;
    private double gia;
    private byte[] anh;

    public SanPham(SanPham sanPham) {
      
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNhanHieu() {
        return nhanHieu;
    }

    public void setNhanHieu(String nhanHieu) {
        this.nhanHieu = nhanHieu;
    }

    public KichThuoc getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(KichThuoc kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public LoaiSP getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(LoaiSP loaiSP) {
        this.loaiSP = loaiSP;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, String nhanHieu, KichThuoc kichThuoc, ChatLieu chatLieu, LoaiSP loaiSP, MauSac mauSac, int soLuong, String trangThai, double gia, byte[] anh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.nhanHieu = nhanHieu;
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.loaiSP = loaiSP;
        this.mauSac = mauSac;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.gia = gia;
        this.anh = anh;
    }

    @Override
    public String toString() {
        DecimalFormat fmt = new DecimalFormat("###,###,###VND");
        return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ",nhanHieu=" + nhanHieu + ", kichThuoc=" + kichThuoc + ","
                + "chatLieu=" + chatLieu + ", loaiSP=" + loaiSP + ", mauSac=" + mauSac + ", soLuong=" + soLuong + ","
                + " trangThai=" + trangThai + ", loaiSP=" + fmt.format(gia) + ", anh=" + anh + "]";
    }

}
