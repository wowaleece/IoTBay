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
import uts.isd.model.Customer;
import uts.isd.model.dao.DBCustomer;
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
        DBCustomer customerManager = (DBCustomer) session.getAttribute("customerManager");
        Validator validator = new Validator();
        
        
        //capture the posted credentials
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
        int userID;
        
        
        //fill null strings
        User user = (User) session.getAttribute("user");
        Customer customer = (Customer) session.getAttribute("customer");
        userID = user.getUserID();
        if (phoneNo == null) phoneNo = user.getPhoneNo();
        if (fName == null) fName = customer.getfName();
        if (lName == null) lName = customer.getlName();
        if (sex == null) sex = customer.getSex();
        if (dob == null) dob = customer.getDob();
        
            
        //validate input
        validator.clear(session); // updated the %Err attributes to default "please enter"
        
        //process the address, get ID
        //int addressID = manager.findAddress(address);
        Address address = customer.getAddress();
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

        try {
            manager.updateUser(userID, phoneNo);
            customerManager.updateCustomer(customer.getCustomerID(), fName, lName, state, sex, dob, postcode);
            
            
        } catch (SQLException ex) {           
            Logger.getLogger(EditCServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("editCustomer.jsp").include(request, response);
    }
    
}