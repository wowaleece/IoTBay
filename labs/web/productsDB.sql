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
CREATE TABLE "PRODUCTS"( 
    "PRODUCTID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    "PRODUCTNAME" VARCHAR(30) NOT NULL, 
    "STOCKLEVEL" VARCHAR(10) NOT NULL, 
    "UNITPRICE" FLOAT NOT NULL, 
    "CATEGORY" VARCHAR(30) NOT NULL
);                                                                      

INSERT INTO "PRODUCTS" 
VALUES (1, 'Arduino', 'Low', 30, 'Micro-Controller')
, (2, 'BME180', 'Low', 15, 'Sensor')
, (3, 'Motion Sensor', 'Low', 5, 'Sensor'); 