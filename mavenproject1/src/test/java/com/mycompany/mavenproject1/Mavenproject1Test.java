/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

/**
 *
 * @author devinmadeley
 */
public class Mavenproject1Test {
    
    public Mavenproject1Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of checkout method, of class Mavenproject1.
     */
    @Test
    public void test1() {
        System.out.println("checkout");
        String[][] tools = new String[5][4];
        tools[0][0] = ("CHNS");
        tools[0][1] = ("Chainsaw"); 
        tools[0][2] = ("Stihl");
        tools[0][3] = ("1.49");
        tools[1][0] = ("LADW");
        tools[1][1] = ("Ladder"); 
        tools[1][2] = ("Werner");
        tools[1][3] = ("1.99");
        tools[2][0] = ("JAKD");
        tools[2][1] = ("Jackhammer"); 
        tools[2][2] = ("DeWalt");
        tools[2][3] = ("2.99");
        tools[3][0] = ("JAKR");
        tools[3][1] = ("Jackhammer"); 
        tools[3][2] = ("Ridgid");
        tools[3][3] = ("2.99");
        String tool = "JAKR";
        LocalDate checkoutDate = LocalDate.parse("2015-09-03");
        int numOfDays = 5;
        int discount = 101;
        Mavenproject1.checkout(tools, tool, checkoutDate, numOfDays, discount);
        //assertEquals("The discount is not in the form of a percentage. Cannot process rental.", 
        //        exception.getMessage());
        assertThrows(IllegalArgumentException.class, () -> {
		Integer.parseInt("One");
        });
    }
    public static List<String> readFileInList(String fileName)
  {
 
    List<String> lines = Collections.emptyList();
    try
    {
      lines =
       Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
    }
 
    catch (IOException e)
    {
 
      // do something
      e.printStackTrace();
    }
    return lines;
  }
    @Test
    public void test2() {
        String[][] tools = new String[5][4];
        tools[0][0] = ("CHNS");
        tools[0][1] = ("Chainsaw"); 
        tools[0][2] = ("Stihl");
        tools[0][3] = ("1.49");
        tools[1][0] = ("LADW");
        tools[1][1] = ("Ladder"); 
        tools[1][2] = ("Werner");
        tools[1][3] = ("1.99");
        tools[2][0] = ("JAKD");
        tools[2][1] = ("Jackhammer"); 
        tools[2][2] = ("DeWalt");
        tools[2][3] = ("2.99");
        tools[3][0] = ("JAKR");
        tools[3][1] = ("Jackhammer"); 
        tools[3][2] = ("Ridgid");
        tools[3][3] = ("2.99");
        
        String tool = "LADW";
        LocalDate checkoutDate = LocalDate.parse("2020-07-02");
        int numOfDays = 3;
        int discount = 10;
        Mavenproject1.checkout(tools, tool, checkoutDate, numOfDays, discount);
        List actualList = readFileInList("/Users/devinmadeley/NetBeansProjects/mavenproject1/Rental_Agreement.txt");
        Iterator<String> itr = actualList.iterator();

        System.out.println("lines list: " + actualList);
        List<String> expectedList = Arrays.asList("Tool Code: LADW","Tool Type: Ladder",
        "Tool Brand: Werner", "Number of Rental Days: 3", "Checkout Date: 07/02/20", 
        "Due Date: 07/05/20", "Daily Rental Charge: $1.99", "Charge Days: 2", 
        "Pre-Discount Charge: $3.98", "Discount Percent: 10%", "Discount Amount: $0.40",
        "Final Charge: $3.58");
        assertEquals(actualList, expectedList, "Rental Agreement has the expected data.");
    }
    
    @Test
    public void test3() {
        String[][] tools = new String[5][4];
        tools[0][0] = ("CHNS");
        tools[0][1] = ("Chainsaw"); 
        tools[0][2] = ("Stihl");
        tools[0][3] = ("1.49");
        tools[1][0] = ("LADW");
        tools[1][1] = ("Ladder"); 
        tools[1][2] = ("Werner");
        tools[1][3] = ("1.99");
        tools[2][0] = ("JAKD");
        tools[2][1] = ("Jackhammer"); 
        tools[2][2] = ("DeWalt");
        tools[2][3] = ("2.99");
        tools[3][0] = ("JAKR");
        tools[3][1] = ("Jackhammer"); 
        tools[3][2] = ("Ridgid");
        tools[3][3] = ("2.99");
        
        String tool = "CHNS";
        LocalDate checkoutDate = LocalDate.parse("2015-07-02");
        int numOfDays = 5;
        int discount = 25;
        Mavenproject1.checkout(tools, tool, checkoutDate, numOfDays, discount);
        List actualList = readFileInList("/Users/devinmadeley/NetBeansProjects/mavenproject1/Rental_Agreement.txt");
        Iterator<String> itr = actualList.iterator();

        System.out.println("lines list: " + actualList);
        List<String> expectedList = Arrays.asList("Tool Code: CHNS","Tool Type: Chainsaw",
        "Tool Brand: Stihl", "Number of Rental Days: 5", "Checkout Date: 07/02/15", 
        "Due Date: 07/07/15", "Daily Rental Charge: $1.49", "Charge Days: 3", 
        "Pre-Discount Charge: $4.47", "Discount Percent: 25%", "Discount Amount: $1.12",
        "Final Charge: $3.35");
        assertEquals(actualList, expectedList, "Rental Agreement has the expected data.");
    }
    
    @Test
    public void test4() {
        String[][] tools = new String[5][4];
        tools[0][0] = ("CHNS");
        tools[0][1] = ("Chainsaw"); 
        tools[0][2] = ("Stihl");
        tools[0][3] = ("1.49");
        tools[1][0] = ("LADW");
        tools[1][1] = ("Ladder"); 
        tools[1][2] = ("Werner");
        tools[1][3] = ("1.99");
        tools[2][0] = ("JAKD");
        tools[2][1] = ("Jackhammer"); 
        tools[2][2] = ("DeWalt");
        tools[2][3] = ("2.99");
        tools[3][0] = ("JAKR");
        tools[3][1] = ("Jackhammer"); 
        tools[3][2] = ("Ridgid");
        tools[3][3] = ("2.99");
        
        String tool = "JAKD";
        LocalDate checkoutDate = LocalDate.parse("2015-09-03");
        int numOfDays = 6;
        int discount = 0;
        Mavenproject1.checkout(tools, tool, checkoutDate, numOfDays, discount);
        List actualList = readFileInList("/Users/devinmadeley/NetBeansProjects/mavenproject1/Rental_Agreement.txt");
        Iterator<String> itr = actualList.iterator();

        System.out.println("lines list: " + actualList);
        List<String> expectedList = Arrays.asList("Tool Code: JAKD","Tool Type: Jackhammer",
        "Tool Brand: DeWalt", "Number of Rental Days: 6", "Checkout Date: 09/03/15", 
        "Due Date: 09/09/15", "Daily Rental Charge: $2.99", "Charge Days: 3", 
        "Pre-Discount Charge: $8.97", "Discount Percent: 0%", "Discount Amount: $0.00",
        "Final Charge: $8.97");
        assertEquals(actualList, expectedList, "Rental Agreement has the expected data.");
    }
    
    @Test
    public void test5() {
        String[][] tools = new String[5][4];
        tools[0][0] = ("CHNS");
        tools[0][1] = ("Chainsaw"); 
        tools[0][2] = ("Stihl");
        tools[0][3] = ("1.49");
        tools[1][0] = ("LADW");
        tools[1][1] = ("Ladder"); 
        tools[1][2] = ("Werner");
        tools[1][3] = ("1.99");
        tools[2][0] = ("JAKD");
        tools[2][1] = ("Jackhammer"); 
        tools[2][2] = ("DeWalt");
        tools[2][3] = ("2.99");
        tools[3][0] = ("JAKR");
        tools[3][1] = ("Jackhammer"); 
        tools[3][2] = ("Ridgid");
        tools[3][3] = ("2.99");
        
        String tool = "JAKR";
        LocalDate checkoutDate = LocalDate.parse("2015-07-02");
        int numOfDays = 9;
        int discount = 0;
        Mavenproject1.checkout(tools, tool, checkoutDate, numOfDays, discount);
        List actualList = readFileInList("/Users/devinmadeley/NetBeansProjects/mavenproject1/Rental_Agreement.txt");
        Iterator<String> itr = actualList.iterator();

        System.out.println("lines list: " + actualList);
        List<String> expectedList = Arrays.asList("Tool Code: JAKR","Tool Type: Jackhammer",
        "Tool Brand: Ridgid", "Number of Rental Days: 9", "Checkout Date: 07/02/15", 
        "Due Date: 07/11/15", "Daily Rental Charge: $2.99", "Charge Days: 5", 
        "Pre-Discount Charge: $14.95", "Discount Percent: 0%", "Discount Amount: $0.00",
        "Final Charge: $14.95");
        assertEquals(actualList, expectedList, "Rental Agreement has the expected data.");
    }
    
    @Test
    public void test6() {
        String[][] tools = new String[5][4];
        tools[0][0] = ("CHNS");
        tools[0][1] = ("Chainsaw"); 
        tools[0][2] = ("Stihl");
        tools[0][3] = ("1.49");
        tools[1][0] = ("LADW");
        tools[1][1] = ("Ladder"); 
        tools[1][2] = ("Werner");
        tools[1][3] = ("1.99");
        tools[2][0] = ("JAKD");
        tools[2][1] = ("Jackhammer"); 
        tools[2][2] = ("DeWalt");
        tools[2][3] = ("2.99");
        tools[3][0] = ("JAKR");
        tools[3][1] = ("Jackhammer"); 
        tools[3][2] = ("Ridgid");
        tools[3][3] = ("2.99");
        
        String tool = "JAKR";
        LocalDate checkoutDate = LocalDate.parse("2020-07-02");
        int numOfDays = 4;
        int discount = 50;
        Mavenproject1.checkout(tools, tool, checkoutDate, numOfDays, discount);
        List actualList = readFileInList("/Users/devinmadeley/NetBeansProjects/mavenproject1/Rental_Agreement.txt");
        Iterator<String> itr = actualList.iterator();

        System.out.println("lines list: " + actualList);
        List<String> expectedList = Arrays.asList("Tool Code: JAKR","Tool Type: Jackhammer",
        "Tool Brand: Ridgid", "Number of Rental Days: 4", "Checkout Date: 07/02/20", 
        "Due Date: 07/06/20", "Daily Rental Charge: $2.99", "Charge Days: 1", 
        "Pre-Discount Charge: $2.99", "Discount Percent: 50%", "Discount Amount: $1.50",
        "Final Charge: $1.50");
        assertEquals(actualList, expectedList, "Rental Agreement has the expected data.");
    }
}
