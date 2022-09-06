package com.example.demo.coffee;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/viewCoffeeByIDServlet")
public class ViewCoffeeByIDServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        Coffee coffee = null;
        try {
            coffee = CoffeeRepository.getCoffeeById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            out.print(coffee);
            out.close();
        }
    }
}
