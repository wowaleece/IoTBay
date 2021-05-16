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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBProduct; 

/**
 *
 * @author Kayla Gelman
 */
public class AddProductsServlet extends HttpServlet {

   /* private static Scanner in = new Scanner(System.in); 
    private DBConnector connector; 
    private Connection conn;  
    private DBProduct db; */
    

    /*public AddProductsServlet() throws SQLException { 
        try { 
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBProduct(conn); 
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(TestProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String PRODUCTNAME = request.getParameter("PRODUCTNAME"); 
        String STOCKLEVEL = request.getParameter("STOCKLEVEL"); 
        Float UNITPRICE = Float.parseFloat(request.getParameter("UNITPRICE"));
        String CATEGORY= request.getParameter("CATEGORY"); 
        
        
        DBProduct product = (DBProduct) session.getAttribute("product"); 
        
       try {
            
            product.addProduct(PRODUCTNAME, STOCKLEVEL, UNITPRICE, CATEGORY);
            session.setAttribute("product", product);
            request.getRequestDispatcher("index.jsp").include(request,response);
        } catch (SQLException ex) { 
            Logger.getLogger(AddProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("AddNewProduct.js").include(request, response);

        }

          /*session.setAttribute("existErr", "Failed to add product");
          request.getRequestDispatcher("AddNewProduct.jsp").include(request, response);*/
        
        
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

}
