package com.csmithswim;

import org.sqlite.SQLiteDataSource;
import java.sql.*;

public class Database {
    static String url = "";

    protected static void createBankDatabase(String name) {
        url = "jdbc:sqlite:" + name;

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url)) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card (\n" +
                        "id INTEGER," +
                        "number TEXT," +
                        "pin TEXT, " +
                        "balance INTEGER DEFAULT 0)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void insertTableValues(int id, String creditCardNumber, String pin, int balance) {

        StringBuilder message = new StringBuilder();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url)) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("INSERT INTO card " +
                        "VALUES (" + id + "," + creditCardNumber + "," + pin + "," + balance + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        message.append("\nYour card has been created\nYour card number:\n")
                .append(creditCardNumber).append("\nYour card PIN:\n").append(pin).append("\n");
        System.out.println(message);
    }

    protected static String[] queryAndDisplayTable(String userCard, String userPin) {
        String[] userAccountDetails = new String[4];
        String number = "";
        String pin = "";
        int balance;

        StringBuilder message = new StringBuilder();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url)) {
            try (Statement statement = conn.createStatement()) {

                ResultSet cardInfo = statement.executeQuery("SELECT * FROM CARD");{
                    while (cardInfo.next()) {
                        int id      = cardInfo.getInt("id");
                        number  = cardInfo.getString("number");
                        pin     = cardInfo.getString("pin");
                        balance = cardInfo.getInt("balance");

                        userAccountDetails = new String[]{String.valueOf(id), number, pin, String.valueOf(balance)};
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userCard.equals(number) && userPin.equals(pin)) {
            message.append("\nYou have successfully logged in!\n");
        } else {
            message.replace(0, message.length(), "\nWrong card number or PIN!\n");
        }
        System.out.println(message);
        return userAccountDetails;
    }

    protected static void addIncome(int id, int amountToTransfer) {
        SQLiteDataSource dataSource = new SQLiteDataSource();

        dataSource.setUrl(url);

        String sql = "UPDATE card SET balance = ? + balance "
                + "WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setInt(1, amountToTransfer);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }

    }

    //String sendingAccountId, String receivingAccountId, int amount
    protected static void transfer(String senderId, String receiverId,
                                   int transferAmount, int balance) {
        //validation with console messages
        StringBuilder console = new StringBuilder();






    }

}
