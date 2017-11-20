package com.project.servlet;

import com.project.processing.RegistrationFormProcessor;
import com.project.utils.ServletUtils;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtils.instanse.redirect(request, response, getServletContext(),"/WEB-INF/views/registration.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationFormProcessor registretionFormProcessor = new RegistrationFormProcessor();
        if(registretionFormProcessor.registerUser(request, getServletContext())){

            response.sendRedirect("/login?login=" +request.getParameter("login")
                    +"&password=" + request.getParameter("password"));

        } else {
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/registration.jsp");
        }
    }
}

