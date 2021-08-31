package com.Mail;

import java.util.List;
import java.util.Scanner;

public class SigninFile {
    static void SigninPage(Context context)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("......WELCOME TO NEW ACCOUNT OPENING......");
        System.out.print("Enter your Name:  ");
        String Name = scanner.next();
        CheckNewIdName(context, Name);
    }
    static void CheckNewIdName(Context context, String Name)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your New Id Name:  ");
        String NewId = scanner.next();
        if(context.IdList.contains(NewId))
        {
            System.out.println("This ID name is Already Taken Try Another Id Name");
            CheckNewIdName(context,  Name);
        }
        context.IdList.add(NewId);
        CheckNewNumber(context, Name, NewId);
    }
    static void CheckNewNumber(Context context,String Name, String  NewId)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your New Mobile Number:  ");
        int Number = scanner.nextInt();
        if(context.NumberList.contains(Number))
        {
            System.out.println("This Number is Already Taken Try Another Number");
            CheckNewNumber(context, Name, NewId);
        }
        context.NumberList.add(Number);
        GetNewPassword(context, Name, NewId, Number);
    }
  static void GetNewPassword(Context context, String Name, String NewId, int Number)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your New Password:  ");
        String Password = scanner.next();
        System.out.print("Enter your New Password Again :  ");
        String AgainPass = scanner.next();
        System.out.println(Password);
        System.out.println(AgainPass);
        boolean s = Password.equals(AgainPass);
        System.out.println(s);
        if (Password.equals(AgainPass) == true)
        {
            User tempUser = new User(Number, Password);
            tempUser.Id= NewId;
            tempUser.Name = Name;
            context.UserMaps.put(tempUser.Id, tempUser);
            PreviewUser(context, NewId);
        }
        System.out.println("Incorrect Password. Try Again.");
        GetNewPassword(context, Name, NewId, Number);
    }
    static void PreviewUser(Context context, String NewId)
    {
        System.out.println("This is for Preview ");
        User PushedUser = context.UserMaps.get(NewId);
        System.out.println("your Name --> " + PushedUser.Name);
        System.out.println("your Id Name --> " + PushedUser.Id);
        System.out.println("Your Accouct is Successfully Created :)");
        MailClasses.main(context);
    }
}

