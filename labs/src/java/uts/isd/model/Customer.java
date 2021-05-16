/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author super
 */
public class Customer implements Serializable {
    protected int customerID;
    protected String fName;
    protected String lName;
    protected String sex;
    protected Date dob;
    protected Address address;
    
    public Customer(int customerID, String fName, String lName, String sex, Date dob) {
        this.customerID = customerID;
        this.fName = fName;
        this.lName = lName;
        this.sex = sex;
        this.dob = dob;
    }
    
    public Customer(int customerID, String fName, String lName, String sex, Date dob,Address address) {
        this.customerID = customerID;
        this.fName = fName;
        this.lName = lName;
        this.sex = sex;
        this.dob = dob;
        this.address = address;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
