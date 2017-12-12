/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @authors Akiet
 */
import java.io.*;
import java.util.*;

public class ATM 
{
    Accounts[] accs = new Accounts[3];
     
    public static void main(String[] args) throws Exception
    {
        ATM ac = new ATM();
        int input;
        System.out.println("Welcome to the ATM.");
        System.out.println("Have you ran the ATM before? Input 1 for Yes or 2 for No");
        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();
        if(input == 1)
        { 
            ac.loadAccts();   
        }
        else
        {
            ac.populateAccts(); 
        }
         
        ac.pickAccts();
        ac.saveAccts(); 
    }
     
    public void populateAccts()
    {
       for(int i = 0; i < accs.length; i++)
        {
            accs[i] = new Accounts();
        } 
    }
     
    public void loadAccts() throws Exception
    {
        FileInputStream fis = new FileInputStream("file.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        accs = (Accounts[])ois.readObject();
        fis.close();
    }
     
    public void pickAccts()
    {
        int input; 
        do
        {                    
            System.out.println("Choose an account:");
            System.out.println("1) Debit");
            System.out.println("2) Credit");
            System.out.println("3) Savings");
            System.out.println("4) Exit");
                 
            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();
                        
            switch(input)
            {
                //Debit Account
                case 1:
                {
                    System.out.println("You have chosen Debit.\n");
                    accs[input-1].menu();                        
                }
                break;
                //Credit Account
                case 2:
                {
                    System.out.println("You have chosen Credit.\n");
                    accs[input-1].menu();
                }
                break;
                //Savings Account
                case 3:
                {
                    System.out.println("You have chosen Savings.\n");
                    accs[input-1].menu();
                }
                break;
                case 4:
                {
                    System.out.println("Good-bye.");
                }
                break;
                default:
                {
                    
                }
                break;
            }              
        }while(input != 4);
    }
     
    public void saveAccts() throws Exception
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("file.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(accs);
            oos.flush();
            fos.close();
        }
      
        catch(FileNotFoundException fnf)
        {
            System.out.println("File not found.");
            saveAccts();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
