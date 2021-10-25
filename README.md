Java application that models a bank and credit card system. 

This program generates a random sixteen digit credit card number based upon the Luhn algorithm and generates
a random four digit pin to correspond to the credit card. This information is stored using sqlite within
the program's folder and is used to validate the user's credentials when they log in.

TODO
[x]Edit menu
[]Refactor the method that returns and prints balance
[x]Add income item should allow us to deposit money to the account.
[]Do transfer item should allow transferring money to another account. You should handle the following errors:
    []Console messages: 
    [x]If the user tries to transfer more money than he/she has, output: Not enough money!
    [x]If the user tries to transfer money to the same account, output the following message: You can't transfer money to the same account!
    [x]If the receiver's card number does not pass the Luhn algorithm, you should output: Probably you made a mistake in the card number. Please try again!
    [x]If the receiver's card number does not exist, you should output: Such a card does not exist.
    [x]If there is no error, ask the user how much money they want to transfer and make the transaction.

[]If the user chooses the Close an account item, you should delete that account from the database.