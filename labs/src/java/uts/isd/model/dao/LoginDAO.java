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
 
    public User checkLogin(String email, String hash) throws SQLException,
            ClassNotFoundException {
        String jdbcURL = "jdbc:derby://localhost:1527/Auth";
        String dbUser = "Auth";
        String dbPassword = "Auth";
 
        Class.forName("org.apache.derby.jbdc.ClientDriver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String sql = "SELECT * FROM users WHERE email = ? and hash = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, hash);
 
        ResultSet result = statement.executeQuery();  //search database for matching email hash pair
 
        User user = null;
        if (result.next()) {user = new User(result.getInt("userID"));} // if the result is not null, get userID
 
        connection.close();
 
        return user;
    }
    
}