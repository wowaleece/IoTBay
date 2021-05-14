/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.unitTests;

import java.util.*;
import java.util.logging.*;
import java.sql.*;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBProduct;
import uts.isd.model.dao.*;
import uts.isd.model.Product;

/**
 *
 * @author Kayla Gelman
 */
public class TestProductDB {
    
    private static Scanner in = new Scanner(System.in); 
    private DBConnector connector; 
    private Connection conn;  
    private DBProduct db; 
   
    public static void main(String[] args) throws ClassNotFoundException, SQLException{ 
        (new TestProductDB()).runQueries(); 
    }  
    
    public TestProductDB() throws SQLException { 
        try { 
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBProduct(conn); 
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(TestProductDB.class.getName()).log(Level.SEVERE, null, ex);
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
                    showAll();  
                    break;  
                default: 
                    System.out.println("Unkown Command");
                    break;  
            } 
        }
    }
    
    private void testAdd() throws SQLException { 
         System.out.print("Product Name: "); 
         String productName = in.nextLine();  
         System.out.print("Stock Level: "); 
         String stockLevel = in.nextLine();    
         System.out.print("UnitPrice: "); 
         float unitPrice = in.nextFloat();
         System.out.print("Category: "); 
         in.next();
         String category= in.nextLine();  
         db.addProduct(productName, stockLevel, unitPrice, category); 
         System.out.println("Product has been added to the database");
    }
    
    
    /* This works */ 
    private void testRead() throws SQLException {  
         System.out.print("Product Name: "); 
         String PRODUCTNAME = in.nextLine();  
         Product product = db.findProduct(PRODUCTNAME); 
         if (product != null){  
             System.out.println("Product " + product.getProductName() + " exists in the database.");
   
         } else { 
             System.out.println("Product does not exist"); 
         }  
         
    }
                
    
    /*Stops working at UnitPrice - Eror: Columns of type 'DOUBLE' cannot hold values of type 'CHAR'. */ 
    private void testUpdate() throws SQLException { 
        System.out.print("Product Name: "); 
        String PRODUCTNAME = in.nextLine();  
        
        try { 
            if(db.CheckProduct(PRODUCTNAME)){ 
                System.out.print("Product Name: "); 
                String ProductName = in.nextLine();  
                System.out.print("Stock Level: "); 
                String StockLevel = in.nextLine();    
                System.out.print("UnitPrice: "); 
                Float UnitPrice = in.nextFloat();    
                System.out.print("Category: "); 
                String Category= in.nextLine();
                db.updateProduct(ProductName, StockLevel, UnitPrice, Category);   
            }else { 
                System.out.println("Product does not exist"); 
            }
        }catch (SQLException ex) { 
            Logger.getLogger(TestProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     

    /*TO DO*/
    private void testDelete() throws SQLException {       
    }     
    
    /*THIS WORKS*/ 
    private void showAll() throws SQLException {  
        List<Product> products = db.DisplayProducts();
        System.out.println("PRODUCTS TABLE: ");
        products.forEach((Product) -> {
            System.out.printf("%s %s %f %s \n ", Product.getProductName(), Product.getStockLevel(), Product.getUnitPrice(), Product.getCategory());
        });
        System.out.println();
         
    } 
    
   
}
    
   


