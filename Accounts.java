/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author Akiet
 */
import java.io.*;
import java.text.*;
import java.util.*;

public class Accounts implements Serializable
{
    private double balance = 300;
    private long initialdate;
    private long laterdate;
    private boolean dateflag = false;
    private double rate;
     
     public void deposit()
    {
        Scanner sc = new Scanner(System.in); 
            
        if(dateflag == false) 
        {
            Locale us = new Locale ("us");
            NumberFormat usFormat = NumberFormat.getCurrencyInstance(us);
            getdate1();
            System.out.println("Enter your deposit amount");
            double input = sc.nextDouble();
            balance += input;
            calcInterest();
            System.out.println("Your balance total currently is: " + usFormat.format(balance));
            
        }
        else
        {
            Locale us = new Locale ("us");
            NumberFormat usFormat = NumberFormat.getCurrencyInstance(us);
            getdate2();
            System.out.println("Enter your deposit amount");
            double input = sc.nextDouble();
            balance += input;
            calcInterest();
            System.out.println("Your balance total currently is: " + usFormat.format(balance));
        }
//           System.out.println("Enter your deposit amount");
//           double input = sc.nextDouble();
//           balance = balance + input;
//           calcInterest();
//           System.out.println("Your balance total currently is: " + balance); 
    }
     
    public void withdraw()
    {        
        Locale us = new Locale ("us");
        NumberFormat usFormat = NumberFormat.getCurrencyInstance(us);
        Scanner sc = new Scanner(System.in);
        System.out.println("Your current balance is: " + usFormat.format(balance));
        System.out.println("Enter withdraw amount: ");
        double tempBalance = sc.nextDouble();
        if(tempBalance > balance)
            {   
                while(tempBalance > balance)
                {    
                    System.out.println("\nInsufficient funds.");
                    System.out.println("Please enter a valid amount.");
                    tempBalance = sc.nextDouble();
                }    
            }
        double b = tempBalance;
        balance = balance - b;
        System.out.println("Your remaining balance is: " + usFormat.format(balance) + "\n");        
    }
         
    public void checkBalance()
    {    
        Locale us = new Locale("us");
        NumberFormat usFormat = NumberFormat.getCurrencyInstance(us);
         
        System.out.println("Your current total balance is: " + usFormat.format(balance) + "\n");
    }
     
    public void getdate1()
    {
        boolean isNotValid = true;
        Date cal1 = null;
        System.out.println("Enter Date ('MM/DD/YYYY')");
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
 
        do {
 
            try {
                String date = sc.nextLine();
                cal1 = formatter.parse(date);
                isNotValid = false;
            } catch (ParseException e) {
 
                System.out.println("Please enter a valid date");
            }
        } while (isNotValid);
 
        initialdate = cal1.getTime();
        dateflag = true;
//        System.out.println("Enter Date ('MM/DD/YYYY')");
//        Calendar cal1 = Calendar.getInstance();
//        Scanner sc = new Scanner(System.in);
//        String input = sc.next();
//        SimpleDateFormat formatter;
//        formatter = new SimpleDateFormat("MM/dd/yyyy");
//        ParsePosition pos = new ParsePosition(0);
//        Date date = formatter.parse(input, pos);
//        cal1.setTime(date);
//        initialdate = cal1.get(Calendar.DAY_OF_YEAR);
//        dateflag = true;
    }
     
    public void getdate2()
    {
        boolean isNotValid = true;
        Date cal2 = null;
        System.out.println("Enter Date ('MM/DD/YYYY')");
        Scanner sc = new Scanner(System.in);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
 
            do {
 
                try {
                    String date = sc.nextLine();
                    cal2 = formatter.parse(date);
                    laterdate = cal2.getTime();
 
 
                    if (laterdate >= initialdate) {
                        calcInterest();
                        initialdate = laterdate;
                        isNotValid = false;
                    } else {
                        System.out.println("Please enter a date after " + new Date(initialdate).toString());
                    }
 
                } catch (ParseException e) {
 
                    System.out.println("Please enter a valid date");
                }
            } while (isNotValid);
//        do{
//            System.out.println("Enter Date ('MM/DD/YYYY')");
//            Calendar cal2 = Calendar.getInstance();
//            Scanner sc = new Scanner(System.in);
//            String input = sc.next();
//            SimpleDateFormat formatter;
//            formatter = new SimpleDateFormat("MM/dd/yyyy");
//            ParsePosition pos = new ParsePosition(0);
//            Date date = formatter.parse(input, pos);
//            cal2.setTime(date);
//            laterdate = cal2.get(Calendar.DAY_OF_YEAR);
//             
//            if(laterdate < initialdate)
//            {
//                System.out.println("Please enter a date after " + initialdate);
//            }          
//        }while(laterdate < initialdate);
    }
     
    public void calcInterest()
    {
        NumberFormat formatter = new DecimalFormat("#0.00");
        long datediff = (laterdate - initialdate) / (24 * 60 * 60 * 1000);
        rate = .072/365;
//        rate can be changed, I just entered a random value in
        double ratetime = Math.pow(1+rate,datediff);
              
        if(laterdate != 0)
        {   
            balance = Double.valueOf(formatter.format(balance * ratetime));
            initialdate = laterdate;
        }
        
//        int datediff = laterdate - initialdate;
//        rate = .072/365;
////        rate can be changed, I just entered a random value in
//        double ratetime = Math.pow(1+rate,datediff);
//             
//        if(laterdate > 0)
//        {   
//            balance = balance * ratetime;
////            initialdate = laterdate;
//        }
    }
         
    public void menu()
    {
        int input = 0;
        Scanner sc = new Scanner(System.in);
             
        while(input != 4)
        {
            System.out.println("What would you like to do?");
            System.out.println("1) Deposit Money");
            System.out.println("2) Withdraw Money");
            System.out.println("3) Check Current Balance");
            System.out.println("4) Exit");    
               
            input = sc.nextInt();
            switch(input)
            {
                //Deposit
                case 1:
                {
                    System.out.println("You have chosen to Deposit.");
                    deposit();
                }
                break;
                //Withdraw
                case 2:
                {
                    System.out.println("You have chose to Withdraw.");
                    withdraw();
                }
                break;
                //Check Balance
                case 3:
                {
                    checkBalance();
                }
                break;
                default:
                {
                    System.out.println("Going back to main menu.");
                }
                break;
                
            }
        }
    } 
}
