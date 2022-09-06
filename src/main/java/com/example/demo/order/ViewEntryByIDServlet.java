package com.example.demo.order;

import com.example.demo.coffee.CoffeeRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/viewEntryByIDServlet")
public class ViewEntryByIDServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sentryId = request.getParameter("entryid");
        int entryId = Integer.parseInt(sentryId);

        Order order = null;
        try {
            order = OrderRepository.getEntryById(entryId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            out.print(order);
            out.close();
        }
    }
}