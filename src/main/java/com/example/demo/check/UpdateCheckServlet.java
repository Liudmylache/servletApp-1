package com.example.demo.check;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/updateCheckServlet")
public class UpdateCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String scheckId = request.getParameter("checkid");
        int checkId = Integer.parseInt(scheckId);

        String guestName = request.getParameter("guestname");

        String stotal = request.getParameter("total");
        double total = Double.parseDouble(stotal);


        Check check = new Check();
        check.setCheckId(checkId);
        check.setGuestName(guestName);
        check.setTotal(total);

        int status;
        try {
            status = CheckRepository.updateById(check);
            if (status > 0) {
                response.sendRedirect("viewCheckServlet");
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

