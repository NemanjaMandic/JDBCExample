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
import jdbcexamples.beans.Student;

/**
 *
 * @author nemus
 */
public class StudentManager {
    
    private static final Student student = new Student();
    private static Connection conn = DBConnectionUtil.getConnection();
    private static ResultSet rs = null;
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
        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
           
            ps.setString(1, stud.getFirstName());
            ps.setString(2, stud.getLastName());
            ps.setDate(3, stud.getDateOfBirth());
            ps.setBoolean(4, stud.isOnBudget());
            
            int rowAffected = ps.executeUpdate();
            if(rowAffected == 1){
                rs = ps.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);
            }
            System.out.println("Inserted " + stud.getFirstName() + 
                    " " +stud.getLastName() + 
                    " "+ stud.getDateOfBirth() + 
                    " " + stud.isOnBudget() );
            
        } catch (SQLException ex) {
            DBConnectionUtil.proccessException(ex);
        }
    }
    
    public static void update(int id, Student student){
        String sql = "UPDATE student SET firstName = ?, lastName = ?, dateOfBirth = ?, onBudget = ? WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(Student.FIRST_NAME, student.getFirstName());
            ps.setString(Student.LAST_NAME, student.getLastName());
            ps.setDate(Student.DATE_OF_BIRTH, student.getDateOfBirth());
            ps.setBoolean(Student.STATUS, student.isOnBudget());
            ps.setInt(Student.ID, id);
            
            int rowsUpdated = ps.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("User updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
