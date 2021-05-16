package uts.isd.model.dao;

import uts.isd.model.User;
import uts.isd.model.Address;
import java.sql.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.client.am.DateTime;
import uts.isd.model.Log;
import uts.isd.model.Product;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBCustomer {

    private Statement st; 
    private Connection conn; // using connection and prepared statements instead of dynamic statement 
                             // https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html

    public DBCustomer(Connection conn) throws SQLException {       
        this.conn = conn;
    }
    /*
    public DBCustomer(DBManager manager) throws SQLException {
        this.conn = manager.getConn();
    }
    */
    
    
    public int addCustomer(String email, String password, String phoneNo, String fName, String lName, String sex, Date dob, int addressID) throws SQLException {
        DBManager manager = new DBManager(conn);
        int userID = 0;
        int customerID;
        
        try{
            userID = manager.addUser(email, password, "Customer", phoneNo);
            customerID = addCustomer(userID, fName, lName, sex, dob);
            return customerID;
        } catch (SQLException ex){
            try{
                if(userID != 0) manager.deleteUser(userID);
            }   catch (DataAccessException dex) {
                Logger.getLogger(DBCustomer.class.getName()).log(Level.SEVERE, null, dex);
            }
            throw (SQLException) ex;
        }
    }
    
    
    /**
     * add registered customer
     * @param userID
     * @param fName
     * @param lName
     * @param sex
     * @param dob
     * @return customerID
     * @throws SQLException 
     */
    public int addCustomer(int userID, String fName, String lName, String sex, Date dob) throws SQLException {                   //code for add-operation       
        String sql = "INSERT INTO customers (userID, fName, lName, sex, dob)"
                   + " VALUES ( ? , ? , ? , ? , ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, userID);
        statement.setString(2, fName); //need to add hash method later
        statement.setString(3, lName);
        statement.setString(4, sex);
        statement.setDate(5, dob);
        statement.executeUpdate();
        
        ResultSet rs = statement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
        
        //log(userID, "Registered", "" + userID + " Registered as a customer");
    }//addCustomer()
    
   
    /**
     *  add anonymous customer
     * @param fName
     * @param lName
     * @param title
     * @param sex
     * @param dob
     * @param addressID
     * @return int customerID of new customer
     * @throws SQLException 
     */
    public int addCustomer( String fName, String lName, String title, String sex, String dob, int addressID) throws SQLException {                   //code for add-operation       
        String sql = "INSERT INTO customers ( fName, lName, title, sex, dob, addressid)"
                   + " VALUES ( ? , ? , ? , ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, fName); //need to add hash method later
        statement.setString(2, lName);
        statement.setString(3, title);
        statement.setString(4, sex);
        statement.setString(5, dob);
        statement.setInt(6, addressID);
        statement.executeUpdate();
        
        ResultSet rs = statement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }//addCustomer()
    
   
    public void updateCustomer(Integer customerID, String fName, String lName, String title, String sex, Date dob, Integer addressID) throws SQLException {                   //code for add-operation       
        String sql = "UPDATE customers "
                    + "SET fName = ?, lName = ?, title = ?, sex = ?, dob = ?, addressID = ?"
                    + "WHERE customerID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, fName); //need to add hash method later
        statement.setString(2, lName);
        statement.setString(3, title);
        statement.setString(4, sex);
        statement.setDate(5, dob);
        statement.setInt(6, addressID);
        statement.setInt(7, customerID);
        statement.executeUpdate();
    }//updateCustomer()
    
    public void updateCustomerAddress(int customerID, Integer addressID) throws SQLException {                   //code for add-operation       
        String sql = "UPDATE customers "
                    + "SET addressID = ?"
                    + "WHERE customerID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, addressID);
        statement.setInt(2, customerID);
        statement.executeUpdate();
    }//updateCustomer()
    
    public void deleteCustomer(int customerID) throws SQLException {                   //code for add-operation       
       
    }//deleteCustomer()
    
    

    
   

}