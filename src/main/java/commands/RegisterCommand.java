package commands;

import business.User;
import daos.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegisterCommand implements Command{
    private HttpServletRequest request;
    private HttpServletResponse response;

    public RegisterCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }


    @Override
    public String execute() {
        String destiny = "index.jsp";
        HttpSession session = request.getSession(true);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        if(username != null && password != null && firstname != null && lastname != null && !username.isEmpty() && !password.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty()){
            UserDao userDao = new UserDao("user_database");
            int userId = userDao.addUser(username, password, firstname, lastname);
            if(userId != -1){
                // User has successfully identified themselves
                // Treat as logged in from here.
                User user = new User(userId, firstname, lastname, username, password);
                session.setAttribute("loggedInUser", user);
                destiny = "home.jsp";
            }else{
                // User already exists with these credentials
                String errorMessage = "User couldn't be added to the database at this time. "
                        + "Please <a href='index.jsp'>go back</a> and try again.<br/>Maybe try a different " +
                        "username!";
                session.setAttribute("errorMessage", errorMessage);
                destiny = "error.jsp";
            }
        }else{
            // Bad/no information provided
            String errorMessage = "One or more fields were missing. Please <a href='index.jsp'>go back</a> and try " +
                    "again.";
            session.setAttribute("errorMessage", errorMessage);
            destiny = "error.jsp";
        }

        return destiny;
    }
}
