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

/**
 *
 * @author Kayla Gelman
 */
public class ProductsServlet extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


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
 
        
        HttpSession session = request.getSession();
        Product product;
     
        try { 
            DBProduct ProductManager = (DBProduct) session.getAttribute("ProductManager"); 
            /*DBProduct db  = new DBProduct();  */
            List<Product> products = ProductManager.DisplayProducts(); 
            request.setAttribute("product", products);
        }catch (Exception e){ 
            System.out.println("Product does not exist"); 
            return; 
        }
        
        RequestDispatcher requestDispatcher; 
        requestDispatcher = request.getRequestDispatcher("DeviceCatalogue.jsp");
        requestDispatcher.forward(request, response);
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        DBProduct db = (DBProduct) session.getAttribute("product");
   
        String ProductName = request.getParameter("PRODUCTNAME"); 
        String StockLevel = request.getParameter("STOCKLEVEL");
        String UnitPrice = request.getParameter("UNITPRICE");
        String Category = request.getParameter("CATEGORY");
        
        try {
            db.addProduct(ProductName, StockLevel, UnitPrice, Category);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(""); 
        
        
    }*/


}
