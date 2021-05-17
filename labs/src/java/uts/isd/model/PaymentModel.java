/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;


/**
 *
 * @author marinasantanelli
 */
public class PaymentModel implements Serializable{
    
    UUID id;
    String paymentMethod;
    int cardNumber;
    int cvv;
    String nameOnCard;
    String cardExpiry;
    String paymentDate;
    
    
    //constructors
    public PaymentModel() {
        this.id = UUID.randomUUID();
    }
    
    // method for making a payment
    public PaymentModel(String paymentMethod, int cardNumber, int cvv, String nameOnCard, String cardExpiry, String paymentDate) {
         this();
         this.paymentMethod = paymentMethod;
         this.cardNumber = cardNumber;
         this.cvv = cvv;
         this.nameOnCard = nameOnCard;
         this.cardExpiry = cardExpiry;
         this.paymentDate = paymentDate;
    }

    
    // getters and setters
    
    public PaymentModel(UUID id) {
        this.id = id;
    }
    
   
    public UUID getID() {
        return id;
    }
   
    public void setID(UUID id) {
        this.id = id;
    }
    
   
     public String getPaymentMethod() {
        return paymentMethod;
    }
        
     
     public void setPaymentMethod(String paymentMethod) {
         this.paymentMethod = paymentMethod;
     }
    
    
     public String getNameOnCard() {
        return nameOnCard;
    }    
     
  
    public int getCardNumber() {
        return cardNumber;
    }
    
    
    public int getCVV() {
        return cvv;
    }
    

    public String getCardExpiry() {
        return cardExpiry; 
            //  mm-yy format
    }

   
    
}
    
