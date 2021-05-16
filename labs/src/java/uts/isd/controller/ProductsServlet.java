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
    //private DBProduct product; 
    /*
    public void init() { 
        try {
            product = new DBProduct(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
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
                    break;*/
                case "/ProductsServletEditProduct":
                    EditProduct(request, response);
                    break;
                 case "/ProductsServletDelete":
                    DeleteProducts(request, response);
                    break;
                case "/ProductsServletList":
                    DisplayProducts(request, response);
                    break;
                    
                case "/ProductsServletAdminList":
                    DisplayProductsAdmin(request, response);
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
        List <Product> products = ProductManager.DisplayProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewProducts.jsp");
        dispatcher.forward(request, response);
     }
    
    private void DisplayProductsAdmin(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        DBProduct ProductManager = (DBProduct) session.getAttribute("ProductManager"); 
        List <Product> products = ProductManager.DisplayProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DeviceCatalogue.jsp");
        dispatcher.forward(request, response);
     }
    
     /*  private void DeleteProducts(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        
        int ID = Integer.parseInt(request.getParameter("PRODUCTID"));
        ProductManager.DeleteProduct(ID); 
        response.sendRedirect("DeleteProductConfirmation.jsp");
        
        /*DBProduct ProductManager = (DBProduct) session.getAttribute("ProductManager"); 
        int PRODUCTID = Integer.parseInt(request.getParameter("PRODUCTID"));
        ProductManager.DeleteProduct(PRODUCTID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteProduct.jsp");
        dispatcher.forward(request, response);
     }*/
    
   /* private void DeleteProducts(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        DBProduct ProductManager = (DBProduct) session.getAttribute("ProductManager");
        int ProductID = Integer.parseInt(request.getParameter("PRODUCTID"));
       // DBProduct db = new DBProduct(); 
        //product.DeleteProduct(ProductID);
        ProductManager.DeleteProduct(ProductID);
        response.sendRedirect("DeleteProductConfirmation.jsp");
     }*/
    
    private void DeleteProducts(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        DBProduct ProductManager = (DBProduct) session.getAttribute("ProductManager"); 
        int PRODUCTID = Integer.parseInt(request.getParameter("PRODUCTID"));
        try{ 
            ProductManager.DeleteProduct(PRODUCTID);
            request.getRequestDispatcher("DeleteProductConfirmation.jsp").include(request, response);
        }catch (SQLException ex) { 
            Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex); 
            request.getRequestDispatcher("DeviceCatalogue.jsp").include(request, response);
        }
     }
    
        private void EditProduct(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        DBProduct ProductManager = (DBProduct) session.getAttribute("ProductManager"); 
        int PRODUCTID = Integer.parseInt(request.getParameter("PRODUCTID"));
        
        String PRODUCTNAME = request.getParameter("PRODUCTNAME"); 
        int QUANTITY = Integer.parseInt(request.getParameter("QUANTITY")); 
        String STOCKLEVEL = request.getParameter("STOCKLEVEL"); 
        Float UNITPRICE = Float.parseFloat(request.getParameter("UNITPRICE"));
        String CATEGORY= request.getParameter("CATEGORY"); 
        
        ProductManager.updateProduct(PRODUCTNAME, QUANTITY, STOCKLEVEL, UNITPRICE, CATEGORY);
        response.sendRedirect("ProductsServletList");
		
     }
 
 }  
