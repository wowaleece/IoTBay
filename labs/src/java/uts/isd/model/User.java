/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;
/**

/**
 *
 * @author marinasantanelli
 */
public class User implements Serializable {
    private int userID; // don't know that this is secure enough? 
    private String email;
    private String uType; //user type customer/admin
    private String phoneNo; //can be null filled on order completion
    

    public User(int userID, String email , String uType, String phoneNo) { //String password,
        this.userID = userID;
        this.email = email;
        this.uType = uType;
        this.phoneNo = phoneNo; 
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getuType() {
        return uType;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phNumber) {
        this.phoneNo = phoneNo;
    }
    
   
}
