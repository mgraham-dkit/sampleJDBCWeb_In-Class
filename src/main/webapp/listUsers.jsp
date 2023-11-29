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
        <td><%=u.getId()%></td>
        <td><%=u.getUsername()%></td>
        <td><%=u.getFirstName()%></td>
        <td><%=u.getLastName()%></td>
      </tr>
      <%
        }
    %>
    </table>
    <%
      }
    %>
    <a href="index.jsp">Back to index</a>
  </body>
</html>
