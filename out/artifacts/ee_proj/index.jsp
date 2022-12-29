
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/player">
    <input type="submit" value="player" />
</form>

<form action="${pageContext.request.contextPath}/currencies">
    <input type="submit" value="currencies" />
</form>

<form action="${pageContext.request.contextPath}/items">
    <input type="submit" value="items" />
</form>

<form action="${pageContext.request.contextPath}/progress">
    <input type="submit" value="progress" />
</form>

</body>
</html>
