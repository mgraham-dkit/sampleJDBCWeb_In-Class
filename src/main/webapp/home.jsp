<%@ page import="business.User" %><%--
  Created by IntelliJ IDEA.
  User: michelle
  Date: 27/11/2023
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
  <%
    User u = (User) session.getAttribute("loggedInUser");
    if(u == null){
      response.sendRedirect("error.jsp");
    }
  %>
  <p>Hi, <%=u.getUsername()%>!</p>
</body>
</html>
