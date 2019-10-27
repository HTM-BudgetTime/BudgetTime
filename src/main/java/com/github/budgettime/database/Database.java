package com.github.budgettime.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public Database() {}

    public Connection getConnection() throws SQLException {
        String dbUri      = System.getenv("BT_DB_URI");
        String dbUsername = System.getenv("BT_DB_USERNAME");
        String dbPassword = System.getenv("BT_DB_PASSWORD");

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection(dbUri, dbUsername, dbPassword);
        return con;
    }
}
