/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

/**
 *
 * @author Kayla Gelman
 */
public class Product {
    protected int ID;  
    protected String ProductName;  
    protected String StockLevel; 
    protected float UnitPrice; 
    protected String Category;     
    
    public Product() {         
    }
    
    public Product(int id){ 
        this.ID = ID; 
    }
    
    /**
     *
     * @param ID
     * @param ProductName
     * @param StockLevel
     * @param UnitPrice
     * @param Category
     */
    public Product(int ID, String ProductName, String StockLevel, float UnitPrice, String Category){ 
        this(ProductName, StockLevel, Category); 
        this.ID = ID; 
    }
    
    public Product(String ProductName, String StockLevel, float UnitPrice ){ 
        this.ProductName = ProductName;  
        this.StockLevel = StockLevel;  
        this.UnitPrice = UnitPrice; 
    }

    private Product(String ProductName, String StockLevel, String Category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Product(String ProductName, String StockLevel, String UnitPrice, String Category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getID(){ 
        return ID; 
    }
    
    public void setID(int ID){ 
        this.ID = ID; 
    }
    
    public String getProductName(){ 
        return ProductName; 
    }
    
    public void setProductName(String ProductName) { 
        this.ProductName = ProductName; 
    }
    
    public String getStockLevel(){ 
        return StockLevel; 
    }
    
    public void getStockLevel(String StockLevel) { 
        this.StockLevel = StockLevel; 
    }
    
    public float getUnitPrice(){ 
        return UnitPrice; 
    }
    
    public void getUnitPrice(float UnitPrice) { 
        this.UnitPrice = UnitPrice; 
    }
    
    public String getCategory(){ 
        return Category; 
    }
    
    public void getCategory(String Category) { 
        this.Category = Category; 
    }
}
    
    