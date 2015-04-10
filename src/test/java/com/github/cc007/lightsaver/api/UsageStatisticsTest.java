/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.api;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rik
 */
public class UsageStatisticsTest {

    public UsageStatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getHours method, of class UsageStatistics.
     */
    @Test
    public void testGetHours_Date_Date() {
        System.out.println("getHours");
        Date startDate = new Date(1420000000000L);
        Date endDate = new Date(1430000000000L);
        int expResult = 0;
        int result = 0;
        try {
            //result = UsageStatistics.getHours(startDate, endDate);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(" Expected: " + expResult);
        System.out.println(" Result: " + result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getHours method, of class UsageStatistics.
     */
    @Test
    public void testGetHours_3args() {
        System.out.println("getHours with ID");
        Date startDate = new Date(1420000000000L);
        Date endDate = new Date(1430000000000L);
        int applianceId = 2;
        int expResult = 0;
        int result = 0;
        try {
            result = UsageStatistics.getHours(startDate, endDate, applianceId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(" Expected: " + expResult);
        System.out.println(" Result: " + result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSeconds method, of class UsageStatistics.
     */
    @Test
    public void testGetSeconds_Date_Date() {
        System.out.println("getSeconds");
        Date startDate = new Date(1420000000000L);
        Date endDate = new Date(1430000000000L);
        int expResult = 0;
        int result = 0;
        try {
            //result = UsageStatistics.getSeconds(startDate, endDate);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(" Expected: " + expResult);
        System.out.println(" Result: " + result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSeconds method, of class UsageStatistics.
     */
    @Test
    public void testGetSeconds_3args() {
        System.out.println("getSeconds with ID");
        Date startDate = new Date(1420000000000L);
        Date endDate = new Date(1430000000000L);
        int applianceId = 2;
        int expResult = 0;
        int result = 0;
        try {
           result = UsageStatistics.getSeconds(startDate, endDate, applianceId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(" Expected: " + expResult);
        System.out.println(" Result: " + result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getElectricityUsage method, of class UsageStatistics.
     */
    @Test
    public void testGetElectricityUsage_Date_Date() {
        System.out.println("getElectricityUsage");
        Date startDate = new Date(1420000000000L);
        Date endDate = new Date(1430000000000L);
        double expResult = 0.0;
        double result = 0.0;
        try {
           //result = UsageStatistics.getElectricityUsage(startDate, endDate);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(" Expected: " + expResult);
        System.out.println(" Result: " + result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getElectricityUsage method, of class UsageStatistics.
     */
    @Test
    public void testGetElectricityUsage_3args() {
        System.out.println("getElectricityUsage with ID");
        Date startDate = new Date(1420000000000L);
        Date endDate = new Date(1430000000000L);
        int applianceId = 2;
        double expResult = 0.0;
        double result = 0.0;
        try {
          result = UsageStatistics.getElectricityUsage(startDate, endDate, applianceId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(" Expected: " + expResult);
        System.out.println(" Result: " + result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
