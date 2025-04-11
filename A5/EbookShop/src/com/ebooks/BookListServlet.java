package com.ebookshop;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class BookListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.initializeDatabase()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            out.println("<h2>Book List</h2><table border='1'>");
            out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th><th>Qty</th><th>Action</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td>" +
                        "<td>" + rs.getString("title") + "</td>" +
                        "<td>" + rs.getString("author") + "</td>" +
                        "<td>" + rs.getDouble("price") + "</td>" +
                        "<td>" + rs.getInt("qty") + "</td>" +
                        "<td><a href='buy?id=" + rs.getInt("id") + "'>Buy</a></td></tr>");
            }
            out.println("</table><br><a href='addbook.html'>Add New Book</a>");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}