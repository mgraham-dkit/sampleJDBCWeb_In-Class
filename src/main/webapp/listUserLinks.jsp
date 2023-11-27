<%@ page import="daos.UserDao" %>
<%@ page import="business.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current userlist</title>
</head>
<body>
<%
  UserDao userDao = new UserDao("user_database");
  List<User> users = userDao.findAllUsers();
  if(!users.isEmpty()){
%>
<table>
<%
    for(User u: users){
      %>
  <tr>
    <td><a href="userprofile.jsp?userId=<%=u.getId()%>"><%=u.getUsername()%></a></td>
  </tr>
  <%
    }
%>
</table>
<%
  }
%>
</body>
</html>
