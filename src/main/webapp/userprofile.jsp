<%@ page import="daos.UserDao" %>
<%@ page import="business.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile Page</title>
</head>
<body>
<%
  String id = request.getParameter("userId");
  if( id != null){
    try {
      int userId = Integer.parseInt(id);
      UserDao dao = new UserDao("user_database");
      User u = dao.findUserById(userId);
      if(u != null){
        %>
          <tr>
            <td><%=u.getId()%></td>
            <td><%=u.getUsername()%></td>
            <td><%=u.getFirstName()%></td>
            <td><%=u.getLastName()%></td>
          </tr>
        <%
      }else{
        %>
        <p>No user with the supplied ID value.</p>
        <%
      }

    }catch(NumberFormatException e){
      %>
        <p>Incorrect id information supplied. Please use a number for the user's id!</p>
      <%
    }
  }else{
      %>
        <p>No id information supplied. Please try again!</p>
      <%
    }
%>
</body>
</html>
