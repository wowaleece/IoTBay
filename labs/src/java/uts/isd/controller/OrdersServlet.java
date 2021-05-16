/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Orders;
import java.util.Scanner;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager_Orders;
import uts.isd.controller.TestOrderDB;


/**
 *
 * @author alice_zly8mn7
 */
@WebServlet(name = "OrdersServlet", urlPatterns = {"/OrdersServlet"})
public class OrdersServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
 
    private static Scanner in = new Scanner(System.in); 
    private DBConnector connector; 
    private Connection conn;  
    private DBManager_Orders db; 
   
    public OrdersServlet() throws SQLException { 
    try { 
        connector = new DBConnector();
        conn = connector.openConnection();
        db = new DBManager_Orders(conn); 
    } catch (ClassNotFoundException ex) { 
        Logger.getLogger(TestOrderDB.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    private void DisplayOrder(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        DBManager_Orders ProductManager = (DBManager_Orders) session.getAttribute("ProductManager"); 
        int id = Integer.parseInt(request.getParameter("OrderID"));
        Orders order = ProductManager.findOrder(id);
        request.setAttribute("order", order);
        request.getRequestDispatcher("viewOrder.jsp").include(request, response);
     }
    
    private void DeleteOrder(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        DBManager_Orders ProductManager = (DBManager_Orders) session.getAttribute("ProductManager"); 
        try{ 
            int id = Integer.parseInt(request.getParameter("OrdertoDelete"));
            ProductManager.deleteOrder(id);
            
        } catch (SQLException ex) { 
            Logger.getLogger(OrdersServlet.class.getName()).log(Level.SEVERE, null, ex); 
            request.getRequestDispatcher("OrdersServlet").include(request, response);
        }catch (NumberFormatException ex) {
            Logger.getLogger(OrdersServlet.class.getName()).log(Level.SEVERE, null, ex); 
            request.setAttribute("OrderError","unable to delete");
            request.getRequestDispatcher("OrdersServlet").include(request, response);
        }
        
        
    }
    
    
}
