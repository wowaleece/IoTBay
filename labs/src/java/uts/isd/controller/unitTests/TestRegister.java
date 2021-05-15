/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.unitTests;

import java.util.*;
import java.util.logging.*;
import java.sql.*;
import uts.isd.controller.Validator;


import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBCustomer;
import uts.isd.model.dao.DBManager;




/**
 *
 * @author super
 */
public class TestRegister {
    
    private static Scanner in = new Scanner(System.in);
    
    
    public static void main(String[] args) {
        try {
            //start login
            //LoginDAO loginDAO = new LoginDAO();
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager manager = new DBManager(conn);
            DBCustomer dbCust = new DBCustomer(conn);
            Validator validator = new Validator();
            
            //test input
            System.out.print("User email: ");
            String email = in.nextLine();

            System.out.print("User password: ");
            String password = in.nextLine();
            
            System.out.print("User phoneNo: ");
            String phoneNo = in.nextLine();
            
            System.out.print("User fName: ");
            String fName = in.nextLine();
            
            System.out.print("User lName: ");
            String lName = in.nextLine();
            
            System.out.print("User sex: ");
            String sex = in.nextLine();
            
            System.out.print("User dob(yyyymmdd): ");
            String dateString = in.nextLine();
            java.sql.Date dob;
            dob = validator.sanitiseDate(dateString);
            System.out.println("DOB date sanitised");
            
            System.out.print("User StreetName: ");
            String streetname = in.nextLine();
            
            User user = manager.checkLogin(email,password);
            if( user != null) {
                System.out.println("User Already exists");
                
            } else {
                manager.addUser(email, password, lName, phoneNo);
                System.out.println("added user");
                user  = manager.checkLogin(email,password);
                System.out.println("got userID");
                dbCust.addCustomer(user.getUserID(), fName, lName, sex, dob, 2);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
}
