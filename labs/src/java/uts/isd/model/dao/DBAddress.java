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
    
    public Address allAddresses(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//allAddress
    
    public ArrayList<Address> findAddresses(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    
    

    
   

}