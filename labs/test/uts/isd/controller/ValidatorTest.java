/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.sql.Date;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author super
 */
public class ValidatorTest {
    
    public ValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validate method, of class Validator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        String pattern = "";
        String input = "";
        Validator instance = new Validator();
        boolean expResult = false;
        boolean result = instance.validate(pattern, input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkEmpty method, of class Validator.
     */
    @Test
    public void testCheckEmpty() {
        System.out.println("checkEmpty");
        String email = "";
        String password = "";
        Validator instance = new Validator();
        boolean expResult = false;
        boolean result = instance.checkEmpty(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateEmail method, of class Validator.
     */
    @Test
    public void testValidateEmail() {
        System.out.println("validateEmail");
        String email = "";
        Validator instance = new Validator();
        boolean expResult = false;
        boolean result = instance.validateEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateString method, of class Validator.
     */
    @Test
    public void testValidateString() {
        System.out.println("validateString");
        String x = "";
        Validator instance = new Validator();
        boolean expResult = false;
        boolean result = instance.validateString(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validatePassword method, of class Validator.
     */
    @Test
    public void testValidatePassword() {
        System.out.println("validatePassword");
        String password = "";
        Validator instance = new Validator();
        boolean expResult = false;
        boolean result = instance.validatePassword(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateTimestamp method, of class Validator.
     */
    @Test
    public void testValidateTimestamp() {
        System.out.println("validateTimestamp");
        String ts = "2021-05-16 01:12:00";
        Validator instance = new Validator();
        boolean expResult = true;
        boolean result = instance.validateTimestamp(ts);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of sanitiseDate method, of class Validator.
     */
    @Test
    public void testSanitiseDate() {
        System.out.println("sanitiseDate");
        String date = "2sdf7y9823";
        Validator instance = new Validator();
        Date expResult = null;
        Date result = instance.sanitiseDate(date);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of sanitiseTimestamp method, of class Validator.
     */
    @Test
    public void testSanitiseTimestamp() {
        System.out.println("sanitiseTimestamp");
        String ts = "2021-05-16T01:12";
        Validator instance = new Validator();
        String expResult = "2021-05-16 01:12:00";
        String result = instance.sanitiseTimestamp(ts);
        assertEquals(expResult, result);
    }

    /**
     * Test of sanitiseString method, of class Validator.
     */
    @Test
    public void testSanitiseString() {
        //test toLowercase()
        System.out.println("sanitiseString");
        String s = "AbjklsjiEj@sjflk.NMseio.";
        Validator instance = new Validator();
        String expResult = "abjklsjiej@sjflk.nmseio.";
        String result = instance.sanitiseString(s);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of clear method, of class Validator.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        HttpSession session = null;
        Validator instance = new Validator();
        instance.clear(session);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
