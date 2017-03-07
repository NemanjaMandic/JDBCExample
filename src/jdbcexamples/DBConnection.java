/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nemus
 */
public class DBConnection {
    
    private static final String USER_NAME = "nemus";
    private static final String PASSWORD = "nemus123";
    private static final String CON_URL = "jdbc:mysql://localhost/explorecalifornia";
    private static Connection con = null;
    
    public static Connection getConnection(){
       
        try {
            con = DriverManager.getConnection(CON_URL, USER_NAME, PASSWORD);
            
                 System.out.println("You are connected to: " + con.getCatalog()+" database");
          
           
        } catch (SQLException ex) {
            proccessException(ex);
        }
        return con;
             
         
    }
    
    public static void proccessException(SQLException ex){
        System.err.println("ERROR MESSAGE: " + ex.getMessage());
        System.err.println("ERROR CODE: " + ex.getErrorCode());
        System.err.println("SQL STATE: " + ex.getSQLState());
    }
}
