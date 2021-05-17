/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.controller.unitTests.TestProductDB;
import uts.isd.model.Orders;
import uts.isd.model.User;
import uts.isd.model.PaymentModel;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;
import uts.isd.model.dao.DBManager_Orders;
import uts.isd.model.dao.DBPayment;
import uts.isd.model.dao.DBProduct;


/**
 *
 * @author marinasantanelli
 */
public class PaymentServlet extends HttpServlet{

      

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderID = Integer.parseInt(session.getAttribute("orderID").toString());
        int userID = Integer.parseInt(session.getAttribute("userID").toString());
        DBPayment orderManager = (DBPayment)session.getAttribute("orderManager");
        String BillingAddress = request.getParameter("billingAddress");
        String shippingStatus = request.getParameter("shippingStatus");
        try {
            Orders order = orderManager.findOrder(orderID, userID);
            order.setShippingStatus(shippingStatus);
            /* BillingAddress is set to boolean but might need to be string */ 
            order.setBillingAddress(true);
            
            
           // String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            
           // order.setOrderTime(date);
           // orderManager.updateOrder(orderID, userID, date, order.getTax(), order.getTotalPrice(), shippingStatus, billingAddress);
            session.setAttribute("order", order);
            request.getRequestDispatcher("finalOrder.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// PaymentModel = new payment(paymentMethod, cardNumber, cvv, nameOnCard, cardExpiry, paymentDate);
}