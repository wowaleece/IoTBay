DROP TABLE REGD_USERS;
DROP TABLE USERS;
DROP TABLE addresses;

CREATE TABLE "ADDRESSES" (
	"ADDRESSID" INT NOT NULL PRIMARY KEY
);

CREATE TABLE "USERS" (
	 "USERID" INT NOT NULL PRIMARY KEY --AUTO_INCREMENT
	,"EMAIL" VARCHAR(30) UNIQUE NOT NULL
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

INSERT INTO USERS (userID, email, uType)
VALUES (10,'james','smith')
      ,(4,'smith','smith')
      ,(6,'bobby','smith')
      ,(8,'Bobby','smith')
;
INSERT INTO REGD_USERS (userID, fname, lname)
VALUES (10,'james','smith')
      ,(4,'smith','smith')
      ,(6,'bobby','smith')
      ,(8,'Bobby','smith')
;