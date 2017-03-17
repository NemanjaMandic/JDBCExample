/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexamples.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import jdbcexamples.DBConnectionUtil;

/**
 *
 * @author nemus
 */
public class StatesManager {
    
   public static void displayAllRows(){
        String sql = "SELECT adminId, userName, password FROM admin";
        
        try(
                Connection conn = DBConnectionUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            
           
            while(rs.next()){
                String fullName = 
                        rs.getString("stateId") + ": " + rs.getString("stateName");
                System.out.println(fullName);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
}
