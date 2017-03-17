
package jdbcexamples.beans;

import java.io.Serializable;


public class Admin implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int adminId;
    private String userName;
    private String password;

    public Admin(){
        
    }
    public Admin(int adminId, String userName, String password){
        this.adminId=adminId;
        this.userName=userName;
        this.password=password;
    }
    
    /**
     * @return the adminId
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * @param adminId the adminId to set
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
