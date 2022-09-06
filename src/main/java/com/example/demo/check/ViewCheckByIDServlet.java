package com.example.demo.check;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/viewCheckByIDServlet")
public class ViewCheckByIDServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String scheckId = request.getParameter("checkid");
        int checkId = Integer.parseInt(scheckId);

        Check check = null;
        try {
            check = CheckRepository.getCheckById(checkId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            out.print(check);
            out.close();
        }
    }
}

