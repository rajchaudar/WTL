package com.ebookshop;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        int qty = Integer.parseInt(request.getParameter("qty"));

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Book Added</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head><body class='container mt-5'>");
        out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark mb-4'>"
                + "<div class='container-fluid'>"
                + "<a class='navbar-brand' href='books'>📘 E-Bookshop</a>"
                + "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav'"
                + " aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>"
                + "<span class='navbar-toggler-icon'></span></button>"
                + "<div class='collapse navbar-collapse' id='navbarNav'>"
                + "<ul class='navbar-nav ms-auto'>"
                + "<li class='nav-item'><a class='nav-link' href='books'>Book List</a></li>"
                + "<li class='nav-item'><a class='nav-link' href='addbook.html'>Add Book</a></li>"
                + "</ul></div></div></nav>");

        try (Connection con = DBConnection.initializeDatabase()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO books (title, author, price, qty) VALUES (?, ?, ?, ?)");
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            ps.executeUpdate();

            out.println("<div class='alert alert-success'><h4>✅ Book added successfully!</h4></div>");
            out.println("<a href='books' class='btn btn-primary mt-3'>📚 View Book List</a>");
        } catch (Exception e) {
            out.println("<div class='alert alert-danger'><strong>Error:</strong> " + e.getMessage() + "</div>");
        }

        out.println("</body></html>");
    }
}