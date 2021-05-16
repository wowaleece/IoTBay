/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

/**
 *
 * @author super
 */
import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;


public class Validator implements Serializable{ 

    // debug regex using https://regexr.com/346hf
    private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,5})*)"; //"([a-zA-Z0-9-]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";  //"[a-zA-Z0-9]{2,}";    
    private String stringPattern = "[A-Za-z0-9]*";//"([A-Z][a-z]+[\\s])+[A-Z][a-z]*";       
    private String passwordPattern = "[a-zA-Z0-9]{2,}";  // "^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" // at least 8 char, one letter, one number and one special character
    private String expiryPattern = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$";
    /*private String datePattern = "(18|19|20)[0-9]{2}[-](0[13578]|1[02])[-](0[1-9]|[12][0-9]|3[01])" //31 day months
                               + "(18|19|20)[0-9]{2}[-](0[469]|11)[-](0[1-9]|[12][0-9]|30)[-]" //30 day months
                               + "(18|19|20)[0-9]{2}[-](02)[-](0[1-9]|1[0-9]|2[0-8])" // 28 day Feb
                               + "(((18|19|20)(04|08|[2468][048]|[13579][26]))|2000)[-](02)[-]29" // 29 day Feb
    
            
    */
    
    public Validator(){    }       

    public boolean validate(String pattern, String input){       
        Pattern regEx = Pattern.compile(pattern);       
        Matcher match = regEx.matcher(input);       

        return match.matches(); 
    }       



    public boolean checkEmpty(String email, String password){       

        return  email.isEmpty() || password.isEmpty();   
    }


    public boolean validateEmail(String email){                       

        return validate(emailPattern,email);   
    }

    public boolean validateCardExpiry(String cardExpiry){                       

        return validate(expiryPattern,cardExpiry);   
    }

    public boolean validateString(String x){

        return validate(stringPattern,x); 
    }       


    public boolean validatePassword(String password){

        return validate(passwordPattern,password); 
    }
    
    public Date sanitiseDate(String date){        
        try {
                return java.sql.Date.valueOf(date);
        }catch (IllegalArgumentException ex) {
            Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void clear(HttpSession session) {
        session.setAttribute("emailErr", "Enter email");
        session.setAttribute("passErr", "Enter password");
        session.setAttribute("existErr", "");
        session.setAttribute("nameErr", "Enter name");
        session.setAttribute("expiryErr", "Enter expiry date = mm/yy");
    }
    
}