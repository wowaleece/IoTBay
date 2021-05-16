/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.unitTests;

import java.util.*;
import java.util.logging.*;
import java.sql.*;


import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;
import uts.isd.controller.unitTests.TestInput;




/**
 *
 * @author super
 */
public class TestLogin {
    
    
    
    
    public static void main(String[] args) {
        try {
            //start login
            //LoginDAO loginDAO = new LoginDAO();
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager manager = new DBManager(conn);
            
            //test input
            String email = TestInput.askString("User Email:");
            String password = TestInput.askString("User password: ");
            
            User user = manager.checkLogin(email,password);
            if( user != null) {
                System.out.println("login details are correct");
                
            } else {
                System.out.println("Login Failed, incorrect username or password");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
}
