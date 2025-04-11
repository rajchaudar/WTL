package com.ebookshop;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class BuyBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Buy Book</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head><body class='container mt-5'>");
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

        try (Connection con = DBConnection.initializeDatabase()) {
            PreparedStatement ps = con.prepareStatement("UPDATE books SET qty = qty - 1 WHERE id = ? AND qty > 0");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("<div class='alert alert-success'><h4>✅ Book purchased successfully!</h4></div>");
            } else {
                out.println("<div class='alert alert-danger'><h4>❌ Out of stock or invalid book ID!</h4></div>");
            }

            out.println("<a href='books' class='btn btn-primary mt-3'>⬅ Back to Book List</a>");
        } catch (Exception e) {
            out.println("<div class='alert alert-warning'>Error: " + e.getMessage() + "</div>");
        }

        out.println("</body></html>");
    }
}