package com.beck.beck_demos.schedule_app.controllers;

/******************
 Create the Servlet  For Viewing all of the  Friend_Line table
 Created By Jonathan Beck 5/7/2025
 ***************/

import com.beck.beck_demos.schedule_app.data.FriendDAO;
import com.beck.beck_demos.schedule_app.models.Friend;
import com.beck.beck_demos.schedule_app.models.Friend_VM;
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

@WebServlet("/all-Friends")
public class AllFriendServlet extends HttpServlet {
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

    int errors = 0;
    HashMap<String,String> results = new HashMap<>();


    String search_term = req.getParameter("search");
    if (search_term==null){
      search_term ="";
    }
    if (!search_term.equals("") && (search_term.length()<2||search_term.length()>100)){
      errors++;
      results.put("searchError","Invalid search term");
    }
    session.setAttribute("currentPage",req.getRequestURL());
    List<Friend_VM> friends = null;
    try {

      friends =friendDAO.getFriendebyUser(user.getUser_ID());
    } catch (Exception e) {
      friends = new ArrayList<>();
    }
    List<Friend_VM> approved_Friends = new ArrayList<>();
    List<Friend_VM> incoming_friends = new ArrayList<>();
    List<Friend_VM> outgoing_friends = new ArrayList<>();
    for (Friend_VM friend : friends) {
      if (friend.getStatus().toLowerCase().contains("accepted")){
        approved_Friends.add(friend);
      }
      else if (friend.getStatus().equalsIgnoreCase("pending - received")){
        incoming_friends.add(friend);
      }
      else if (friend.getStatus().equalsIgnoreCase("pending - sent")){
        outgoing_friends.add(friend);
      }



    }



    req.setAttribute("approved_Friends", approved_Friends);
    req.setAttribute("incoming_friends", incoming_friends);
    req.setAttribute("outgoing_friends", outgoing_friends);
    req.setAttribute("pageTitle", "Manage Friends");
    req.getRequestDispatcher("WEB-INF/Schedule_App/all-Friends.jsp").forward(req,resp);

  }
}





