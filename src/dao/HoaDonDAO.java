/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HoaDonDAO {

    private NhanVienDAO nv_dao = new NhanVienDAO();
    private KhachHangDAO kh_dao = new KhachHangDAO();

    public boolean insert(HoaDon hd) throws Exception {
        String sql = "INSERT INTO dbo.HoaDon(ngayLapHD,maNV,maKH,tongTien)"
                + "values(?,?,?,?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            Date date = new Date(hd.getNgayLapHD().getTime());
            stm.setDate(1, date);
            stm.setString(2, hd.getNhanVien().getMaNV());
            stm.setString(3, hd.getKhachHang().getMaKH());
            stm.setFloat(4, hd.getTongTien());
            return stm.executeUpdate() > 0;
        }
    }

    public List<HoaDon> finAll() throws Exception {
        String sql = "SELECT HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD,"
                + "HoaDon.tongTien, HoaDon.id\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  HoaDon ON NhanVien.maNV = HoaDon.maNV INNER JOIN\n"
                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH INNER JOIN\n"
                + "                  ChiTietHD ON HoaDon.maHD = ChiTietHD.maHD INNER JOIN\n"
                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP\n"
                + "	group by  HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD,HoaDon.tongTien, HoaDon.id\n"
                + "     order by HoaDon.id desc";

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

    public HoaDon getHoaDonByID(String id) throws Exception {
        String sql = "select * from HoaDon where maHD =  '" + id + "'";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(rs.getString("maHD"));
                    hd.setNgayLapHD(rs.getDate("ngayLapHD"));
                    NhanVien nv = nv_dao.getNhanVienByID(rs.getString("maNV"));
                    hd.setNhanVien(nv);
                    KhachHang kh = kh_dao.getKhachHangByID(rs.getString("maKH"));
                    hd.setKhachHang(kh);
                    return hd;
                }

            }
            return null;
        }
    }

    public HoaDon getTop1HD() throws Exception {
        String sql = "select top 1 maHD,ID from HoaDon\n"
                + "order by ID desc";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    String mahd = rs.getString("maHD");
                    HoaDon hd = new HoaDon(mahd);
                    return hd;
                }

            }
            return null;
        }
    }

    public boolean delete(String maHD) throws Exception {
        String sql = "delete from HoaDon" + " where maHD = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, maHD);
            return stm.executeUpdate() > 0;
        }
    }

    public List<HoaDon> getDSHDTheoSDTKH(String sdt) throws Exception {
        String sql = "SELECT HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD, "
                + "HoaDon.tongTien, HoaDon.id\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  HoaDon ON NhanVien.maNV = HoaDon.maNV INNER JOIN\n"
                + "                  ChiTietHD ON HoaDon.maHD = ChiTietHD.maHD INNER JOIN\n"
                + "                  SanPham ON ChiTietHD.maSP = SanPham.maSP INNER JOIN\n"
                + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH\n"
                + "where KhachHang.soDT=  '" + sdt + "'\n"
                + "group by HoaDon.maHD, NhanVien.maNV, KhachHang.maKH, HoaDon.ngayLapHD,HoaDon.tongTien, HoaDon.id\n"
                + "order by HoaDon.id desc";

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
