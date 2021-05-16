package uts.isd.model.dao;

import uts.isd.model.User;
import uts.isd.model.Address;
import java.sql.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.derby.client.am.DateTime;
import uts.isd.model.Log;
import uts.isd.model.Product;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBAddress {


    private Connection conn; // using connection and prepared statements instead of dynamic statement 
                             // https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html

    public DBAddress(Connection conn) throws SQLException {       
        this.conn = conn;
    }

    

    
    


    /*public Product DisplayProduct() {   
        try {
            String sql = "SELECT * FROM PRODUCTS";
            PreparedStatement Statement = conn.prepareStatement(sql);
            ResultSet rs = Statement.executeQuery();  //search database for matching product 
            
            while (rs.next()){ 
                Integer PRODUCTID = rs.getInt(1);  
                String PRODUCTNAME = rs.getString(2); 
                String STOCKLEVEL = rs.getString(3); 
                Float UNITPRICE = rs.getFloat(4); 
                String CATEGORY = rs.getString(5); 
                boolean add = temp.add(new Product(PRODUCTID, PRODUCTNAME, STOCKLEVEL, UNITPRICE, CATEGORY)); 
            }
        
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null,e); 
        }
        return temp;
    } */
    
    public List<Address> allAddresses() throws SQLException{
        ArrayList<Address> addresses = new ArrayList<Address>();
        //try {
            String sql = "SELECT addressID, unitNumber, streetName, suburb, postcode, state, country " 
                        + " FROM addresses WHERE active <> false";
            PreparedStatement Statement = conn.prepareStatement(sql);
            ResultSet rs = Statement.executeQuery();  //search database for all active addresses 
            
            while(rs.next()){
                addresses.add(new Address(rs));
            }
                
        //} catch (SQLException e) { 
        //    JOptionPane.showMessageDialog(null,e); 
        //}
        
    
        return addresses;  
    }//allAddress
    
    public List<Address> allAddresses(int customerID) throws SQLException{
        ArrayList<Address> addresses = new ArrayList<Address>();
        //try {
            String sql = "SELECT addressID, unitNumber, streetName, suburb, postcode, state, country " 
                       + " FROM customers c addresses a on c.addressID = a.addressID WHERE active <> false ";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();  //search database for all active addresses 
            
            while(rs.next()){
                addresses.add(new Address(rs));
            }
                
        //} catch (SQLException e) { 
        //    JOptionPane.showMessageDialog(null,e); 
        //}
        
    
        return addresses;  
    }//allAddress
    
    public List<Address> findAddresses(String streetName,String unitNumber) throws SQLException {
        ArrayList<Address> addresses = new ArrayList<Address>();
        String sql = "SELECT addressID, unitNumber, streetName, suburb, postcode, state, country " 
                   + " FROM addresses WHERE active <> false AND (streetName LIKE ? OR unitNumber LIKE ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, streetName + "%");
        statement.setString(2, unitNumber + "%");
        ResultSet rs = statement.executeQuery();  //search database for all active addresses 

        while(rs.next()){
            addresses.add(new Address(rs));
        }
    
        return addresses; 
    }

    /**
     * 
     * @param street
     * @param unitNo
     * @param suburb
     * @param postcode
     * @param state
     * @param country
     * @return Address or Null
     * @throws SQLException 
     */
    public Address findAddress(String street, String unitNo, String suburb, 
                                int postcode, String state, String country) 
            throws SQLException{
        String sql = "SELECT addressID, unitNumber, streetName, suburb, postcode, state, country " 
                       + " FROM addresses WHERE active <> false "
                       + "AND (streetName = ? AND unitNumber = ? "
                            + "AND suburb = ? AND postcode = ? "
                            + "AND state = ? , country = ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, street);
        statement.setString(2, unitNo);
        statement.setString(3, suburb);
        statement.setInt(4, postcode);
        statement.setString(5, state);
        statement.setString(6, country);
        ResultSet rs = statement.executeQuery();  //search database for all active addresses 

        Address address  = null;
        if(rs.next()){
            return new Address(rs);
        } else {
            return null;
        }
    }
    
    /**
     * 
     * @param street
     * @param unitNo
     * @param suburb
     * @param postcode
     * @param state
     * @param country
     * @return addressID or 0
     * @throws SQLException 
     */
    public int findAddressID(String street, String unitNo, String suburb, 
                                int postcode, String state, String country) 
            throws SQLException{
        String sql = "SELECT addressID, unitNumber, streetName, suburb, postcode, state, country " 
                       + " FROM addresses WHERE active <> false "
                       + "AND (streetName = ? AND unitNumber = ? "
                            + "AND suburb = ? AND postcode = ? "
                            + "AND state = ? AND country = ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, street);
        statement.setString(2, unitNo);
        statement.setString(3, suburb);
        statement.setInt(4, postcode);
        statement.setString(5, state);
        statement.setString(6, country);
        ResultSet rs = statement.executeQuery();  //search database for all active addresses 

        Address address  = null;
        if(rs.next()){
            return rs.getInt("addressID");
        } else {
            return 0;
        }
    }
    
    /**
     * Adds address if it doesn't already exits
     * @param street
     * @param unitNo
     * @param suburb
     * @param postcode
     * @param state
     * @param country
     * @return new Adreess ID or exiting AddressID
     * @throws SQLException 
     */
    public int addAddress(String street, String unitNo, String suburb, 
                                int postcode, String state, String country) throws SQLException{
        int adrExisting = findAddressID(street, unitNo, suburb, postcode, state, country);
        if (adrExisting == 0){
            String sql = "INSERT INTO addresses (streetName, UnitNumber, suburb, postcode, state, country)"
                   + " VALUES ( ? , ? , ? , ? , ? , ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, street); //need to add hash method later
            statement.setString(2, unitNo);
            statement.setString(3, suburb);
            statement.setInt(4, postcode);
            statement.setString(5, state);
            statement.setString(6, country);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        }else {
            return adrExisting; 
        }
    }
    
}
    
    
    

    
   

