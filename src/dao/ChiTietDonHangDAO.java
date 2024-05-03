/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.ChiTietDonHang;
import entity.DonDatHang;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ChiTietDonHangDAO {

    private DonDatHangDAO dh_dao = new DonDatHangDAO();
    private SanPhamDAO sp_dao = new SanPhamDAO();

    public List<ChiTietDonHang> finAll() throws Exception {
        String sql = "SELECT ChiTietDonHang.maDonHang, SanPham.maSP, SanPham.tenSP, SanPham.maChatLieu, SanPham.maMauSac,"
                + " SanPham.maKichThuoc, ChiTietDonHang.soLuong, SanPham.gia, tongTien=SUM(ChiTietDonHang.soLuong * SanPham.gia) \n"
                + "FROM     ChiTietDonHang INNER JOIN\n"
                + "                  DonDatHang ON ChiTietDonHang.maDonHang = DonDatHang.maDonHang INNER JOIN\n"
                + "                  SanPham ON ChiTietDonHang.maSP = SanPham.maSP\n"
                + "Group by ChiTietDonHang.maDonHang, SanPham.maSP, SanPham.tenSP, SanPham.maChatLieu, "
                + "SanPham.maMauSac, SanPham.maKichThuoc, ChiTietDonHang.soLuong, SanPham.gia";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<ChiTietDonHang> list = new ArrayList<>();
                while (rs.next()) {
                    ChiTietDonHang ctdh = new ChiTietDonHang();
                    DonDatHang dh = dh_dao.getDonDatHangByID(rs.getString("maDonHang"));
                    ctdh.setDonHang(dh);
                    SanPham sp = sp_dao.getSanPhamByID(rs.getString("maSP"));
                    ctdh.setSanPham(sp);
                    ctdh.setSoLuong(rs.getInt("soLuong"));
                    ctdh.setTongTien(rs.getFloat("tongTien"));
                    list.add(ctdh);
                }
                return list;
            }
        }
    }

    public boolean update(ChiTietDonHang ctdh) throws Exception {
        String sql = "UPDATE dbo.ChiTietDonHang"
                + " SET soLuong = ?"
                + " where maDonHang = ?";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(2, ctdh.getDonHang().getMaDH());
            stm.setInt(1, ctdh.getSoLuong());
            return stm.executeUpdate() > 0;
        }
    }

    public boolean insert(ChiTietDonHang ctdh) throws Exception {
        String sql = "INSERT INTO dbo.ChiTietDonHang(maDonHang,maSP,soLuong)"
                + "values(?,?,?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try {
                stm.setString(1, ctdh.getDonHang().getMaDH());
                stm.setString(2, ctdh.getSanPham().getMaSP());
                stm.setInt(3, ctdh.getSoLuong());
                return stm.executeUpdate() > 0;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public List<ChiTietDonHang> getDSCTDHTheoDH(String maDH) throws Exception {
        String sql = "SELECT ChiTietDonHang.maSP, ChiTietDonHang.soLuong\n"
                + "FROM     ChiTietDonHang INNER JOIN\n"
                + "                  DonDatHang ON ChiTietDonHang.maDonHang = DonDatHang.maDonHang\n"
                + "where DonDatHang.maDonHang =  '" + maDH + "'";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<ChiTietDonHang> list = new ArrayList<>();
                while (rs.next()) {
                    ChiTietDonHang ctdh = new ChiTietDonHang();
                    SanPham sp = sp_dao.getSanPhamByID(rs.getString("maSP"));
                    ctdh.setSanPham(sp);
                    ctdh.setSoLuong(rs.getInt("soLuong"));
                    list.add(ctdh);
                }
                return list;
            }
        }
    }

    public boolean delete(String maDH) throws Exception {
        String sql = "delete from ChiTietDonHang" + " where maDonHang = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, maDH);
            return stm.executeUpdate() > 0;
        }
    }
}
