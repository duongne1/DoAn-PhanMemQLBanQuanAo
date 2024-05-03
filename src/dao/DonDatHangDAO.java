/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.DonDatHang;
import entity.KhachHang;

import entity.NhanVien;
import entity.SanPham;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DonDatHangDAO {

    static DefaultTableModel tblModel;

    private NhanVienDAO nv_dao = new NhanVienDAO();
    private KhachHangDAO kh_dao = new KhachHangDAO();

    public List<DonDatHang> finAll() throws Exception {
        String sql = "SELECT DonDatHang.maDonHang, NhanVien.maNV , KhachHang.maKH, "
                + "DonDatHang.ngayDatHang,DonDatHang.tongTien, DonDatHang.id\n"
                + "FROM     ChiTietDonHang INNER JOIN\n"
                + "                  DonDatHang ON ChiTietDonHang.maDonHang = DonDatHang.maDonHang INNER JOIN\n"
                + "                  NhanVien ON DonDatHang.maNV = NhanVien.maNV INNER JOIN\n"
                + "                  KhachHang ON DonDatHang.maKH = KhachHang.maKH INNER JOIN\n"
                + "                  SanPham ON ChiTietDonHang.maSP = SanPham.maSP\n"
                + "group by  DonDatHang.maDonHang, NhanVien.maNV , KhachHang.maKH, DonDatHang.ngayDatHang,DonDatHang.tongTien, DonDatHang.id\n"
                + "order by DonDatHang.id desc";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<DonDatHang> list = new ArrayList<>();
                while (rs.next()) {
                    DonDatHang dh = new DonDatHang();
                    dh.setMaDH(rs.getString("maDonHang"));
                    NhanVien nv = nv_dao.getNhanVienByID(rs.getString("maNV"));
                    dh.setNhanVien(nv);
                    KhachHang kh = kh_dao.getKhachHangByID(rs.getString("maKH"));
                    dh.setKhachHang(kh);
                    dh.setNgayDatHang(rs.getDate("ngayDatHang"));
                    dh.setTongTien(rs.getFloat("tongTien"));
                    list.add(dh);
                }
                return list;
            }
        }
    }

    public DonDatHang getDonDatHangByID(String id) throws Exception {
        String sql = "select * from DonDatHang where maDonHang =  '" + id + "'";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    DonDatHang dh = new DonDatHang();
                    dh.setMaDH(rs.getString("maDonHang"));
                    NhanVien nv = nv_dao.getNhanVienByID(rs.getString("maNV"));
                    dh.setNhanVien(nv);
                    KhachHang kh = kh_dao.getKhachHangByID(rs.getString("maKH"));
                    dh.setKhachHang(kh);
                    dh.setNgayDatHang(rs.getDate("ngayDatHang"));
                    dh.setTongTien(rs.getFloat("tongTien"));
                    return dh;
                }
            }
            return null;
        }
    }

    public boolean insert(DonDatHang dh) throws Exception {
        String sql = "INSERT INTO dbo.DonDatHang(ngayDatHang,maKH,maNV,tongTien)"
                + "values(?,?,?,?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            Date date = new Date(dh.getNgayDatHang().getTime());
            stm.setDate(1, date);
            stm.setString(2, dh.getKhachHang().getMaKH());
            stm.setString(3, dh.getNhanVien().getMaNV());
            stm.setFloat(4, dh.getTongTien());
            return stm.executeUpdate() > 0;
        }
    }
    
        public boolean update(DonDatHang dh) throws Exception {
        String sql = "UPDATE dbo.DonDatHang"
                + " SET ngayDatHang = ?, maKH = ?,maNV = ?,tongTien = ?"
                + " where maDonHang = ?";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(5, dh.getMaDH());
            Date date = new Date(dh.getNgayDatHang().getTime());
            stm.setDate(1, date);
            stm.setString(2, dh.getKhachHang().getMaKH());
            stm.setString(3, dh.getNhanVien().getMaNV());
            stm.setFloat(4, dh.getTongTien());
            return stm.executeUpdate() > 0;
        }
    }

    public DonDatHang getTop1DonHang() throws Exception {
        String sql = "select top 1 maDonHang,ID from DonDatHang\n"
                + "order by ID desc";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    String madh = rs.getString("maDonHang");
                    DonDatHang dh = new DonDatHang(madh);
                    return dh;
                }

            }
            return null;
        }
    }
    
        public boolean delete(String maDH) throws Exception {
        String sql = "delete from DonDatHang" + " where maDonHang = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, maDH);
            return stm.executeUpdate() > 0;
        }
    }
        
            public DonDatHang getDSDHTheoSDTKH(String sdt) throws Exception {
        String sql = "SELECT DonDatHang.maDonHang, NhanVien.maNV, KhachHang.maKH, DonDatHang.ngayDatHang, "
                + "DonDatHang.tongTien, DonDatHang.id\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  DonDatHang ON NhanVien.maNV = DonDatHang.maNV INNER JOIN\n"
                + "                  ChiTietDonHang ON DonDatHang.maDonHang = ChiTietDonHang.maDonHang INNER JOIN\n"
                + "                  SanPham ON ChiTietDonHang.maSP = SanPham.maSP INNER JOIN\n"
                + "                  KhachHang ON DonDatHang.maKH = KhachHang.maKH\n"
                + "where KhachHang.soDT=  '" + sdt + "'\n"
                + "group by DonDatHang.maDonHang, NhanVien.maNV, KhachHang.maKH, DonDatHang.ngayDatHang,DonDatHang.tongTien, DonDatHang.id\n"
                + "order by DonDatHang.id desc";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    DonDatHang dh = new DonDatHang();
                    dh.setMaDH(rs.getString("maDonHang"));
                    NhanVien nv = nv_dao.getNhanVienByID(rs.getString("maNV"));
                    dh.setNhanVien(nv);
                    KhachHang kh = kh_dao.getKhachHangByID(rs.getString("maKH"));
                    dh.setKhachHang(kh);
                    dh.setNgayDatHang(rs.getDate("ngayDatHang"));
                    dh.setTongTien(rs.getFloat("tongTien"));
                    return dh;
                } 
            }
            return null;
        }
    }
}
