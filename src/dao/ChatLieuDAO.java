/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.DatabaseHelper;
import entity.ChatLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChatLieuDAO {

    public List<ChatLieu> finAll() throws Exception {
        String sql = "select * from ChatLieu";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                List<ChatLieu> list = new ArrayList<>();
                while (rs.next()) {
                    ChatLieu cl = new ChatLieu();
                    cl.setMaChatLieu(rs.getString("maChatLieu"));
                    cl.setTenChatLieu(rs.getString("tenChatLieu"));
                    list.add(cl);
                }
                return list;
            }
        }
    }

    public ChatLieu getChatLieuByName(String tenCL) throws Exception {
        ChatLieu cl = null;
        String sql = "select * from ChatLieu where tenChatLieu = N'" + tenCL + "'";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    cl = new ChatLieu(rs.getString(1), rs.getString(2));
                }
                return cl;
            }
        }
    }

    public ChatLieu getChatLieuByID(String id) throws Exception {
        ChatLieu cl = null;
        String sql = "select * from ChatLieu where maChatLieu = N'" + id + "'";

        try (
                 Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            try ( ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    cl = new ChatLieu(rs.getString(1), rs.getString(2));
                }
                return cl;
            }
        }
    }
    
         public boolean insert(ChatLieu cl) throws Exception {
        String sql = "INSERT INTO dbo.KichThuoc(tenChatLieu)"
                + "values(?)";
        try (
                 java.sql.Connection con = DatabaseHelper.opConnection();  PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, cl.getTenChatLieu());
            return stm.executeUpdate() > 0;
        }
    }
    
}
