/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;

/**
 *
 * @author Kayla Gelman
 */
public class Product implements Serializable{
    private Integer ProductID;  
    private String ProductName;  
    private String StockLevel; 
    private Integer UnitPrice; 
    private String Category;     
    
    public Product(Integer ProductID, String ProductName, String StockLevel, Integer UnitPrice, String Category){ 
        this.ProductID = ProductID; 
        this.ProductName = ProductName; 
        this.StockLevel = StockLevel; 
        this.UnitPrice = UnitPrice; 
        this.Category = Category; 
    } 
    
    public Integer getID(){ 
        return ProductID;  
    }  
    
    public String ProductName(){ 
        return ProductName;  
    }  
    
    public String StockLevel(){ 
        return StockLevel;  
    }    
    
    public Integer UnitPrice(){ 
        return UnitPrice;  
    }   
    
    public String Category(){ 
        return Category;  
    }   
}

