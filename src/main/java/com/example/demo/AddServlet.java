package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();


        String guestName = request.getParameter("guestname");

        String squantity = request.getParameter("quantity");
        int quantity = Integer.parseInt(squantity);

        String scoffeeId = request.getParameter("coffeeid");
        int coffeeId = Integer.parseInt(scoffeeId);

        String stotal = request.getParameter("total");
        double total = Double.parseDouble(stotal);


        Check check = new Check();

        check.setGuestName(guestName);
        check.setQuantity(quantity);
        check.setCoffeeId(coffeeId);
        check.setTotal(total);

        //out.println(employee.toString());
        //out.println(EmployeeRepository.getConnection());

        int status;
        try {
            status = CoffeeRepository.add(check);
            if (status > 0) {
                out.print("Record added successfully!");
            } else {
                out.println("Sorry! unable to add record");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
        //out.println(status);
    }
}
