/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uts.isd.model.Address;

/**
 *
 * @author super
 */
public class DBAddressTest {
    
    public DBAddressTest() {
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
     * Test of allAddresses method, of class DBAddress.
     */
    @Test
    public void testAllAddresses() {
        System.out.println("allAddresses");
        DBAddress instance = null;
        List<Address> expResult = null;
        List<Address> result = instance.allAddresses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAddresses method, of class DBAddress.
     */
    @Test
    public void testFindAddresses() {
        System.out.println("findAddresses");
        String search = "";
        DBAddress instance = null;
        List<Address> expResult = null;
        List<Address> result = instance.findAddresses(search);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
