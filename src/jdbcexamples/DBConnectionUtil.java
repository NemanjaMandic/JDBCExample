
package jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnectionUtil {
    
    private static final String USER_NAME = "nemus";
    private static final String PASSWORD = "nemus123";
    private static final String CON_URL = "jdbc:mysql://localhost/school";
    private static Connection con = null;
    
    public static Connection getConnection(){
       
        try {
            con = DriverManager.getConnection(CON_URL, USER_NAME, PASSWORD);
                  System.out.println("Konektovo se na: " + con.getCatalog());
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
    
    public static Connection closeConnection(){
        if(con != null){
            try {
                con.close();
                System.out.println("Conection closed");
            } catch (SQLException ex) {
                proccessException(ex);
            }
        }
        return null;
    }
}
