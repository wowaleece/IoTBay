DROP TABLE LOGS;
DROP TABLE CUSTOMERS;
DROP TABLE USERS;
DROP TABLE ADDRESSES;

CREATE TABLE "ADDRESSES" (
	"ADDRESSID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
        ,"ADDRESS" VARCHAR(30)
);

CREATE TABLE "USERS" (
	 "USERID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
	,"EMAIL" VARCHAR(30) UNIQUE NOT NULL
	,"PASSWORD" VARCHAR(30) unique NOT NULL
	,"UTYPE" VARCHAR(30) -- type of user
	,"PHONENO" VARCHAR(22) -- allowing for 15 for international support + 7 for "ext" + actual extention() (
);

CREATE TABLE "CUSTOMERS" (
    "CUSTOMERID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
    ,"USERID" INT 
    ,"FNAME" VARCHAR(30)
    ,"LNAME" VARCHAR(30)
    ,"TITLE" VARCHAR(30)
    ,"SEX" VARCHAR(30)
    ,"DOB" VARCHAR(30)
	,"ADDRESSID" INT 
        ,FOREIGN KEY (ADDRESSID) REFERENCES ADDRESSES(ADDRESSID)
    --,"regDate" DATE(1)
    ,FOREIGN KEY (userID) REFERENCES USERS(userID)
);

CREATE TABLE "LOGS" (
	"LOGID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
	,"USERID" INT
	,"LOGTIME" TIMESTAMP
	,"ACTIVITYTYPE" VARCHAR(30)
	,"ACTIVITYDETAILS" VARCHAR(60)
	,FOREIGN KEY (USERid) REFERENCES USERS(USERID)
);

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


INSERT INTO USERS (email, password, uType)
VALUES ('james', 'smith', 'Customer')
      ,('smith', 'Dickson', 'Customer')
      ,('bobby', 'Jerry', 'Customer')
      ,('Bobby', 'Bart', 'Customer')
      ,('Jack', 'Graham', 'Admin')
;
INSERT INTO CUSTOMERS (userID, fname, lname)
VALUES (2,'james','smith')
      ,(4,'smith','smith')
      ,(5,'bobby','smith')
      ,(3,'Bobby','smith')
;

INSERT INTO ADDRESSES (ADDRESS) VALUES ('fuck'), ('meme'),('suck'),('ass');