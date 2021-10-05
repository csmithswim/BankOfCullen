package com.csmithswim;
import java.util.Scanner;

public class Program {

    protected boolean program = true;

    Program(boolean program) {
        this.program = program;
    }

    protected void run() {
        while (program) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
           int userInput = scanner.nextInt();
           if (userInput == 0) {
                break;
            }
        }


//    protected void getInput() {
//
//    }
    }
}
