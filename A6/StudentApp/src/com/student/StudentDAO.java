package com.student;

import java.sql.*;
import java.util.*;

public class StudentDAO {
    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "raj@1415");
    }

    public static int addStudent(Student s) throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO students(name, email, course) VALUES (?, ?, ?)");
        ps.setString(1, s.getName());
        ps.setString(2, s.getEmail());
        ps.setString(3, s.getCourse());
        int result = ps.executeUpdate();
        con.close();
        return result;
    }

    public static List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students");

        while (rs.next()) {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setEmail(rs.getString("email"));
            s.setCourse(rs.getString("course"));
            list.add(s);
        }

        con.close();
        return list;
    }

    public static void deleteStudent(int id) throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }
}