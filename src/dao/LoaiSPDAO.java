/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.LoaiSP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class LoaiSPDAO {

    public List<LoaiSP> finAll() throws Exception {
        String sql = "select * from LoaiSP";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<LoaiSP> list = new ArrayList<>();
                while (rs.next()) {
                    LoaiSP loai = new LoaiSP();
                    loai.setMaLoai(rs.getString("maLoai"));
                    loai.setTenLoai(rs.getString("tenLoai"));
                    list.add(loai);
                }
                return list;
            }
        }
    }

    public LoaiSP getLoaiSPByName(String tenLoai) throws Exception {
        LoaiSP loai = null;
        String sql = "select * from LoaiSP where tenLoai = N'" + tenLoai + "'";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    loai = new LoaiSP(rs.getString(1), rs.getString(2));
                }
                return loai;
            }
        }
    }
        public LoaiSP getLoaiSPByID(String id) throws Exception {
        LoaiSP loai = null;
        String sql = "select * from LoaiSP where maLoai = N'" + id + "'";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    loai = new LoaiSP(rs.getString(1), rs.getString(2));
                }
                return loai;
            }
        }
    }
        
                public boolean insert(LoaiSP loai) throws Exception {
        String sql = "INSERT INTO dbo.LoaiSP(tenLoai)"
                + "values(?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, loai.getTenLoai());
            return stm.executeUpdate() > 0;
        }
    }
}
