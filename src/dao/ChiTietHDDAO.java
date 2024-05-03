/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.ChiTietDonHang;
import entity.ChiTietHD;
import entity.DonDatHang;
import entity.HoaDon;
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
public class ChiTietHDDAO {

    private DonDatHangDAO dh_dao = new DonDatHangDAO();
    private HoaDonDAO hd_dao = new HoaDonDAO();
    private SanPhamDAO sp_dao = new SanPhamDAO();

    public boolean insert(ChiTietHD cthd) throws Exception {
        String sql = "INSERT INTO dbo.ChiTietHD(maHD,maSP,soLuong)"
                + "values(?,?,?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, cthd.getHoaDon().getMaHD());
            stm.setString(2, cthd.getSanPham().getMaSP());
            stm.setInt(3, cthd.getSoLuong());
            return stm.executeUpdate() > 0;
        }
    }

    public List<ChiTietHD> getCTHDTheoMaDH(String maHD) throws Exception {
        String sql = "SELECT ChiTietHD.maSP, ChiTietHD.soLuong\n"
                + "FROM     ChiTietHD INNER JOIN\n"
                + "                  HoaDon ON ChiTietHD.maHD = HoaDon.maHD\n"
                + "where HoaDon.maHD =  '" + maHD + "'";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<ChiTietHD> list = new ArrayList<>();
                while (rs.next()) {
                    ChiTietHD cthd = new ChiTietHD();
                    SanPham sp = sp_dao.getSanPhamByID(rs.getString("maSP"));
                    cthd.setSanPham(sp);
                    cthd.setSoLuong(rs.getInt("soLuong"));
                    list.add(cthd);
                }
                return list;
            }
        }
    }
    
        public boolean delete(String maHD) throws Exception {
        String sql = "delete from ChiTietHD" + " where maHD = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, maHD);
            return stm.executeUpdate() > 0;
        }
    }

}
