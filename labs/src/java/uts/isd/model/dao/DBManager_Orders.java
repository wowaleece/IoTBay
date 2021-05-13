/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;
import uts.isd.model.User;
import java.sql.*;

/**
 *
 * @author alice_zly8mn7
 */

public class DBManager_Orders {

    private Statement st;

    public DBManager_Orders(Connection conn) throws SQLException {       
       st = conn.createStatement();   
    }
    // Saving an order is reserved for customers with a registered ID. Otherwise, order is saved as a cart in a session for Guests.
    // If a registered user wants to save an order, query will check if order exists already in their account and use old order details, but just add more item lines to it. 
    public void saveOrder(String UserID, String OrderStatus, String StreetName, String UnitNumber, String Suburb, int Postcode, String AddressState, boolean BillingAddress, String[] ProductName)throws SQLException {
        int check;
        check = st.executeUpdate("WHERE EXISTS (SELECT OrderID FROM Orders WHERE UserID = " + UserID + "and OrderStatus = 'Saved')");
        // this checks if user has an existing order saved in their database. Return 1 if there is existing order, 0 is there is none. 
        if(check == 1){
            String OrderID = getOrderID(UserID);   
            updateOrder(UserID, OrderID, OrderStatus, StreetName, UnitNumber, Suburb, Postcode, AddressState, BillingAddress, "0", "0", 0);
            addOrderLineItem(UserID, "Saved", ProductName);
        } else {
            String query = "INSERT INTO Orders ('UserID', 'OrderStatus', 'StreetName', 'UnitNumber', 'Suburb', 'Postcode', 'AddressState', 'BillingAddress') VALUES ('" + UserID+ "', 'Saved', '" + StreetName+"', '" + UnitNumber +"', '" + Suburb +"', '"+Postcode+"', '"+AddressState+"', '"+BillingAddress+"'";            
            st.executeUpdate(query);
            addOrderLineItem(UserID, "Saved", ProductName);
        }
    }
   
    public String getOrderID(String UserID)throws SQLException {
        try {
            ResultSet rs = st.executeQuery("SELECT OrderID FROM Orders WHERE UserID = " + UserID + "and OrderStatus = 'Saved'");
            while (rs.next()) {
                String orderID = rs.getString("OrderID");
                System.out.println(orderID);
                return orderID;
            }
        } catch (SQLException e) {
            System.out.println("No order ID found");
        }
        return null;
    }   
    
    public void findOrder()throws SQLException {
        st.executeUpdate("sql query here");
    }
    
    public void updateOrder(String UserID, String OrderID, String OrderStatus, String StreetName, String UnitNumber, String Suburb, int Postcode, String AddressState, boolean BillingAddress, String NameonCard, String CardType, float TotalPrice) throws SQLException {
        String query = "UPDATE Orders SET UserID = '" + UserID + "', OrderID = '" + OrderID + "' , StreetName = '" + StreetName + "' , UnitNumber = '" + UnitNumber +"' , Suburb = '" + Suburb + "' , Postcode = '" + Postcode + "' , AddressState = '" + AddressState + "', BillingAddress = '" + BillingAddress +"', NameonCard = '" + NameonCard +"' , CardType = '" + CardType +"' , TotalPrice = '" + TotalPrice +"' ";   
        st.executeUpdate(query);
    }

    public void deleteOrder(String OrderID, String OrderStatus)throws SQLException {
        String query = "UPDATE Orders SET OrderStatus = 'Invalid' WHERE OrderID = '" + OrderID+"'";
        st.executeUpdate(query);
    }
    
    public float getTotalPrice(float[] unitPrice){
        float total_price = 0; 
        for (int i = 0; i < unitPrice.length; i++){
            total_price = total_price + unitPrice[i];
        }
        return total_price; 
    }
    
    public void addOrderLineItem(String UserID, String Status, String[] ProductName)throws SQLException {
        int check;
        check = st.executeUpdate("WHERE EXISTS (SELECT OrderID FROM Orders WHERE UserID = " + UserID + "and OrderStatus = 'Saved')");
        // this checks if user has an existing order saved in their database. Return 1 if there is existing order, 0 is there is none. 
        if(check == 1){
            String OrderID = getOrderID(UserID);
            String query = "UPDATE OrderLineItems SET UserID = '" + UserID+"', OrderID = '"+OrderID+"', Status = 'Saved', ProductName = '" + ProductName + "', UnitPrice = '" + UnitPrice + "'";
            st.executeUpdate(query);
        } else {
            String query = " ";
        }
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

   
