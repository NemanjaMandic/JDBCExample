/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexamples;

import java.sql.Connection;

/**
 *
 * @author nemus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try(Connection con = DBConnection.getConnection()){
            System.out.println("You are connected to: " + con.getCatalog());
        }catch(Exception ex){
            
        }
    }
    
}
