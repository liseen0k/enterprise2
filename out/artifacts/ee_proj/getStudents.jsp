<%@ page import="com.example.edu_com_plati_za_edu.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GetStudents</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<table>
    <thead>
    <tr>
        <td>id</td>
        <td>fio</td>
        <td>email</td>
        <td>group_id</td>
    </tr>
    </thead>
    <tbody>
    <% List<Student> students = (List<Student>) request.getAttribute("students"); %>
    <% for (Student student : students) {%>
    <tr>
        <td><%= student.getId() %></td>
        <td><%= student.getFio() %></td>
        <td><%=student.getEmail()%></td>
        <td><%=student.getGroupId()%></td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
