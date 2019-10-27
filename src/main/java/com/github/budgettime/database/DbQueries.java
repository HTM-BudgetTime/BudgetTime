package com.github.budgettime.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbQueries {

    private Connection connection;

    public DbQueries(Connection connection) {
        this.connection = connection;
    }

    public boolean isUserInDb(final String username, final String password) throws SQLException {
        final PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) as count FROM accounts WHERE username=(?) AND password=(?)");
        ps.setString(1, username);
        ps.setString(2, password);

        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int count = rs.getInt("count");
            if (count == 1) {
                return true;
            }
        }

       return  false;

    }

    public void createUserInDb(final String username, final String password) throws SQLException {
        final PreparedStatement ps = connection.prepareStatement("INSERT INTO accounts (username, password) VALUES (' username', ' password')");

         ps.executeUpdate();
    }
}
