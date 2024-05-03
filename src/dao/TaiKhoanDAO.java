/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanDAO {

    public TaiKhoan checkLogin(String maTK, String matKhau) throws Exception {
        String sql = "select matKhau, vaiTro from TaiKhoan " + " where maTK=? and matKhau = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, maTK);
            stm.setString(2, matKhau);
            try ( ResultSet rs = stm.executeQuery();) {
                if (rs.next()) {
                    TaiKhoan nd = new TaiKhoan();
                    nd.setMaTK(maTK);
                    nd.setVaiTro(rs.getString("vaitro"));
                    return nd;
                }
            }
        }
        return null;
    }

    public boolean insert(TaiKhoan nd) throws Exception {
        String sql = "INSERT INTO dbo.TaiKhoan(matKhau,vaiTro)"
                + "values(?,?)";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, nd.getMatKhau());
            stm.setString(2, nd.getVaiTro());

            return stm.executeUpdate() > 0;
        }
    }

    public TaiKhoan finById(String maTK) throws Exception {
        String sql = "select * from TaiKhoan where maTK = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, maTK);
            try ( ResultSet rs = stm.executeQuery();) {
                if (rs.next()) {
                    TaiKhoan nd = new TaiKhoan();
                    nd.setMaTK(rs.getString("MaTK"));
                    nd.setMatKhau(rs.getString("MatKhau"));
                    nd.setVaiTro(rs.getString("VaiTro"));
                    return nd;
                }
            }
            return null;
        }
    }

    public List<TaiKhoan> finAll() throws Exception {
        String sql = "select * from TaiKhoan order by TaiKhoan.id desc";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<TaiKhoan> list = new ArrayList<>();
                while (rs.next()) {
                    TaiKhoan nd = new TaiKhoan();
                    nd.setMaTK(rs.getString("maTK"));
                    nd.setMatKhau(rs.getString("MatKhau"));
                    nd.setVaiTro(rs.getString("VaiTro"));

                    list.add(nd);
                }
                return list;
            }
        }
    }

    public boolean delete(String maTK) throws Exception {
        String sql = "delete from TaiKhoan" + " where maTK = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, maTK);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean update(TaiKhoan nd) throws Exception {
        String sql = "UPDATE dbo.TaiKhoan"
                + " SET matKhau = ?,vaiTro = ?"
                + " where maTK = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(3, nd.getMaTK());
            stm.setString(1, nd.getMatKhau());
            stm.setString(2, nd.getVaiTro());

            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateMK(TaiKhoan nd) throws Exception {
        String sql = "UPDATE dbo.TaiKhoan"
                + " SET matKhau = ?"
                + " where maTK = ?";
        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(2, nd.getMaTK());
            stm.setString(1, nd.getMatKhau());
            return stm.executeUpdate() > 0;
        }
    }

    public TaiKhoan getTaiKhoanByID(String id) throws Exception {
        TaiKhoan tk = null;
        String sql = "select * from TaiKhoan where maTK = N'" + id + "'";

        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3));
                }
                return tk;
            }
        }
    }

}
