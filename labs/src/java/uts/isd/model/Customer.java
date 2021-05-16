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
    private int customerID;
    private String fName;
    private String lName;
    private String sex;
    private Date dob;
    
    public Customer(int customerID, String fName, String lName, String sex, Date dob) {
        this.customerID = customerID;
        this.fName = fName;
        this.lName = lName;
        this.sex = sex;
        this.dob = dob;
    }
}
