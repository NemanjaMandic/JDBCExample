
package jdbcexamples;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import jdbcexamples.beans.Student;
import jdbcexamples.model.StudentManager;

public class Main {

   // private static final String SQL = "{CALL GetToursByPrice(?)}";
    
    public static void main(String[] args) throws SQLException {
       /*
        AdminManager.displayAllRows();
       
        int adminId = InputHelper.getIntegerInput("Select a row to update: ");
        
        Admin bean = AdminManager.getRow(adminId);
        if(bean == null){
            System.err.println("Row not found");
            return;
        }
         String username = InputHelper.getInput("Enter new username: ");
        bean.setPassword(username);
        
        String password = InputHelper.getInput("Enter new password: ");
        bean.setPassword(password);
        
        if(AdminManager.update(bean)){
            System.out.println("Success");
        }else{
            System.err.println("whoopss");
        }
       */
        
        Student student = new Student();
        
        student.setFirstName(InputHelper.getInput("Enter first name: "));
        student.setLastName(InputHelper.getInput("Enter last name: "));
        student.setDateOfBirth(InputHelper.getDateInput("Enter date of birth in yyyy-[m]m-[d]d format: "));
        student.setOnBudget(InputHelper.getBooleanInput("Is student on budget true/false ? "));
        
        StudentManager.insertStudent(student);
      
      
        
    }
    
}
