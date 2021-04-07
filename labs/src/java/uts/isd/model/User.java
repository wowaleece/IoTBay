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
    private String password;
    private String gender;
    
public User(String email, String name, String password) {
    this.email = email;
    this.fname = name;
    this.password = password;
    this.gender = gender;
    
    
}
    
public String getEmail() {
    return email;
}
    
public String getName() {
    return fname;
}

public String getPassword() {
    return password;
}

public String getGender() {
    return gender;
}




public void setEmail(String email) {
    this.email = email;
}

public void setName(String name) {
    this.fname = name;
}

public void setPassword(String password) {
    this.password = password;
}

public void setGender(String gender) {
    this.gender = gender;
}




}
