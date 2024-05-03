/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
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
public class ThongKeKhachHangDAO {

    private HoaDonDAO hd_dao = new HoaDonDAO();

    public List<KhachHang> thongKeKHAll() throws Exception {
        String sql = "SELECT HoaDon.maKH, KhachHang.tenKH, count(KhachHang.tenKH) AS soLanMua\n"
                + ",tongTien=sum(HoaDon.tongTien), KhachHang.id\n"
                + "from HoaDon INNER JOIN"
                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH\n"
                + "group by HoaDon.maKH,KhachHang.tenKH, KhachHang.id\n"
                + "ORDER BY  KhachHang.id ASC";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<KhachHang> list = new ArrayList<>();
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getString("maKH"));
                    kh.setHoTen(rs.getString("tenKH"));
                    kh.setSoDT(rs.getString("soLanMua"));
                    kh.setDiaChi(rs.getString("tongTien"));
                    list.add(kh);
                }
                return list;
            }
        }
    }

    public List<KhachHang> thongKeKHTheoNam(int nam) throws Exception {
        String sql = "SELECT HoaDon.maKH, KhachHang.tenKH, count(KhachHang.tenKH) AS soLanMua\n"
                + ",tongTien=sum(HoaDon.tongTien), KhachHang.id\n"
                + "from HoaDon INNER JOIN"
                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH\n"
                + " where Year(HoaDon.ngayLapHD)= '" + nam + "'\n"
                + "group by HoaDon.maKH,KhachHang.tenKH, KhachHang.id\n"
                + "ORDER BY  tongTien DESC";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<KhachHang> list = new ArrayList<>();
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getString("maKH"));
                    kh.setHoTen(rs.getString("tenKH"));
                    kh.setSoDT(rs.getString("soLanMua"));
                    kh.setDiaChi(rs.getString("tongTien"));
                    list.add(kh);
                }
                return list;
            }
        }
    }

    public List<KhachHang> thongKeKHTheoThangNam(String thang, String nam) throws Exception {
        String sql = "SELECT HoaDon.maKH, KhachHang.tenKH, count(KhachHang.tenKH) AS soLanMua\n"
                + ",tongTien=sum(HoaDon.tongTien), KhachHang.id\n"
                + "from HoaDon INNER JOIN"
                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH\n"
                + "           where Month(HoaDon.ngayLapHD)= '" + thang + "' and Year(HoaDon.ngayLapHD)='" + nam + "'\n"
                + "group by HoaDon.maKH,KhachHang.tenKH, KhachHang.id\n"
                + "ORDER BY  tongTien DESC";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<KhachHang> list = new ArrayList<>();
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getString("maKH"));
                    kh.setHoTen(rs.getString("tenKH"));
                    kh.setSoDT(rs.getString("soLanMua"));
                    kh.setDiaChi(rs.getString("tongTien"));
                    list.add(kh);
                }
                return list;
            }
        }
    }

    public List<KhachHang> thongKeKHTheoNgayThangNam(String ngay, String thang, String nam) throws Exception {
        String sql = "SELECT HoaDon.maKH, KhachHang.tenKH, count(KhachHang.tenKH) AS soLanMua\n"
                + ",tongTien=sum(HoaDon.tongTien), KhachHang.id\n"
                + "from HoaDon INNER JOIN"
                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH\n"
                + "           where Day(HoaDon.ngayLapHD)='" + ngay + "' and Month(HoaDon.ngayLapHD)='" + thang + "'"
                + " and Year(HoaDon.ngayLapHD)='" + nam + "'\n"
                + "group by HoaDon.maKH,KhachHang.tenKH, KhachHang.id\n"
                + "ORDER BY  tongTien DESC";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<KhachHang> list = new ArrayList<>();
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getString("maKH"));
                    kh.setHoTen(rs.getString("tenKH"));
                    kh.setSoDT(rs.getString("soLanMua"));
                    kh.setDiaChi(rs.getString("tongTien"));
                    list.add(kh);
                }
                return list;
            }
        }
    }
}
