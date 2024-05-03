/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKeDoanhThuDAO {

    private NhanVienDAO nv_dao = new NhanVienDAO();
    private KhachHangDAO kh_dao = new KhachHangDAO();

    public List<HoaDon> ThongKeDoanhThuTheoNam(int nam) throws Exception {
        String sql = "SELECT HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD,"
                + "tongTien=sum( ChiTietHD.soLuong* SanPham.gia)\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  HoaDon ON NhanVien.maNV = HoaDon.maNV INNER JOIN\n"
                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH INNER JOIN\n"
                + "                  ChiTietHD ON HoaDon.maHD = ChiTietHD.maHD INNER JOIN\n"
                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP\n"
                + "           where Year(HoaDon.ngayLapHD)= '" + nam + "'\n"
                + "	group by  HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<HoaDon> list = new ArrayList<>();
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(rs.getString("maHD"));
                    NhanVien nv = nv_dao.getNhanVienByID(rs.getString("maNV"));
                    hd.setNhanVien(nv);
                    KhachHang kh = kh_dao.getKhachHangByID(rs.getString("maKH"));
                    hd.setKhachHang(kh);
                    hd.setNgayLapHD(rs.getDate("ngayLapHD"));
                    hd.setTongTien(rs.getFloat("tongTien"));
                    list.add(hd);
                }
                return list;
            }
        }
    }

    public List<HoaDon> ThongKeDoanhThuTheoThangNam(String thang, String nam) throws Exception {
        String sql = "SELECT HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD,"
                + "tongTien=sum( ChiTietHD.soLuong* SanPham.gia)\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  HoaDon ON NhanVien.maNV = HoaDon.maNV INNER JOIN\n"
                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH INNER JOIN\n"
                + "                  ChiTietHD ON HoaDon.maHD = ChiTietHD.maHD INNER JOIN\n"
                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP\n"
                + "           where Month(HoaDon.ngayLapHD)= '" + thang + "' and Year(HoaDon.ngayLapHD)='" + nam + "'\n"
                + "	group by  HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<HoaDon> list = new ArrayList<>();
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(rs.getString("maHD"));
                    NhanVien nv = nv_dao.getNhanVienByID(rs.getString("maNV"));
                    hd.setNhanVien(nv);
                    KhachHang kh = kh_dao.getKhachHangByID(rs.getString("maKH"));
                    hd.setKhachHang(kh);
                    hd.setNgayLapHD(rs.getDate("ngayLapHD"));
                    hd.setTongTien(rs.getFloat("tongTien"));
                    list.add(hd);
                }
                return list;
            }
        }
    }

//    public List<HoaDon> ThongKeDoanhThuTheoNgay(int ngay) throws Exception {
//        String sql = "SELECT HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD,"
//                + "tongTien=sum( ChiTietHD.soLuong* SanPham.gia)\n"
//                + "FROM     NhanVien INNER JOIN\n"
//                + "                  HoaDon ON NhanVien.maNV = HoaDon.maNV INNER JOIN\n"
//                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH INNER JOIN\n"
//                + "                  ChiTietHD ON HoaDon.maHD = ChiTietHD.maHD INNER JOIN\n"
//                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP\n"
//                + "           where Day(HoaDon.ngayLapHD)= '" + ngay + "'\n"
//                + "	group by  HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD";
//
//        try (
//                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
//            try ( ResultSet rs = stm.executeQuery();) {
//                List<HoaDon> list = new ArrayList<>();
//                while (rs.next()) {
//                    HoaDon hd = new HoaDon();
//                    hd.setMaHD(rs.getString("maHD"));
//                    NhanVien nv = nv_dao.getNhanVienByID(rs.getString("maNV"));
//                    hd.setNhanVien(nv);
//                    KhachHang kh = kh_dao.getKhachHangByID(rs.getString("maKH"));
//                    hd.setKhachHang(kh);
//                    hd.setNgayLapHD(rs.getDate("ngayLapHD"));
//                    hd.setTongTien(rs.getFloat("tongTien"));
//                    list.add(hd);
//                }
//                return list;
//            }
//        }
//    }

    public List<HoaDon> ThongKeDoanhThuTheoNgayThangNam(String ngay, String thang, String nam) throws Exception {
        String sql = "SELECT HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD,"
                + "HoaDon.tongTien\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  HoaDon ON NhanVien.maNV = HoaDon.maNV INNER JOIN\n"
                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH INNER JOIN\n"
                + "                  ChiTietHD ON HoaDon.maHD = ChiTietHD.maHD INNER JOIN\n"
                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP\n"
                + "           where Day(HoaDon.ngayLapHD)='" + ngay + "' and Month(HoaDon.ngayLapHD)='" + thang + "'"
                + " and Year(HoaDon.ngayLapHD)='" + nam + "'\n"
                + "	group by  HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD, HoaDon.tongTien";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<HoaDon> list = new ArrayList<>();
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(rs.getString("maHD"));
                    NhanVien nv = nv_dao.getNhanVienByID(rs.getString("maNV"));
                    hd.setNhanVien(nv);
                    KhachHang kh = kh_dao.getKhachHangByID(rs.getString("maKH"));
                    hd.setKhachHang(kh);
                    hd.setNgayLapHD(rs.getDate("ngayLapHD"));
                    hd.setTongTien(rs.getFloat("tongTien"));
                    list.add(hd);
                }
                return list;
            }
        }
    }
}
