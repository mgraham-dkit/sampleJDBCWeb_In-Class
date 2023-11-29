<%@ page import="daos.UserDao" %>
<%@ page import="business.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile Page</title>
</head>
  <body>
    <p>
      This page will display the information for the user that was clicked on in the previous page.
    </p>
    <p>
      If no user id was supplied (or text was supplied instead of a number), the user will be informed.<br/>
      To test this, change the url in the address bar to remove the userId parameter (or change it from a numeric
      value to text).
    </p>
  <%
    String id = request.getParameter("userId");
    if( id != null){
      try {
        int userId = Integer.parseInt(id);
        UserDao dao = new UserDao("user_database");
        User u = dao.findUserById(userId);
        if(u != null){
          %>
            <table>
              <tr><td>User ID:</td><td><%=u.getId()%></td></tr>
              <tr><td>Username:</td><td><%=u.getUsername()%></td></tr>
              <tr><td>First name:</td><td><%=u.getFirstName()%></td></tr>
              <tr><td>Last name:</td><td><%=u.getLastName()%></td></tr>
            </table>
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
    <a href="index.jsp">Back to index</a>
  </body>
</html>
