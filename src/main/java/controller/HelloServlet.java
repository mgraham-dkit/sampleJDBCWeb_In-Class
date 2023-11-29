package controller;

import java.io.*;

import business.User;
import daos.UserDao;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/controller")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String destiny = "index.jsp";
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        System.out.println(action);
        if(action != null){
            // Handle the action
            switch(action){
                case "login":
                    destiny = loginCommand(request, response);
                    break;
                case "register":
                    destiny = registerCommand(request, response);
                    break;
                case "logout":
                    // Nuclear option - wipes everything
                    // session.invalidate();
                    // Targeted option: just remove the user from the session, this lets whatever else is stored
                    // e.g. a shopping cart, be kept
                    session.removeAttribute("loggedInUser");
                    destiny = "index.jsp";
                    break;
                default:
                    // Use custom error page to handle this
                    String errorMessage = "No such action provided by this controller";
                    session.setAttribute("errorMessage", errorMessage);
                    destiny = "error.jsp";
            }
        }else{
            // handle lack of action
            String errorMessage = "No action value was provided";
            session.setAttribute("errorMessage", errorMessage);
            destiny = "error.jsp";
        }
        response.sendRedirect(destiny);
    }

    public String loginCommand(HttpServletRequest request, HttpServletResponse response){
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

    public String registerCommand(HttpServletRequest request, HttpServletResponse response){
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