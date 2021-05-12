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

 

public class RegisterServlet extends HttpServlet {

    private String email;
    private String hash;

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        
        //init helper classes
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();
        
        //capture the posted credentials     
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNo = request.getParameter("phoneNo");
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String sex = request.getParameter("sex");
        Date dob = validator.sanitiseDate(request.getParameter("dob"));
        String address = request.getParameter("address");
        String tos = request.getParameter("agree");
        
        //fill null strings
        if (phoneNo == null) phoneNo = "";
        if (fName == null) fName = "";
        if (lName == null) lName = "";
        if (sex == null) sex = "";
        if (address == null) address = "";
        
        
        
        
        
        //validate input
        User user = null;  
        validator.clear(session); // updated the %Err attributes to default "please enter"
        
        
        
        //process the address, get ID
        //int addressID = manager.findAddress(address);
        
        
       
        
        
        //validate email and create user/customer
        
        
        
        if (!validator.validateEmail(email)) {           
      
            session.setAttribute("emailErr", "Error: Email format incorrect"); 
            request.getRequestDispatcher("register.jsp").include(request,response);


        } else if (!validator.validatePassword(password)) {            
            session.setAttribute("passErr","Error: Password format incorrect");
            request.getRequestDispatcher("register.jsp").include(request,response);
        } else {
            try {

                manager.addUser(email, password, "Customer", phoneNo);
                user = manager.checkLogin(email,password);   
                //logg a login attempt
                if (user != null) {
                    session.setAttribute("user",user);
                    
                    //manager.addCustomer(user.getUserID(), fName, lName, title, sex, dob, addressID);
                    manager.addCustomer(user.getUserID(), fName, lName, sex, dob, 2);
                    request.getRequestDispatcher("index.jsp").include(request, response);
                } else {
                    session.setAttribute("existErr", "Registry Failed");
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }
                //reload the page
                
            } catch (SQLException ex) {           
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);     
                request.getRequestDispatcher("register.jsp").include(request, response);
            }
        }
    }
    
}