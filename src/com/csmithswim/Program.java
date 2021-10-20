package com.csmithswim;
import javax.xml.crypto.Data;
import java.util.Random;
import java.util.Scanner;

public class Program {

    protected boolean program = true;
    protected long[] account = new long[2];

    Program(boolean program) {
        this.program = program;
    }

        /*  CARD TABLE COLUMNS
            id INTEGER
            number TEXT
            pin TEXT
            balance INTEGER DEFAULT 0 */

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
                System.out.println("Enter your card number:");
                String cardNumber = scanner.next();
                scanner.nextLine();
                System.out.println("Enter your pin:");
                String cardPin = scanner.nextLine();

                System.out.println(cardNumber);

                System.out.println(cardPin);

                Database.queryAndDisplayTable(cardNumber, cardPin);
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

//    Query database and check if account number and pin is correct
//    protected boolean checkLoginCredentials(String account, String pin) {
//        Database.queryAndDisplayTable();
//
//        if (account[0] == userAccount[0] && account[1] == userAccount[1]) {
//            System.out.println("\nYou have successfully logged in!\n");
//            return true;
//        } else {
//            System.out.println("Wrong card number or PIN!");
//            return false;
//        }
//    }

    protected void createCreditCardAccount() {
        //make ID & INSERT INTO BANK
        int id = 1;
        String creditCardAccount = createCreditCardNumber();
        String pin = createCreditCardPin();
        int balance = 0;

        Database.insertTableValues(id, creditCardAccount, pin, balance);

//        account[0] = Long.parseLong(createCreditCardNumber());
//        account[1] = Integer.parseInt(createCreditCardPin());

    }

    protected String createCreditCardNumber() {
        Random        random                 = new Random();
        StringBuilder randomCreditCardNumber = new StringBuilder("400000");

        for (int i = 0; i < 9; i++) {
            int randomNumber = random.nextInt(9) + 1;
            randomCreditCardNumber.append(randomNumber);
        }

        String randomCreditCardNumberString = new String(randomCreditCardNumber);
        int sum      = 0;
        int checkSum = 0;
        String[] stringArray = randomCreditCardNumberString.split("");

        for (int i = 0; i < stringArray.length; i++) {
            int x = Integer.parseInt(stringArray[i]);
            if (i % 2 == 0) {
                x *= 2;
            }
            if (x > 9) {
                x -= 9;
            }

            sum += x;

            if (i == stringArray.length - 1) {
                while ((sum + checkSum) % 10 != 0) {
                    checkSum++;
                }
                randomCreditCardNumberString += String.valueOf(checkSum);
            }
        }

//        System.out.println("\nYour card has been created\n" +
//            "Your card number:");
//
//        System.out.println(randomCreditCardNumberString);

        return randomCreditCardNumberString;
    }

    protected String createCreditCardPin() {
        Random random = new Random();
        StringBuilder randomCreditCardPin = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            randomCreditCardPin.append(random.nextInt(10));
        }
//        System.out.println("Your card PIN:");
//        System.out.println(randomCreditCardPin + "\n");

        return randomCreditCardPin.toString();
    }

}