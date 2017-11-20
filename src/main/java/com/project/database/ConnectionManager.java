package com.project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/chat?useSSL=false";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/chat?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() throws SQLException {
        Connection result = null;
        System.out.println("Connecting to database...");
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        result = DriverManager.getConnection(DB_URL, USER, PASS);
        return result;
    }
}
