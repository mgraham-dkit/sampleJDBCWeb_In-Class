<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
    <form action="controller" method="POST">
        Username:<input type="text" name="username"/>
        <button type="submit" value="Submit">aef</button>
    </form>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>