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
import uts.isd.model.dao.DBManager;
import uts.isd.model.dao.*;
import uts.isd.model.dao.DBManager_Orders;
import uts.isd.controller.unitTests.TestOrderDB;


/**
 *
 * @author alice_zly8mn7
 */

@WebServlet(name = "OrdersServlet", urlPatterns = {"/OrdersServlet"})
public class OrdersServlet extends HttpServlet {
//    private static final long serialVersionUID =1L;
 
//    private static Scanner in = new Scanner(System.in); 
    private DBConnector connector; 
    private Connection conn;
//    private DBManager_Orders orders; 
//   
//    public OrdersServlet() throws SQLException { 
//        try { 
//            connector = new DBConnector();
//            conn = connector.openConnection();
//            orders  = new DBManager_Orders(conn); 
//        } catch (ClassNotFoundException ex) { 
//            Logger.getLogger(TestOrderDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
////    @Override
//    public void init() { 
//        try {
//            orders = new DBManager_Orders(conn);
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                 case "/OrdersServletDelete":
                    DeleteOrder(request, response);
                    break;
                case "/OrdersServletFind":
                    DisplayOrder(request, response);
                    break;
                case "/OrdersServletList":
                    ListOrders(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    //dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void DisplayOrder(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        DBManager_Orders OrdersManager = (DBManager_Orders) session.getAttribute("OrderManager"); 
        int id = Integer.parseInt(request.getParameter("OrderID"));
        Orders order = OrdersManager.findOrder(id);
        request.setAttribute("order", order);
        request.getRequestDispatcher("viewOrder.jsp").include(request, response);
     }
    
    private void ListOrders(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        System.out.println("Hello line 107");
        HttpSession session = request.getSession();
        DBManager_Orders OrderManager = (DBManager_Orders) session.getAttribute("OrderManager"); 
        List <Orders> orders = OrderManager.DisplayOrders();
        request.setAttribute("Orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewOrdersList.jsp");
        dispatcher.forward(request,response);
     }
    
    private void DeleteOrder(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        DBManager_Orders ProductManager = (DBManager_Orders) session.getAttribute("ProductManager"); 
        try{ 
            int id = Integer.parseInt(request.getParameter("OrderID"));
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
