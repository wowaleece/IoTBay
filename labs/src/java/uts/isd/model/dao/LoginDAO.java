/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.*;
import uts.isd.model.User;

/**
 *
 * @author James Smith 25/04/2021
 */
public class LoginDAO {
 
    public Integer checkLogin( String email, String hash) throws SQLException,
            ClassNotFoundException {
        //need to think about where to store this connection string.
        String authURL = "jdbc:derby://localhost:1527/Auth";
        String dbUser = "Auth";
        String dbPassword = "Auth";
 
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connection = DriverManager.getConnection(authURL, dbUser, dbPassword);
        String sql = "SELECT userID FROM users WHERE email = ? and hash = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, hash);
       
        ResultSet result = statement.executeQuery();  //search database for matching email hash pair
 
        //find corresponding user in SHOP DB and set that as the session user.
        //User user = null;
        Integer userID = null; // needs to be big int to be null, needs to be int because of split DB design.
        if (result.next()) {userID =  result.getInt("userID");} // if the result is not null, get userID
 
        connection.close();
 
        return userID;
    }
    
    
    
    
}