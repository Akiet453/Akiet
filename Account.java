/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asg2;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author Nick
 */
public class Account implements Serializable
{
    private double balance = 300;
    private int initialdate;
    private int laterdate;
    private boolean dateflag = false;
    private double rate;
     
    public void deposit()
    {
        Scanner sc = new Scanner(System.in); 
            
        if(dateflag != true) 
        {
            getdate1();
            System.out.println("Enter your deposit amount");
            double input = sc.nextDouble();
            balance += input;
            calcInterest();
            System.out.println("Your balance total currently is: " + balance);
            
        }
        else
        {  
            getdate2();
            System.out.println("Enter your deposit amount");
            double input = sc.nextDouble();
            balance += input;
            calcInterest();
            System.out.println("Your balance total currently is: " + balance);
        }
//           System.out.println("Enter your deposit amount");
//           double input = sc.nextDouble();
//           balance = balance + input;
//           calcInterest();
//           System.out.println("Your balance total currently is: " + balance); 
    }
     
    public void withdraw()
    {        
        Scanner sc = new Scanner(System.in);
        System.out.println("Your current balance is: " + balance);
        System.out.println("Enter withdraw amount: ");
        double tempBalance = sc.nextDouble();
        
        double b = tempBalance;
        balance = balance - b;
        System.out.println("Your remaining balance is: " + balance);        
    }
         
    public void checkBalance()
    {    
        Locale us = new Locale("us");
        NumberFormat usFormat = NumberFormat.getCurrencyInstance(us);
         
        System.out.println("Your current total balance is: " + usFormat.format(balance));
    }
     
    public void getdate1()
    {
        System.out.println("Enter Date ('MM/DD/YYYY')");
        Calendar cal1 = Calendar.getInstance();
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("MM/DD/YYYY");
        ParsePosition pos = new ParsePosition(0);
        Date date = formatter.parse(input, pos);
        cal1.setTime(date);
        initialdate = cal1.get(Calendar.DAY_OF_YEAR);
        dateflag = true;
    }
     
    public void getdate2()
    {
        do{
            System.out.println("Enter Date ('MM/DD/YYYY')");
            Calendar cal2 = Calendar.getInstance();
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat("MM/DD/YYYY");
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(input, pos);
            cal2.setTime(date);
            laterdate = cal2.get(Calendar.DAY_OF_YEAR);
             
            if(laterdate < initialdate)
            {
                System.out.println("Please enter a date after " + initialdate);
            }          
        }while(laterdate < initialdate);
    }
     
    public void calcInterest()
    {
        int datediff = laterdate - initialdate;
        rate = .072/365;
//        rate can be changed, I just entered a random value in
        double ratetime = Math.pow(1+rate,datediff);
             
        if(laterdate > 0)
        {   
            balance = balance * ratetime;
//            initialdate = laterdate;
        }
    }
         
    public void menu()
    {
        int input = 0;
        Scanner sc = new Scanner(System.in);
             
        while(input != 4)
        {
            System.out.println("1) Deposit Money");
            System.out.println("2) Withdraw Money");
            System.out.println("3) Check Current Balance");
            System.out.println("4) Exit");    
               
            input = sc.nextInt();
            if(input == 1)
            {                
                deposit();           
            }
            else if (input == 2)
            {
                withdraw();
            }
            else if (input == 3)
            {
                checkBalance();
            }
            else if (input != 1 && input != 2 && input != 3)
            {
                    
            }
        }
    }    
}