/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  alice_zly8mn7
 * Created: 27/04/2021
 */

CREATE TABLE Orders (
    OrderID BIGINT NOT NULL PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY 
        (START WITH 1, INCREMENT BY 1),
    UserID varchar(20) NOT NULL, 
    OrderTime timestamp DEFAULT CURRENT_TIMESTAMP,
--     OrderDate date, we do not need date because OrderTime accounts for date also. 
    OrderStatus varchar(20) DEFUALT, 
    ShippingStatus varchar(20) DEFAULT 'Unsent', 
    PaymentStatus varchar(20) DEFAULT 'Unpaid', 
    StreetName varchar(30), 
    UnitNumber varchar(10), 
    Suburb varchar(30), 
    Postcode int, 
    AddressState varchar(20), 
    BillingAddress boolean, 
    NameonCard varchar(30),
    CardType varchar(20), 
    TotalPrice float
--     PRIMARY KEY (OrderID)
);

CREATE TABLE OrderLineItems (
    UserID varchar(20) NOT NULL, 
    OrderID int,
    Status varchar(20),
    ProductName varchar(20), 
    UnitPrice float
--     PRIMARY KEY (UserID)
);
