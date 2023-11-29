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
        if(action != null){
            // Handle the action
            switch(action){
                case "login":
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
                            destiny = "error.jsp";
                        }
                    }else{
                        // Bad/no information provided
                        destiny = "error.jsp";
                    }
                    break;
                case "logout":
                    // Nuclear option - wipes everything
                    // session.invalidate();
                    session.removeAttribute("loggedInUser");
                    destiny = "index.jsp";
                    break;
                default:
                    // Use custom error page to handle this
                    destiny = "error.jsp";
            }
        }else{
            // handle lack of action
            destiny = "error.jsp";
        }
        response.sendRedirect(destiny);
    }

    public void destroy() {
    }
}