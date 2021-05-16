/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import uts.isd.model.dao.DBAddress;
import uts.isd.model.Address;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Customer;
import uts.isd.model.dao.DBCustomer;
import uts.isd.model.dao.DBManager;


/**
 *
 * @author super
 */
public class AddressServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddressServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddressServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        /*String pathinfo = request.getServletPath();
        switch (pathinfo)
        {
            case "/ListAddress":
                doListAddressGet(request, response);
                break;
            case "/AdminLogs":
                doAdminLogsGet(request, response);
                break;
                
        }*/
        
        //only need one action when getting address view??
        HttpSession session = request.getSession();
        DBAddress addressManager = (DBAddress)session.getAttribute("addressManager");
        
        //get any relevant session attributes
        
        session.getAttribute("customer");
        List<Address> addresses;
        try{
            addresses = addressManager.allAddresses();
            request.setAttribute("addresses", addresses);
            
            //request.getRequestDispatcher("/view/addressSearch.jsp").include(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(UserLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("existErr", "Addresses unavaliable");
        }
        //request.getRequestDispatcher("/view/addressSearch.jsp").include(request,response);
        request.getRequestDispatcher("/view/addressSearch.jsp").include(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathinfo = request.getServletPath();
        switch (pathinfo)
        {
            case "/AddAddress":
                doAddAddressPost(request, response);
                break;
                
            case "/FindAddress":
                doFindAddressPost(request, response);
                break;
                
            case "/UpdateAddress":
                doAddAddressPost(request, response);
                request.getRequestDispatcher("account.jsp").include(request,response);
            break;
            
            case "/DeleteAddress":
                doDeleteAddressPost(request, response);
            break;
                
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void doAddAddressPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        DBAddress addressManager = (DBAddress) session.getAttribute("addressManager");
        DBCustomer customerManager = (DBCustomer) session.getAttribute("customerManager");
        Customer customer = (Customer) session.getAttribute("customer");
        Validator validator = new Validator();
        
        //get and sanitise
        String unitNo = validator.sanitiseString(request.getParameter("unitNo"));
        String street = validator.sanitiseString(request.getParameter("street"));
        String suburb = validator.sanitiseString(request.getParameter("suburb"));
        String state = validator.sanitiseString(request.getParameter("state"));
        String country = validator.sanitiseString(request.getParameter("country"));
        int postcode;
        String tempPC = validator.sanitiseString(request.getParameter("postcode"));
        //validate input
        postcode = tempPC != null ? Integer.parseInt(tempPC) : 0;
        
        
        //Add address if it doesn't already exist
        try{
            int newAddressID = addressManager.addAddress(street, unitNo, suburb, postcode, state, country);
            //customer.setAddress(newAddress);
            customerManager.updateCustomerAddress(customer.getCustomerID(),newAddressID);

        } catch(SQLException ex){
            Logger.getLogger(UserLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    private void doFindAddressPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    private void doUpdateAddressPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void doDeleteAddressPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBCustomer customerManager = (DBCustomer) session.getAttribute("customerManager");
        Customer customer = (Customer) session.getAttribute("customer");
        Validator validator = new Validator();
        
        try{
            customerManager.deleteCustomerAddress(customer.getCustomerID());
        }catch (SQLException ex) {
            Logger.getLogger(UserLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("account.jsp").include(request,response);
        
    }

}
