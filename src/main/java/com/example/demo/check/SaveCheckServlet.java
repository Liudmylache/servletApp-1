package com.example.demo.check;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/saveCheckServlet")
public class SaveCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String guestName = request.getParameter("guestname");

//        String stotal = request.getParameter("total");
//        double total = Double.parseDouble(stotal);


        Check check = new Check();
        check.setGuestName(guestName);
//        check.setTotal(total);


        int status;
        try {
            status = CheckRepository.saveCheck(check);
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
    }
}

