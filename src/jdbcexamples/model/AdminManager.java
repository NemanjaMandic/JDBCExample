/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexamples.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbcexamples.DBConnectionUtil;
import jdbcexamples.beans.Admin;

/**
 *
 * @author nemus
 */
public class AdminManager {
    
    public static void displayAllRows() {
        String sql = "SELECT adminId, userName, password FROM admin";

        try (
                Connection conn = DBConnectionUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Admin table:");
            while (rs.next()) {
                StringBuffer sb = new StringBuffer();
                sb.append(rs.getInt("adminId") + ": ");
                sb.append(rs.getString("userName") + ", ");
                sb.append(rs.getString("password") + ", ");
                System.out.println(sb.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Admin getRow(int adminId){
        String sql = "SELECT * FROM admin WHERE adminId = ?";
        ResultSet rs = null;
        
        try( Connection conn = DBConnectionUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);){
            
            stmt.setInt(1, adminId);
            rs = stmt.executeQuery();
         
            if(rs.next()){
                Admin bean = new Admin();
                bean.setAdminId(adminId);
                bean.setUserName(rs.getString("userName"));
                bean.setPassword(rs.getString("password"));
                return bean;
            }else{
                System.err.println("No rows found");
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
        
    }
    
    public static boolean insert(Admin bean) throws SQLException{
        String sql = "INSERT INTO admin(userName, password) " + "VALUES(?, ?)";
        ResultSet keys = null;
        try(
            Connection conn = DBConnectionUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            int affected = stmt.executeUpdate();
            
            if(affected == 1){
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                bean.setAdminId(newKey);
            }else{
                System.err.println("No rows affected");
                return false;
            }
        }
        return true;
    }
    
    public static boolean update(Admin bean) throws SQLException{
        String sql = "UPDATE admin SET " +
                     "userName = ?, password = ? " +
                     "WHERE adminId = ?";
        try(Connection conn = DBConnectionUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            stmt.setInt(3, bean.getAdminId());
            
            int affected = stmt.executeUpdate();
            if(affected == 1){
                  return true;
            }else{
            return false;
            }
        }
    }
    
     public static boolean delete(int adminId) throws SQLException{
        String sql = "DELETE FROM admin WHERE adminId = ?";
        try(Connection conn = DBConnectionUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            stmt.setInt(1, adminId);
            
            int affected = stmt.executeUpdate();
            if(affected == 1){
                  return true;
            }else{
            return false;
            }
         
        }
    }
}
