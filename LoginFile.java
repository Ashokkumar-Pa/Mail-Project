package com.Mail;

import javax.management.ObjectName;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LoginFile {
    static void LoginPage(Context context)
    {
        System.out.println(".......Welcome to Login :)");
        System.out.print("Enter Your Login Id ::  ");
        Scanner scanner = new Scanner(System.in);
        String GetLoginID = scanner.next();
        boolean Check = context.UserMaps.containsKey(GetLoginID);
        System.out.println(Check);
        if(Check == true)
        {
            System.out.println("Your Id Name is " + GetLoginID + " is " + Check);
            CallPasswordCheck(context, GetLoginID);
        }
        System.out.println("Invalied ID Name");
        MailClasses.main(context);
    }
    static void CallPasswordCheck(Context context, String GetLoginID)
    {
        User OneUserDetailes = context.UserMaps.get(GetLoginID);
        boolean isPassWordCorrect = OneUserDetailes.CheckPW();
        System.out.println(isPassWordCorrect);
        if (isPassWordCorrect == true)
        {
            CallOptions(context, OneUserDetailes);
        }
        System.out.println("Invalied Password");
        CallPasswordCheck(context, GetLoginID);
    }
    static void CallOptions(Context context, User OneUserDetailes)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello " + OneUserDetailes.Name + "!");
        System.out.println(OneUserDetailes.NewMails.size() + " New Mails");
        System.out.println("Enter No: 1 --> Open New Mails");
        System.out.println("Enter No: 2 --> Open Inbox");
        System.out.println("Enter No: 3 --> Open Outbox");
        System.out.println("Enter No: 4 --> Send New Mail");
        System.out.println("Enter No: 0 --> Logout");
        System.out.print("Enter Your Option ::  ");
        int CheckIn = scanner.nextInt();
        if (CheckIn == 1)
        {
            if(OneUserDetailes.NewMails.size() > 0)
            {
                OpenNewMail(OneUserDetailes);
            }
            else
            {
                System.out.println("New Mail Box is Empty");
            }
        }
        else if (CheckIn == 2)
        {
            if(OneUserDetailes.Inbox.size() > 0)
            {
                OpenInbox(OneUserDetailes);
            }
            else
            {
                System.out.println("Inbox is Empty");
            }
        }
        else if(CheckIn == 3)
        {
            if(OneUserDetailes.Outbox.size() > 0)
            {
                OpenOutbox(OneUserDetailes);
            }
            else
            {
                System.out.println("Outbox is Empty");
            }
        }
        else if (CheckIn == 4)
        {
            CreateNewMail(OneUserDetailes, context);
        }
        else if (CheckIn == 0)
        {
            MailClasses.main(context);
        }
        CallOptions(context, OneUserDetailes);
    }
    static void OpenNewMail(User OneUserDetailes)
    {
        List<Mail> UsersNewMails = OneUserDetailes.NewMails;
        for (int i = 0; i < UsersNewMails.size(); i++)
        {
            Mail NewMail = UsersNewMails.get(i);
            System.out.println("New Mail Number --> " + (i+1));
            System.out.println("From ID Address --> " + NewMail.From);
            System.out.println("Subject --> " + NewMail.Subject);
        }
        System.out.print("Enter New Mail Number to open a Content of Mail ::  ");
        Scanner scanner = new Scanner(System.in);
        int SearchMailNumber = scanner.nextInt();
        Mail ShowMail = UsersNewMails.get(SearchMailNumber-1);
        System.out.println("From -> " + ShowMail.From);
        System.out.println("Subject -> " + ShowMail.Subject);
        System.out.println("Content -> " + ShowMail.Content);

        OneUserDetailes.Inbox.add(ShowMail);
        OneUserDetailes.NewMails.remove(ShowMail);

        return;
    }
    static void OpenInbox(User OneUserDetailes)
    {
        List<Mail> UsersInbox = OneUserDetailes.Inbox;
        for (int i = 0; i < UsersInbox.size(); i++)
        {
            Mail NewMail = UsersInbox.get(i);
            System.out.println("Mail Number --> " + (i+1));
            System.out.println("From ID Address --> " + NewMail.From);
            System.out.println("Subject --> " + NewMail.Subject);
        }
        System.out.println("Enter Mail Number to open a Content of Mail ::  ");
        Scanner scanner = new Scanner(System.in);
        int SearchMailNumber = scanner.nextInt();
        Mail ShowMail = UsersInbox.get(SearchMailNumber - 1);
        System.out.println("From -> " + ShowMail.From);
        System.out.println("Subject -> " + ShowMail.Subject);
        System.out.println("Content -> " + ShowMail.Content);

        return;
    }
    static void OpenOutbox(User OneUserDetailes)
    {
        List<Mail> UsersInbox = OneUserDetailes.Outbox;
        for (int i = 0; i < UsersInbox.size(); i++) {
            Mail NewMail = UsersInbox.get(i);
            System.out.println("Mail Number --> " + (i+1));
            System.out.println("From ID Address --> " + NewMail.From);
            System.out.println("Subject --> " + NewMail.Subject);
        }
        System.out.println("Enter Mail Number to open a Content of Mail ::  ");
        Scanner scanner = new Scanner(System.in);
        int SearchMailNumber = scanner.nextInt();
        Mail ShowMail = UsersInbox.get(SearchMailNumber-1);
        System.out.println("From -> " + ShowMail.From);
        System.out.println("Subject -> " + ShowMail.Subject);
        System.out.println("Content -> " + ShowMail.Content);

        return;
    }
    static void CreateNewMail(User Sender, Context context)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("To ::  ");
        String ToID = scanner.next();
        boolean CheckToID = context.UserMaps.containsKey(ToID);
        if (CheckToID == false)
        {
            System.out.println("Invalied Id Name... Retry It");
            CreateNewMail(Sender, context);
        }
        System.out.print("Enter Subject (or) Key Word ( in Short ) ::  ");
        String Subject = scanner.next();
        User Receiver = context.UserMaps.get(ToID);
        System.out.print("Enter Content ::  ");
        String Content = scanner.next();
        Mail NewMailTo = new Mail();
        NewMailTo.From = Sender.Id;
        NewMailTo.To = Receiver.Id;
        NewMailTo.Subject = Subject;
        NewMailTo.Content = Content;

        Receiver.NewMails.add(NewMailTo);
        Sender.Outbox.add(NewMailTo);

        return;
    }
}
