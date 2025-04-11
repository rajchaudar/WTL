package com.ebookshop;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class BuyBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.initializeDatabase()) {
            PreparedStatement ps = con.prepareStatement("UPDATE books SET qty = qty - 1 WHERE id = ? AND qty > 0");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("<h3>Book purchased successfully!</h3>");
            } else {
                out.println("<h3>Out of stock or invalid ID!</h3>");
            }
            out.println("<br><a href='books'>Back to List</a>");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}