package com.csmithswim;
import java.util.Random;
import java.util.Scanner;

public class Program {

    protected boolean program = true;
    protected long[] account = new long[2];
    static int id = 1;


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
                String cardNumber = scanner.next();
                scanner.nextLine();
                System.out.println("Enter your pin:");
                String cardPin = scanner.nextLine();

                if (Database.queryAndValidateLogin(cardNumber, cardPin)) {
                    while (true) {

                        System.out.println("1. Balance\n" +
                                "2. Add income\n" +
                                "3. Do transfer\n" +
                                "4. Close account\n" +
                                "5. Log out\n" +
                                "0. Exit");
                        int input = scanner.nextInt();
                        if (input == 1) {
                            System.out.println("\nBalance: " + Database.displayBalance(cardNumber) + "\n");
                            continue;
                        } else if (input == 2) {
                            System.out.println("Enter income:");
                            int transferAmount = scanner.nextInt();
                            Database.depositFunds(cardNumber, transferAmount);
                            System.out.println("Income was added!");
                            continue;
                        } else if (input == 0) {
                            program = false;
                            System.out.println("\nBye!");
                            break;
                        } else if (input == 3) {
                            System.out.println("Transfer\nEnter card number:");
                            scanner.nextLine();
                            String userAccountInput = scanner.nextLine();

                            if (luhnAlgorithmValidator(userAccountInput) == false) {
                                continue;
                            }
                            if (Database.queryAndValidateCard(userAccountInput) == false) {
                                continue;
                            }
                            if (userAccountInput.equals(cardNumber)) {
                                System.out.println("You can't transfer money to your own account!");
                                continue;
                            }
                            System.out.println("Enter how much money you want to transfer:");
                            int amount = scanner.nextInt();
                            if (amount > Database.displayBalance(cardNumber)) {
                                System.out.println("Not enough money!");
                            } else {
                                Database.depositFunds(userAccountInput,amount);
                                Database.deductFunds(cardNumber, amount);
                                continue;
                            }
                        } else if (input == 4) {
                            Database.deleteAccount(cardNumber);
                            break;
                        } else if (input == 5) {
                            System.out.println("\nYou have successfully logged out!\n");
                            break;
                        }
                    }
                }
            }
        }
    }

    protected boolean luhnAlgorithmValidator(String input) {
        String[] inputArray = input.split("");
        int sum = 0; 
        
        for (int i = 0; i < inputArray.length - 1; i++) {
            int x = Integer.parseInt(inputArray[i]);
                if (i % 2 == 0) {
                    x *= 2;
                }
                if (x > 9) {
                    x -= 9;
                }

            sum += x;
        }

        if ((sum + Integer.parseInt(inputArray[inputArray.length - 1])) % 10 != 0) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return false;
        } else {
            return true;
        }
    }


    protected void createCreditCardAccount() {
        String creditCardAccount = createCreditCardNumber();
        String pin = createCreditCardPin();
        int balance = 0;

        Database.insertTableValues(id, creditCardAccount, pin, balance);
        id++;
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

        return randomCreditCardNumberString;
    }

    protected String createCreditCardPin() {
        Random random = new Random();
        StringBuilder randomCreditCardPin = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            randomCreditCardPin.append(random.nextInt(10));
        }
        return randomCreditCardPin.toString();
    }
}