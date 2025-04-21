package com.beck.beck_demos.schedule_app.controllers;



/******************
 Create the Servlet  For  signing in
 Created By Jonathan Beck 4/9/2025

 ***************/
import com.beck.beck_demos.schedule_app.data.*;
import com.beck.beck_demos.schedule_app.models.*;
import com.beck.beck_demos.shared.EmailService;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/schedule-dash")
public class UserDashServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String Role = "User";

    User user = (User)session.getAttribute("User_C");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("schedule_in");
      return;
    }
    session.setAttribute("currentPage",req.getRequestURL());

    req.getRequestDispatcher("WEB-INF/Schedule_App/user_dash.jsp").forward(req, resp);
  }


}

