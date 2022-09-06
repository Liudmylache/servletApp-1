package com.example.demo.order;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/viewEntriesByCheckServlet")
public class ViewEntriesByCheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String scheckId = request.getParameter("checkid");
        int checkId = Integer.parseInt(scheckId);

        List<Order> list;
        try {
            list = OrderRepository.getEntriesByCheckId(checkId);
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
