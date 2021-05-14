DROP TABLE LOGS;
DROP TABLE CUSTOMERS;
DROP TABLE USERS;
DROP TABLE ADDRESSES;

CREATE TABLE "ADDRESSES" (
	"ADDRESSID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
        ,"STREETNAME" VARCHAR(30)
        ,"UNITNUMBER" VARCHAR(30)
        ,"SUBURB" VARCHAR(30)
        ,"POSTCODE" INT
        ,"STATE" VARCHAR(30)
        ,"COUNTRY" VARCHAR(30)
);

CREATE TABLE "USERS" (
	 "USERID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
	,"EMAIL" VARCHAR(30) UNIQUE NOT NULL
	,"PASSWORD" VARCHAR(30) UNIQUE NOT NULL
	,"UTYPE" VARCHAR(30) -- type of user
	,"PHONENO" VARCHAR(22) -- allowing for 15 for international support + 7 for "ext" + actual extention() (
        ,"ACTIVE" BOOLEAN DEFAULT TRUE
);

CREATE TABLE "CUSTOMERS" (
    "CUSTOMERID" INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
    ,"USERID" INT 
    ,"FNAME" VARCHAR(30)
    ,"LNAME" VARCHAR(30)
    ,"TITLE" VARCHAR(30)
    ,"SEX" VARCHAR(30)
    ,"DOB" DATE
    ,"ADDRESSID" INT 
    ,"REGDATE" DATE
    ,FOREIGN KEY (ADDRESSID) REFERENCES ADDRESSES(ADDRESSID)
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


INSERT INTO USERS (email, password, uType)
VALUES ('james', 'smith', 'Customer')
      ,('smith', 'Jackson', 'Customer')
      ,('bobby-2@student.uts.edu.au', 'Jerry', 'Customer')
      ,('Bobby@hotmail.com.au', 'Bart', 'Customer')
      ,('Jack@gmail.com', 'Graham', 'Admin')
;
INSERT INTO CUSTOMERS (userID, fname, lname)
VALUES (2,'james','smith')
      ,(4,'smith','smith')
      ,(5,'bobby','smith')
      ,(3,'Bobby','smith')
;

INSERT INTO ADDRESSES (STREETNAME) VALUES ('address'), ('address'),('address'),('address');