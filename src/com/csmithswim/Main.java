package com.csmithswim;

public class Main {

    public static void main(String[] args) {
        if (args[0].equals("-fileName")) {
            Database.createBankDatabase(args[1]);
            Program program = new Program(true);
            program.run();
        }
    }

}



