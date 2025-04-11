package com.ebookshop;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        int qty = Integer.parseInt(request.getParameter("qty"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.initializeDatabase()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO books (title, author, price, qty) VALUES (?, ?, ?, ?)");
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            ps.executeUpdate();

            out.println("<h3>Book added successfully!</h3>");
            out.println("<br><a href='books'>View Book List</a>");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}