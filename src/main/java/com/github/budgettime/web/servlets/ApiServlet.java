package com.github.budgettime.web.servlets;

import com.github.budgettime.web.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

        RequestDispatcher dispatcher = null;

        final String      action = request.getPathInfo();
        final PrintWriter writer = response.getWriter();

        System.out.println("action = " + action);

        if (action.equals("/users")) {
            dispatcher = this.getServletContext().getRequestDispatcher("/html/index.html");
        } else {
            response.setContentType(ServletUtil.CONTENT_TYPE_HTML);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
