package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.FriendDAO;
import com.beck.beck_demos.schedule_app.data.UserDAO;
import com.beck.beck_demos.schedule_app.iData.iUserDAO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Friend_Line table
 Created By Jonathan Beck 5/7/2025
 ***************/
@WebServlet("/addFriend_Line")
public class AddFriendServlet extends HttpServlet{
  private iFriendDAO friend_DAO;
  private iUserDAO user_DAO;

  @Override
  public void init() {
    friend_DAO = new FriendDAO();
    user_DAO = new UserDAO();
  }
  public void init(iFriendDAO friendDAO, iUserDAO user_DAO){
    this.friend_DAO = friendDAO;
    this.user_DAO = user_DAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    req.setAttribute("pageTitle", "Add a Friend");


    req.getRequestDispatcher("WEB-INF/Schedule_App/AddFriend.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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



    String _User_One = req.getParameter("inputfriend_lineUser_One");
    if (_User_One!=null) {
      _User_One=_User_One.trim();
    }

    Map<String, String> results = new HashMap<>();
    results.put("User_One",_User_One);

    Friend friend = new Friend();
    int errors =0;
    try {
      String userID = user_DAO.getUserID(_User_One);
      if (userID!=null) {
        friend.setFriend(userID);
      }
      else {
        throw new Exception("Unable to find user.");
      }
    } catch(Exception e) {
      results.put("friend_lineUser_Oneerror", e.getMessage());
      errors++;
    }

    int result=0;
    if (errors==0){
      try{
        result=friend_DAO.add(friend, user.getUser_ID());
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Friend_Line Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Friends");
        return;
      } else {
        results.put("dbStatus","Friend_Line Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add a Friend");
    req.getRequestDispatcher("WEB-INF/Schedule_App/AddFriend.jsp").forward(req, resp);

  }
}