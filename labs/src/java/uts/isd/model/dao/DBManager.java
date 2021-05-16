package uts.isd.model.dao;

import uts.isd.model.User;
import uts.isd.model.Address;
import java.sql.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.client.am.DateTime;
import uts.isd.controller.RegisterServlet;
import uts.isd.model.Log;
import uts.isd.model.Product;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

    /*private Statement st;*/ 
    private Connection conn; // using connection and prepared statements instead of dynamic statement 
                             // https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html

    public DBManager(Connection conn) throws SQLException {       
        this.conn = conn;
    }

    protected Connection getConn(){
        return conn;
    }
    
    public User findUser(int userID) throws SQLException {  
        String sql = "SELECT email, utype, phoneno FROM users WHERE userID = ?"; //and isValid = true // validTo < sysdate
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, userID);
        ResultSet result = statement.executeQuery();  //search database for matching email hash pair
        User user = null;
        if (result.next()) {
            user = new User(userID, result.getString("email"),result.getString("utype"),result.getString("phoneno"));
        } // if the result is not null, get userID
        
        return user;   
    }
    
    //Find user by email and password in the database   
    public User checkLogin(String email, String password) throws SQLException{
        String sql = "SELECT u.userID, u.email, u.utype, u.PHONENO"
                + " FROM app.users u"
                + " WHERE u.active = TRUE and u.email = ? and u.password = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password); //need to add hash method later
        
        ResultSet result = statement.executeQuery();  //search database for matching email hash pair
        User user = null;
        // if the result is not null, get userID
        if (result.next()) {user =  new User(result.getInt("userID"), result.getString("email"),result.getString("utype"),result.getString("phoneno"));} 
       
        return user;
    }
    
    //Add a user-data into the database   
    public int addUser(String email, String password, String uType, String phoneNo) throws SQLException{
        String sql = "INSERT INTO users (email, password, uType, phoneNo) "
                             + "  VALUES ( ?  , ?       , ?    , ?       )";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, email);
        statement.setString(2, password); //need to add hash method later
        statement.setString(3, uType);
        statement.setString(4, phoneNo);
        statement.executeUpdate();
        
        ResultSet rs = statement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }
    
    

    //update a user details in the database   
    public void updateUser( int userID, String password, String phoneNo) throws SQLException {       
        String sql = "UPDATE users " 
                   + "SET password = ? , phoneNo = ? " 
                   + "WHERE userID = ? " ;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, password); //need to add hash method later
        statement.setString(2, phoneNo);
        statement.setInt(3, userID);
        statement.executeUpdate();
        //code for update-operation   
        
    }
    public void updateUser(int userID, String phoneNo) throws SQLException {       
        String sql = "UPDATE users " 
                   + "SET phoneNo = ? " 
                   + "WHERE userID = ? " ;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, phoneNo);
        statement.setInt(2, userID);
        statement.executeUpdate();
    }

    /**
     * Delete user in emergency situations
     * @param userID
     * @throws DataAccessException 
     */  
    protected void deleteUser(int userID) throws DataAccessException{
        if(userID == 0) return; //0 indicates no userID to delete
        try{
            String sql = "DELETE FROM users WHERE userID = ?"; //and isValid = true // validTo < sysdate
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.executeUpdate();  //search database for matching email hash pair

        } catch (SQLException ex){
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException("Error deleting " + userID, ex);
        }
    }

    
    /**
     * 
     * @param userID
     * @param actType
     * @param actDesc
     * @return long logID / 0 if failed
     * @throws SQLException 
     */
    public long log(int userID, String actType, String actDesc) throws SQLException {
        Timestamp ts = new Timestamp(System.currentTimeMillis());  // Capture the current moment in UTC.

        String sql = "Insert into logs (userid,logTime,activityType,activityDetails) "
                            +  "Values (     ?,      ?,           ?,              ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userID);
        ps.setObject(2, ts);
        ps.setString(3, actType);
        ps.setString(4, actDesc);
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs != null && rs.next()) {
            return rs.getLong(1);
        } else {
            return 0;
        }
    }//log
    
    /**
     * 
     * @param userID
     * @param actType
     * @param actDesc
     * @param relatedID
     * @return logID long / 0 if failed.
     * @throws SQLException 
     */
    public long log(int userID, String actType, String actDesc, long relatedID) throws SQLException {
        Timestamp ts = new Timestamp(System.currentTimeMillis());  // Capture the current moment in UTC.

        String sql = "Insert into logs (userid,logTime,activityType,activityDetails,relatedID) "
                            +  "Values (     ?,      ?,           ?,              ?,        ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userID);
        ps.setObject(2, ts);
        ps.setString(3, actType);
        ps.setString(4, actDesc);
        ps.setLong(5, relatedID);
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs != null && rs.next()) {
            return rs.getLong(1);
        } else {
            return 0;
        }
    }//log
    
    
    /**
     * 
     * @param userID
     * @param from
     * @param to
     * @return ArrayList<Log> for given user ID and dates
     * @throws java.sql.SQLException
     */
    public List<Log> findLogs(int userID, Timestamp from, Timestamp to) throws SQLException {
        //Build list of log objects to return
        ArrayList<Log> logs = new ArrayList<Log>();
        
        String sql = "SELECT l.logID, l.userID, l.logTime, l.activityType, l.activityDetails"
                   + " FROM logs l"
                   + " WHERE l.userID = ? AND l.logTime > ? AND l.logTime < ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userID);
        ps.setTimestamp(2, from);
        ps.setTimestamp(3, to);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            logs.add(new Log(rs.getLong("logID"), rs.getInt("userID"), (Timestamp) rs.getObject("logTime"), rs.getString("activityType"), rs.getString("activityDetails")));
        }
        
        return logs;
    }
    
    

    
   

}