package com.example.demo.order;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/viewOrderServlet")
public class ViewOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Order> list;
        try {
            list = OrderRepository.getAllOrders();
            for (Order order : list) {
                out.print(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}

