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
import uts.isd.model.Address;

import uts.isd.model.User;
import uts.isd.model.dao.DBManager;

 

public class EditCServlet extends HttpServlet {

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
        
        String unitNo = request.getParameter("unitNo");
        String street = request.getParameter("street");
        String suburb = request.getParameter("suburb");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        int postcode;
        String tempPC = request.getParameter("postcode");
        
        
        String tos = request.getParameter("agree");
        
        
        //fill null strings
        User user = (User) session.getAttribute("user");
        if (phoneNo == null) phoneNo = user.getPhoneNo();
        if (fName == null) fName = user.getfName();
        if (lName == null) lName = user.getlName();
        if (sex == null) sex = user.getSex();
        
        
        
        
        
        
        
        
        //validate input
         
        validator.clear(session); // updated the %Err attributes to default "please enter"
        
        
        
        //process the address, get ID
        //int addressID = manager.findAddress(address);
        Address address = user.getAddress();
        if(unitNo == null) unitNo = address.getUnitNumber();
        if(street == null) street = address.getStreetName();
        if(suburb == null) suburb = address.getSuburb();
        if(tempPC == null) {
            postcode = address.getPostcode();
        } else {
            postcode = Integer.parseInt(tempPC);
        }
        if(state == null) state = address.getState();
        if(country == null) country = address.getCountry();
            
        
       
        
        
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
                    
//                    //manager.addCustomer(user.getUserID(), fName, lName, title, sex, dob, addressID);
//                    manager.addCustomer(user.getUserID(), fName, lName, sex, dob, 2);
//                    request.getRequestDispatcher("index.jsp").include(request, response);
                } else {
                    session.setAttribute("existErr", "Registry Failed");
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }
                //reload the page
                
            } catch (SQLException ex) {           
                Logger.getLogger(EditCServlet.class.getName()).log(Level.SEVERE, null, ex);     
                request.getRequestDispatcher("register.jsp").include(request, response);
            }
        }
    }
    
}