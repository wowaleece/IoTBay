/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;
import uts.isd.model.User;
import java.sql.*;
import uts.isd.model.Orders;

/**
 *
 * @author alice_zly8mn7
 */

public class DBManager_Orders {

    private Statement st;
    private Connection conn; 

    public DBManager_Orders(Connection conn) throws SQLException {       
       this.conn = conn;   
    }
    // Saving an order is reserved for customers with a registered ID. Otherwise, order is saved as a cart in a session for Guests.
    // If a registered user wants to save an order, query will check if order exists already in their account and use old order details, but just add more item lines to it. 
    public void saveOrder(String UserID, String OrderStatus, String StreetName, String UnitNumber, String Suburb, int Postcode, String AddressState, boolean BillingAddress, String ProductName)throws SQLException {
        int check = 0; 
        String query = "SELECT ORDERID FROM APP.ORDERS WHERE USERID = '" + UserID + "' AND ORDERSTATUS = 'Saved'";
        PreparedStatement Statement = conn.prepareStatement(query);
        ResultSet rs = Statement.executeQuery();
        while (rs.next()){
            int OrderID = rs.getInt("ORDERID");
            System.out.println(OrderID);
            check = 1; 
        }
        if (check == 1) {
            int OrderID = getOrderID(UserID);
            updateOrder(UserID, OrderID, OrderStatus, "0", StreetName, UnitNumber, Suburb, Postcode, AddressState, BillingAddress, "0", "0");
            addOrderLineItem(UserID, OrderID, "Saved", ProductName);
        }else{
            System.out.println(check);
            query = "INSERT INTO APP.ORDERS (USERID, ORDERSTATUS, STREETNAME, UNITNUMBER, SUBURB, POSTCODE, ADDRESSSTATE, BILLINGADDRESS) VALUES (?, ?, ?, ?, ? , ?, ?, ?)";            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, UserID);
            statement.setString(2, OrderStatus);
            statement.setString(3, StreetName);
            statement.setString(4, UnitNumber);
            statement.setString(5, Suburb);
            statement.setInt(6, Postcode);
            statement.setString(7, AddressState);
            statement.setBoolean(8, BillingAddress);
            statement.executeUpdate();
            int OrderID = getOrderID(UserID);
            addOrderLineItem(UserID, OrderID, "Saved", ProductName);            
            
        }    

    }
   
    public int getOrderID(String UserID)throws SQLException {
        String query = "SELECT ORDERID FROM APP.ORDERS WHERE USERID = '" + UserID + "' AND ORDERSTATUS = 'Saved'";
        PreparedStatement Statement = conn.prepareStatement(query);
        ResultSet rs = Statement.executeQuery();        
        while (rs.next()) {
            int orderID = rs.getInt("ORDERID");
            System.out.println(orderID);
            return orderID;
        }
        return 0;
    }   
    
    public Orders findOrder(int OrderID)throws SQLException { // Only searches via OrderID
        String sql = "SELECT * FROM APP.ORDERS WHERE ORDERID = " + OrderID ;
        PreparedStatement Statement = conn.prepareStatement(sql);
        ResultSet rs = Statement.executeQuery();
        
        while (rs.next()){ 
            int ORDERID_TEMP = rs.getInt(1); 
            if (ORDERID_TEMP == OrderID){
                String UserID = rs.getString(2);
                Timestamp OrderTime_ts = rs.getTimestamp(3);
                    String OrderTime = OrderTime_ts.toString();
                String OrderStatus = rs.getString(4);
                String ShippingStatus = rs.getString(5);
                String StreetName = rs.getString(6);
                String UnitNumber = rs.getString(7);
                String Suburb = rs.getString(8);
                int Postcode = rs.getInt(9);
                String AddressState = rs.getString(10);
                boolean BillingAddress = rs.getBoolean(11);
                String NameonCard = rs.getString(12);
                String CardType = rs.getString(13);
                float TotalPrice = rs.getFloat(14);
                return new Orders(OrderID, UserID, OrderStatus, ShippingStatus, OrderTime, StreetName, UnitNumber, Suburb, Postcode, AddressState, BillingAddress, NameonCard, CardType, TotalPrice); 
            }
        }
        return null;  
    }
    
    public void updateOrder(String UserID, int OrderID, String OrderStatus, String ShippingStatus, String StreetName, String UnitNumber, String Suburb, int Postcode, String AddressState, boolean BillingAddress, String NameonCard, String CardType) throws SQLException {
        float TotalPrice = getTotalPrice(OrderID);
        String query = "UPDATE APP.ORDERS SET TOTALPRICE = ? , STREETNAME = ? , UNITNUMBER = ? , SUBURB = ? , POSTCODE = ? , ADDRESSSTATE = ?, BILLINGADDRESS = ?, NAMEONCARD = ? , CARDTYPE = ? WHERE USERID = ? AND ORDERID = ?";   
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setFloat(1, TotalPrice);
        statement.setString(2, StreetName);
        statement.setString(3, UnitNumber);
        statement.setString(4, Suburb);
        statement.setInt(5, Postcode);
        statement.setString(6, AddressState);
        statement.setBoolean(7, BillingAddress);
        statement.setString(8, NameonCard);
        statement.setString(9, CardType);
        statement.setString(10, UserID);
        statement.setInt(11, OrderID);
        statement.executeUpdate();
    }

    public void deleteOrder(int OrderID)throws SQLException {
        String query = "SELECT ORDERSTATUS FROM APP.ORDERS WHERE ORDERID = " + OrderID ;
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        PreparedStatement Statement = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            rs.updateString("ORDERSTATUS", "Cancelled");
            rs.updateRow();
        }
        
        query = "SELECT * FROM APP.ORDERLINEITEMS WHERE ORDERID = " + OrderID;
        rs = stmt.executeQuery(query);
        while (rs.next()){
            rs.updateString("STATUS", "Cancelled");
            rs.updateRow();
        }
        
    }
    
    public float getTotalPrice(int OrderID)throws SQLException {
        float TotalPrice = 0; 
        // get all the product prices associated with OrderID
//        String ProductName = getProductName(OrderID)
        String query = "SELECT UNITPRICE FROM APP.ORDERLINEITEMS WHERE ORDERID = "+ OrderID;
        PreparedStatement Statement = conn.prepareStatement(query);
        ResultSet rs = Statement.executeQuery();    
        while (rs.next()) {
            float UnitPrice = rs.getFloat("UNITPRICE");
            System.out.println(UnitPrice);
            TotalPrice = TotalPrice + UnitPrice;
        }
//        } catch (SQLException e) {
//            System.out.println("No Product Details Found");
//        }
//        
        return TotalPrice;   
    }
  
    public float getProductPrice(String ProductName) throws SQLException {
        try {
            String query = "SELECT UNITPRICE FROM PRODUCTS WHERE PRODUCTNAME = '" + ProductName +"'";
            PreparedStatement Statement = conn.prepareStatement(query);
            ResultSet rs = Statement.executeQuery(query);
            while (rs.next()) {
                float UnitPrice = rs.getFloat("UNITPRICE");
                System.out.println(UnitPrice);
                return UnitPrice;
            }
        } catch (SQLException e) {
            System.out.println("No product details found");
        }
        return 0;
    }

    public void addOrderLineItem(String UserID, int OrderID, String Status, String ProductName)throws SQLException {
        float ProductPrice = getProductPrice(ProductName); // need to link to products db with Kayla
        String query = "INSERT INTO APP.ORDERLINEITEMS (USERID, ORDERID, STATUS, PRODUCTNANE, UNITPRICE) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, UserID);
        statement.setString(2, "Saved");
        statement.setString(3, Status);
        statement.setString(4, ProductName);
        statement.setFloat(5, ProductPrice);
        statement.executeUpdate();
        
    }
 
    public void deleteOrderLineItem(String UserID, int OrderID, String ProductName)throws SQLException {
        String query = "SELECT * FROM APP.ORDERLINEITEMS WHERE USERID = '" + UserID + "' AND ORDERSTATUS = 'Saved'";
        PreparedStatement Statement = conn.prepareStatement(query);
        ResultSet rs = Statement.executeQuery();
        while (rs.next()){
            rs.updateString("ORDERSTATUS", "Cancelled");
        }
    }
    
    public String getProductName(int OrderID)throws SQLException {
        String query = "SELECT PRODUCTNAME FROM ORDERLINEITEMS WHERE ORDERID = " + OrderID;
        PreparedStatement Statement = conn.prepareStatement(query);
        ResultSet rs = Statement.executeQuery();
//        String[] ProductName_Arr;
        while (rs.next()){
//            int row = rs.getRow();
            String ProductName = rs.getString("PRODUCTNAME");
//            ProductName_Arr[row] = ProductName; 
            return ProductName; 
        }
        
        return null; 
    }
    
}

    /* Create 
       Read
       Update
       Delete

       Add item to cart - check in Order line items if there are any statuses of 'saved' matching to userID. 
            if no, add entry to Orders table. Add entry to Order Line Items. 
            if yes, grab order ID number and add entry to Order Line Items. 

       Read items from cart - check status 'saved' and userID from Orders. 
            if exists, gather all rows attaining to userID and Order ID from order line items. List product name. 
            if no, display error message. 

        Delete item from cart - 

        */  

   
