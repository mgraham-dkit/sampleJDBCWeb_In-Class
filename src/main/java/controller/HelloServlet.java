package controller;

import java.io.*;

import business.User;
import commands.*;
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
        Command c = null;
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        System.out.println(action);
        if(action != null){
            // Handle the action
            switch(action){
                case "login":
                    // destiny = loginCommand(request, response);
                    c = new LoginCommand(request, response);
                    break;
                case "register":
                    c = new RegisterCommand(request, response);
                    break;
                case "logout":
                    c = new LogoutCommand(request, response);
                    break;
                default:
                    // Use custom error page to handle this
                    String errorMessage = "No such action provided by this controller";
                    c = new ErrorCommand(request, response, errorMessage);
            }
        }else{
            // handle lack of action
            String errorMessage = "No action value was provided";
            c = new ErrorCommand(request, response, errorMessage);
        }
        destiny = c.execute();
        response.sendRedirect(destiny);
    }
}