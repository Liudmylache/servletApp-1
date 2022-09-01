package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCheckServlet")
public class DeleteCheckServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sorderId = request.getParameter("orderid");
        int orderId = Integer.parseInt(sorderId);
        CoffeeRepository.deleteCheck(orderId);
        response.sendRedirect("viewCheckServlet");
    }
}
