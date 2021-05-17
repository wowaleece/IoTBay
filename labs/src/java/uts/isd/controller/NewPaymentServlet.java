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
import uts.isd.model.dao.DBManager;
import uts.isd.model.dao.DBManager_Orders;
import uts.isd.model.dao.DBPayment;
import uts.isd.model.PaymentModel;

/**
 *
 * @author marinasantanelli
 */

public class NewPaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Session
         HttpSession session = request.getSession();
         String paymentMethod = request.getParameter("paymentMethod");
         Integer cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
         Integer cvv = Integer.parseInt(request.getParameter("cvv"));
         String nameOnCard = request.getParameter("nameOnCard");
         String cardExpiry = request.getParameter("cardExpiry");
         String paymentDate = request.getParameter("paymentDate");     
         
         PaymentModel payment = new PaymentModel( paymentMethod, cardNumber, cvv, nameOnCard, cardExpiry, paymentDate);
         DBPayment manager = (DBPayment) session.getAttribute("manager");
         
         try {
             if(payment != null){
                 session.setAttribute("payment", payment);
                 Integer orderId = manager.getOrderID();
                 session.setAttribute("orderId", orderId);
                 manager.addPayment(orderId, paymentMethod, cardNumber, cvv, nameOnCard, cardExpiry, paymentDate);
                 Integer paymentId = manager.getPaymentId();
                 session.setAttribute("paymentId", paymentId);
                 request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
                 response.sendRedirect("orderConfirmation.jsp");
             } else {
                 request.getRequestDispatcher("cart.jsp").include(request, response);
             }
         } catch ( SQLException ex){
             Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
 }     