package com.github.budgettime.web.servlets;

import com.github.budgettime.web.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
        processRequest(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        processRequest(request, response);
    }

    @Override
    public void init() {
        System.out.println("init");
        System.out.println("TESTING");
    }


    private void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("processRequest");
        response.setContentType(ServletUtil.CONTENT_TYPE_HTML);

        RequestDispatcher dispatcher = null;

        final String      action = request.getPathInfo();
        final PrintWriter writer = response.getWriter();

        System.out.println("action = " + action);

        if (action.equals("/index")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/index.html");
        } else if (action.equals("/register")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/register.html");
        } else if (action.equals("/mainData")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/mainData.html");
        } else if (action.equals("/login")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/login.html");
        } else if (action.equals("/doLogon")) {
            String username = request.getParameter("username");
            String password = request.getParameter("Password");

            writer.print("Username = " + username + "<br/>\n");
            writer.print("Password = " + password + "<br/>\n");

        } else if (action.equals("/doUpdateEntries")) {
            writer.print("Success!");

        } else if (action.equals("/userChoice")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/userChoice.html");
        } else {
            response.setContentType(ServletUtil.CONTENT_TYPE_HTML);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
