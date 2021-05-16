/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.util.UUID;

/**
 *
 * @author marinasantanelli
 */
public class TransactionModel {
    UUID transactionID;
    PaymentModel paymentMethod;
    Address billingAddress;
    double totalPrice;
    
    
    //constructorrs
    
    public TransactionModel() {
        transactionID = UUID.randomUUID();
    }
    
    
    public TransactionModel(String transactionID, PaymentModel paymentMethod, Address billingAddress, double totalPrice) {
        this.transactionID = UUID.fromString(transactionID);
        this.paymentMethod = paymentMethod;
        this.billingAddress = billingAddress;
        this.totalPrice = totalPrice;
    }
    
    
    
    public TransactionModel(PaymentModel paymentMethod, Address billingAddress, double totalPrice) {
        this(UUID.randomUUID().toString(), paymentMethod, billingAddress, totalPrice);
    }
    
    
}
