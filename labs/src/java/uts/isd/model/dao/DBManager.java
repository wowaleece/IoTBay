package uts.isd.model.dao;

import uts.isd.model.User;
import uts.isd.model.Address;
import java.sql.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.derby.client.am.DateTime;
import uts.isd.model.Log;
import uts.isd.model.Product;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

    private Statement st; 
    private Connection conn; // using connection and prepared statements instead of dynamic statement 
                             // https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html

    public DBManager(Connection conn) throws SQLException {       
        this.conn = conn;
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
    public void addUser(String email, String password, String uType, String phoneNo) throws SQLException{
        String sql = "INSERT INTO users (email, password, uType, phoneNo) "
                             + "  VALUES ( ?  , ?       , ?    , ?       )";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password); //need to add hash method later
        statement.setString(3, uType);
        statement.setString(4, phoneNo);
        statement.executeUpdate();
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

    //delete a user from the database   
    public void deleteUser(String email) throws SQLException{       
        //code for delete-operation   
        // can we invalidate instead? 
    }
    
    public ArrayList<Product> fectProducts() throws SQLException { 
        String sql = "select * from PRODUCTS"; 
        /*PreparedStatement Statement = conn.prepareStatement(sql);*/
        ResultSet rs = st.executeQuery(sql);   
        ArrayList<Product> temp = new ArrayList();  
        
        while (rs.next()){ 
            Integer PRODUCTID = rs.getInt(1);  
            String PRODUCTNAME = rs.getString(2); 
            String STOCKLEVEL = rs.getString(3); 
            Float UNITPRICE = rs.getFloat(4); 
            String CATEGORY = rs.getString(5);  
            temp.add(new Product(PRODUCTID, PRODUCTNAME, STOCKLEVEL, UNITPRICE, CATEGORY)); 
        
        }
        return temp; 
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
    
    
    //Find by ProductName in database 
    public Product findProduct(String PRODUCTNAME) throws SQLException {   
        String sql = "SELECT * FROM PRODUCTS WHERE PRODUCTNAME = '" + PRODUCTNAME + "'";
        PreparedStatement Statement = conn.prepareStatement(sql);
        ResultSet result = Statement.executeQuery();  //search database for matching product 
        
        while (result.next()){ 
            String PRODUCTNAME_TEMP = result.getString(2); 
            if (PRODUCTNAME_TEMP.equals(PRODUCTNAME)){
                Integer PRODUCTID = result.getInt(1); 
                String STOCKLEVEL = result.getString(3); 
                Float UNITPRICE = result.getFloat(4);
                String CATEGORY = result.getString(5); 
                return new Product(PRODUCTID, PRODUCTNAME, STOCKLEVEL, UNITPRICE, CATEGORY); 
            }
        }
        return null;  
    } 
       
    
    //Add new product into the database   
    public void addProduct(int PRODUCTID, String PRODUCTNAME, String STOCKLEVEL, Float UNITPRICE, String CATEGORY) throws SQLException{
        String sql = ("INSERT INTO PRODUCTS" + "VALUES('"+ PRODUCTNAME +"', '" + STOCKLEVEL +"','"+ UNITPRICE +"', '" + CATEGORY + "'");
        PreparedStatement statement = conn.prepareStatement(sql);
        /*
        statement.setInt(1, PRODUCTID); 
        statement.setString(2, PRODUCTNAME);
        statement.setString(3, STOCKLEVEL);
        statement.setFloat(4, UNITPRICE);
        statement.setString(5, CATEGORY);
        statement.executeUpdate();*/
    }
    
    //update product details in the database   
    public void updateProduct(String PRODUCTNAME, String STOCKLEVEL, Float UNITPRICE, String CATEGORY) throws SQLException {       
        String sql = ("UPDATE PRODUCTS SET PRODUCTNAME ='"+ PRODUCTNAME +"', STOCKLEVEL ='" + STOCKLEVEL +"',UNITPRICE ='"+ UNITPRICE +"', CATEGORY = '" + CATEGORY + "' WHERE PRODUCTNAME= '" + PRODUCTNAME + "'");
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, PRODUCTNAME);
        statement.setString(2, STOCKLEVEL); 
        statement.setFloat(3, UNITPRICE);
        statement.setString(4,CATEGORY);
        statement.executeUpdate();
        
    }       
    
    //Deletes product from the database
    public void DeleteProduct(String PRODUCTNAME)throws SQLException{
      st.executeUpdate("DELETE FROM PRODUCTS WHERE PRODUCTNAME = '" + PRODUCTNAME + "'"); 
    }

    public void updateProduct(String PRODUCTNAME, String STOCKLEVEL, String UNITPRICE, String CATEGORY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addProduct(String PRODUCTID, String PRODUCTNAME, String STOCKLEVEL, String UNITPRICE, String CATEGORY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    public void addCustomer(int userID, String fName, String lName, String sex, Date dob, int addressID) throws SQLException {                   //code for add-operation       
        String sql = "INSERT INTO customers (userID, fName, lName, sex, dob, addressid)"
                   + " VALUES ( ? , ? , ? , ? , ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, userID);
        statement.setString(2, fName); //need to add hash method later
        statement.setString(3, lName);
        statement.setString(4, sex);
        statement.setDate(5, dob);
        statement.setInt(6, addressID);
        statement.executeUpdate();
        
        //log(userID, "Registered", "" + userID + " Registered as a customer");
    }//addCustomer()
    
    //add anonymous customer
    /**
     * 
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
    
    public void updateCustomer(int customerID, Integer addressID) throws SQLException {                   //code for add-operation       
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
    
    
    
    
    public int findAddress(String address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//findAddress
    
    
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
    public ArrayList<Log> findLogs(int userID, Timestamp from, Timestamp to) throws SQLException {
        //Build list of log objects to return
        ArrayList<Log> logs = new ArrayList<Log>();
        
        String sql = "SELECT l.logID, l.userID, l.logTime, l.activityType, l.activityDetail"
                   + " FROM logs "
                   + " WHERE l.userID = ? AND l.logTime > ? AND l.logTime < ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userID);
        ps.setObject(2, from);
        ps.setObject(3, to);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            logs.add(new Log(rs.getLong("logID"), rs.getInt("userID"), (Timestamp) rs.getObject("logTime"), rs.getString("activityType"), rs.getString("activityDetail")));
        }
        
        return logs;
    }
    
    

    
   

}