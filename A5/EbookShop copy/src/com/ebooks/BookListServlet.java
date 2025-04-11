package com.ebookshop;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class BookListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Book List</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head><body class='container mt-4'>");
        
        out.println("<!-- Navbar -->");
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

        out.println("<h2 class='mb-4'>📖 Book List</h2>");

        try (Connection con = DBConnection.initializeDatabase()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            out.println("<table class='table table-bordered table-hover'>");
            out.println("<thead class='table-dark'><tr><th>ID</th><th>Title</th><th>Author</th><th>Price (₹)</th><th>Qty</th><th>Action</th></tr></thead>");
            out.println("<tbody>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td>" +
                        "<td>" + rs.getString("title") + "</td>" +
                        "<td>" + rs.getString("author") + "</td>" +
                        "<td>&#8377;" + rs.getDouble("price") + "</td>" +
                        "<td>" + rs.getInt("qty") + "</td>" +
                        "<td><a href='buy?id=" + rs.getInt("id") + "' class='btn btn-sm btn-success'>Buy 🛒</a></td></tr>");
            }
            out.println("</tbody></table>");

            out.println("<a href='addbook.html' class='btn btn-primary mt-3'>➕ Add New Book</a>");
        } catch (Exception e) {
            out.println("<div class='alert alert-danger'>Error: " + e.getMessage() + "</div>");
        }

        out.println("</body></html>");
    }
}