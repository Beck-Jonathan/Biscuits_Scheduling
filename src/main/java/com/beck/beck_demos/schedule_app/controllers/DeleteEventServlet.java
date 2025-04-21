package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****************** <br/>
 Create the Servlet  For deleting from The  Event table
 doPost to delete an event
 Created By Jonathan Beck 4/9/2025 <br/>
 **************  */
@WebServlet("/deleteEvent")
public class DeleteEventServlet extends HttpServlet {
  private iEventDAO eventDAO;
  @Override
  public void init(){
    eventDAO = new EventDAO();
  }
  public void init(iEventDAO eventDAO){
    this.eventDAO = eventDAO;
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    req.setAttribute("pageTitle", "Delete Event");
    String EventID = req.getParameter("eventid");
    String search_term = req.getParameter("search");
    if (search_term==null){
      search_term = "";
    }

    int result = 0;

      try{
        result = eventDAO.deleteEvent(EventID);
        if (result == 0){


          throw new SQLException("Unable To Find Event.","un");
        }
      }
      catch(SQLException ex){
        results.put("dbStatus", "Unable To Find Event.");
      }

    List<Event> events = null;
    CalendarDay day = new CalendarDay();
    try {
      events = eventDAO.getAllEvent(day,search_term);
    } catch (SQLException e) {
      results.put("dbError",e.getMessage());
    }
    req.setAttribute("result",result);
    req.setAttribute("results",results);
    req.setAttribute("Events", events);
    req.setAttribute("pageTitle", "All Event");
    req.getRequestDispatcher("WEB-INF/Schedule_App/all-events.jsp").forward(req, resp);
  }
}
