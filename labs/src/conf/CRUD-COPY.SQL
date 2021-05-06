("SELECT u.userID, u.email, u.utype, u.PHONENO"
                + " FROM app.users u INNER JOIN app.customers c on c.userid = u.userid"
                + " WHERE u.email = ? and u.hash = ?";)

INSERT INTO users (email, password, uType, phoneNo) 
			VALUES ( ?  , ?       , ?    , ?      )
;


UPDATE users 
SET email = ? , password = ? , uType = ? , phoneNo = ?
WHERE userID = ?;

INSERT INTO customers (userID, fName, lName, title, sex, dob, addressid)
			values 	( ? , ? , ? , ? , ?, ?) 
			;