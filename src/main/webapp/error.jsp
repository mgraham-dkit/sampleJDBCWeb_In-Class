<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>An Error Occurred...</title>
</head>
    <body>
        <h1>Something went wrong</h1>
        <%
          String message = (String) session.getAttribute("errorMessage");
          if(message != null) {
            %>
            <div><%=message%></div>
            <%
                // We have finished with the results of this action
                // so now we can remove the value from the session
                session.removeAttribute("errorMessage");
                // This makes sure that old error messages
                // don't mistakenly get printed out later
          }else{
            %>
        <div>Did you come here by mistake?? No error has occurred recently...</div>
        <%
          }
        %>
    </body>
</html>
