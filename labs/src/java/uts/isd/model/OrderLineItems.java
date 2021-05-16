/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author alice_zly8mn7
 */
public class OrderLineItems implements Serializable {
    
    private String UserID;
    private int OrderID;
    private String Status;
    private String ProductName;
    private float unitPrice;

    public OrderLineItems(String UserID, int OrderID, String Status, String ProductName, float unitPrice) {
        this.UserID = UserID;
        this.OrderID = OrderID;
        this.Status = Status;
        this.ProductName = ProductName;
        this.unitPrice = unitPrice;
    }
    
    public OrderLineItems(ResultSet rs)
    {
        try
        {
            this.OrderID = rs.getInt("ORDERID");
            this.UserID = rs.getString("USERID");  
            this.ProductName = rs.getString("PRODUCTNAME"); 
            this.Status = rs.getString("STATUS");  
            this.unitPrice= rs.getFloat("UNITPRICE");
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderLineItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    
}
