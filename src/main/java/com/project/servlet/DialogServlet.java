package com.project.servlet;

import com.project.beans.Dialog;
import com.project.beans.User;
import com.project.chat.DialogsMap;
import com.project.chat.OnlineUsersMap;
import com.project.utils.ServletUtils;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/dialog"})
public class DialogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DialogServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if( (request.getParameter("user1") != null) && (request.getParameter("user2") != null)){
            if(!DialogsMap.dialogsUserMap.containsKey(request.getParameter("user1") + request.getParameter("user2"))){
                DialogsMap.dialogsUserMap.put(request.getParameter("user1") + request.getParameter("user2"),new Dialog(new User(OnlineUsersMap.onlineUserMap.get(request.getParameter("user1"))),new User(OnlineUsersMap.onlineUserMap.get(request.getParameter("user2")))));
            }
            request.getSession().setAttribute("user1",request.getParameter("user1"));
            request.getSession().setAttribute("user2",request.getParameter("user2"));
            request.getSession().setAttribute("whos",request.getParameter("whos"));
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/dialog.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
