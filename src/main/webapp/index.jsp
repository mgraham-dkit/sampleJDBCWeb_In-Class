<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
    <form action="controller" method="POST">
        <label for="username">Username:</label><input type="text" id=username name="username" required/>
        <label for="password">Password:</label><input type="password" id="password" name="password" required/>
        <button type="submit" value="Submit">aef</button>
        <input type ="hidden" name="action" value="login">
    </form>
</h1>
</body>
</html>