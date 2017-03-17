/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexamples.beans;

import java.io.Serializable;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author nemus
 */
public class Student implements Serializable{
    
   private static final long serialVersionUID = 1L;
   
   public static final int ID = 1;
   public static final int FIRST_NAME = 2;
   public static final int LAST_NAME = 3;
   public static final int DATE_OF_BIRTH = 4;
   public static final int STATUS = 5;
   
   private int studentId;
   private String firstName;
   private String lastName;
   private Date dateOfBirth;
   private boolean onBudget;
   
   public Student(){
       
   }
   
   public Student(int studentId, String firstName, String lastName, Date dateOfBirth, boolean onBudget){
       this(firstName, lastName, dateOfBirth, onBudget);
       this.studentId=studentId;
      
   }
    public Student(String firstName, String lastName, Date dateOfBirth, boolean onBudget){
       
       this.firstName=firstName;
       this.lastName=lastName;
       this.dateOfBirth=dateOfBirth;
       this.onBudget=onBudget;
   }

    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the onBudget
     */
    public boolean isOnBudget() {
        return onBudget;
    }

    /**
     * @param onBudget the onBudget to set
     */
    public void setOnBudget(boolean onBudget) {
        this.onBudget = onBudget;
    }
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.studentId;
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        hash = 29 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 29 * hash + (this.onBudget ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentId != other.studentId) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (this.onBudget != other.onBudget) {
            return false;
        }
        return true;
    }
    
   @Override
   public String toString(){
       return "Student ID: " + studentId +"\n" +
               "First name: " + firstName + "\n" + 
               "Last name: " + lastName + "\n" +
               "Date of birth: " + dateOfBirth + "\n" +
               "On budget: " + onBudget +"\n"+
               "***************************************";
   }
}
