package com.csmithswim;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Program {

    protected boolean program = true;
    protected long[] account = new long[2];

    Program(boolean program) {
        this.program = program;
    }

    protected void run() {
        while (program) {
            StringBuilder message = new StringBuilder("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            Scanner scanner = new Scanner(System.in);
            System.out.println(message);
           int userInput = scanner.nextInt();
           if (userInput == 0) {
               message.replace(0, message.length(), "\nBye!");
               System.out.println(message);
                break;
           } else if (userInput == 1) {
               createCreditCardAccount();
               continue;
           } else if (userInput == 2) {
               System.out.println("\nEnter your card number:");
               long cardNumber = scanner.nextLong();
               System.out.println("Enter your pin:");
               int cardPin = scanner.nextInt();

               if (checkLoginCredentials(new long[]{cardNumber, cardPin})) {
                   while (true) {
                       System.out.println("1. Balance\n" +
                               "2. Log out\n" +
                               "0. Exit");
                       int input = scanner.nextInt();
                       if (input == 1) {
                           System.out.println("Balance: 0");
                           continue;
                       } else if (input == 2) {
                           break;
                       } else if (input == 0) {
                           program = false;
                           System.out.println("\nBye!");
                           break;
                       }
                   }
               }
           }
        }
    }

    protected boolean checkLoginCredentials(long[] userAccount) {
        if (account[0] == userAccount[0] && account[1] == userAccount[1]) {
            System.out.println("\nYou have succesfully logged in!\n");
            return true;
        } else {
            System.out.println("Wrong card number or PIN!");
            return false;
        }
    }

    protected void createCreditCardAccount() {
        account[0] = Long.parseLong(createCreditCardNumber());
        account[1] = Integer.parseInt(createCreditCardPin());
    }

    //Number begins with 4
    //BIN(Banking Identification Number) must be 400_000
    //7th digit to the second to last digit is the customer account number.
    //Whole number is 16 digits
    protected String createCreditCardNumber() {
        //set CC #
        //set pin #
        //pair them together?

        //generate random number from 0 to 9, add it to 400000
        Random random = new Random();
        StringBuilder randomCreditCardNumber = new StringBuilder("400000");

        for (int i = 0; i < 10; i++) {
            randomCreditCardNumber.append(random.nextInt(9) + 1);
        }
        System.out.println("\nYour card has been created\n" +
                "Your card number:");
        System.out.println(randomCreditCardNumber);
        System.out.println(randomCreditCardNumber.length());
        return randomCreditCardNumber.toString();
    }

    protected String createCreditCardPin() {
        Random random = new Random();
        StringBuilder randomCreditCardPin = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            randomCreditCardPin.append(random.nextInt(10));
        }
        System.out.println("Your card PIN:");
        System.out.println(randomCreditCardPin + "\n");
        return randomCreditCardPin.toString();
    }

//    protected void setCreditCardInformation(int creditCardNumber)


}
