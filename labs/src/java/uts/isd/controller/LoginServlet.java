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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;
import uts.isd.model.dao.DBManager;

 

public class LoginServlet extends HttpServlet {

    private String email;
    private String hash;

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        
        //1- retrieve the current session
        HttpSession session = request.getSession();
        //2- create an instance of the Validator class    
        Validator validator = new Validator();
        //3- capture the posted credentials     
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //5- retrieve the manager instance from session    // now doing this step during LoginDAO after step 12   
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        User user = null;  
        validator.clear(session); // updated the %Err attributes to default "please enter"
        
        if (!validator.validateEmail(email)) {           
            //8-set incorrect email error to the session           
            session.setAttribute("emailErr", "Error: Email format incorrect"); //why do we use a different err for pass and email ext
            request.getRequestDispatcher("login.jsp").include(request,response);
            //9- redirect user back to the login.jsp     

        } else if (!validator.validatePassword(password)) {                  

            //11-set incorrect password error to the session           
            session.setAttribute("passErr","Error: Password format incorrect");
            //12- redirect user back to the login.jsp          
            request.getRequestDispatcher("login.jsp").include(request,response);
        } else {
            try {
                //user = loginDAO.checkLogin(email, hash);
                user = manager.checkLogin(email, password);
                //log a login attempt
                if (user != null) {
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("index.jsp").include(request, response);
                    manager.log(user.getUserID(), "login", "logged in");
                } else {
                    session.setAttribute("existErr", "User does not exist");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }   
            } catch (SQLException ex) {           
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);       
            }
        }
    }
    
}