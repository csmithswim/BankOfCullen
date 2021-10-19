package com.csmithswim;


import org.sqlite.SQLiteDataSource;

import javax.xml.transform.Result;
import java.sql.*;


public class Main {


    public static void main(String[] args) {
        if (args[0].equals("-fileName")) {
            createNewDatabase(args[1]);

//            Program program = new Program(true);
        }




    }

    /*


    id INTEGER
    number TEXT
    pin TEXT
    balance INTEGER DEFAULT 0

     */

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
}




//    public static void queryDataBase(String fileName) {
//
//        try (createNewDatabase();)
//    }



/*
if (conn != null) {
                        DatabaseMetaData meta = conn.getMetaData();
                        System.out.println("The driver name is " + meta.getDriverName());
                        System.out.println("A new database has been created.");
                    }
 */