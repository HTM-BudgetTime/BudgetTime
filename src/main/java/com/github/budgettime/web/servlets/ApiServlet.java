package com.github.budgettime.web.servlets;

import com.github.budgettime.TestUtil;
import com.github.budgettime.database.Database;
import com.github.budgettime.database.DbQueries;
import com.github.budgettime.model.Budget;
import com.github.budgettime.model.BudgetEntry;
import com.github.budgettime.model.BudgetPeriod;
import com.github.budgettime.model.User;
import com.github.budgettime.web.ServletUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ApiServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void init() {
    }


    private void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(ServletUtil.CONTENT_TYPE_JSON);

        HttpSession       session    = request.getSession();
        RequestDispatcher dispatcher = null;

        final String      action = request.getPathInfo();
        final PrintWriter writer = response.getWriter();

        System.out.println("action =  " + action);

        if (action.equals("/users")) {
            User user1 = new User("aaa-aaa-aaa", "Steve", "Smith");
            User user2 = new User("bbb-bbb-bbb", "Jenny", "Helen");

            List<User> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);

            JSONArray data = new JSONArray();
            users.forEach(user -> {
                data.put(user.toJsonObject());
            });

            JSONObject output = new JSONObject();
            output.put("users", data);

            writer.print(output.toString(2));

        } else if (action.equals("/budgetContent")) {
            int      budgetId = 1;
            Database database = new Database();
            try (Connection connection = database.getConnection()) {
                DbQueries               dbQueries = new DbQueries(connection);
                final List<BudgetEntry> entries   = dbQueries.getBudgetEntriesForBudgetId(budgetId);

                Budget budget = new Budget(budgetId);
                entries.forEach(budget::addEntry);

                writer.println(budget.toJsonArray());

            } catch (SQLException e) {
                writer.println("ERROR! <br/><br/>\n\n");
                writer.println(e.getMessage());
            }

        } else if (action.equals("/budgetData")) {
            String username       = request.getParameter("username");
            String budgetId       = request.getParameter("budget_id");
            String start_date     = request.getParameter("start_date");
            String duration_weeks = request.getParameter("duration_weeks");


            BudgetPeriod budgetPeriod = new BudgetPeriod(
                    LocalDate.now(),
                    LocalDate.now().minus(Period.ofWeeks(2))
            );

            for (int i = 0; i < 8; i++) {
                BudgetEntry budgetEntry = TestUtil.randomBudgetEntry();
                budgetPeriod.addEntry(budgetEntry);
            }

            JSONObject meta = new JSONObject();
            meta.put("count", budgetPeriod.length());

            JSONObject output = new JSONObject();
            output.put("data", budgetPeriod.toJsonArray());
            output.put("meta", meta);

            writer.print(output.toString(2));

        } else {
            response.setContentType(ServletUtil.CONTENT_TYPE_HTML);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
