/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.unitTests;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import uts.isd.model.dao.*; 
import uts.isd.model.dao.DBManager_Orders;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DB;
import uts.isd.model.Orders; 

/**
 *
 * @author alice_zly8mn7
 */

    public class TestOrderDB {

    private static Scanner in = new Scanner(System.in);
    private DBConnector connector; 
    private Connection conn;  
    private DBManager_Orders db;     


    public static void main(String[] args) throws ClassNotFoundException, SQLException{ 
        (new TestOrderDB()).runQueries(); 
    } 

    public TestOrderDB() throws SQLException { 
        try { 
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBManager_Orders(conn); 
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(TestOrderDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private char readChoice() {  
        System.out.print("Operation CRUDS or * to exit: ");  
        return in.nextLine().charAt(0); 
    }
    
    private void runQueries() throws SQLException { 
        char c;  
        while ((c = readChoice()) != '*') { 
            switch (c) { 
                case 'C': 
                    testAdd(); 
                    break;  
                case 'R': 
                    testRead(); 
                    break;  
                case 'U': ; 
                    testUpdate(); 
                    break;  
                case 'D': ;  
                    testDelete(); 
                    break;  
                case 'S': ; 
//                    showAll();  
                    break;  
                default: 
                    System.out.println("Unkown Command");
                    break;  
            } 
        }
    }    

    public void testAdd() throws SQLException{
        System.out.print("User ID: ");
        String UserID = in.nextLine();
        System.out.print("Order Status: ");
        String OrderStatus = in.nextLine();
        System.out.print("Street Name: ");
        String StreetName = in.nextLine();
        System.out.print("Unit Number: ");
        String UnitNumber = in.nextLine();
        System.out.print("Suburb: ");
        String Suburb = in.nextLine();
        System.out.print("Postcode: ");
        String String_Postcode = in.nextLine();
        int Postcode = Integer.parseInt(String_Postcode);
        System.out.print("Address State: ");
        String AddressState = in.nextLine();        
        System.out.print("Billing Address (1 for same or 0 for different): ");        
        String String_BillingAddress = in.nextLine();
        boolean BillingAddress = Boolean.parseBoolean(String_BillingAddress);
        System.out.print("Product Name: ");        
        String ProductName = in.nextLine();

        try {
            db.saveOrder(UserID, OrderStatus, StreetName, UnitNumber, Suburb, Postcode, AddressState, BillingAddress, ProductName);
        } catch (SQLException ex){
            Logger.getLogger(TestOrderDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void testRead() throws SQLException{
        System.out.print("Search via OrderID: ");
        String OrderID_Str = in.nextLine();
        int OrderID = Integer.parseInt(OrderID_Str);
        Orders order = db.findOrder(OrderID);
        if (order != null) {
            System.out.println("Order is found");
        }else{
            System.out.println("Order cannot be found");
        }

    }
    
    public void testUpdate() throws SQLException{
        System.out.print("User ID: ");
        String UserID = in.nextLine();
        System.out.print("Order ID: ");
        String OrderID_Str = in.nextLine();
        int OrderID = Integer.parseInt(OrderID_Str);
        System.out.print("Order Status: ");
        String OrderStatus = in.nextLine();
        System.out.print("Shipping Status: ");
        String ShippingStatus = in.nextLine();
        System.out.print("Street Name: ");
        String StreetName = in.nextLine();
        System.out.print("Unit Number: ");
        String UnitNumber = in.nextLine();
        System.out.print("Suburb: ");
        String Suburb = in.nextLine();
        System.out.print("Postcode: ");
        String String_Postcode = in.nextLine();
        int Postcode = Integer.parseInt(String_Postcode);
        System.out.print("Address State: ");
        String AddressState = in.nextLine();        
        System.out.print("Billing Address (1 for same or 0 for different): ");        
        String String_BillingAddress = in.nextLine();
        boolean BillingAddress = Boolean.parseBoolean(String_BillingAddress);
        System.out.print("Name on Card: ");
        String NameonCard = in.nextLine();        
        System.out.print("Card Type: ");        
        String CardType= in.nextLine();

        try {
            db.updateOrder(UserID, OrderID, OrderStatus, ShippingStatus, StreetName, UnitNumber, Suburb, Postcode, AddressState, BillingAddress, NameonCard, CardType);
        } catch (SQLException ex){
            Logger.getLogger(TestOrderDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   
     public void testDelete() throws SQLException{
        System.out.print("Order ID: ");
        String OrderID_str = in.nextLine();
        int OrderID = Integer.parseInt(OrderID_str);
        
        try {
            db.deleteOrder(OrderID);
        } catch (SQLException ex){
            Logger.getLogger(TestOrderDB.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     

    
    
    }
