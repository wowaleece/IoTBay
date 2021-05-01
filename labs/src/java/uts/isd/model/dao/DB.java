/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uts.isd.model.dao;
import java.sql.Connection; 

/**
 *
 * @author Kayla Gelman
 */
public abstract class DB {
    
    protected String URL = "jdbc:derby://localhost:1527/"; 
    protected String db = "ShopDB"; 
    protected String dbuser = "App"; 
    protected String dbpass = "App";  
    protected String driver =  "org.apache.derby.jdbc.ClientDriver"; 
    protected Connection conn; 
    
}
