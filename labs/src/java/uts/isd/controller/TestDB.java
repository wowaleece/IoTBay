/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import uts.isd.model.dao.*; 


/**
 *
 * @author Kayla Gelman 
 */
public class TestDB {
    
    private static Scanner in = new Scanner(System.in);

 

    public static void main(String[] args) {

    try {

    DBConnector connector = new DBConnector();

    Connection conn = connector.openConnection();

    DBManager db = new DBManager(conn);



    System.out.print("Product ID: ");
    int ProductID = in.nextInt();
    in.nextLine(); // consume /n not consumed by next int

    System.out.print("Product Name: ");
    String ProductName = in.nextLine();

    System.out.print("Stock Level: ");
    int StockLevel= in.nextInt();

    System.out.print("Unit Price: ");
    double UnitPrice = in.nextDouble();
    in.nextLine(); // consume /n not consumed by next Double
    
    System.out.print("Category: ");
    String Category = in.nextLine();

    db.addProduct(ProductID, ProductName, StockLevel, UnitPrice, Category);

    System.out.println("Product has been added to the database.");

    connector.closeConnection();



    } catch (ClassNotFoundException | SQLException ex) {

    Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

    }

    }

    
 }
    
 




