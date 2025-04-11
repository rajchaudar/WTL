package com.student;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            StudentDAO.deleteStudent(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("viewStudents.jsp");
    }
}