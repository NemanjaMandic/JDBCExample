/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexamples.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import jdbcexamples.DBConnectionUtil;
import jdbcexamples.beans.Admin;
import jdbcexamples.beans.Student;

/**
 *
 * @author nemus
 */
public class StudentManager {
    
    private static final Student student = new Student();
    private static Connection conn = DBConnectionUtil.getConnection();
    private static ResultSet rs = null;
    
    
      public static Student getRow(int studentId){
        String sql = "SELECT * FROM student WHERE id = ?";
       
        
        try(PreparedStatement ps = conn.prepareStatement(sql);){
            
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
         
            if(rs.next()){
               
                student.setStudentId(studentId);
                student.setFirstName(rs.getString(Student.FIRST_NAME));
                student.setLastName(rs.getString(Student.LAST_NAME));
                student.setDateOfBirth(rs.getDate(Student.DATE_OF_BIRTH));
                student.setOnBudget(rs.getBoolean(Student.STATUS));
                return student;
            }else{
                System.err.println("No rows found");
                return null;
            }
        } catch (SQLException ex) {
            DBConnectionUtil.proccessException(ex);
        }finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                   DBConnectionUtil.proccessException(ex);
                }
            }
        }
        return null;
        
    }
    public static void displayAllStudents(){
         String sql = "SELECT * FROM student";
        try(
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);){
          
            System.out.println("List of all students: ");
            while(rs.next()){
                student.setStudentId(rs.getInt(Student.ID));
                student.setFirstName(rs.getString(Student.FIRST_NAME));
                student.setLastName(rs.getString(Student.LAST_NAME));
                student.setDateOfBirth( rs.getDate(Student.DATE_OF_BIRTH));
                student.setOnBudget(rs.getBoolean(Student.STATUS));
                System.out.println(student);
              
            }
            
        }catch(SQLException ex){
            DBConnectionUtil.proccessException(ex);
        }finally{
            DBConnectionUtil.closeConnection();
        }
    }
    public static void insertStudent(Student stud){
        int candidateId = 0;
        
        String sql = "INSERT INTO student(firstName, lastName, dateOfBirth, onBudget) VALUES(?, ?, ?, ? );";
        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
           
            ps.setString(1, stud.getFirstName());
            ps.setString(2, stud.getLastName());
            ps.setDate(3, stud.getDateOfBirth());
            ps.setBoolean(4, stud.isOnBudget());
            
            int rowAffected = ps.executeUpdate();
           
            if(rowAffected == 1){
                rs = ps.getGeneratedKeys();
                rs.next();
                candidateId = rs.getInt(1);
                stud.setStudentId(candidateId);
            }else{
                System.err.println("No rows affected");
            }
            System.out.println("Inserted " + stud.getFirstName() + 
                    " " +stud.getLastName() + 
                    " "+ stud.getDateOfBirth() + 
                    " " + stud.isOnBudget() );
            
        } catch (SQLException ex) {
            DBConnectionUtil.proccessException(ex);
        }finally{
            if(rs != null)
                try {
                    rs.close();
            } catch (SQLException ex) {
                DBConnectionUtil.proccessException(ex);
            }
        }
    }
    
    public static void update(Student student){
        String sql = "UPDATE student SET firstName = ?, lastName = ?, dateOfBirth = ?, onBudget = ? WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql);){
            
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, student.getDateOfBirth());
            ps.setBoolean(4, student.isOnBudget());
            ps.setInt(5, student.getStudentId());
            
            int rowsUpdated = ps.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("User updated");
            }else{
                System.err.println("Something went wrong.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
