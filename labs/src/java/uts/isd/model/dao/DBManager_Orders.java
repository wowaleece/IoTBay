/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

/**
 *
 * @author alice_zly8mn7
 */
public class DBManager_Orders {
    
   
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
    
}
