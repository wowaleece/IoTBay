/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import uts.isd.model.Product;  
import java.sql.*;
/**
 *
 * @author Kayla Gelman
 */
public class DBManager {

    private Statement st;
   
    public DBManager(Connection conn) throws SQLException {       
    st = conn.createStatement(); 
    }
    
    //Find product in the database 
    public Product findProduct(String ProductName) throws SQLException { 
       
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters   
       return null;   
    }

    //Add product-data into the database   
    public void addProduct(Integer ProductID, String ProductName, int StockLevel, double UnitPrice, String Category) throws SQLException { //code for add-operation
       st.executeUpdate("INSERT INTO ISDUSER.Products" + "VALUES ('" + ProductID + "','"+ProductName +"','" + StockLevel +"','" + UnitPrice + "','" + Category + "');" );    
    }

    //update a user details in the database   
    public void updateProduct(Integer ProductID, String ProductName, String StockLevel, Integer UnitPrice, String Category) throws SQLException {       
       //code for update-operation   

    }       

    //delete a user from the database   
    public void deleteProduct(Integer ProductID, String ProductName, String StockLevel, Integer UnitPrice, String Category) throws SQLException{       
       //code for delete-operation   

    }

}