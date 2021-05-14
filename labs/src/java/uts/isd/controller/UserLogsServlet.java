/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

 

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;
import uts.isd.model.dao.DBManager;

 

public class UserLogsServlet extends HttpServlet {

    private String email;
    private String hash;

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        
        //init helper classes
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();
        

        // get session user and check exists
        
        User user = (User) session.getAttribute("user");
        
        if (user != null) {           
            
            try {
                 manager.findLogs(user.getUserID());
            } catch (SQLException ex) {           
                Logger.getLogger(UserLogsServlet.class.getName()).log(Level.SEVERE, null, ex);     
                request.getRequestDispatcher("logs.jsp").include(request, response);
            }
        } else {
            session.setAttribute("existErr", "Error: No User logged in"); 
            request.getRequestDispatcher("register.jsp").include(request,response);
        }
    }
    
}