package com.example.demo.coffee;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        String name = request.getParameter("name");

        String sprice = request.getParameter("price");
        double price = Double.parseDouble(sprice);

        String ssugar = request.getParameter("sugar");
        boolean sugar = Boolean.parseBoolean(ssugar);

        String smilk = request.getParameter("milk");
        boolean milk = Boolean.parseBoolean(smilk);

        String scoffeeBeans = request.getParameter("coffeebeans");
        int coffeeBeans = Integer.parseInt(scoffeeBeans);

        Coffee coffee = new Coffee();
        coffee.setId(id);
        coffee.setName(name);
        coffee.setPrice(price);
        coffee.setSugar(sugar);
        coffee.setMilk(milk);
        coffee.setCoffeeBeans(coffeeBeans);

        int status;
        try {
            status = CoffeeRepository.update(coffee);
            if (status > 0) {
                response.sendRedirect("viewCoffeeServlet");
            } else {
                out.println("Sorry! unable to update record");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
