## The Bank Of Cullen ##

A Simple Banking and Credit Card Java Application.

-------------

#### Features

* A unique user can be created and their information such as their credit card number, balance and pin are stored in the application.
* The storage is an embedded SQLite database and all database management is done by JDBC.
* Users can be created, logged out, check their balance, deposit money to their personal accounts, transfer money to other accounts and be deleted. 
* Account authentication, deposits, transfers and user details are validated by checking their details and also validated by using the Luhn algorithm, an algorithm commonly 
  used to 
  validate 
  credit card numbers and social security numbers.

### How The Application Works ###

* Application is managed through a command line interface and is run through command line arguments. 
* Accounts are created using a method that will generate a credit card number that is based upon common principles in the financial industry such as a Major Industry Identifier, 
  Bank Identification Number and validated using a Luhn algorithm.
* A randomly generated pin is generated and associated with each new account. 
* A BANK database with a CARD table is created on the initial run of the application and the database will persist after the application stops. 
* The database is a SQLite database and all SQL commands are facilitated with JDBC. 
* Each entity contains an ID, credit card number, a pin and balance. 
* A created account has the following features:
    * Viewing their balance.
    * Adding income to their account.
    * Transferring money to another account.
    * Closing their account.
    * Logging out.
    * Exiting the application.
* The application has several error handling and validation features such as:
    * Authenticating a user based upon their pin and credit card number.
    * Authenticating a credit card number based upon the Luhn algorithm principles.
    * Transferring an amount larger than an account's balance.
    * Transferring money to the same account.
    * Checking a receiving account's existence. 

-------------

### Closer Glance At The Luhn Algorithm

![luhn_algorithm](https://github.com/csmithswim/BankOfCullen/blob/main/images/Luhn%20Algorithm.png)

Above illustrates the specific properties a number must have for it to be considered a valid credit card number according to the Luhn algorithm. A number's check digit is 
calculated to 
validate the credit 
card number. 
More information can be found here: https://en.wikipedia.org/wiki/Luhn_algorithm

**My Implementation of The Luhn Algorithm**
```
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
    System.out.println("Probably you made a mistake in the card number. Please try  
    again!");
    return false;
  } else {
    return true;
  }
}
```
-------------

### Running The Application

**User Login, Adding Income and Viewing Balance**

![balance_functions](https://github.com/csmithswim/BankOfCullen/blob/main/images/Bank%20Of%20Cullen%20Balance%20Functions.gif)

**Balance Transfer**

![balance_transfer](https://github.com/csmithswim/BankOfCullen/blob/main/images/Bank%20Transfer.gif)

**Error Handling and Closing Account**

![error_handling_and_closing_account](https://github.com/csmithswim/BankOfCullen/blob/main/images/Error%20Handling%20and%20Closing%20Account.gif)

-------------
### Final Thoughts

I made this application to learn SQL and database management within the Java ecosystem. I choose JDBC to learn database management to challenge myself with writing my own 
implementations of SQL's CRUD functions and handling SQL exceptions. I made it a simple credit card application to provide context to real world applications and learn more 
about OOP, security and data structures within the Java landscape. 

This was a rewarding project, and I learned a great deal about the fundamentals of database management and writing SQL commands within a Java application. In the future I would 
like to learn more about how modern day financial institutions validate and check if a credit card or bank account number is valid and using JDBC for other types of data 
management. 

Note: This application was developed to learn about SQL, JDBC and Java. If anyone would like to use it there is a presumption that they know how to install SQLite and how to 
set their SQLite up with the correct drivers and directory. The application runs via command line arguments once SQLite has been set up properly. 