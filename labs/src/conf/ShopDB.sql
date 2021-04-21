DROP TABLE REGD_USERS;
DROP TABLE USERS;
DROP TABLE addresses;

CREATE TABLE "ADDRESSES" (
	"addressID" INT NOT NULL PRIMARY KEY
);

CREATE TABLE "USERS" (
	 "userIK" INT NOT NULL PRIMARY KEY --AUTO_INCREMENT
	,"userID" VARCHAR(30) UNIQUE NOT NULL
	,"uType" VARCHAR(30) -- type of user
	,"phoneNo" VARCHAR(22) -- allowing for 15 for international support + 7 for "ext" + actual extention() (
	,"addressID" INT 
        ,FOREIGN KEY ("addressID") REFERENCES ADDRESSES("addressID")
);

CREATE TABLE "REGD_USERS" (
    "userIK" INT PRIMARY KEY 
    ,"USER_TYPE" VARCHAR(8) -- need to apply some kind of condition to this
    ,"fname" VARCHAR(30)
    ,"lname" VARCHAR(30)
    ,"title" VARCHAR(30)
    ,"sex" VARCHAR(30)
    ,"dob" VARCHAR(30)
    --,"regDate" DATE(1)
    ,FOREIGN KEY ("userIK") REFERENCES USERS("userIK")
);