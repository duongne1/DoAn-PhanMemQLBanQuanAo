/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.MauSac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class MauSacDAO {

    public List<MauSac> finAll() throws Exception {
        String sql = "select * from MauSac";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<MauSac> list = new ArrayList<>();
                while (rs.next()) {
                    MauSac ms = new MauSac();
                    ms.setMaMau(rs.getString("maMauSac"));
                    ms.setTenMau(rs.getString("tenMauSac"));
                    list.add(ms);
                }
                return list;
            }
        }
    }

    public MauSac getMauSacByName(String tenMauSac) throws Exception {
        MauSac mau = null;
        String sql = "select * from MauSac where tenMauSac = N'" + tenMauSac + "'";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    mau = new MauSac(rs.getString(1), rs.getString(2));
                }
                return mau;
            }
        }
    }

    public MauSac getMauSacByID(String id) throws Exception {
        MauSac mau = null;
        String sql = "select * from MauSac where maMauSac = N'" + id + "'";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    mau = new MauSac(rs.getString(1), rs.getString(2));
                }
                return mau;
            }
        }
    }

    public boolean insert(MauSac mau) throws Exception {
        String sql = "INSERT INTO dbo.MauSac(tenMauSac)"
                + "values(?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, mau.getTenMau());
            return stm.executeUpdate() > 0;
        }
    }


}
