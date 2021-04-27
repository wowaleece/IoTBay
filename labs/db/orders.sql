/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  alice_zly8mn7
 * Created: 27/04/2021
 */

CREATE TABLE orders (
    OrderID varchar(20) NOT NULL,
    OrderTime timestamp DEFAULT CURRENT_TIMESTAMP,
    OrderDate date,
    isPaid boolean, 
    StreetName varchar(30), 
    UnitNumber varchar(10), 
    Suburb varchar(30), 
    Postcode int, 
    AddressState varchar(20), 
    BillingAddress boolean, 
    NameonCard varchar(30),
    CardType varchar(20), 
    FirstName varchar(20), 
    LastName varchar(20), 
    EmailAddress varchar(50),
    PRIMARY KEY (OrderID)
);
