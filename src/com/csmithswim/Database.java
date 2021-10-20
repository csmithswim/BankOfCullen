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

    protected static int queryAndDisplayTable(String userCard, String userPin) {
        int id = 1;
        String number = "";
        String pin = "";
        int balance = 0;

        StringBuilder message = new StringBuilder();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url)) {
            try (Statement statement = conn.createStatement()) {

                ResultSet cardInfo = statement.executeQuery("SELECT * FROM CARD");{
                    while (cardInfo.next()) {
                        id      = cardInfo.getInt("id");
                        number  = cardInfo.getString("number");
                        pin     = cardInfo.getString("pin");
                        balance = cardInfo.getInt("balance");

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
        return balance;
    }
}
