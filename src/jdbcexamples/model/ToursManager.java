
package jdbcexamples.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import jdbcexamples.DBConnectionUtil;


public class ToursManager {
    
    public static void displayAllRows() throws SQLException{
        
          String sql = "SELECT adminId, userName, password FROM admin";
        
        try(
                Connection conn = DBConnectionUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
        while(rs.next()){
            StringBuffer sb = new StringBuffer();
            sb.append("Tour: " + rs.getInt("tourId") + ": ");
            sb.append(rs.getString("tourName"));
            
            double price = rs.getDouble("price");
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            String formatedPrice = nf.format(price);
            
            sb.append(formatedPrice);
             
            System.out.println(sb.toString());
        }
    }
    }
}
