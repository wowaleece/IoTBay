/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

 

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Log;

import uts.isd.model.User;
import uts.isd.model.dao.DBManager;

 

public class UserLogsServlet extends HttpServlet {

    private Timestamp from;
    private Timestamp to;
    private long defaultFrom = 2592000000L; // 30 days
    
    
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        
        //init helper classes
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();
        
        
        //get session info
        User user = (User) session.getAttribute("user");
        String fromString = request.getParameter("from");
        String toString = request.getParameter("to");
        
        
       

        // validate input
        if(!validator.validateTimestamp(fromString)){
            from = Timestamp.valueOf(fromString);
        } else {
            from = new Timestamp(System.currentTimeMillis() - defaultFrom); // -30 days
        }
        
        if(!validator.validateTimestamp(toString)){
            to = Timestamp.valueOf(toString);
        } else {
            to = new Timestamp(System.currentTimeMillis());
        }
        
        
                
        //execute query
        if (user != null) {           
            
            try {
                ArrayList<Log> logs = manager.findLogs(user.getUserID(),from,to);
                request.setAttribute("logs", logs);
            } catch (SQLException ex) {
                request.setAttribute("existErr", "no results");
                Logger.getLogger(UserLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            session.setAttribute("existErr", "Please Login"); 
            request.getRequestDispatcher("login.jsp").include(request,response);
        }
    }
    
}