package uts.isd.model.dao;

import uts.isd.model.User;
import uts.isd.model.Address;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
    public void updateUser( int userID, String email, String password, String uType, String phoneNo) throws SQLException {       
        String sql = "UPDATE users " 
                   + "SET email = ? , password = ? , uType = ? , phoneNo = ? " 
                   + "WHERE userID = ? " ;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password); //need to add hash method later
        statement.setString(3, uType);
        statement.setString(4, phoneNo);
        statement.setInt(5, userID);
        statement.executeUpdate();
        //code for update-operation   
        
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
    public void addCustomer( String fName, String lName, String title, String sex, String dob, int addressID) throws SQLException {                   //code for add-operation       
        String sql = "INSERT INTO customers ( fName, lName, title, sex, dob, addressid)"
                   + " VALUES ( ? , ? , ? , ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, fName); //need to add hash method later
        statement.setString(2, lName);
        statement.setString(3, title);
        statement.setString(4, sex);
        statement.setString(5, dob);
        statement.setInt(6, addressID);
        statement.executeUpdate();
    }//addCustomer()
    
    
    public void updateCustomer(int userID, String fName, String lName, String title, String sex, String dob, int addressID) throws SQLException {                   //code for add-operation       
        String sql = "INSERT INTO customers (userID, fName, lName, title, sex, dob, addressid)"
                   + " VALUES ( ? , ? , ? , ? , ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, userID);
        statement.setString(2, fName); //need to add hash method later
        statement.setString(3, lName);
        statement.setString(4, title);
        statement.setString(5, sex);
        statement.setString(6, dob);
        statement.setInt(7, addressID);
        statement.executeUpdate();
    }//updateCustomer()
    
    public void deleteCustomer(int customerID) throws SQLException {                   //code for add-operation       
       
    }//deleteCustomer()
    
    
    
    
    public int findAddress(String address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//findAddress
    
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
    
    
    

    public List<Log> findLogs(int userID,OffsetDateTime from, OffsetDateTime to) {
        String sql = "SELECT c.fname,c.lname, l.logTime, l.activityType, l.activityDetail"
                   + " FROM logs l INNER JOIN customers c on c.userID = l.userID INNER JOIN users u ON u.userID = l.userID"
                   + " WHERE l.userID = ? AND l.logTime > ? AND l.logTime < ?"
    }
    
    

    
   

}