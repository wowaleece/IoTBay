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
import uts.isd.model.Customer;

import uts.isd.model.User;
import uts.isd.model.dao.DBCustomer;
import uts.isd.model.dao.DBManager;

 

public class RegisterServlet extends HttpServlet {

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        
        //init helper classes
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        DBCustomer dbCust= (DBCustomer) session.getAttribute("customerManager");
        //DBCustomer dbCust = new DBCustomer();
        Validator validator = new Validator();
        
        //capture the posted credentials   
        String email = request.getParameter("email").toLowerCase();
        String password = request.getParameter("password");
        String phoneNo = request.getParameter("phoneNo").toLowerCase();
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String sex = request.getParameter("sex");
        String dobString = request.getParameter("dob");
        String tos = request.getParameter("agree");
        
        //fill null strings, sanatise input
        
        if (fName == null) fName = "";
        if (lName == null) lName = "";
        if (sex == null) sex = "";
        Date dob = validator.sanitiseDate(dobString);
        
        
        
        
        //validate input
        User user = null;
        int customerID = 0 ;
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

                //int userID = manager.addUser(email, password, "Customer", phoneNo);
                //user = manager.checkLogin(email,password);   
                //logg a login attempt
                //if (userID != 0) {
                    //manager.addCustomer(user.getUserID(), fName, lName, sex, dob, addressID);
                    //dbCust.addCustomer(userID, fName, lName, sex, dob, 0);
                    
                    customerID = dbCust.addCustomer(email,password,phoneNo, fName, lName, sex, dob, 0);
                    session.setAttribute("uType","Customer");
                    user = manager.checkLogin(email, password);
                    if(user != null && customerID != 0){
                        session.setAttribute("customerID", customerID);
                        user.Update(dbCust.findCustomer(customerID));
                        session.setAttribute("user", user);
                        
                    }
                    
                    request.getRequestDispatcher("index.jsp").include(request, response);
                //} else {
                //    session.setAttribute("existErr", "Registry Failed");
                //    request.getRequestDispatcher("register.jsp").include(request, response);
                //}
                //reload the page
            } catch (SQLException ex) {           
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);     
                request.getRequestDispatcher("register.jsp").include(request, response);
            }
        }
    }
    
}