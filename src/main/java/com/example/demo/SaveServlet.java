package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

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

        coffee.setName(name);
        coffee.setPrice(price);
        coffee.setSugar(sugar);
        coffee.setMilk(milk);
        coffee.setCoffeeBeans(coffeeBeans);

        //out.println(employee.toString());
        //out.println(EmployeeRepository.getConnection());

        int status;
        try {
            status = CoffeeRepository.save(coffee);
            if (status > 0) {
                out.print("Record saved successfully!");
            } else {
                out.println("Sorry! unable to save record");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
        //out.println(status);
    }
}
