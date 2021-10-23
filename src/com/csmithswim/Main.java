package com.csmithswim;

public class Main {

    public static void main(String[] args) {

            Program program = new Program(true);

        System.out.println(program.createCreditCardNumber());

        int i = 0;

        while (i < 3) {
            program.luhnAlgorithmValidator(program.createCreditCardNumber());
            i++;
        }

//        if (args[0].equals("-fileName")) {
//            Database.createBankDatabase(args[1]);
//            Program program = new Program(true);
//            program.run();
//
//        }
    }
}



