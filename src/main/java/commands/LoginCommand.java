package commands;

import business.User;
import daos.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command{

    private HttpServletRequest request;
    private HttpServletResponse response;

    public LoginCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String execute() {
        String destiny = "index.jsp";
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username != null && password != null && !username.isEmpty() && !password.isEmpty()){
            UserDao userDao = new UserDao("user_database");
            User user = userDao.findUserByUsernamePassword(username, password);
            if(user != null){
                // User has successfully identified themselves
                // Treat as logged in from here.
                session.setAttribute("loggedInUser", user);
                destiny = "home.jsp";
            }else{
                // No such user with those credentials
                String errorMessage = "No user exists for this username/password combination. Please <a href=\"index" +
                        ".jsp\">go back</a> and try again.";
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
