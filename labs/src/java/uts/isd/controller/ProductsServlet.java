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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.controller.unitTests.TestProductDB;
import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBProduct; 
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.*;
/**
 *
 * @author Kayla Gelman
 */
public class ProductsServlet extends HttpServlet {
    
    private DBConnector connector; 
    private Connection conn;  
    private DBProduct product; 
    
    public void init() { 
        try {
            product = new DBProduct(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                /*case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertTodo(request, response);
                    break;
                case "/delete":
                    deleteTodo(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateTodo(request, response);
                    break;*/
                case "/ProductsServletList":
                    DisplayProducts(request, response);
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

    private void DisplayProducts(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        DBProduct ProductManager = (DBProduct) session.getAttribute("ProductManager"); 
        List <Product> product = ProductManager.DisplayProducts();
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DeviceCatalogue.jsp");
        dispatcher.forward(request, response);
    }
 
   /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
 
        HttpSession session = request.getSession();
        DBProduct ProductManager = (DBProduct) session.getAttribute("ProductManager"); 
        try { 
            List<Product> products = ProductManager.DisplayProducts(); 
            request.setAttribute("products", products);

        }catch (Exception e){ 
            Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, e);
            return; 
        }
        
        request.getRequestDispatcher("DeviceCatalogue.jsp").include(request, response);

        requestDispatcher = request.getRequestDispatcher("DeviceCatalogue.jsp");
  }*/


 
 }  