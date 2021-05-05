/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import uts.isd.model.User;
import uts.isd.model.dao.*;

/**
 *
 * @author super
 */
public class LogoutServlet {
    /*
        public LogoutServlet() {
            super();
        }
    */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // retrieve the current session,user and DBconnection
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            DBManager manager = (DBManager) session.getAttribute("manager");
        
            // invalidate session
            session.invalidate();
        // log the logout activity   
            try {
                manager.log(user.getUserID(), "logout",user.getEmail() + " has logged out");
                //how to disconnect from the DB? 

            } catch (SQLException ex) {
                Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //send user back to the main page
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }
}
