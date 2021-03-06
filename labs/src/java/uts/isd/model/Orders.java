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
public class Orders implements Serializable {
    
    private int OrderID;
    private String UserID;
    private String Status; 
    private String ShippingStatus;
    private String PaymentStatus; 
    private String OrderTime; 
    private String StreetName; 
    private String UnitNumber; 
    private String Suburb; 
    private int Postcode; 
    private String AddressState; 
    private boolean BillingAddress; 
    private String NameonCard; 
    private String CardType; 
    private float TotalPrice; 

    public Orders(int OrderID, String UserID, String Status, String ShippingStatus, String PaymentStatus, String OrderTime, String StreetName, String UnitNumber, String Suburb, int Postcode, String AddressState, boolean BillingAddress, String NameonCard, String CardType, float TotalPrice) {
        this.OrderID = OrderID;
        this.UserID = UserID;
        this.Status = Status;
        this.ShippingStatus = ShippingStatus; 
        this.PaymentStatus = PaymentStatus;
        this.OrderTime = OrderTime;
        this.StreetName = StreetName;
        this.UnitNumber = UnitNumber;
        this.Suburb = Suburb;
        this.Postcode = Postcode;
        this.AddressState = AddressState;
        this.BillingAddress = BillingAddress;
        this.NameonCard = NameonCard;
        this.CardType = CardType;
        this.TotalPrice = TotalPrice;
    }
    
     public Orders(ResultSet rs)
    {
        try
        {
            this.OrderID = rs.getInt("ORDERID");
            this.UserID = rs.getString("USERID");  
            this.Status = rs.getString("ORDERSTATUS"); 
            this.ShippingStatus = rs.getString("SHIPPINGSTATUS");  
            this.PaymentStatus = rs.getString("PAYMENTSTATUS");
            this.OrderTime = (rs.getTimestamp("ORDERTIME")).toString(); 
            this.StreetName= rs.getString("STREENAME"); 
            this.UnitNumber = rs.getString("UNITNUMBER");
            this.Suburb = rs.getString("SUBRUB");  
            this.Postcode = rs.getInt("POSTCODE"); 
            this.AddressState = rs.getString("ADDRESSSTATE");  
            this.BillingAddress= rs.getBoolean("BILLINGADDRESS"); 
            this.NameonCard= rs.getString("NAMEONCARD");            
            this.CardType= rs.getString("CARDTYPE"); 
            this.TotalPrice= rs.getFloat("TOTALRPICE");                    
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
        public String getShippingStatus() {
        return ShippingStatus;
    }

    public void setShippingStatus(String ShippingStatus) {
        this.ShippingStatus = ShippingStatus;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }
    
    

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String OrderTime) {
        this.OrderTime = OrderTime;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String StreetName) {
        this.StreetName = StreetName;
    }

    public String getUnitNumber() {
        return UnitNumber;
    }

    public void setUnitNumber(String UnitNumber) {
        this.UnitNumber = UnitNumber;
    }

    public String getSuburb() {
        return Suburb;
    }

    public void setSuburb(String Suburb) {
        this.Suburb = Suburb;
    }

    public int getPostcode() {
        return Postcode;
    }

    public void setPostcode(int Postcode) {
        this.Postcode = Postcode;
    }

    public String getAddressState() {
        return AddressState;
    }

    public void setAddressState(String AddressState) {
        this.AddressState = AddressState;
    }

    public boolean isBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(boolean BillingAddress) {
        this.BillingAddress = BillingAddress;
    }

    public String getNameonCard() {
        return NameonCard;
    }

    public void setNameonCard(String NameonCard) {
        this.NameonCard = NameonCard;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String CardType) {
        this.CardType = CardType;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(float TotalPrice) {
        this.TotalPrice = TotalPrice;
    }
    
    
}


