package com.ebookshop;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection initializeDatabase() throws Exception {
        String dbURL = "jdbc:mysql://localhost:3306/ebookshop";
        String dbUser = "root";
        String dbPass = "raj@1415"; // Replace

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbURL, dbUser, dbPass);
    }
}