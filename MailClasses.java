package com.Mail;

import javax.naming.ldap.HasControls;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MailClasses {
    public static void main(String[] args)
    {
        Context context = new Context();
        main(context);
    }
    public static void main(Context context)
    {
        System.out.println(".......WELCOME TO MAIL.......");
        System.out.println("No: 1 --> Login");
        System.out.println("No: 2 --> Signin");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Your Option ::  ");
        int check = scanner.nextInt();
        if(check == 1)
        {
            LoginFile.LoginPage(context);
        }
        else if (check == 2)
        {
            SigninFile.SigninPage(context);
        }
    }
}
class User {
    String Id;
    String Name;
    private int Number;
    private String Code;
    List<Mail> NewMails = new ArrayList<Mail>();
    List<Mail> Inbox = new ArrayList<Mail>();
    List<Mail> Outbox = new ArrayList<Mail>();

    public User(int Number, String Code) {
        this.Number = Number;
        this.Code = Code;
    }

    public boolean CheckPW()
    {
        System.out.print("Enter Your Password ::  ");
        Scanner scanner = new Scanner(System.in);
        String GetPassword = scanner.next();
        if (GetPassword.equals(this.Code) == true)
        {
            return true;
        }
        return false;
    }
}
class Mail
{
    String To;
    String From;
    String Subject;
    String Content;

}
class Context
{
    HashMap<String, User> UserMaps = new HashMap<String, User>();
    List<String> IdList = new ArrayList<String>();
    List<Integer> NumberList = new ArrayList<Integer>();
}