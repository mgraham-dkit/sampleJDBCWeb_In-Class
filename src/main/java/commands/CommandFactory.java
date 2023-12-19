package commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommandFactory {
    public static Command getCommand(String action, HttpServletRequest request, HttpServletResponse response){
        Command c = null;
        if(action != null){
            // Handle the action
            switch(action){
                case "login":
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
        return c;
    }
}
