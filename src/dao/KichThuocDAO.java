/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.KichThuoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KichThuocDAO {

    public List<KichThuoc> finAll() throws Exception {
        String sql = "select * from KichThuoc";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<KichThuoc> list = new ArrayList<>();
                while (rs.next()) {
                    KichThuoc kt = new KichThuoc();
                    kt.setMaKichThuoc(rs.getString("maKichThuoc"));
                    kt.setTenKichThuoc(rs.getString("tenKichThuoc"));
                    list.add(kt);
                }
                return list;
            }
        }
    }

    public KichThuoc getKichThuocByName(String tenKT) throws Exception {
        KichThuoc kt = null;
        String sql = "select * from KichThuoc where tenKichThuoc = N'" + tenKT + "'";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    kt = new KichThuoc(rs.getString(1), rs.getString(2));
                }
                return kt;
            }
        }
    }

    public KichThuoc getKichThuocByID(String id) throws Exception {
        KichThuoc kt = null;
        String sql = "select * from KichThuoc where maKichThuoc = N'" + id + "'";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    kt = new KichThuoc(rs.getString(1), rs.getString(2));
                }
                return kt;
            }
        }
    }

    public boolean insert(KichThuoc kt) throws Exception {
        String sql = "INSERT INTO dbo.KichThuoc(tenKichThuoc)"
                + "values(?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, kt.getTenKichThuoc());
            return stm.executeUpdate() > 0;
        }
    }

}
