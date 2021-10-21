Java application that models a bank and credit card system. 

This program generates a random sixteen digit credit card number based upon the Luhn algorithm and generates
a random four digit pin to correspond to the credit card. This information is stored using sqlite within
the program's folder and is used to validate the user's credentials when they log in.

TODO
[]If the user asks for Balance, you should read the balance of the account from the database and output it into the console.

[]Add income item should allow us to deposit money to the account.

[]Do transfer item should allow transferring money to another account. You should handle the following errors:

    Console messages: 
    []If the user tries to transfer more money than he/she has, output: Not enough money!
    []If the user tries to transfer money to the same account, output the following message: You can't transfer money to the same account!
    []If the receiver's card number doesn’t pass the Luhn algorithm, you should output: Probably you made a mistake in the card number. Please try again!
    []If the receiver's card number doesn’t exist, you should output: Such a card does not exist.
    []If there is no error, ask the user how much money they want to transfer and make the transaction.

[]If the user chooses the Close an account item, you should delete that account from the database.