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

Above illustrates the specific properties a number must have for it to be considered a valid credit card number. A number's check digit is calculated to validate the credit 
card number. 
More information can be found here: https://en.wikipedia.org/wiki/Luhn_algorithm

**My Implementation of The Luhn Algorithm**

code()
Markup : `code()`
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


