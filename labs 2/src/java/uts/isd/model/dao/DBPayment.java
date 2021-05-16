/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import com.sun.javafx.util.Logging;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import uts.isd.model.Orders;
import uts.isd.model.PaymentModel;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;

/**
 *
 * @author marinasantanelli
 */
public class DBPayment {
    private Statement st;
    
    private Connection conn; // using connection and prepared statements instead of dynamic statement 
                             // https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html

    public DBPayment(Connection conn) throws SQLException, ClassNotFoundException {   
        DBConnector connector = new DBConnector();
        this.conn = conn;
    }
    public void close() throws SQLException {
        this.conn.close();
        
}
    
    public Integer getOrderID() throws SQLException {
       int orderId;
       String fetch = "select max(ORDERIDpayment) FROM APP.Orders" ;
       ResultSet rs = st.executeQuery(fetch);
       if (rs.next()) {
            orderId = rs.getInt(1);
            return orderId;
       } else {
           return 0;
       }
    }

    
    
    public void addPayment( Integer OrderId, String paymentMethod, Integer cardNumber, Integer cvv, String nameOnCard, String cardExpiry, String paymentDate) throws SQLException {
        st.executeUpdate("INSERT INTO APP.PAYMENT " + "VALUES (DEFAULT , " + OrderId + ",'" + paymentMethod + "', " + cardNumber + ", " + cvv + ",'" + nameOnCard + "', '" + cardExpiry + "', '" + paymentDate + "') ");
    }

    
    
    public int getPaymentId() throws SQLException {
       int paymentId;
       String fetch = "select MAX(PAYMENTID) FROM APP.PAYMENT" ;
       ResultSet rs = st.executeQuery(fetch);
       if (rs.next()) {
            paymentId = rs.getInt(1);
            return paymentId;
       } else {
           return 0;
       }
    }
    
    
    public Orders findOrder(int oID, int uID) throws SQLException {
        String fetch = "select * from APP.ORDERS where orderID = " + oID + " and userID='" + uID + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {  // reads every row in USERS table and gets the result by index and stores them into Strings
            int OrderID = rs.getInt(1);
            int UserID = rs.getInt(2);
            if (OrderID == oID && UserID == uID) {
                String Status = rs.getString(3);
                String ShippingStatus = rs.getString(4);
                String OrderTime = rs.getString(5);
                String StreetName = rs.getString(6);
                String UnitNumber = rs.getString(7);
                String Suburb = rs.getString(8);
                Integer Postcode = rs.getInt(9);
                String AddressState = rs.getString(10);
                Boolean BillingAddress = rs.getBoolean(11);
                String NameonCard = rs.getString(12);
                String CardType = rs.getString(13);
                Float TotalPrice = rs.getFloat(14);
                return new Orders(OrderID, UserID, Status, ShippingStatus, OrderTime, StreetName, UnitNumber, Suburb, Postcode, AddressState, BillingAddress, NameonCard, CardType, TotalPrice);
                

            }
        }
        return null;
    }
    
    
    public PaymentModel findPaymentId(Integer paymentId) throws SQLException {       
       String fetch = "select * from APP.PAYMENT WHERE PAYMENTID = "+paymentId+" ";
       ResultSet rs = st.executeQuery(fetch);
       
       while(rs.next()){
           Integer payment_Id = rs.getInt(1);
           if(payment_Id.equals(paymentId)){
                String paymentMethod  = rs.getString(3);
                Integer cardNumber = rs.getInt(4);
                Integer cvv = rs.getInt(5);
                String nameOnCard = rs.getString(6);
                String expiryDate = rs.getString(7);
                String datePaid = rs.getString(8);
                return new PaymentModel(paymentMethod, cardNumber, cvv, nameOnCard, expiryDate, datePaid );
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
            System.out.println("No price found");
        }
        
        return TotalPrice;   
    }
  
    
    
}