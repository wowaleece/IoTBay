/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kayla Gelman
 */
public class Product implements Serializable {
    protected int PRODUCTID;  
    protected String PRODUCTNAME; 
    protected int QUANTITY; 
    protected String STOCKLEVEL; 
    protected float UNITPRICE; 
    protected String CATEGORY;     
    
   
    /**
     *
     * @param PRODUCTID
     * @param PRODUCTNAME
     * @param QUANTITY
     * @param STOCKLEVEL
     * @param UNITPRICE
     * @param CATEGORY
     */
    /*public Product(int PRODUCTID, String PRODUCTNAME, String STOCKLEVEL, float UNITPRICE, String CATEGORY){ 
        this(PRODUCTNAME, STOCKLEVEL, CATEGORY); 
        this.PRODUCTID = PRODUCTID; 
    }*/
    
    public Product(int PRODUCTID, String PRODUCTNAME, int QUANTITY, String STOCKLEVEL, float UNITPRICE, String CATEGORY){ 
        this.PRODUCTID = PRODUCTID;  
        this.PRODUCTNAME = PRODUCTNAME;  
        this.QUANTITY = QUANTITY; 
        this.STOCKLEVEL = STOCKLEVEL;  
        this.UNITPRICE= UNITPRICE; 
        this.CATEGORY= CATEGORY; 
    }
    
    //For searching 
    public Product(ResultSet rs)
    {
        try
        {
            this.PRODUCTID = rs.getInt("PRODUCTID");
            this.PRODUCTNAME = rs.getString("PRODUCTNAME");  
            this.QUANTITY = rs.getInt("QUANTITY"); 
            this.STOCKLEVEL = rs.getString("STOCKLEVEL");  
            this.UNITPRICE= rs.getFloat("UNITPRICE"); 
            this.CATEGORY= rs.getString("CATEGORY"); 
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public int getID(){ 
        return PRODUCTID; 
    }
    
    public void setID(int ID){ 
        this.PRODUCTID = PRODUCTID; 
    }
    
    public String getProductName(){ 
        return PRODUCTNAME; 
    }
    
    public void setProductName(String ProductName) { 
        this.PRODUCTNAME = PRODUCTNAME; 
    }
    
    public int getQuantity(){ 
        return QUANTITY; 
    }
    
    public void setQuantity(int QUANTITY){ 
        this.QUANTITY = QUANTITY; 
    }
    public String getStockLevel(){ 
        return STOCKLEVEL; 
    }
    
    public void setStockLevel(String StockLevel) { 
        this.STOCKLEVEL = STOCKLEVEL; 
    }
    
    public float getUnitPrice(){ 
        return UNITPRICE; 
    }
    
    public void setUnitPrice(float UnitPrice) { 
        this.UNITPRICE = UNITPRICE; 
    }
    
    public String getCategory(){ 
        return CATEGORY; 
    }
    
    public void setCategory(String Category) { 
        this.CATEGORY = CATEGORY; 
    }
}
    
    