/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

 

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Log;

import uts.isd.model.User;
import uts.isd.model.dao.DBManager;

 

public class UserLogsServlet extends HttpServlet {

    private Timestamp from;
    private Timestamp to;
    private long defaultFrom = 2592000000L; // 30 days
    private long currentTime;
    
    
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        /* Use getPathInfo to figure out what URL suffix is being used in the request.
         * i.e. the bit after the controller mapping.
         * https://stackoverflow.com/questions/4278083/how-to-get-request-uri-without-context-path
         * 
         * ALSO: Make sure in web.xmxl you use a wildcard to match any sub-path you want to use in this controller.
         *  <servlet-mapping>
         *    <servlet-name>UsersController</servlet-name>
         *    <url-pattern>/User/*</url-pattern>
         *   </servlet-mapping>
         */
        String pathinfo = request.getServletPath();
        switch (pathinfo)
        {
            case "/UserLogs":
                doUserLogsGet(request, response);
                break;
            case "/AdminLogs":
                doAdminLogsGet(request, response);
                break;
                
        }
       
    }
    
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        
        //init helper classes
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();
        
        
        //get session info
        User user = (User) session.getAttribute("user");
        String fromString = request.getParameter("from");
        String toString = request.getParameter("to");
        
        
       

        // validate input
        if(!validator.validateTimestamp(fromString)){
            from = Timestamp.valueOf(fromString);
        } else {
            from = new Timestamp(System.currentTimeMillis() - defaultFrom); // -30 days
        }
        
        if(!validator.validateTimestamp(toString)){
            to = Timestamp.valueOf(toString);
        } else {
            to = new Timestamp(System.currentTimeMillis());
        }
        
        
                
        //execute query
        if (user != null) {           
            
            try {
                List<Log> logs = manager.findLogs(user.getUserID(),from,to);
                request.setAttribute("logs", logs);
            } catch (SQLException ex) {
                request.setAttribute("existErr", "no results");
                Logger.getLogger(UserLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            session.setAttribute("existErr", "Please Login"); 
            request.getRequestDispatcher("login.jsp").include(request,response);
        }
    }

    private void doAdminLogsGet(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
             
    /**
     * Handles the HTTP <code>GET</code> method.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void doUserLogsGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        if (user == null){
            session.setAttribute("existErr", "Error, no one is logged in.");
            request.getRequestDispatcher("account.jsp").include(request,response);
            return;//early return since nothing can be done.
        } 
        
        currentTime = System.currentTimeMillis();
        from = new Timestamp(currentTime - defaultFrom);
        to = new Timestamp(currentTime);
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        List<Log> logs;
        try{
            logs = manager.findLogs(user.getUserID(), from, to);
            request.setAttribute("logs", logs);
        }   catch (SQLException ex) {
            Logger.getLogger(UserLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("existErr", "Logs unavaliable");
        }
        
        request.getRequestDispatcher("/logs.jsp").include(request,response); //should be .forward(request,response); ? 
    }
}