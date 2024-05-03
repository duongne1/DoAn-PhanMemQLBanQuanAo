/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.jdi.connect.spi.Connection;
import connect.DatabaseHelper;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO {

    private TaiKhoanDAO tk_dao = new TaiKhoanDAO();

    public boolean insert(NhanVien nv) throws Exception {
        String sql = "INSERT INTO dbo.NhanVien(tenNV,gioiTinh,ngaySinh,cmnd,soDT,diaChi,maTK)"
                + "values(?,?,?,?,?,?,?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, nv.getHoTen());
            stm.setBoolean(2, nv.isGioiTinh());

            Date date = new Date(nv.getNgaySinh().getTime());
            stm.setDate(3, date);
            stm.setInt(4, nv.getCmnd());
            stm.setString(5, nv.getSoDT());
            stm.setString(6, nv.getDiaChi());
            stm.setString(7, nv.getTaiKhoan().getMaTK());
            return stm.executeUpdate() > 0;
        }
    }

    public boolean update(NhanVien nv) throws Exception {
        String sql = "UPDATE dbo.NhanVien"
                + " SET tenNV = ?, gioiTinh = ?,ngaySinh = ?,cmnd = ?,soDT = ?"
                + ",diaChi = ?,maTK = ?"
                + " where maNV = ?";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(8, nv.getMaNV());
            stm.setString(1, nv.getHoTen());
            stm.setBoolean(2, nv.isGioiTinh());

            Date date = new Date(nv.getNgaySinh().getTime());
            stm.setDate(3, date);
            stm.setInt(4, nv.getCmnd());
            stm.setString(5, nv.getSoDT());
            stm.setString(6, nv.getDiaChi());
            stm.setString(7, nv.getTaiKhoan().getMaTK());
            return stm.executeUpdate() > 0;
        }
    }

    public List<NhanVien> finAll() throws Exception {
        String sql = "select * from NhanVien order by NhanVien.id desc";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<NhanVien> list = new ArrayList<>();
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("maNV"));
                    nv.setHoTen(rs.getString("tenNV"));
                    nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                    nv.setNgaySinh(rs.getDate("ngaySinh"));
                    nv.setCmnd(rs.getInt("cmnd"));
                    nv.setSoDT(rs.getString("soDT"));
                    nv.setDiaChi(rs.getString("diaChi"));
                    TaiKhoan tk = tk_dao.getTaiKhoanByID(rs.getString("maTK"));
                    nv.setTaiKhoan(tk);
                    list.add(nv);
                }
                return list;
            }
        }
    }

    public NhanVien getNhanVienByID(String id) throws Exception {
        String sql = "select * from NhanVien where maNV =  '" + id + "'";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("maNV"));
                    nv.setHoTen(rs.getString("tenNV"));
                    nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                    nv.setNgaySinh(rs.getDate("ngaySinh"));
                    nv.setCmnd(rs.getInt("cmnd"));
                    nv.setSoDT(rs.getString("soDT"));
                    nv.setDiaChi(rs.getString("diaChi"));
                    TaiKhoan tk = tk_dao.getTaiKhoanByID(rs.getString("maTK"));
                    nv.setTaiKhoan(tk);
                    return nv;
                }

            }
            return null;
        }

    }

    public boolean delete(String maNV) throws Exception {
        String sql = "delete from NhanVien" + " where maNV = ?";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, maNV);
            return stm.executeUpdate() > 0;
        }
    }

    public List<NhanVien> getDSNV() throws Exception {
        String sql = "SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.gioiTinh, NhanVien.ngaySinh, NhanVien.cmnd, "
                + "NhanVien.soDT, NhanVien.diaChi, TaiKhoan.vaiTro\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  TaiKhoan ON NhanVien.maTK = TaiKhoan.maTK  \n"
                + "group by NhanVien.maNV, NhanVien.tenNV, NhanVien.gioiTinh, NhanVien.ngaySinh, NhanVien.cmnd, NhanVien.soDT, "
                + "NhanVien.diaChi, TaiKhoan.vaiTro";

        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<NhanVien> list = new ArrayList<>();
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("maNV"));
                    nv.setHoTen(rs.getString("tenNV"));
                    nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                    nv.setNgaySinh(rs.getDate("ngaySinh"));
                    nv.setCmnd(rs.getInt("cmnd"));
                    nv.setSoDT(rs.getString("soDT"));
                    nv.setDiaChi(rs.getString("diaChi"));
                    TaiKhoan tk = tk_dao.getTaiKhoanByID(rs.getString("maTK"));
                    nv.setTaiKhoan(tk);
                    list.add(nv);
                }
                return list;
            }
        }
    }

    public List<NhanVien> getNhanVienTheoTen(String ten) throws Exception {
        String sql = "SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.gioiTinh, NhanVien.ngaySinh, NhanVien.cmnd, "
                + "NhanVien.soDT, NhanVien.diaChi, TaiKhoan.vaiTro, TaiKhoan.maTK\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  TaiKhoan ON NhanVien.maTK = TaiKhoan.maTK  \n"
                + "where NhanVien.tenNV LIKE N'%" + ten + "%'\n"
                + "group by NhanVien.maNV, NhanVien.tenNV, NhanVien.gioiTinh, NhanVien.ngaySinh, NhanVien.cmnd, NhanVien.soDT, "
                + "NhanVien.diaChi, TaiKhoan.vaiTro, TaiKhoan.maTK";

        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<NhanVien> list = new ArrayList<>();
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("maNV"));
                    nv.setHoTen(rs.getString("tenNV"));
                    nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                    nv.setNgaySinh(rs.getDate("ngaySinh"));
                    nv.setCmnd(rs.getInt("cmnd"));
                    nv.setSoDT(rs.getString("soDT"));
                    nv.setDiaChi(rs.getString("diaChi"));
                    TaiKhoan tk = tk_dao.getTaiKhoanByID(rs.getString("maTK"));
                    nv.setTaiKhoan(tk);
                    list.add(nv);
                }
                return list;
            }
        }
    }
    
    public List<NhanVien> getNhanVienTheoChucVu(String CV) throws Exception {
        String sql = "SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.gioiTinh, NhanVien.ngaySinh, NhanVien.cmnd, "
                + "NhanVien.soDT, NhanVien.diaChi, TaiKhoan.vaiTro, TaiKhoan.maTK\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  TaiKhoan ON NhanVien.maTK = TaiKhoan.maTK  \n"
                + "where TaiKhoan.vaiTro= N'" + CV + "'\n"
                + "group by NhanVien.maNV, NhanVien.tenNV, NhanVien.gioiTinh, NhanVien.ngaySinh, NhanVien.cmnd, NhanVien.soDT, "
                + "NhanVien.diaChi, TaiKhoan.vaiTro, TaiKhoan.maTK";

        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<NhanVien> list = new ArrayList<>();
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("maNV"));
                    nv.setHoTen(rs.getString("tenNV"));
                    nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                    nv.setNgaySinh(rs.getDate("ngaySinh"));
                    nv.setCmnd(rs.getInt("cmnd"));
                    nv.setSoDT(rs.getString("soDT"));
                    nv.setDiaChi(rs.getString("diaChi"));
                    TaiKhoan tk = tk_dao.getTaiKhoanByID(rs.getString("maTK"));
                    nv.setTaiKhoan(tk);
                    list.add(nv);
                }
                return list;
            }
        }
    }

    public NhanVien getNhanVienTheoSDT(String id) throws Exception {
        String sql = "SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.gioiTinh, NhanVien.ngaySinh, NhanVien.cmnd, "
                + "NhanVien.soDT, NhanVien.diaChi, TaiKhoan.vaiTro, TaiKhoan.maTK\n"
                + "FROM     NhanVien INNER JOIN\n"
                + "                  TaiKhoan ON NhanVien.maTK = TaiKhoan.maTK  \n"
                + "where NhanVien.soDT =  '" + id + "'\n"
                + "group by NhanVien.maNV, NhanVien.tenNV, NhanVien.gioiTinh, NhanVien.ngaySinh, NhanVien.cmnd, NhanVien.soDT, "
                + "NhanVien.diaChi, TaiKhoan.vaiTro, TaiKhoan.maTK";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("maNV"));
                    nv.setHoTen(rs.getString("tenNV"));
                    nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                    nv.setNgaySinh(rs.getDate("ngaySinh"));
                    nv.setCmnd(rs.getInt("cmnd"));
                    nv.setSoDT(rs.getString("soDT"));
                    nv.setDiaChi(rs.getString("diaChi"));
                    TaiKhoan tk = tk_dao.getTaiKhoanByID(rs.getString("maTK"));
                    nv.setTaiKhoan(tk);
                    return nv;
                }

            }
            return null;
        }
    }

    public NhanVien getNhanVienTheoMaTK(String id) throws Exception {
        String sql = "SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.maTK\n"
                + "FROM     TaiKhoan INNER JOIN\n"
                + "                  NhanVien ON TaiKhoan.maTK = NhanVien.maTK\n"
                + "where NhanVien.maTK =  '" + id + "'\n"
                + "group by NhanVien.maNV, NhanVien.tenNV, NhanVien.maTK";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("maNV"));
                    nv.setHoTen(rs.getString("tenNV"));
                    TaiKhoan tk = tk_dao.getTaiKhoanByID(rs.getString("maTK"));
                    nv.setTaiKhoan(tk);
                    return nv;
                }

            }
            return null;
        }
    }

    public NhanVien getNhanVienTheoMaDH(String id) throws Exception {
        String sql = "SELECT NhanVien.maNV\n"
                + "FROM     DonDatHang INNER JOIN\n"
                + "                  NhanVien ON DonDatHang.maNV = NhanVien.maNV\n"
                + "where DonDatHang.maDonHang =  '" + id + "'";

        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("maNV"));
                    return nv;
                }

            }
            return null;
        }
    }
}
