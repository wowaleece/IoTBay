package uts.isd.model.dao;

import uts.isd.model.User;
import uts.isd.model.Address;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.apache.derby.client.am.DateTime;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

    private Connection conn; // using connection and prepared statements instead of dynamic statement 
                             // https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html

    public DBManager(Connection conn) throws SQLException {       
        this.conn = conn;
    }

    //Find user by email and password in the database   
    public User findUser(int userID) throws SQLException {  
        String sql = "SELECT email, utype, phoneno, addressid FROM users WHERE userID = ?"; //and isValid = true // validTo < sysdate
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, userID);
        ResultSet result = statement.executeQuery();  //search database for matching email hash pair
        User user = null;
        if (result.next()) {
            user = new User(result.getString("email"),result.getString("utype"),result.getString("phoneno"),result.getInt("addressID"));
        } // if the result is not null, get userID
        
        return user;   
    }
    
    public void addUser(String email, String uType, String phoneNo){
        
    }
    
    //Add a user-data into the database   
    public void addCustomer(String email, String name, String gender, String favcol) throws SQLException {                   //code for add-operation       
        // should just do this as a stored procedure.

        //get address ID
        
        //add generic user
        
        //add registered user
        
        
        //add login entry


    }

    //update a user details in the database   
    public void updateUser( String email, String name, String password, String gender, String favcol) throws SQLException {       
        //code for update-operation   

    }       

    //delete a user from the database   
    public void deleteUser(String email) throws SQLException{       
        //code for delete-operation   

    }
    
    public void log(int userID, String actType, String actDesc) throws SQLException {
        OffsetDateTime odt = OffsetDateTime.now( ZoneOffset.UTC ) ;  // Capture the current moment in UTC.

        String sql = "Insert into logs (userid,logTime,activityType,activityDetails) ";
        sql = sql +  "          Values (     ?,      ?,           ?,              ?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, userID);
        st.setObject(2, odt);
        st.setString(3, actType);
        st.setString(3, actDesc);
        st.executeUpdate();
    }//log
   

}