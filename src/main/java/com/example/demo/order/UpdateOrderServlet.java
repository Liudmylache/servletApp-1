package com.example.demo.order;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/updateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sentryId = request.getParameter("entryid");
        int entryId = Integer.parseInt(sentryId);

        String scheckId = request.getParameter("checkid");
        int checkId = Integer.parseInt(scheckId);

        String scoffeeId = request.getParameter("coffeeid");
        int coffeeId = Integer.parseInt(scoffeeId);

        String squantity = request.getParameter("quantity");
        int quantity = Integer.parseInt(squantity);

//        String sitemPrice = request.getParameter("itemprice");
//        double itemPrice = Double.parseDouble(sitemPrice);
//
//        String sentryTotal = request.getParameter("entrytotal");
//        double entryTotal = Double.parseDouble(sentryTotal);


        Order order = new Order();
        order.setEntryId(entryId);
        order.setCheckId(checkId);
        order.setCoffeeId(coffeeId);
        order.setQuantity(quantity);
//        order.setItemPrice(itemPrice);
//        order.setEntryTotal(entryTotal);


        int status;
        try {
            status = OrderRepository.updateEntry(order);
            if (status > 0) {
                response.sendRedirect("viewOrderServlet");
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


