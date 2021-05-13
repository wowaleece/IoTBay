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

    DBManager_Orders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // Saving an order is reserved for customers with a registered ID. Otherwise, order is saved as a cart in a session for Guests.
    // If a registered user wants to save an order, query will check if order exists already in their account and use old order details, but just add more item lines to it. 
    public void saveOrder(String UserID, String OrderStatus, String StreetName, String UnitNumber, String Suburb, int Postcode, String AddressState, boolean BillingAddress, String[] ProductName)throws SQLException {
        int check;
        check = st.executeUpdate("WHERE EXISTS (SELECT OrderID FROM Orders WHERE UserID = " + UserID + "and OrderStatus = 'Saved');");
        // this checks if user has an existing order saved in their database. Return 1 if there is existing order, 0 is there is none. 
        if(check == 1){
            String OrderID = getOrderID(UserID);   
            updateOrder(UserID, OrderID, OrderStatus, StreetName, UnitNumber, Suburb, Postcode, AddressState, BillingAddress, "0", "0");
            addOrderLineItem(UserID, OrderID, "Saved", ProductName);
        } else {
            String query = "INSERT INTO Orders ('UserID', 'OrderStatus', 'StreetName', 'UnitNumber', 'Suburb', 'Postcode', 'AddressState', 'BillingAddress') VALUES ('" + UserID+ "', 'Saved', '" + StreetName+"', '" + UnitNumber +"', '" + Suburb +"', '"+Postcode+"', '"+AddressState+"', '"+BillingAddress+"');";            
            st.executeUpdate(query);
            String OrderID = getOrderID(UserID);
            addOrderLineItem(UserID, OrderID, "Saved", ProductName);
        }
    }
   
    public String getOrderID(String UserID)throws SQLException {
        try {
            ResultSet rs = st.executeQuery("SELECT OrderID FROM Orders WHERE UserID = " + UserID + "and OrderStatus = 'Saved';");
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
        st.executeUpdate("stiill need to find a way to filter through");
    }
    
    public void updateOrder(String UserID, String OrderID, String OrderStatus, String StreetName, String UnitNumber, String Suburb, int Postcode, String AddressState, boolean BillingAddress, String NameonCard, String CardType) throws SQLException {
        float TotalPrice = getTotalPrice(OrderID);
        String query = "UPDATE Orders SET UserID = '" + UserID + "', OrderID = '" + OrderID + "', TotalPrice = '" + TotalPrice +"' , StreetName = '" + StreetName + "' , UnitNumber = '" + UnitNumber +"' , Suburb = '" + Suburb + "' , Postcode = '" + Postcode + "' , AddressState = '" + AddressState + "', BillingAddress = '" + BillingAddress +"', NameonCard = '" + NameonCard +"' , CardType = '" + CardType +"';";   
        st.executeUpdate(query);
    }

    public void deleteOrder(String OrderID, String OrderStatus)throws SQLException {
        String query = "UPDATE Orders SET OrderStatus = 'Invalid' WHERE OrderID = '" + OrderID + "';";
        st.executeUpdate(query);
    }
    
    public float getTotalPrice(String OrderID)throws SQLException {
        float TotalPrice = 0; 
        // get all the product prices associated with OrderID
        try {
            String query = "SELECT UnitPrice FROM Products WHERE OrderID = '" + OrderID +"'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                float UnitPrice = rs.getFloat("UnitPrice");
                System.out.println(UnitPrice);
                TotalPrice = TotalPrice + UnitPrice;
            return TotalPrice;    
            }
        } catch (SQLException e) {
            System.out.println("No product details found");
        }
        return 0;   
    }
//    
//    public float getTotalPrice(float[] unitPrice){
//        float total_price = 0; 
//        for (int i = 0; i < unitPrice.length; i++){
//            total_price = total_price + unitPrice[i];
//        }
//        return total_price; 
//    }
//    
    public float getProductPrice(String ProductName) throws SQLException {
        try {
            String query = "SELECT UnitPrice FROM Products WHERE ProductName = '" + ProductName +"';";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                float UnitPrice = rs.getFloat("UnitPrice");
                System.out.println(UnitPrice);
                return UnitPrice;
            }
        } catch (SQLException e) {
            System.out.println("No product details found");
        }
        return 0;
    }

    public void addOrderLineItem(String UserID, String OrderID, String Status, String[] ProductName)throws SQLException {
        for (int i = 0; i < ProductName.length; i++){
            float ProductPrice = getProductPrice(ProductName[i]);
            String query = "INSERT INTO OrderLineItems (UserID, OrderID, Status, ProductName, UnitPrice) VALUES ('" + UserID+ "', '"+ OrderID+"','Saved','"+ProductName[i]+"', " + ProductPrice+");";
            st.executeUpdate(query);
        }
    }
    
    public void deleteOrderLineItem(String UserID, String OrderID, String[] ProductName)throws SQLException {
        for (int i = 0; i < ProductName.length; i++){
            String query = "UPDATE OrderLineItems SET Status = 'Invalid' WHERE UserID = '" + UserID + "' AND OrderID = '"+ OrderID + "';";
            st.executeUpdate(query);
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

   
