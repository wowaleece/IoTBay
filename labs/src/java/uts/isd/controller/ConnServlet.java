/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.*;

 
/**
 *
 * @author super
 */
public class ConnServlet extends HttpServlet {

    private DBConnector db;
    private DBManager manager;
    private DBProduct productManager; 
    private DBCustomer customerManager;  
    private DBAddress addressManager;
    private Connection conn; 
    private DBManager_Orders OrdersManager; 


                    
        
    @Override //Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            
            db = new DBConnector();

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

        }      

    }

      

    @Override //Add the DBConnector, DBManager, Connection instances to the session
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();

        conn = db.openConnection();       

        try {
            manager = new DBManager(conn);
            customerManager = new DBCustomer(conn);
            productManager = new DBProduct(conn);
            addressManager = new DBAddress(conn);
            
            OrdersManager = new DBManager_Orders(conn);

        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

           //export the DB manager to the view-session (JSPs)
        session.setAttribute("manager", manager);     
        session.setAttribute("ProductManager", productManager);  //please use camel case           
        session.setAttribute("addressManager", addressManager);  //please use camel case           
        session.setAttribute("customerManager", customerManager);  //please use camel case           
        session.setAttribute("manager", manager);   
        session.setAttribute("OrderManager", OrdersManager);

    }   

        

    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection) // automatic when application closes
    public void destroy() {

        try {
            db.closeConnection();

        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}