/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;
import java.sql.Date;
/**

/**
 *
 * @author marinasantanelli
 */
public class User implements Serializable {
    private int userID; // don't know that this is secure enough? 
    private Customer customer;
    private String email;
    private String uType; //user type customer/admin
    private String phoneNo; //can be null filled on order completion
    private String fName;
    private String lName;
    private String sex;
    private Date dob;
    private Date regDate;
    private Address address;
    private Log log;
    

    public User(int userID, String email , String uType, String phoneNo) { //String password,
        this.userID = userID;
        this.email = email;
        this.uType = uType;
        this.phoneNo = phoneNo; 
    }
    
    public User(int userID, String email , String uType, String phoneNo, Customer customer) { //String password,
        this.userID = userID;
        this.email = email;
        this.uType = uType;
        this.phoneNo = phoneNo; 
        this.customer = customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public int getCustomerID(){
        return customer.getCustomerID();
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
    
    public Date getRegDate() {
        return regDate;
    }
    
    public Log getLog(){
        return log;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", uType=" + uType + ", phoneNo=" + phoneNo + ", fName=" + fName + ", lName=" + lName + ", sex=" + sex + ", dob=" + dob + ", regDate=" + regDate + ", address=" + address + ", log=" + log + '}';
    }

 
    

    
    
    
   
}
