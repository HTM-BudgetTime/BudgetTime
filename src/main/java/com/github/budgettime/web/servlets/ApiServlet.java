package com.github.budgettime.web.servlets;

import com.github.budgettime.model.User;
import com.github.budgettime.web.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
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

        RequestDispatcher dispatcher = null;

        final String      action = request.getPathInfo();
        final PrintWriter writer = response.getWriter();

        System.out.println("action = " + action);

        if (action.equals("/users")) {
            User user1 = new User("Steve", "Smith", "aaa-aaa-aaa");
            User user2 = new User("Jenny", "Helen", "bbb-bbb-bbb");

            List<User> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);

            List<String> usersJsonObject = new ArrayList<>();
            for (User user : users) {
                StringBuilder sb = new StringBuilder();
                sb.append("{\n");
                sb.append("    'userId': '" + user.getUserId() + "',");
                sb.append("    'personalName': '" + user.getPersonalName() + "',");
                sb.append("    'familyName': '" + user.getFamilyName() + "'");
                sb.append("}");

                usersJsonObject.add(sb.toString());
            }

            StringBuilder output = new StringBuilder();
            output.append("{'users': ");
            output.append('[');
            output.append(String.join(",\n", usersJsonObject));
            output.append(']');
            output.append('}');

            writer.print(output.toString());
//            dispatcher = this.getServletContext().getRequestDispatcher("/html/index.html");

        } else {
            response.setContentType(ServletUtil.CONTENT_TYPE_HTML);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
