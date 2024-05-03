/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.KhachHang;
import entity.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhachHangDAO {

    private TaiKhoanDAO tk_dao = new TaiKhoanDAO();

    public boolean insert(KhachHang kh) throws Exception {
        String sql = "INSERT INTO dbo.KhachHang(tenKH,gioiTinh,soDT,diaChi)"
                + "values(?,?,?,?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, kh.getHoTen());
            stm.setBoolean(2, kh.isGioiTinh());
            stm.setString(3, kh.getSoDT());
            stm.setString(4, kh.getDiaChi());
            return stm.executeUpdate() > 0;
        }
    }

    public boolean update(KhachHang kh) throws Exception {
        String sql = "UPDATE dbo.KhachHang"
                + " SET tenKH = ?, gioiTinh = ?,soDT = ?"
                + ",diaChi = ?"
                + " where maKH = ?";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(5, kh.getMaKH());
            stm.setString(1, kh.getHoTen());
            stm.setBoolean(2, kh.isGioiTinh());
            stm.setString(3, kh.getSoDT());
            stm.setString(4, kh.getDiaChi());
            return stm.executeUpdate() > 0;
        }
    }

    public List<KhachHang> finAll() throws Exception {
        String sql = "select * from KhachHang order by KhachHang.id desc";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<KhachHang> list = new ArrayList<>();
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getString("maKH"));
                    kh.setHoTen(rs.getString("tenKH"));
                    kh.setGioiTinh(rs.getBoolean("gioiTinh"));
                    kh.setSoDT(rs.getString("soDT"));
                    kh.setDiaChi(rs.getString("diaChi"));
                    list.add(kh);
                }
                return list;
            }
        }
    }

    public KhachHang getKhachHangByID(String id) throws Exception {
        String sql = "select * from KhachHang where maKH =  '" + id + "'";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getString("maKH"));
                    kh.setHoTen(rs.getString("tenKH"));
                    kh.setGioiTinh(rs.getBoolean("gioiTinh"));
                    kh.setSoDT(rs.getString("soDT"));
                    kh.setDiaChi(rs.getString("diaChi"));
                    return kh;
                }

            }
            return null;
        }
    }

    public boolean delete(String maKH) throws Exception {
        String sql = "delete from KhachHang" + " where maKH = ?";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, maKH);
            return stm.executeUpdate() > 0;
        }
    }

    public KhachHang getKhachHangTheoSDT(String sdt) throws Exception {
        String sql = "select * from KhachHang where soDT = '" + sdt + "'";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getString("maKH"));
                    kh.setHoTen(rs.getString("tenKH"));
                    kh.setGioiTinh(rs.getBoolean("gioiTinh"));
                    kh.setSoDT(rs.getString("soDT"));
                    kh.setDiaChi(rs.getString("diaChi"));
                    return kh;
                }
                return null;
            }
        }
    }

    public KhachHang getTop1KH() throws Exception {
        String sql = "select top 1 maKH,ID from KhachHang\n"
                + "order by ID desc";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    String maKH = rs.getString("maKH");
                    KhachHang kh = new KhachHang(maKH);
                    return kh;
                }

            }
            return null;
        }
    }

    public KhachHang getKhachHangTheoMaDH(String id) throws Exception {
        String sql = "SELECT KhachHang.maKH\n"
                + "FROM     DonDatHang INNER JOIN\n"
                + "                  KhachHang ON DonDatHang.maKH = KhachHang.maKH\n"
                + "where DonDatHang.maDonHang =  '" + id + "'";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getString("maKH"));
                    return kh;
                }
                return null;
            }
        }
    }
    
     public List<KhachHang> getKhachHangTheoTen(String ten) throws Exception {
        String sql = "SELECT KhachHang.maKH, KhachHang.tenKH, KhachHang.gioiTinh,KhachHang.soDT, KhachHang.diaChi\n"
                + "FROM     KhachHang \n"
                + "where KhachHang.tenKH LIKE N'%" + ten + "%'\n";

        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<KhachHang> list = new ArrayList<>();
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getString("maKH"));
                    kh.setHoTen(rs.getString("tenKH"));
                    kh.setGioiTinh(rs.getBoolean("gioiTinh"));
                    kh.setSoDT(rs.getString("soDT"));
                    kh.setDiaChi(rs.getString("diaChi"));
                    list.add(kh);
                }
                return list;
            }
        }
    }
}
