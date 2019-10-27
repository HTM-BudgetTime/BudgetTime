package com.github.budgettime.web.servlets;

import com.github.budgettime.database.Database;
import com.github.budgettime.database.DbQueries;
import com.github.budgettime.model.User;
import com.github.budgettime.web.ServletUtil;

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

public class MainServlet extends HttpServlet {

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
        response.setContentType(ServletUtil.CONTENT_TYPE_HTML);

        HttpSession       session    = request.getSession();
        RequestDispatcher dispatcher = null;

        final String      action = request.getPathInfo();
        final PrintWriter writer = response.getWriter();

        System.out.println("action = " + action);

        if (action.equals("/index")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/index.jsp");
        } else if (action.equals("/register")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/register.jsp");
        } else if (action.equals("/mainData")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/mainData.jsp");
        } else if (action.equals("/login")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/login.jsp");
        } else if (action.equals("/browseBudget")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/browseBudget.jsp");
        } else if (action.equals("/doLogout")) {
            session.invalidate();
            dispatcher = this.getServletContext().getRequestDispatcher("/html/loggedOut.jsp");
        } else if (action.equals("/doLogon")) {
            String username = request.getParameter("username");
            String password = request.getParameter("Password");
            try {
                Database   database   = new Database();
                Connection connection = database.getConnection();

                DbQueries dbQueries          = new DbQueries(connection);
                boolean   isValidCredentials = dbQueries.isUserInDb(username, password);

                if (isValidCredentials) {
                    session.setAttribute("isLoggedIn", true);

                    User user = dbQueries.getUserFromUsername(username).get();
                    session.setAttribute("username", user.getUsername());
                    session.setAttribute("family_name", user.getFamilyName());
                    session.setAttribute("personal_name", user.getPersonalName());

                    response.sendRedirect(request.getContextPath() + "/main/index");
                } else {
                    // Throw away the session if not logged in
                    // TODO: Better to track e.g. number of incorrect attempts?
//                    session = request.getSession();
                    session.invalidate();
                    response.sendRedirect(request.getContextPath() + "/main/login");
                }


            } catch (SQLException e) {
                writer.println("Database error -- try again later!");
                writer.println("<br>");
                writer.println("<br>");
                writer.println(e.getMessage());
                e.printStackTrace();
            }


        } else if (action.equals("/doUpdateEntry")) {

            String entryId       = request.getParameter("entry_id");
            String category      = request.getParameter("category");
            String description   = request.getParameter("description");
            String hoursBudgeted = request.getParameter("hours_budgeted");
            String hoursLogged   = request.getParameter("hours_logged");

            System.out.println(request.getParameterMap());

            try {
                Database   database   = new Database();
                Connection connection = database.getConnection();

                DbQueries dbQueries = new DbQueries(connection);
                dbQueries.addBudgetEntry(category, description, hoursBudgeted);

            } catch (SQLException e) {
                writer.println("Database error -- try again later!");
                writer.println("<br>");
                writer.println("<br>");
                writer.println(e.getMessage());
                e.printStackTrace();
            }


        } else if (action.equals("/doUpdateEntries")) {
            writer.print("Success!");

        } else if (action.equals("/userInput")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/userInput.jsp");
        } else if (action.equals("/userChoice")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/userChoice.jsp");
        } else if (action.equals("/userOutput")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/userOutput.jsp");
        } else {
            response.setContentType(ServletUtil.CONTENT_TYPE_HTML);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
