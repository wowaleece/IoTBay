DROP TABLE USERS;
CREATE TABLE "USERS" ( 
	"USERID" INT NOT NULL PRIMARY KEY --AUTO_INCREMENT
	,"EMAIL" VARCHAR(30) UNIQUE NOT NULL
	,"HASH" VARCHAR(30) 
) -- ENGINE=InnoDB ??? 

;

INSERT INTO USERS 
VALUES (10,'james','smith')
      ,(4,'smith','smith')
      ,(6,'bobby','smith')
      ,(8,'Bobby','smith')
;