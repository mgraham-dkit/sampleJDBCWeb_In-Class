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
    <title>User Home Page</title>
</head>
<body>
  <%
    // Get the user object from the session
    User user = (User) session.getAttribute("loggedInUser");
    // If the user has successfully logged in (and their session hasn't expired yet)
    // Then display a greeting to them
    if(user != null){
  %>
  <h1>Welcome, <%=user.getUsername()%></h1>
  <%
    }
    // Otherwise, redirect them to the login page and tell them to try again
    else{
      String sessionExpired = "Your session has expired, please log in again.";
      session.setAttribute("sessionExpired", sessionExpired);
      response.sendRedirect("index.jsp");
    }
  %>
  <div></div>
  <a href="controller?action=logout">Logout</a>
</body>
</html>
