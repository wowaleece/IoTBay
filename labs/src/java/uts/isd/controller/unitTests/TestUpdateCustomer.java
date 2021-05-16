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
import uts.isd.model.Address;
import uts.isd.model.Customer;


import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBCustomer;
import uts.isd.model.dao.DBManager;




/**
 *
 * @author super
 */
public class TestUpdateCustomer {
    
    private static Scanner in = new Scanner(System.in);
    
    
    public static void main(String[] args) {
        try {
            //start login
            //LoginDAO loginDAO = new LoginDAO();
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager manager = new DBManager(conn);
            DBCustomer customerManager = new DBCustomer(conn);
            Validator validator = new Validator();
            
            //test input
            System.out.println("Login");
            System.out.print("User email: ");
            String email = in.nextLine();
            
            System.out.print("User password: ");
            String password = in.nextLine();
            
            User user = manager.checkLogin(email,password);
            Customer cust = user.getCustomer();
            if( user != null) {
                System.out.println("login successful, update details:");
                System.out.println(user.toString());
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

                System.out.print("User unitNo: ");
                String unitNo = in.nextLine();

                System.out.print("User StreetName: ");
                String streetname = in.nextLine();

                System.out.print("User suburb: ");
                String suburb = in.nextLine();

                System.out.print("User state: ");
                String state = in.nextLine();

                System.out.print("User country: ");
                String country = in.nextLine();

                System.out.print("User postcode: ");
                int postcode = in.nextInt();
                in.nextLine();
                
                //fill null strings
                int userID = user.getUserID();
                //int customerID = user.getCustomerID();
                if (phoneNo == null) phoneNo = user.getPhoneNo();
                if (fName == null) fName = cust.getfName();
                if (lName == null) lName = cust.getlName();
                if (sex == null) sex = cust.getSex();
                if (dob == null) dob = cust.getDob();

                customerManager.updateCustomer(userID, fName, lName, sex, dob);
                /*
                //process the address, get ID
                //int addressID = manager.findAddress(address);
                Address address = user.getAddress();
                if(unitNo == null) unitNo = address.getUnitNumber();
                if(street == null) street = address.getStreetName();
                if(suburb == null) suburb = address.getSuburb();
                if(tempPC == null) {
                    postcode = address.getPostcode();
                } else {
                    postcode = Integer.parseInt(tempPC);
                }
                if(state == null) state = address.getState();
                if(country == null) country = address.getCountry();
                
                
                
                //find address
                int addressID  = manager.findAddress();
                */
                
                //int addressID = user.getAddress().getAddressID();
                //manager.updateCustomerAddress(customerID, fName, lName, state, sex, dob, addressID);
                
            } else {
                System.out.println("login failed.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestUpdateCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
