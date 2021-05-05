DROP TABLE LOGS;
DROP TABLE REGD_USERS;
DROP TABLE USERS;
DROP TABLE ADDRESSES;

CREATE TABLE "ADDRESSES" (
	"ADDRESSID" INT NOT NULL PRIMARY KEY
);

CREATE TABLE "USERS" (
	 "USERID" INT NOT NULL PRIMARY KEY --AUTO_INCREMENT
	,"EMAIL" VARCHAR(30) UNIQUE NOT NULL
	,"HAHS" VARCHAR(30) unique NOT NULL
	,"UTYPE" VARCHAR(30) -- type of user
	,"PHONENO" VARCHAR(22) -- allowing for 15 for international support + 7 for "ext" + actual extention() (
	,"ADDRESSID" INT 
        ,FOREIGN KEY (ADDRESSID) REFERENCES ADDRESSES(ADDRESSID)
);

CREATE TABLE "REGD_USERS" (
    "USERID" INT PRIMARY KEY 
    ,"USER_TYPE" VARCHAR(8) -- need to apply some kind of condition to this
    ,"FNAME" VARCHAR(30)
    ,"LNAME" VARCHAR(30)
    ,"TITLE" VARCHAR(30)
    ,"SEX" VARCHAR(30)
    ,"DOB" VARCHAR(30)
    --,"regDate" DATE(1)
    ,FOREIGN KEY (userID) REFERENCES USERS(userID)
);

CREATE TABLE "LOGS" (
	"LOGID" INT PRIMARY KEY
	,"USERID" INT
	,"LOGTIME" DATETIME
	,"ACTIVITYTYPE" VARCHAR(30)
	,"ACTIVITYDETAILS" VARCHAR(60)
	,FOREIGN KEY (USERid) REFERENCES USERS(USERID)
)


INSERT INTO USERS (userID, email, password, uType)
VALUES (10,'james', 'smith', 'Customer')
      ,(4, 'smith', 'smith', 'Customer')
      ,(6, 'bobby', 'smith', 'Customer')
      ,(8, 'Bobby', 'smith', 'Customer')
	  ,(7, 'Jack', 'Graham', 'Admin')
;
INSERT INTO REGD_USERS (userID, fname, lname)
VALUES (10,'james','smith')
      ,(4,'smith','smith')
      ,(6,'bobby','smith')
      ,(8,'Bobby','smith')
;
