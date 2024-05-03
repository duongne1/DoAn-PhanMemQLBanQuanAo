/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.ChatLieu;
import entity.KichThuoc;
import entity.MauSac;
import entity.LoaiSP;
import entity.SanPham;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class SanPhamDAO {

    private KichThuocDAO kt_dao = new KichThuocDAO();
    private ChatLieuDAO cl_dao = new ChatLieuDAO();
    private LoaiSPDAO loai_dao = new LoaiSPDAO();
    private MauSacDAO ms_dao = new MauSacDAO();
    private byte[] hinh;

    public SanPham getSanPhamByID(String id) throws Exception {
        String sql = "select * from SanPham where maSP =  '" + id + "'";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    sp.setNhanHieu(rs.getString("nhanHieu"));
                    KichThuoc kt = kt_dao.getKichThuocByID(rs.getString("maKichThuoc"));
                    ChatLieu cl = cl_dao.getChatLieuByID(rs.getString("maChatLieu"));
                    LoaiSP loai = loai_dao.getLoaiSPByID(rs.getString("maLoai"));
                    MauSac ms = ms_dao.getMauSacByID(rs.getString("maMauSac"));
                    sp.setKichThuoc(kt);
                    sp.setChatLieu(cl);
                    sp.setLoaiSP(loai);
                    sp.setMauSac(ms);
                    sp.setSoLuong(rs.getInt("soLuong"));
                    sp.setTrangThai(rs.getString("trangThai"));
                    sp.setGia(rs.getFloat("gia"));
                    Blob blog = rs.getBlob("anh");
                    if (blog != null) {
                        sp.setAnh(blog.getBytes(1, (int) blog.length()));
                    }
                    return sp;
                }
            }
            return null;
        }
    }

    public List<SanPham> finAll() throws Exception {
        String sql = "select * from SanPham order by SanPham.id desc";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    LoaiSP loai = loai_dao.getLoaiSPByID(rs.getString("maLoai"));
                    sp.setLoaiSP(loai);
                    sp.setNhanHieu(rs.getString("nhanHieu"));
                    KichThuoc kt = kt_dao.getKichThuocByID(rs.getString("maKichThuoc"));
                    sp.setKichThuoc(kt);
                    ChatLieu cl = cl_dao.getChatLieuByID(rs.getString("maChatLieu"));
                    sp.setChatLieu(cl);
                    sp.setTrangThai(rs.getString("trangThai"));
                    sp.setGia(rs.getFloat("gia"));
                    list.add(sp);
                }
                return list;
            }
        }
    }

    public List<SanPham> finAll1() throws Exception {
        String sql = "select * from SanPham order by SanPham.id desc";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    LoaiSP loai = loai_dao.getLoaiSPByID(rs.getString("maLoai"));
                    sp.setLoaiSP(loai);
                    sp.setNhanHieu(rs.getString("nhanHieu"));
                    sp.setGia(rs.getFloat("gia"));
                    sp.setSoLuong(rs.getInt("soLuong"));
                    ChatLieu cl = cl_dao.getChatLieuByID(rs.getString("maChatLieu"));
                    sp.setChatLieu(cl);
                    MauSac ms = ms_dao.getMauSacByID(rs.getString("maMauSac"));
                    sp.setMauSac(ms);
                    KichThuoc kt = kt_dao.getKichThuocByID(rs.getString("maKichThuoc"));
                    sp.setKichThuoc(kt);
                    list.add(sp);
                }
                return list;
            }
        }
    }

    public boolean insert(SanPham sp) throws Exception {
        String sql = "INSERT INTO dbo.SanPham(tenSP,nhanHieu,maKichThuoc,maChatLieu,maLoai,maMauSac,soLuong,trangThai,gia,anh)"
                + "values(?,?,?,?,?,?,?,?,?,?)";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, sp.getTenSP());
            stm.setString(2, sp.getNhanHieu());
            stm.setString(3, sp.getKichThuoc().getMaKichThuoc());
            stm.setString(4, sp.getChatLieu().getMaChatLieu());
            stm.setString(5, sp.getLoaiSP().getMaLoai());
            stm.setString(6, sp.getMauSac().getMaMau());
            stm.setInt(7, sp.getSoLuong());
            stm.setString(8, sp.getTrangThai());
            stm.setDouble(9, sp.getGia());

            if (sp.getAnh() != null) {
                Blob hinh = new SerialBlob(sp.getAnh());
                stm.setBlob(10, hinh);
            } else {
                Blob hinh = null;
                stm.setBlob(10, hinh);
            }
            return stm.executeUpdate() > 0;
        }
    }

    public boolean update(SanPham sp) throws Exception {
        String sql = "UPDATE dbo.SanPham"
                + " SET tenSP = ?, nhanHieu = ?,maKichThuoc = ?,maChatLieu = ?,maLoai = ?"
                + ",maMauSac = ?,soLuong = ?,trangThai = ?,gia = ?,anh = ?"
                + " where maSP = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(11, sp.getMaSP());
            stm.setString(1, sp.getTenSP());
            stm.setString(2, sp.getNhanHieu());
            stm.setString(3, sp.getKichThuoc().getMaKichThuoc());
            stm.setString(4, sp.getChatLieu().getMaChatLieu());
            stm.setString(5, sp.getLoaiSP().getMaLoai());
            stm.setString(6, sp.getMauSac().getMaMau());
            stm.setInt(7, sp.getSoLuong());
            stm.setString(8, sp.getTrangThai());
            stm.setDouble(9, sp.getGia());

            if (sp.getAnh() != null) {
                Blob hinh = new SerialBlob(sp.getAnh());
                stm.setBlob(10, hinh);
            } else {
                Blob hinh = null;
                stm.setBlob(10, hinh);
            }
            return stm.executeUpdate() > 0;
        }
    }
    public List<SanPham> getDSSPTheoMaLoai(String maLoai) throws Exception {
        String sql = "select * from SanPham where maLoai= '" + maLoai + "'";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    LoaiSP loai = loai_dao.getLoaiSPByID(rs.getString("maLoai"));
                    sp.setLoaiSP(loai);
                    sp.setNhanHieu(rs.getString("nhanHieu"));
                    sp.setGia(rs.getFloat("gia"));
                    sp.setSoLuong(rs.getInt("soLuong"));
                    ChatLieu cl = cl_dao.getChatLieuByID(rs.getString("maChatLieu"));
                    sp.setChatLieu(cl);
                    MauSac ms = ms_dao.getMauSacByID(rs.getString("maMauSac"));
                    sp.setMauSac(ms);
                    KichThuoc kt = kt_dao.getKichThuocByID(rs.getString("maKichThuoc"));
                    sp.setKichThuoc(kt);
                    list.add(sp);
                }
                return list;
            }
        }
    }

    public List<SanPham> getDSSPTheoChatLieu(String maChatLieu) throws Exception {
        String sql = "select * from SanPham where maChatLieu= '" + maChatLieu + "'";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    LoaiSP loai = loai_dao.getLoaiSPByID(rs.getString("maLoai"));
                    sp.setLoaiSP(loai);
                    sp.setNhanHieu(rs.getString("nhanHieu"));
                    sp.setGia(rs.getFloat("gia"));
                    sp.setSoLuong(rs.getInt("soLuong"));
                    ChatLieu cl = cl_dao.getChatLieuByID(rs.getString("maChatLieu"));
                    sp.setChatLieu(cl);
                    MauSac ms = ms_dao.getMauSacByID(rs.getString("maMauSac"));
                    sp.setMauSac(ms);
                    KichThuoc kt = kt_dao.getKichThuocByID(rs.getString("maKichThuoc"));
                    sp.setKichThuoc(kt);
                    list.add(sp);
                }
                return list;
            }
        }
    }

    public List<SanPham> getDSSPTheoTen(String ten) throws Exception {
        String sql = "select * from SanPham where tenSP like N'%" + ten + "%'\n";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    LoaiSP loai = loai_dao.getLoaiSPByID(rs.getString("maLoai"));
                    sp.setLoaiSP(loai);
                    sp.setNhanHieu(rs.getString("nhanHieu"));
                    sp.setGia(rs.getFloat("gia"));
                    sp.setSoLuong(rs.getInt("soLuong"));
                    ChatLieu cl = cl_dao.getChatLieuByID(rs.getString("maChatLieu"));
                    sp.setChatLieu(cl);
                    MauSac ms = ms_dao.getMauSacByID(rs.getString("maMauSac"));
                    sp.setMauSac(ms);
                    KichThuoc kt = kt_dao.getKichThuocByID(rs.getString("maKichThuoc"));
                    sp.setKichThuoc(kt);
                    list.add(sp);
                }
                return list;
            }
        }
    }

    
    public List<SanPham> getDSSPTheoKichThuoc(String ma) throws Exception {
        String sql = "select * from SanPham where maKichThuoc= '" + ma + "'";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("maSP"));
                    sp.setTenSP(rs.getString("tenSP"));
                    LoaiSP loai = loai_dao.getLoaiSPByID(rs.getString("maLoai"));
                    sp.setLoaiSP(loai);
                    sp.setNhanHieu(rs.getString("nhanHieu"));
                    sp.setGia(rs.getFloat("gia"));
                    sp.setSoLuong(rs.getInt("soLuong"));
                    ChatLieu cl = cl_dao.getChatLieuByID(rs.getString("maChatLieu"));
                    sp.setChatLieu(cl);
                    MauSac ms = ms_dao.getMauSacByID(rs.getString("maMauSac"));
                    sp.setMauSac(ms);
                    KichThuoc kt = kt_dao.getKichThuocByID(rs.getString("maKichThuoc"));
                    sp.setKichThuoc(kt);
                    list.add(sp);
                }
                return list;
            }
        }
    }

}
