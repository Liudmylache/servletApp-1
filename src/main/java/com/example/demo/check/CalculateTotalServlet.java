package com.example.demo.check;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/calculateTotalServlet")
public class CalculateTotalServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String scheckId = request.getParameter("checkid");
        int checkId = Integer.parseInt(scheckId);


        Check check = new Check();
        check.setCheckId(checkId);

        int status;
        try {
            status = CheckRepository.calculateTotal(checkId);
//            if (status > 0) {
                response.sendRedirect("viewCheckServlet");
//            } else {
                out.println("Sorry! unable to update record");
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
