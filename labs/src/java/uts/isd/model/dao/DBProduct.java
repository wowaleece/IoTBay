package uts.isd.model.dao;

import uts.isd.model.User;
import uts.isd.model.Address;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;
import org.apache.derby.client.am.DateTime;
import uts.isd.model.Product;


/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBProduct {

    private Statement st; 
    private Connection conn; // using connection and prepared statements instead of dynamic statement 
                             // https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html

    public DBProduct(Connection conn) throws SQLException {       
        this.conn = conn;
    }
    
    public List<Product> DisplayProducts() 
    {
        try {
            PreparedStatement p = this.conn.prepareStatement("SELECT * FROM PRODUCTS");
            ResultSet rs = p.executeQuery();
            
            //Build list of user objects to return
            ArrayList<Product> products = new ArrayList<Product>();
            
            while (rs.next())
            {
                products.add(new Product(rs));
            }
            return products;
        }
        catch (Exception e)
        {
            /*Logging.logMessage("Unable to getAllProducts", e);*/
            return null;
        }
    }
    
    
    
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
    /*public void addProduct(String PRODUCTNAME, String STOCKLEVEL, Float UNITPRICE, String CATEGORY) throws SQLException{
        String sql = ("INSERT INTO PRODUCTS" + "VALUES('"+ PRODUCTNAME +"', '" + STOCKLEVEL +"','"+ UNITPRICE +"', '" + CATEGORY + "'");
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.executeUpdate(sql); 
        statement.setInt( 1, Product.getID());
        statement.setString(2, PRODUCTNAME);
        statement.setString(3, STOCKLEVEL);
        statement.setFloat(4, UNITPRICE);
        statement.setString(5, CATEGORY);
        statement.executeUpdate();
    }*/
    
        public void addProduct(String productName, String stockLevel, Float unitPrice, String category) throws SQLException {                   //code for add-operation       
        String sql = "INSERT INTO PRODUCTS ( PRODUCTNAME, STOCKLEVEL, UNITPRICE, CATEGORY)"
                   + " VALUES ( ? , ? , ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, productName); //need to add hash method later
        statement.setString(2, stockLevel);
        statement.setFloat(3, unitPrice);
        statement.setString(4, category);

        statement.executeUpdate();
    }//addCustomer()
    
    //update product details in the database   
    public void updateProduct(String PRODUCTNAME, String STOCKLEVEL, Float UNITPRICE, String CATEGORY) throws SQLException {       
        String sql = ("UPDATE PRODUCTS SET PRODUCTNAME ='"+ PRODUCTNAME +"', STOCKLEVEL ='" + STOCKLEVEL +"',UNITPRICE ='"+ UNITPRICE +"', CATEGORY = '" + CATEGORY + "' WHERE PRODUCTNAME= '" + PRODUCTNAME + "'");
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(2, PRODUCTNAME);
        statement.setString(3, STOCKLEVEL); 
        statement.setFloat(4, UNITPRICE);
        statement.setString(5,CATEGORY);
        statement.executeUpdate();
        
    }       
    
    //Deletes product from the database
    public void DeleteProduct(String PRODUCTNAME)throws SQLException{
      st.executeUpdate("DELETE FROM PRODUCTS WHERE PRODUCTNAME = '" + PRODUCTNAME + "'"); 
    }
    
    //Checks for a product  
    public boolean CheckProduct(String PRODUCTNAME) throws SQLException { 
        
        String sql = "SELECT * FROM PRODUCTS WHERE PRODUCTNAME = '" + PRODUCTNAME + "'";
        PreparedStatement Statement = conn.prepareStatement(sql);
        ResultSet rs = Statement.executeQuery();  //search database for matching product 
        
        while (rs.next()){ 
            String ProductName = rs.getString(2); 
            if (ProductName.equals(PRODUCTNAME)){ 
                return true; 
            }
        }
        return false;      
    }

}


    
    
    
   