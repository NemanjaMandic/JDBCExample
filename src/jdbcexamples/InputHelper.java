/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexamples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;

/**
 *
 * @author nemus
 */
public class InputHelper {
    
    public static String getInput(String prompt){
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(prompt);
        System.out.flush();
        
        try{
            return stdin.readLine();
        }catch(Exception e){
            return "Error: " + e.getMessage();
        }
    }
    public static double getDoubleInput(String prompt){
        String input = getInput(prompt);
        return Double.parseDouble(input);
    }
    
    public static int getIntegerInput(String prompt){
        String input = getInput(prompt);
        return Integer.parseInt(input);
    }
    
    public static Date getDateInput(String prompt){
        String input = getInput(prompt);
        return Date.valueOf(input);
    }
    
    public static boolean getBooleanInput(String prompt){
        String input = getInput(prompt);
        return Boolean.parseBoolean(input);
    }
}
