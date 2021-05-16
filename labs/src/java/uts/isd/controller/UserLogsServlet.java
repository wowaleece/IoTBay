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

    private Timestamp tsFrom;
    private Timestamp tsTo;
    private long defaultFrom = 2592000000L; // 30 days
    private long currentTime;
    
    
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        /* Use getPathInfo tsTo figure out what URL suffix is being used in the request.
         * i.e. the bit after the controller mapping.
         * https://stackoverflow.com/questions/4278083/how-tsTo-get-request-uri-without-context-path
         * 
         * ALSO: Make sure in web.xmxl you use a wildcard tsTo match any sub-path you want tsTo use in this controller.
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
        String pathinfo = request.getServletPath();
        switch (pathinfo)
        {
            case "/UserLogs":
                doUserLogsPost(request, response);
                break;
            case "/AdminLogs":
                doAdminLogsPost(request, response);
                break;
                
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
        tsFrom = new Timestamp(currentTime - defaultFrom);
        tsTo = new Timestamp(currentTime);
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        List<Log> logs;
        try{
            logs = manager.findLogs(user.getUserID(), tsFrom, tsTo);
            request.setAttribute("logs", logs);
        }   catch (SQLException ex) {
            Logger.getLogger(UserLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("existErr", "Logs unavaliable");
        }
        
        request.getRequestDispatcher("/logs.jsp").include(request,response); //should be .forward(request,response); ? 
    }
    
    /**
     * serves users' personal logs
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void doUserLogsPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        
        //init helper classes
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();
        
        
        //get session info
        User user = (User) session.getAttribute("user");
        
        //get and sanitise input parameters
        String strTSFrom = request.getParameter("tsFrom");
        String strTSTo = request.getParameter("tsTo");
        strTSFrom = validator.sanitiseTimestamp(strTSFrom);
        strTSTo = validator.sanitiseTimestamp(strTSTo);
        
        request.setAttribute("toDateErr", "");
        request.setAttribute("fromDateErr", "");
        
        // validate input (check null and regex format
        if(validator.validateTimestamp(strTSFrom)){
            tsFrom = Timestamp.valueOf(strTSFrom);
        } else {
            tsFrom = new Timestamp(System.currentTimeMillis() - defaultFrom); // -30 days
            request.setAttribute("fromDateErr", " Defaulted to");
        }

        if(validator.validateTimestamp(strTSTo)){
            tsTo = Timestamp.valueOf(strTSTo);
        } else {
            tsTo = new Timestamp(System.currentTimeMillis());
            request.setAttribute("toDateErr", " Defaulted to");
        }
        
        //revert Timestamp strings tsTo return on the request
        strTSFrom = validator.revertTimestamp(tsFrom.toString());
        strTSTo = validator.revertTimestamp(tsTo.toString());
        request.setAttribute("tsFrom", strTSFrom);
        request.setAttribute("tsTo",strTSTo);        
                
        //execute query        //execute query
        if (user == null) {
            session.setAttribute("existErr", "Please Login"); 
            request.getRequestDispatcher("login.jsp").include(request,response);
        } else {

            try {
                List<Log> logs = manager.findLogs(user.getUserID(),tsFrom,tsTo);
                request.setAttribute("logs", logs);
                
            } catch (SQLException ex) {
                request.setAttribute("existErr", "no results");
                Logger.getLogger(UserLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("/logs.jsp").include(request,response);
        }
    }

    private void doAdminLogsPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}