<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
</head>
    <body>
        <div>
            <h1>Welcome!</h1>
            <p>Please login here</p>
            <%
                // Check if they have landed here because their session expired
                String sessionExpired = (String) session.getAttribute("sessionExpired");
                // If so, then display the session expired message to the user
                if(sessionExpired != null){
                    // Display the error message to the user
                    out.println("<b>" + sessionExpired + "</b>");
                    // Remove the message from the session as it's no longer useful
                    session.removeAttribute("sessionExpired");
                }
            %>
        </div>
        <div>
            <h2>Login:</h2>
            <form action="controller" method="POST">
                <table>
                    <tr>
                        <td><label for="login_username">Username:</label></td>
                        <td><input type="text" size=20 id="login_username" name="username" required/></td>
                    </tr>
                    <tr>
                        <td><label for="login_password">Password:</label></td>
                        <td><input type="password" size=30 id="login_password" name="password" required/></td>
                    </tr>
                </table>
                <button type="submit" value="Submit">Submit</button>
                <input type ="hidden" name="action" value="login">
            </form>

            <br/><br/>

            <h2>Register:</h2>
            <form action="controller" method="POST">
                <table>
                    <tr>
                        <td><label for="register_username">Username:</label></td>
                        <td><input type="text" size=20 id="register_username" name="username" required/></td>
                    </tr>
                    <tr>
                        <td><label for="register_password">Password:</label></td>
                        <td><input type="password" size=30 id="register_password" name="password" required/></td>
                    </tr>
                    <tr>
                        <td><label for="firstname">First name:</label></td>
                        <td><input type="text" size=50 id=firstname name="firstname" required/></td>
                    </tr>
                    <tr>
                        <td><label for="lastname">Last name:</label></td>
                        <td><input type="text" size=80  id="lastname" name="lastname" required/></td>
                    </tr>
                </table>
                <button type="submit" value="Submit">Submit</button>
                <input type ="hidden" name="action" value="register">
            </form>

            <br/><br/>
            <a href="listUserLinks.jsp">View user list</a>
        </div>
    </body>
</html>