package com.example.demo.order;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/addOrderServlet")
public class AddOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();


        String scheckId = request.getParameter("checkid");
        int checkId = Integer.parseInt(scheckId);

        String scoffeeId = request.getParameter("coffeeid");
        int coffeeId = Integer.parseInt(scoffeeId);

        String squantity = request.getParameter("quantity");
        int quantity = Integer.parseInt(squantity);

//        String sitemPrice = request.getParameter("itemprice");
//        double itemPrice = Double.parseDouble(sitemPrice);

//        String sentryTotal = request.getParameter("entrytotal");
//        double entryTotal = Double.parseDouble(sentryTotal);


        Order order = new Order();

        order.setCheckId(checkId);
        order.setCoffeeId(coffeeId);
        order.setQuantity(quantity);
//        order.setItemPrice(itemPrice);
//        order.setEntryTotal(entryTotal);

        int status;
        try {
            status = OrderRepository.addEntry(order);
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
    }
}
