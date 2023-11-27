package com.example.inclasswebwithjdbc;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/controller")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = "Hi " + request.getParameter("username");
        HttpSession session = request.getSession(true);
        session.setAttribute("messageToPrint", message);
        response.sendRedirect("greet.jsp");
    }

    public void destroy() {
    }
}