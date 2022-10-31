
package com.mycompany.mavenproject1;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter; 
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.text.NumberFormat;

/**
 *
 * @author devinmadeley
 */
public class Mavenproject1 {

    public static void main(String[] args)
    {
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
        
        try {
            File rentalAgrmt = new File("Rental_Agreement.txt");
            if (rentalAgrmt.createNewFile()) {
                System.out.println("File created: " + rentalAgrmt.getName());
            }
            else 
            {
                System.out.println("File already exists.");
            }
        } 
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        //checkout(tools, tools[0][0], java.time.LocalDate.now(), 7, 25);
    }
    
    public static int rentalDayCount(int index, LocalDate checkoutDate, int numOfDays)
    {
        boolean weekendCharge = false;
        boolean holidayCharge = false;
        int chargedRentalDayCount = 0;
        int weekDay = checkoutDate.getDayOfWeek().getValue();
        LocalDate dateBeingParsed = checkoutDate;
        LocalDate fourthOfJuly = LocalDate.of(checkoutDate.getYear(), Month.JULY, 4);
        LocalDate laborDay = null;
 
        if(checkoutDate.getMonth() == Month.SEPTEMBER)
        {
            laborDay = checkoutDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        }
    
        //Holiday charge for chainsaw rentals
        if(index == 0)
        {
            holidayCharge = true;
        }
        //Weekend charge for ladder rentals
        else if(index == 1)
        {
            weekendCharge = true;
        }
        
        for(int i = 1; i <= numOfDays; i++)
        {
            dateBeingParsed = dateBeingParsed.plusDays(1);
            //Once we reach Sunday, wrap back around to Monday
            if(weekDay == 7)
            {
                weekDay = 1;
            }
            else
            {
                weekDay++;
            }
            
            if(dateBeingParsed.equals(LocalDate.of(checkoutDate.getYear(), Month.JULY, 3)) && weekDay == 5)
            {
                //If fourthOfJuly falls on Saturday, count Friday as the holiday
                fourthOfJuly = LocalDate.of(checkoutDate.getYear(), Month.JULY, 3);
            }
            else if(dateBeingParsed.equals(fourthOfJuly) && weekDay == 7)
            {
                //If fourthOfJuly falls on Saturday, count Friday as the holiday
                fourthOfJuly = LocalDate.of(checkoutDate.getYear(), Month.JULY, 5);
            }
            
            if((holidayCharge == false && dateBeingParsed.equals(fourthOfJuly)) ||
                (holidayCharge == false && dateBeingParsed.equals(laborDay)))
            {
                //Do nothing, we don't charge for holidays.
            }
            else if(weekDay < 6 || weekendCharge == true ||
                    (holidayCharge == true && dateBeingParsed.equals(fourthOfJuly)) ||
                    (holidayCharge == true && dateBeingParsed.equals(laborDay)))
            {
                chargedRentalDayCount++;
            }
        }
        
        return chargedRentalDayCount;
    }
    public static void checkout(String tools[][], String tool, LocalDate checkoutDate, int numOfDays, int discount)
    {
        int index;
        double discountDec = discount * .01;
        //Date of rental put into the MM/DD/YY format
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yy");
        String parsedDate = checkoutDate.format(formatters);
        //Date tool should be returned
        LocalDate returnDate = checkoutDate.plusDays(numOfDays);
        String parsedReturnDate = returnDate.format(formatters);
        
        if(numOfDays < 1)
        {
            throw new  IllegalArgumentException("The number of days for " +
                "rental is less than one. Cannot process rental. ");
        }
        else if(discount < 0 || discount > 100)
        {
            throw new IllegalArgumentException("The discount is not in the " +
                "form of a percentage. Cannot process rental.");
        }
        else
        {
            System.out.println("Customer has chosen to rent " + tool + " for "
                + numOfDays + " days starting on " + java.time.LocalDate.now());
        }
        //Determine the index based on the tool the customer chose to rent
        switch(tool)
        {
            case "CHNS":
                index = 0;
                break;
            case "LADW":
                index = 1;
                break;
            case "JAKD":
                index = 2;
                break;
            case "JAKR":
                index = 3;
                break;
            default:
                throw new IllegalArgumentException("Invalid tool code: " + tool);
        }
        int chargedRentalDayCount = rentalDayCount(index, checkoutDate, numOfDays);
        
        //Calculate prices
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();       
        double hourlyToolPrice = Double.parseDouble(tools[index][3]);
        double preDiscountCharge = hourlyToolPrice * chargedRentalDayCount;
        String preDiscountChargeString = currencyFormatter.format(preDiscountCharge);
        double discountedAmount = preDiscountCharge * discountDec;
        String discountedAmountString = currencyFormatter.format(discountedAmount);
        double finalCharge = preDiscountCharge - discountedAmount;
        String finalChargeString = currencyFormatter.format(finalCharge);
        
        //Create the rental agreement document
        try
        {
            FileWriter myWriter = new FileWriter("Rental_Agreement.txt");
            myWriter.write("Tool Code: " + tool + System.getProperty("line.separator"));
            myWriter.write("Tool Type: " + tools[index][1] + System.getProperty("line.separator"));
            myWriter.write("Tool Brand: " + tools[index][2] + System.getProperty("line.separator"));
            myWriter.write("Number of Rental Days: " + numOfDays + System.getProperty("line.separator"));
            myWriter.write("Checkout Date: " + parsedDate + System.getProperty("line.separator"));
            myWriter.write("Due Date: " + parsedReturnDate + System.getProperty("line.separator"));
            myWriter.write("Daily Rental Charge: $" + tools[index][3] + System.getProperty("line.separator"));
            myWriter.write("Charge Days: " + chargedRentalDayCount + System.getProperty("line.separator"));
            myWriter.write("Pre-Discount Charge: " + preDiscountChargeString + System.getProperty("line.separator"));
            myWriter.write("Discount Percent: " + discount + "%" + System.getProperty("line.separator"));
            myWriter.write("Discount Amount: " + discountedAmountString + System.getProperty("line.separator"));
            myWriter.write("Final Charge: " + finalChargeString + System.getProperty("line.separator"));
            myWriter.close();
            System.out.println("Rental Agreement Generated.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
}
