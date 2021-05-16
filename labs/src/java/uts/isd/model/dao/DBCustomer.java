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
import uts.isd.model.Customer;
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
    
    
    public Customer findCustomer(int customerID) throws SQLException {  
        String sql = "SELECT  fName, lName, sex, dob FROM customers WHERE customerID = ?"; //and isValid = true // validTo < sysdate
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, customerID);
        ResultSet result = statement.executeQuery();  //search database for matching email hash pair
        Customer customer = null;
        if (result.next()) {
            customer = new Customer(customerID,result.getString("fName"),result.getString("lName"),result.getString("sex"),result.getDate("dob"));
        } // if the result is not null, get userID
        
        return customer;   
    }
    
    public Customer findCustomerFull(int customerID) throws SQLException {  
        String sql = "SELECT  fName, lName, sex, dob, c.addressID, unitNumber, streetName, suburb, postcode, state, country "
                + " FROM customers c INNER JOIN addresses a on a.addressID = c.addressID "
                + " WHERE customerID = ?"; //and isValid = true // validTo < sysdate
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, customerID);
        ResultSet result = statement.executeQuery();  //search database for matching email hash pair
        Customer customer = null;
        Address address = null;
        if (result.next()) {
            address = new Address( 
                result.getInt("addressID"),
                result.getString("streetName"),
                result.getString("unitNumber"),
                result.getString("suburb"),
                result.getInt("postcode"),
                result.getString("state"),
                result.getString("country")
            );
            customer = new Customer(
                customerID,
                result.getString("fName"),
                result.getString("lName"),
                result.getString("sex"),
                result.getDate("dob"),
                address
            );
        } // if the result is not null, get userID
        
        return customer;   
    }
    
    public Customer findCustomerFromUser(int userID) throws SQLException {  
        String sql = "SELECT c.customerID, fName, lName, sex, dob, " //customer fields
                + " c.addressID, unitNumber, streetName, suburb, postcode, state, country" //address fields
                + " FROM users u "
                + " INNER JOIN customers c on u.customerID = c.customerID "
                + " INNER JOIN addresses a on a.addressID = c.addressID"
                + " WHERE c.active <> false AND u.userID = ?"; //and isValid = true // validTo < sysdate
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, userID);
        ResultSet result = statement.executeQuery();  //search database for matching email hash pair
        Customer customer = null;
        Address address = null;
        if (result.next()) {
            address = new Address( 
                result.getInt("addressID"),
                result.getString("streetName"),
                result.getString("unitNumber"),
                result.getString("suburb"),
                result.getInt("postcode"),
                result.getString("state"),
                result.getString("country")
            );
            customer = new Customer(
                result.getInt("customerID"),
                result.getString("fName"),
                result.getString("lName"),
                result.getString("sex"),
                result.getDate("dob"),
                address
            );
        } // if the result is not null, get userID
        
        return customer;   
    }


    /* depricated
    public int addCustomer(String email, String password, String phoneNo, String fName, String lName, String sex, Date dob, int addressID) throws SQLException {
        DBManager manager = new DBManager(conn);
        int userID = 0;
        int customerID = 0;
        
        try{
            userID = manager.addUser(email, password, "Customer", phoneNo);
            customerID = addCustomer(fName, lName, sex, dob);
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
    */
    
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
    public int addCustomer(String fName, String lName, String sex, Date dob) throws SQLException {                   //code for add-operation       
        String sql = "INSERT INTO customers (fName, lName, sex, dob)"
                   + " VALUES ( ? , ? , ? , ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, fName); //need to add hash method later
        statement.setString(2, lName);
        statement.setString(3, sex);
        statement.setDate(4, dob);
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
    
   
    public void updateCustomer(Integer customerID, String fName, String lName, String sex, Date dob) throws SQLException {                   //code for add-operation       
        String sql = "UPDATE customers "
                    + "SET fName = ?, lName = ?, sex = ?, dob = ?"
                    + "WHERE customerID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, fName); //need to add hash method later
        statement.setString(2, lName);
        statement.setString(3, sex);
        statement.setDate(4, dob);
        statement.setInt(5, customerID);
        statement.executeUpdate();
    }//updateCustomer()
    
    public void updateCustomer(Integer customerID,  Integer addressID) throws SQLException {                   //code for add-operation       
        String sql = "UPDATE customers "
                    + "SET addressID = ?"
                    + "WHERE customerID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, addressID);
        statement.setInt(2, customerID);
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
    
    public void deleteCustomerAddress(int customerID) throws SQLException {                   //code for add-operation       
        String sql = "UPDATE customers "
                    + "SET addressID = null"
                    + "WHERE customerID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, customerID);
        statement.executeUpdate();
    }//updateCustomer()
    
    public void deleteCustomer(int customerID) throws SQLException {                   //code for add-operation       
       
    }//deleteCustomer()
    
    

    
   

}