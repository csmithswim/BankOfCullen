package com.csmithswim;

public class Main {


    public static void main(String[] args) {

        //command line argument -> -fileName bank.db

        /*  CARD TABLE COLUMNS
            id INTEGER
            number TEXT
            pin TEXT
            balance INTEGER DEFAULT 0 */

        int id = 1;
        String number = "123456789";
        String pin = "1234";
        int balance = 0;

        if (args[0].equals("-fileName")) {
            Database.createBankDatabase(args[1]);
            Program program = new Program(true);
            program.run();

        }
    }




}


