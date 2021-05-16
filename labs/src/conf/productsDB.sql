/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Kayla Gelman
 * Created: 08/05/2021
 */


/* Products database */

DROP TABLE "PRODUCTS"; 

CREATE TABLE "PRODUCTS"( 
    "PRODUCTID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    "PRODUCTNAME" VARCHAR(30) NOT NULL, 
    "QUANTITY" INT NOT NULL, 
    "STOCKLEVEL" VARCHAR(10) NOT NULL, 
    "UNITPRICE" FLOAT NOT NULL, 
    "CATEGORY" VARCHAR(30) NOT NULL
);                                                                      

INSERT INTO "PRODUCTS" (PRODUCTNAME, STOCKLEVEL, UNITPRICE, CATEGORY)
VALUES ( 'Arduino', 1,'Low', 30, 'Micro-Controller')
, ( 'BME180', 1,'Low', 15, 'Sensor')
, ( 'Motion Sensor', 2, 'Low', 5, 'Sensor'); 