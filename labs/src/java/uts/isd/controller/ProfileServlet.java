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
import javax.servlet.ServletContext;
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


 

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Use getPathInfo tsTo figure out what URL suffix is being used in the request.
         * i.e. the bit after the controller mapping.
         * https://stackoverflow.com/questions/4278083/how-tsTo-get-request-uri-without-context-path
         * 
         * ALSO: Make sure in web.xmxl you use a wildcard tsTo match any sub-path you want tsTo use in this controller.
         *  <servlet-mapping>
         *    <servlet-name>UsersController</servlet-name>
         *    <url-pattern>/User/*</url-pattern>
         *   </servlet-mapping>
         */
        
        //redundant but whatever
        
        String pathinfo = req.getServletPath();
        String context = req.getContextPath();
        ServletContext other = req.getServletContext();
        StringBuffer other2 = req.getRequestURL();
        switch (pathinfo)
        {
            case "/account.jsp":
                doGetProfile(req, resp);
                break;
            case "/admin/*":
                doGetProfileAdmin(req,resp);
                break;
            case "/ProfileServlet":
                doEditProfileGet(req,resp);
                break;
                
        }
        
    }
    
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        String pathinfo = request.getServletPath();
        switch (pathinfo)
        {
            case "/ProfileServlet":
                doEditCustomerPost(request, response);
                break;
            case "/ProfileServlet/Admin/Edit":
                doEditAdminPost(request, response);
                break;
                
        }
    }

    
    protected void doEditCustomerPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //init helper classes
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        DBCustomer customerManager = (DBCustomer) session.getAttribute("customerManager");
        Validator validator = new Validator();
        
        
        //capture the posted credentials
        String password = request.getParameter("password");
        String phoneNo = request.getParameter("phoneNo");
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String sex = request.getParameter("sex");
        Date dob = validator.sanitiseDate(request.getParameter("dob"));
        /*
        String unitNo = request.getParameter("unitNo");
        String street = request.getParameter("street");
        String suburb = request.getParameter("suburb");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        int postcode;
        String tempPC = request.getParameter("postcode");
        */
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

        //validate email and create user/customer

        try {
            manager.updateUser(userID, phoneNo);
            customerManager.updateCustomer(customer.getCustomerID(), fName, lName, sex, dob);
            
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //process the address update for customer and return to account.jsp
        request.getServletContext().getRequestDispatcher("/UpdateAddress").forward(request, response);
        //request.getRequestDispatcher("account.jsp").include(request, response);
    }
    
    private void doGetProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager"); 
        
        Customer customer = (Customer)session.getAttribute("customer");
        boolean isLoggedIn = session.getAttribute("user") != null && customer != null;
        String uType = (String)session.getAttribute("uType");
        boolean isCustomer = uType != null && uType.equals("Customer") ;
        
        if(isLoggedIn && isCustomer){
            //req.getRequestDispatcher("/editCustomer.jsp").include(req,resp);
            req.getRequestDispatcher("/view/viewCustomer.jsp").include(req,resp);
            
        } else {
            req.getRequestDispatcher("/index.jsp").include(req,resp);
        }      
    }
     private void doEditProfileGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager"); 
        
        Customer customer = (Customer)session.getAttribute("customer");
        boolean isLoggedIn = session.getAttribute("user") != null && customer != null;
        String uType = (String)session.getAttribute("uType");
        boolean isCustomer = uType != null && uType.equals("Customer") ;
        
        if(isLoggedIn && isCustomer){
            //req.getRequestDispatcher("/editCustomer.jsp").include(req,resp);
            req.getRequestDispatcher("/view/editCustomer.jsp").include(req,resp);
            
        } else {
            req.getRequestDispatcher("/index.jsp").include(req,resp);
        }      
    }
    
    
    
    private void doGetProfileAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager"); 
        boolean isLoggedIn = session.getAttribute("user") != null;
        String uType = (String)session.getAttribute("uType");
        boolean isAdmin = uType != null && uType.equals("Admin") ;
        
        if(isLoggedIn && isAdmin){
            req.getRequestDispatcher("/admin/profile.jsp").include(req,resp);
        } else if(isLoggedIn) {
            req.getRequestDispatcher("/editCustomer.jsp").include(req,resp);
        } else {
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }      
    }

    private void doEditAdminPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}