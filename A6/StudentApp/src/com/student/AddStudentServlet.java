package com.student;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        Student s = new Student();
        s.setName(name);
        s.setEmail(email);
        s.setCourse(course);

        try {
            StudentDAO.addStudent(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("viewStudents.jsp");
    }
}