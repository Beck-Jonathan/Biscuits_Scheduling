package com.beck.beck_demos.schedule_app.controllers;

/******************
 Create the Servlet For Deleteing from the Friend_Line table
 Created By Jonathan Beck 5/7/2025
 ***************/

import com.beck.beck_demos.schedule_app.data.FriendDAO;
import com.beck.beck_demos.schedule_app.models.Friend;
import com.beck.beck_demos.schedule_app.models.User;
import com.beck.beck_demos.schedule_app.iData.iFriendDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beck.beck_demos.schedule_app.iData.iFriendDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/approveFriend")
public class ApproveFriendServlet extends HttpServlet {
  private iFriendDAO friendDAO;

  @Override
  public void init() {
    friendDAO = new FriendDAO();

  }
  public void init(iFriendDAO friendDAO){
    this.friendDAO = friendDAO;
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();

    User user = (User)session.getAttribute("User_C");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("schedule_in");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Friend_Line");
    String Friend_ID = req.getParameter("friend_lineid");
    String mode = req.getParameter("mode");

    Integer result = 0;
    if (mode==null||mode.isEmpty()){
      mode = "";
    }
    if (mode.equals("approve")){
      try{
        result = friendDAO.approveFriend(Friend_ID,user.getUser_ID());
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else if (mode.equals("reject")||mode.equals("delete")) {
      try{
        result = friendDAO.deleteFriend(Friend_ID,user.getUser_ID());
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }

      resp.setStatus(200);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write(result.toString());
      writer.flush();
      writer.close();
      return;


  }
}