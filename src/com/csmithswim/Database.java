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

    protected static boolean queryAndValidateCard(String transferAccount) {
        String number = "";

        SQLiteDataSource dataSource = new SQLiteDataSource();

        dataSource.setUrl(url);

        String sql = "SELECT number FROM card "
                + "WHERE number = ?";

        try (Connection conn = DriverManager.getConnection(url)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement (sql)) {
                preparedStatement.setString(1, transferAccount);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    number = rs.getString("number");
                }


            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (number.isEmpty()) {
            System.out.println("Such a card does not exist.\n");
            return false;
        } else {
            return true;
        }
    }

    protected static boolean queryAndValidateLogin(String userCard, String userPin) {
        String number = "";
        String pin = "";

        StringBuilder message = new StringBuilder();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        String sql = "SELECT number, pin "
               + "FROM card WHERE number = ? AND pin = ?";

        try (Connection conn = DriverManager.getConnection(url)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setString(1, userCard);
                preparedStatement.setString(2, userPin);

                ResultSet cardInfo = preparedStatement.executeQuery();{
                    while (cardInfo.next()) {
                        number  = cardInfo.getString("number");
                        pin     = cardInfo.getString("pin");
                        System.out.println(number);
                        System.out.println(pin);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userCard.equals(number) && userPin.equals(pin)) {
            System.out.println(message.append("\nYou have successfully logged in!\n"));
            return true;
        } else {
            System.out.println(message.replace(0, message.length(), "\nWrong card number or PIN!\n"));
            return false;
        }
    }

    protected static String[] queryAndStoreTable(String userCard, String userPin) {
        String[] userAccountDetails = new String[4];
        String number = "";
        String pin = "";
        int balance;

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
        return userAccountDetails;
    }

    protected static void depositFunds(String number, int amountToTransfer) {
        SQLiteDataSource dataSource = new SQLiteDataSource();

        dataSource.setUrl(url);

         String sql = "UPDATE card SET balance = ? + balance "
                    + "WHERE number = ?";

        try (Connection conn = DriverManager.getConnection(url)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setInt(1, amountToTransfer);
                preparedStatement.setString(2, number);
                preparedStatement.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        System.out.println("Success!\n");
    }

    protected static void deductFunds(String number, int amountToDeduct) {
        SQLiteDataSource dataSource = new SQLiteDataSource();

        dataSource.setUrl(url);

        String sql = "UPDATE card SET balance = balance - ? "
                + "WHERE number = ?";

        try (Connection conn = DriverManager.getConnection(url)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setInt(1, amountToDeduct);
                preparedStatement.setString(2, number);
                preparedStatement.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected static int displayBalance(String accountNumber) {
        int balance = 0;

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        String sql = "SELECT balance FROM card "
                + "WHERE number = ?";

        try (Connection conn = DriverManager.getConnection(url)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement (sql)) {
                preparedStatement.setString(1, accountNumber);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    balance = rs.getInt("balance");
                }


            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balance;
    }
}
