package com.csmithswim;

import org.sqlite.SQLiteDataSource;
import java.sql.*;

/*  CARD TABLE COLUMNS
    id INTEGER
    number TEXT
    pin TEXT
    balance INTEGER DEFAULT 0 */

public class Database {

    protected static void createBankDatabase(String name) {
        String url = "jdbc:sqlite:" + name;

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url)) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS CARD(" +
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

        String url = "jdbc:sqlite:bank.db";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url)) {
            try (Statement statement = conn.createStatement()) {

                int i = statement.executeUpdate("UPDATE CARD " +
                        "SET id = " + id +
                        ",number = " + creditCardNumber +
                        ",pin = " + pin +
                        ",balance = " + balance);

//                    System.out.println(i);

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

    protected static void queryAndDisplayTable() {

        String url = "jdbc:sqlite:bank.db";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url)) {
            try (Statement statement = conn.createStatement()) {

                ResultSet cardInfo = statement.executeQuery("SELECT * FROM CARD");{
                    while (cardInfo.next()) {
                        int    id      = cardInfo.getInt("id");
                        String number  = cardInfo.getString("number");
                        String pin     = cardInfo.getString("pin");
                        int    balance = cardInfo.getInt("balance");


                        /*
                        Your card has been created
                        Your card number:
                        4000003429795087
                        Your card PIN:
                        6826
                         */

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


/*

                int i = statement.executeUpdate("INSERT INTO CARD VALUES " +
                        "(1, '123456789', '1234', 1000)");
 */




/*
    public static void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:" + fileName;

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url)) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS CARD(" +
                        "id INTEGER," +
                        "number TEXT," +
                        "pin TEXT, " +
                        "balance INTEGER DEFAULT 0)");
                int i = statement.executeUpdate("INSERT INTO CARD VALUES " +
                        "(1, '123456789', '1234', 1000)");

                ResultSet cardInfo = statement.executeQuery("SELECT * FROM CARD");{
                    while (cardInfo.next()) {
                        int    id      = cardInfo.getInt("id");
                        String number  = cardInfo.getString("number");
                        String pin     = cardInfo.getString("pin");
                        int    balance = cardInfo.getInt("balance");

                        System.out.println(number);

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 */