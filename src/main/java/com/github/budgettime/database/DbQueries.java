package com.github.budgettime.database;

import com.github.budgettime.model.Budget;
import com.github.budgettime.model.BudgetEntry;
import com.github.budgettime.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbQueries {

    private Connection connection;

    public DbQueries(Connection connection) {
        this.connection = connection;
    }

    public void addBudgetEntry(final String category, final String description, final String hours_budgeted) throws SQLException {
        final PreparedStatement ps = connection.prepareStatement("INSERT INTO budget_entries (category, description, duration) VALUES ((?),(?),(?))");
        ps.setString(1, category);
        ps.setString(2, description);
        if(hours_budgeted == null) {
            ps.setInt(3, 0);
        } else {
            ps.setInt(3, Integer.parseInt(hours_budgeted, 10));
        }
        ps.executeUpdate();
    }

    public List<BudgetEntry> getBudgetEntriesForBudgetId(final int budgetId) throws SQLException {
        final PreparedStatement ps = connection
                .prepareStatement(
                        "SELECT parent_budget_id, budget_entry_id, category, description, duration\n" +
                        "FROM budget_entries\n" +
                        "\n" +
                        "WHERE budget_entries.parent_budget_id = (?) \n" +
                        "");
        ps.setInt(1, budgetId);

        List<BudgetEntry> budgetEntries = new ArrayList<>();

        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int    dbBudgetId      = rs.getInt("parent_budget_id");
            int    dbBudgetEntryId = rs.getInt("budget_entry_id");
            String category        = rs.getString("category");
            String description     = rs.getString("description");
            float  duration        = rs.getFloat("duration");

            BudgetEntry budgetEntry = new BudgetEntry(category, description, duration);
            budgetEntries.add(budgetEntry);
        }

        return budgetEntries;
    }

    public List<Budget> getBudgetsForUser(final String username) throws SQLException {
        final PreparedStatement ps = connection
                .prepareStatement(
                        "SELECT\n" +
                        "    accounts.username as username,\n" +
                        "    budgets.owner_username, budgets.budget_id as budget_id\n" +
                        "FROM accounts\n" +
                        "RIGHT JOIN budgets ON budgets.owner_username=accounts.username\n" +
                        "\n" +
                        "WHERE accounts.username = (?) \n" +
                        "");
        ps.setString(1, username);

        List<Budget> budgets = new ArrayList<>();

        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String dbUsername = rs.getString("username");
            int    dbBudgetId = rs.getInt("budget_id");
            Budget budget     = new Budget(dbBudgetId);
            budgets.add(budget);
        }

        return budgets;
    }

    public Optional<User> getUserFromUsername(final String username) throws SQLException {
        final PreparedStatement ps = connection.prepareStatement("SELECT username, personal_name, family_name FROM accounts WHERE username=(?) LIMIT 1");
        ps.setString(1, username);

        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String dbUsername     = rs.getString("username");
            String dbPersonalName = rs.getString("personal_name");
            String dbFamilyName   = rs.getString("family_name");

            return Optional.of(new User(dbUsername, dbPersonalName, dbFamilyName));
        }

        return Optional.empty();
    }
    // entries.push({entry_id: 2, category: "Fun", description: "Cinema", hours_budgeted: 3, hours_logged: 0});

    public boolean isUserInDb(final String username, final String password) throws SQLException {
        final PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) as count FROM accounts WHERE username=(?) AND password=(?) LIMIT 1");
        ps.setString(1, username);
        ps.setString(2, password);

        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int count = rs.getInt("count");
            if (count == 1) {
                return true;
            }
        }

        return false;
    }


}
