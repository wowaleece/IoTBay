DROP TABLE LOGS;
DROP TABLE USERS;
DROP TABLE CUSTOMERS;
DROP TABLE ADDRESSES;
DROP TABLE PRODUCTS;

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
       ,"CUSTOMERID" INT 
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
    "PRODUCTNAME" VARCHAR(30) NOT NULL, 
    "QUANTITY" INT NOT NULL, 
    "STOCKLEVEL" VARCHAR(10) NOT NULL, 
    "UNITPRICE" FLOAT NOT NULL, 
    "CATEGORY" VARCHAR(30) NOT NULL
);                                                                      

INSERT INTO "PRODUCTS" (PRODUCTNAME,QUANTITY, STOCKLEVEL, UNITPRICE, CATEGORY)
VALUES ( 'Arduino', 1,'Low', 30, 'Micro-Controller')
, ( 'BME180', 1,'Low', 15, 'Sensor')
, ( 'Motion Sensor', 2, 'Low', 5, 'Sensor'); 

INSERT INTO CUSTOMERS (fname, lname)
VALUES ('james','smith')
      ,('smith','smith')
      ,('bobby','smith')
      ,('Bobby','smith')
;

INSERT INTO USERS (email, password, uType,customerID)
VALUES ('james', 'smith', 'Customer',1)
      ,('smith', 'Jackson', 'Customer',2)
      ,('bobby-2@student.uts.edu.au', 'Jerry', 'Customer',3)
      ,('Bobby@hotmail.com.au', 'Bart', 'Customer',4)
      ,('Jack@gmail.com', 'Graham', 'Admin',null)
      ,('trades@gmail.com', 'Graham', 'Customer',null)

;


INSERT INTO ADDRESSES (STREETNAME) VALUES ('address'), ('address'),('address'),('address');