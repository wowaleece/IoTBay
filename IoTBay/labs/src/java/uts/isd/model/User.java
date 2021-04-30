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
    private String email;
    private String fname;
    private String lname; 
    private String password;
    private String gender;
    private String dob; 

    public User(String email, String fname, String lname, String password, String gender, String dob) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
 
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

}
