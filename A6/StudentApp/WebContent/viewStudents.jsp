<%@ page import="java.util.*,com.student.Student,com.student.StudentDAO" %>
<html>
<head><title>Student List</title></head>
<body>
    <h2>All Students</h2>
    <table border="1">
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>Action</th>
        </tr>
        <%
            List<Student> list = StudentDAO.getAllStudents();
            for(Student s : list){
        %>
        <tr>
            <td><%=s.getId()%></td>
            <td><%=s.getName()%></td>
            <td><%=s.getEmail()%></td>
            <td><%=s.getCourse()%></td>
            <td><a href="DeleteStudentServlet?id=<%=s.getId()%>">Delete</a></td>
        </tr>
        <% } %>
    </table>
    <br>
    <a href="index.jsp">Home</a>
</body>
</html>