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
            String OrderID = rs.getString("ORDERID");
            System.out.println(OrderID);
            check = 1; 
        }
        if (check == 1) {
            String OrderID = getOrderID(UserID);
            updateOrder(UserID, OrderID, OrderStatus, "0", StreetName, UnitNumber, Suburb, Postcode, AddressState, BillingAddress, "0", "0");
            addOrderLineItem(UserID, OrderID, "Saved", ProductName);
        }else{
            System.out.println(check);
            query = "INSERT INTO APP.ORDERS (USERID, ORDERSTATUS, STREETNAME, UNITNUMBER, SUBURB, POSTCODE, ADDRESSSTATE, BILLINGADDRESS) VALUES ('" + UserID+ "', 'Saved', '" + StreetName+"', '" + UnitNumber +"', '" + Suburb +"', "+Postcode+", '"+AddressState+"', "+BillingAddress+")";            
            st.executeUpdate(query);
        }    
    }
   
    public String getOrderID(String UserID)throws SQLException {
        String query = "SELECT ORDERID FROM APP.ORDERS WHERE USERID = '" + UserID + "' AND ORDERSTATUS = 'Saved'";
        PreparedStatement Statement = conn.prepareStatement(query);
        ResultSet rs = Statement.executeQuery();        
        while (rs.next()) {
            String orderID = rs.getString("ORDERID");
            System.out.println(orderID);
            return orderID;
        }
        return null;
    }   
    
    public Orders findOrder(String OrderID)throws SQLException { // Only searches via OrderID
        String sql = "SELECT * FROM APP.ORDERS WHERE ORDERID = '" + OrderID + "'";
        PreparedStatement Statement = conn.prepareStatement(sql);
        ResultSet rs = Statement.executeQuery();
        
        while (rs.next()){ 
            String ORDERID_TEMP = rs.getString(1); 
            if (ORDERID_TEMP.equals(OrderID)){
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
    
    public void updateOrder(String UserID, String OrderID, String OrderStatus, String ShippingStatus, String StreetName, String UnitNumber, String Suburb, int Postcode, String AddressState, boolean BillingAddress, String NameonCard, String CardType) throws SQLException {
        float TotalPrice = getTotalPrice(OrderID);
        String query = "UPDATE APP.ORDERS SET USERID = '" + UserID + "', ORDERID = '" + OrderID + "', TOTALPRICE = " + TotalPrice +" , STREETNAME = '" + StreetName + "' , UNITNUMBER = '" + UnitNumber +"' , SUBURB = '" + Suburb + "' , POSTCODE = " + Postcode + " , ADDRESSSTATE = '" + AddressState + "', BILLINGADDRESS = " + BillingAddress +", NAMEONCARD = '" + NameonCard +"' , CARDTYPE = '" + CardType +"'";   
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(2, UserID);
        statement.setString(4, OrderStatus);
        statement.setString(5, ShippingStatus);
        statement.setString(6, StreetName);
        statement.setString(7, UnitNumber);
        statement.setString(8, Suburb);
        statement.setInt(9, Postcode);
        statement.setString(10, AddressState);
        statement.setBoolean(11, BillingAddress);
        statement.setString(12, NameonCard);
        statement.setString(13, CardType);
        statement.setFloat(14, TotalPrice);
    }

    public void deleteOrder(String OrderID)throws SQLException {
        String query = "SELECT * FROM APP.ORDERS WHERE ORDERID = '" + OrderID + "'";
        PreparedStatement Statement = conn.prepareStatement(query);
        ResultSet rs = Statement.executeQuery();
        while (rs.next()){
            rs.updateString("ORDERSTATUS", "Invalid");
        }
    }
    
    public float getTotalPrice(String OrderID)throws SQLException {
        float TotalPrice = 0; 
        // get all the product prices associated with OrderID
        try {
            String query = "SELECT UNITPRICE FROM PRODUCTS WHERE ORDERID = '" + OrderID +"'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                float UnitPrice = rs.getFloat("UNITPRICE");
                System.out.println(UnitPrice);
                TotalPrice = TotalPrice + UnitPrice;
            }
        } catch (SQLException e) {
            System.out.println("No Product Details Found");
        }
        
        return TotalPrice;   
    }
  
    public float getProductPrice(String ProductName) throws SQLException {
        try {
            String query = "SELECT UNITPRICE FROM PRODUCTS WHERE PRODUCTNAME = '" + ProductName +"'";
            ResultSet rs = st.executeQuery(query);
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

    public void addOrderLineItem(String UserID, String OrderID, String Status, String ProductName)throws SQLException {
        float ProductPrice = getProductPrice(ProductName);
        String query = "INSERT INTO ORDERLINEITEMS (USERID, ORDERID, STATUS, PRODUCTNANE, UNITPRICE) VALUES ('" + UserID+ "', '"+ OrderID+"','Saved','"+ProductName+"', " + ProductPrice+")";
        st.executeUpdate(query);
    }
 
    public void deleteOrderLineItem(String UserID, String OrderID, String ProductName)throws SQLException {
        String query = "SELECT * FROM APP.ORDERLINEITEMS WHERE USERID = '" + UserID + "' AND ORDERSTATUS = 'Saved'";
        PreparedStatement Statement = conn.prepareStatement(query);
        ResultSet rs = Statement.executeQuery();
        while (rs.next()){
            rs.updateString("ORDERSTATUS", "Invalid");
        }
    }
    
}