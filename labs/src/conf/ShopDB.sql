DROP TABLE LOGS;
DROP TABLE USERS;
DROP TABLE PRODUCTS;
DROP TABLE CUSTOMERS;
DROP TABLE ADDRESSES;
DROP TABLE ORDERLINEITEMS;
DROP TABLE ORDERS;



CREATE TABLE "ADDRESSES" (
        "ADDRESSID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
	,"STREETNAME" VARCHAR(30)
	,"UNITNUMBER" VARCHAR(30)
	,"SUBURB" VARCHAR(30)
	,"POSTCODE" INT
	,"STATE" VARCHAR(30)
	,"COUNTRY" VARCHAR(30)
	,"ACTIVE" BOOLEAN DEFAULT TRUE
);

CREATE TABLE "CUSTOMERS" (
    "CUSTOMERID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
    ,"FNAME" VARCHAR(30)
    ,"LNAME" VARCHAR(30)
    ,"TITLE" VARCHAR(30)
    ,"SEX" VARCHAR(30)
    ,"DOB" DATE
    ,"ADDRESSID" INT 
    ,"REGDATE" DATE 
    ,"ACTIVE" BOOLEAN DEFAULT TRUE
    ,FOREIGN KEY (ADDRESSID) REFERENCES ADDRESSES(ADDRESSID)
);

CREATE TABLE "USERS" (
        "USERID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
       ,"EMAIL" VARCHAR(30) UNIQUE NOT NULL
       ,"PASSWORD" VARCHAR(30) NOT NULL
       ,"UTYPE" VARCHAR(30) -- type of user
       ,"PHONENO" VARCHAR(22) -- allowing for 15 for international support + 7 for "ext" + actual extention() (
       ,"ACTIVE" BOOLEAN DEFAULT TRUE
       ,"CUSTOMERID" INT UNIQUE --unique should still allow for null values
       ,FOREIGN KEY (customerID) REFERENCES CUSTOMERS(customerID) 
       ON DELETE CASCADE
);

CREATE TABLE "LOGS" (
	"LOGID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
	,"USERID" INT
	,"LOGTIME" TIMESTAMP
	,"ACTIVITYTYPE" VARCHAR(30)
	,"ACTIVITYDETAILS" VARCHAR(60)
	,"RELATEDID" BIGINT
	,FOREIGN KEY (USERID) REFERENCES USERS(USERID)
	,FOREIGN KEY (RELATEDID) REFERENCES LOGS(LOGID)
);

CREATE TABLE "PRODUCTS"( 
    "PRODUCTID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    "PRODUCTNAME" VARCHAR(30) NOT NULL UNIQUE, --needed since orderlineitems takes name instead of ID
    "QUANTITY" INT NOT NULL, 
    "STOCKLEVEL" VARCHAR(10) NOT NULL, 
    "UNITPRICE" FLOAT NOT NULL, 
    "CATEGORY" VARCHAR(30) NOT NULL,
    CHECK (QUANTITY > 0)
);                                 

CREATE TABLE ORDERS (
    ORDERID BIGINT NOT NULL PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY ,
        --(START WITH 1, INCREMENT BY 1),
    USERID varchar(20) NOT NULL, 
    ORDERTIME timestamp DEFAULT CURRENT_TIMESTAMP,
    ORDERSTATUS varchar(20),
    SHIPPINGSTATUS varchar(20), 
    PAYMENTSTATUS varchar(20), 
    STREETNAME varchar(30), 
    UNITNUMBER varchar(10), 
    SUBURB varchar(30), 
    POSTCODE int, 
    ADDRESSSTATE varchar(20), 
    BILLINGADDRESS boolean, 
    NAMEONCARD varchar(30),
    CARDTYPE varchar(20), 
    TOTALPRICE float

);

CREATE TABLE ORDERLINEITEMS (
    ORDERLINEITEMID BIGINT NOT NULL PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY, 
        --(START WITH 1, INCREMENT BY 1),
    USERID varchar(20) NOT NULL, 
    ORDERID BIGINT,
    STATUS varchar(20), 
    PRODUCTNAME varchar(20), 
    UNITPRICE float,
    FOREIGN KEY (ORDERID) REFERENCES ORDERS(ORDERID)
    --constraint removed because we did not have time to 
    --FOREIGN KEY (PRODUCTNAME) REFERENCES PRODUCTS(PRODUCTNAME)

);
                                     

INSERT INTO "PRODUCTS" (PRODUCTNAME,QUANTITY, STOCKLEVEL, UNITPRICE, CATEGORY) 
VALUES ( 'Arduino', 1,'Low', 30, 'Micro-Controller')
, ( 'BME180', 1,'Low', 15, 'Sensor')
, ( 'Motion Sensor', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 1', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 2', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 3', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 4', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 5', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 6', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 7', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 8', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 9', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 10', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 11', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 12', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 13', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 14', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 15', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 16', 2, 'Low', 5, 'Sensor')
, ( 'Test Sensor 17', 2, 'Low', 5, 'Sensor'); 

INSERT INTO CUSTOMERS (fname, lname)
VALUES ('james','smith')
      ,('smith','smith')
      ,('bobby','smith')
      ,('Kayla','Gel')
      ,('Leah','Gel')
      ,('Tia','smith')
      ,('Irena','smith')
      ,('Alex','smith')
      ,('Simba','smith')
      ,('Marina','smith')
      ,('Alice','smith')
      ,('Atif','smith')
      ,('Sarah','smith')
      ,('marshall','smith')
      ,('Lilly','smith')
      ,('Bob','smith')
      ,('Ross','smith')
      ,('Ruby','smith')
      ,('Ishan','smith')
      ,('jared','smith')
;

INSERT INTO USERS (email, password, uType,customerID)
VALUES ('james', 'smith', 'Customer',1)
      ,('smith', 'Jackson', 'Customer',2)
      ,('bob12by-2@student.uts.edu.au', 'Jerry', 'Customer',3)
      ,('Bob326by@hotmail.com.au', 'Bart', 'Customer',4)
      ,('admin@gmail.com', 'Admin', 'Admin',null)
      ,('Jasdfck@gmail.com', 'Graham', 'Admin',null)
      ,('trade335s@gmail.com', 'Graham', 'Customer',null)
      ,('jojem', 'Jackson', 'Customer',5)
      ,('bobby-2@student.uts.edu.au', 'Jerry', 'Customer',6)
      ,('Bobwby@hotmail.com.au', 'Bart', 'Customer',7)
      ,('Jassck@gmail.com', 'Graham', 'Admin',null)
      ,('abc', 'Graham', 'Customer',null)
      ,('efg', 'Jackson', 'Customer',8)
      ,('hij@student.uts.edu.au', 'Jerry', 'Customer',9)
      ,('klm@hotmail.com.au', 'Bart', 'Customer',10)
      ,('tuv@gmail.com', 'Graham', 'Admin',null)
      ,('wxy@gmail.com', 'Graham', 'Customer',null)
      ,('qrz', 'Jackson', 'Customer',11)
      ,('tuv@student.uts.edu.au', 'Jerry', 'Customer',12)
      ,('wxy@hotmail.com.au', 'Bart', 'Customer',13)
      ,('andz@gmail.com', 'Graham', 'Admin',null)
      ,('never@gmail.com', 'Graham', 'Customer',null)

;

INSERT INTO ADDRESSES (STREETNAME) VALUES ('address'), ('address'),('address'),('address');

INSERT INTO ORDERS (USERID, ORDERSTATUS, STREETNAME, UNITNUMBER, SUBURB, POSTCODE, ADDRESSSTATE, BILLINGADDRESS, NAMEONCARD, CARDTYPE)
VALUES ('001', 'Saved', 'Mary', '7', 'Little', 2100, 'LAMB', true, 'Little Lamb', 'Amex')
      ,('002', 'Complete', 'Peanut', '1', 'Butter', 2121, 'JELLY', true, 'Time', 'Amex')
      ,('003', 'Saved', 'Bing', '10', 'Margarine', 4252, 'NSW', true, 'Bing', 'Mastercard')
      ,('004', 'Complete', 'Alice', '14', 'Meadowleaf', 1235, 'NSW', true, 'UM', 'Visa')
      ,('005', 'Saved', 'Mariam', '47', 'Mellow', 5346, 'NSW', true, 'MQ', 'Amex')
      ,('006', 'Complete', 'Gus', '575', 'Calming', 4656, 'NSW', true, 'UNSW', 'Mastercard')
      ,('007', 'Saved', 'John', '23', 'Storm', 3587, 'NSW', true, 'UTS', 'Visa')
      ,('008', 'Complete', 'Gary', '576', 'Hellstrom', 2347, 'NSW', true, 'Scholarship', 'Amex')
      ,('009', 'Saved', 'Barry', '12', 'Mindfulness', 7534, 'NSW', true, 'Fairy Godmother', 'Visa')
      ,('010', 'Complete', 'Harry', '76', 'Lightning', 9456, 'NSW', true, 'Someones', 'Mastercard')
      ,('011', 'Saved', 'Glary', '487', 'Relaxtown', 4534, 'NSW', true, 'Not mine', 'Amex')
      ,('012', 'Complete', 'Murray', '12', 'Thundercrack', 4564, 'NSW', true, 'Dracula', 'Amex')
      ,('013', 'Saved', 'Sherry', '54', 'Downtown', 1214, 'NSW', true, 'Minion', 'Mastercard')
      ,('014', 'Complete', 'Jerry', '96', 'Uptown', 8786, 'NSW', true, 'Banana', 'Amex')
      ,('015', 'Saved', 'Larry', '4', 'Middletown', 9786, 'NSW', true, 'Gwen Stefani', 'Amex')
      ,('016', 'Complete', 'Perry', '75', 'Westtown', 4563, 'NSW', true, 'Richgirl', 'Mastercard')
      ,('017', 'Saved', 'Carrie', '358', 'Southtown', 6575, 'NSW', true, 'Richboy', 'Visa')
      ,('018', 'Complete', 'Blairy', '32', 'Centretown', 8791, 'NSW', true, 'Mums', 'Visa')
      ,('019', 'Saved', 'Fairy', '278', 'Cornertown', 4564, 'NSW', true, 'Dad', 'Amex')
      ,('020', 'Complete', 'Cherry', '27', 'Littletown', 4564, 'NSW', true, 'Sugar Daddy', 'Visa');

INSERT INTO ORDERLINEITEMS (USERID, ORDERID, STATUS, PRODUCTNAME, UNITPRICE) VALUES 
    ('001', 1, 'Saved', 'iPhone', 699),
    ('001', 1, 'Saved', 'Guitar', 199),
    ('001', 1, 'Saved', 'Xylophone', 10),
    ('001', 1, 'Saved', 'Clarinet', 150),
    ('002', 2, 'Saved', 'Peanut Butter', 12),
    ('002', 2, 'Saved', 'Microphone', 30),
    ('002', 2, 'Saved', 'Keyboard', 140),
    ('002', 2, 'Saved', 'Computer', 800),
    ('002', 2, 'Saved', 'Monitor', 150),
    ('002', 2, 'Saved', 'Mouse', 50),
    ('003', 3, 'Saved', 'Samsung', 1200),
    ('003', 3, 'Saved', 'Earbuds', 250),
    ('003', 3, 'Saved', 'Case', 50),
    ('003', 3, 'Saved', 'Watch', 700),
    ('004', 4, 'Saved', 'Tablet', 500),
    ('004', 4, 'Saved', 'Raspberry Pi', 40),
    ('004', 4, 'Saved', 'Blueberry Pie', 90),
    ('004', 4, 'Saved', 'Key Lime Pie', 20),
    ('004', 4, 'Saved', 'Pumpkin Pie', 80),
    ('004', 4, 'Saved', 'Corn Pie', 20);

