/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class DatabaseHelper {
        public static Connection con;
        public static Connection opConnection()throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=QuanLyBanQuanAo;encrypt=true;trustServerCertificate=true;";  
        String user = "sa";
        String pass = "123456";
        Connection con = DriverManager.getConnection(connectionUrl, user, pass);
        return con;
    }
}
