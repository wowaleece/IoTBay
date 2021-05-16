/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.dao.DBPayment;

/**
 *
 * @author marinasantanelli
 */
public class UpdatePaymentServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Create HTTP session 
        HttpSession session = request.getSession();
        Integer paymentId = Integer.parseInt(request.getParameter("paymentId"));
        DBPayment manager = ( DBPayment) session.getAttribute("manager");
        
        Payment payment = null;
        try{
            // Locate PaymentID to be able to update payment details
            payment = manager.findPaymentId(paymentId);
            System.out.println(paymentId);
            if(payment != null){
                session.setAttribute("payment", payment);
                request.getRequestDispatcher("orderConfirmation.jsp").include(request, response);
                response.sendRedirect("updatePayment.jsp");
            } else {
                session.setAttribute("existErr", "Payment does not exist in database");
                request.getRequestDispatcher("updatePayments.jsp").include(request, response);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
    }  
    
    
}
