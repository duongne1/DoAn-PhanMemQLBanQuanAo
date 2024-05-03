/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKeSanPhamDAO {

    private SanPhamDAO sp_dao = new SanPhamDAO();
    private HoaDonDAO hd_dao = new HoaDonDAO();

    public List<SanPham> thongKeSPTheoNam(int nam) throws Exception {
        String sql = "SELECT top 5  SanPham.maSP, SanPham.tenSP, soluongban=sum(ChiTietHD.soLuong)\n"
                + "FROM     ChiTietHD INNER JOIN\n"
                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP INNER JOIN\n"
                + "				  HoaDon ON ChiTietHD.maHD = HoaDon.maHD\n"
                + "           where Year(HoaDon.ngayLapHD)= '" + nam + "'\n"
                + "group by SanPham.maSP, SanPham.tenSP\n"
                + "ORDER BY soluongban DESC";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    sp.setSoLuong(rs.getInt("soluongban"));
                    list.add(sp);
                }
                return list;
            }
        }
    }

    public List<SanPham> thongKeSPTheoThangNam(String thang, String nam) throws Exception {
        String sql = "SELECT top 5  SanPham.maSP, SanPham.tenSP, soluongban=sum(ChiTietHD.soLuong)\n"
                + "FROM     ChiTietHD INNER JOIN\n"
                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP INNER JOIN\n"
                + "				  HoaDon ON ChiTietHD.maHD = HoaDon.maHD\n"
                + "           where Month(HoaDon.ngayLapHD)= '" + thang + "' and Year(HoaDon.ngayLapHD)='" + nam + "'\n"
                + "group by SanPham.maSP, SanPham.tenSP\n"
                + "ORDER BY soluongban DESC";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    sp.setSoLuong(rs.getInt("soluongban"));
                    list.add(sp);
                }
                return list;
            }
        }
    }

    public List<SanPham> thongKeSPTheoNgayThangNam(String ngay, String thang, String nam) throws Exception {
        String sql = "SELECT top 5 SanPham.maSP, SanPham.tenSP, soluongban=sum(ChiTietHD.soLuong)\n"
                + "FROM     ChiTietHD INNER JOIN\n"
                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP INNER JOIN\n"
                + "				  HoaDon ON ChiTietHD.maHD = HoaDon.maHD\n"
                + "           where Day(HoaDon.ngayLapHD)='" + ngay + "' and Month(HoaDon.ngayLapHD)='" + thang + "'"
                + " and Year(HoaDon.ngayLapHD)='" + nam + "'\n"
                + "group by SanPham.maSP, SanPham.tenSP\n"
                + "ORDER BY soluongban DESC";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    sp.setSoLuong(rs.getInt("soluongban"));
                    list.add(sp);
                }
                return list;
            }
        }
    }

    public List<SanPham> thongKeSP() throws Exception {
        String sql = "SELECT SanPham.maSP, SanPham.tenSP, soluongban=sum(ChiTietHD.soLuong)\n"
                + "FROM     ChiTietHD INNER JOIN\n"
                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP INNER JOIN\n"
                + "				  HoaDon ON ChiTietHD.maHD = HoaDon.maHD\n"
                + "group by SanPham.maSP, SanPham.tenSP\n"
                + "ORDER BY soluongban DESC";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    sp.setSoLuong(rs.getInt("soluongban"));
                    list.add(sp);
                }
                return list;
            }
        }
    }
}
