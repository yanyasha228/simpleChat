package com.project.servlet;

import com.project.processing.LoginFormProcessor;
import com.project.utils.ServletUtils;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ((request.getParameter("login") != null) && (request.getParameter("password") != null)) {
            doPost(request, response);
        }
        ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginFormProcessor.instanse.login(request)) {
            request.getSession().setAttribute("login", request.getParameter("login"));
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/chat.jsp");
        } else {
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/login.jsp");
        }
    }

}
