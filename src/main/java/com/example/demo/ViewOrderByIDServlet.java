package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/viewOrderByIDServlet")
public class ViewOrderByIDServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sOrderId = request.getParameter("orderid");
        int orderId = Integer.parseInt(sOrderId);

        Check check = null;
        try {
            check = CoffeeRepository.getOrderById(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            out.print(check);
            out.close();
        }
    }
}