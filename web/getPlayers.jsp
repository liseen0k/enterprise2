
<%@ page import="java.util.List" %>
<%@ page import="org.example.Models.Player" %>
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
        <td>nickname</td>
        <td>items</td>
        <td>currencies</td>
        <td>progresses</td>
    </tr>
    </thead>
    <tbody>
    <% List<Player> players = (List<Player>) request.getAttribute("players"); %>
    <% for (Player player : players) {%>
    <tr>
        <td><%= player.getPlayerId() %></td>
        <td><%= player.getNickname() %></td>
        <td><%=player.getItems().toString()%></td>
        <td><%=player.getCurrencies().toString()%></td>
        <td><%=player.getProgresses().toString()%></td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
