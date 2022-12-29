<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>create or update</h1>
<br/>

<form action="${pageContext.request.contextPath}/playerCRUD" method = "get">
    <input type="hidden" name="action" value="getAll"/>
    <button type="submit">get all</button>

</form>

<h1>find</h1>
<form action="${pageContext.request.contextPath}/playerCRUD" method = "get">
    <input type="hidden" name="action" value="find"/>
    <label for="find_id">id:</label><br>
    <input type = "number" name="find_id" id = "find_id">
    <button type="submit">find</button>
</form>
<form action="${pageContext.request.contextPath}/player" method = "post">
    <input type="hidden" name="action" value="create"/>
    <label for="id">id:</label><br>
    <input type = "number" name="id" id = "id">
    <label for="nickname">nickname:</label><br>
    <input type = "text" name="nickname" id = "nickname">

    <button type="submit">add</button>
</form>
<h1 >delete</h1>
<form action="${pageContext.request.contextPath}/playerCRUD" method = "post">
    <input type="hidden" name="action" value="delete"/>
    <label for="delete_id">id:</label><br>
    <input type = "number" name="delete_id" id = "delete_id">
    <button type="submit">delete</button>
</form>


<br/>
</body>
</html>

