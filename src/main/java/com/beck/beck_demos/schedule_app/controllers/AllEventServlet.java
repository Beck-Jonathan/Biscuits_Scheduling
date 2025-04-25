package com.beck.beck_demos.schedule_app.controllers;
import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.User;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
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

/****************** <br/>
 Create the Servlet  For adding to The  Event table
 doGet view all events
 Created By Jonathan Beck 4/9/2025 <br/>
 **************  */
@WebServlet("/all-events")
public class AllEventServlet extends HttpServlet {
  private iEventDAO eventDAO;
  @Override
  public void init() {
    eventDAO = new EventDAO();
  }
  public void init(iEventDAO eventDAO){
    this.eventDAO = eventDAO;
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
    HashMap<String,String> results = new HashMap<>();
    CalendarDay day = new CalendarDay();
    session.setAttribute("currentPage",req.getRequestURL());
    String view = req.getParameter("view");
    String cal_day = req.getParameter("day");
    String cal_month = req.getParameter("month");
    String cal_year = req.getParameter("year");
    String search_term = req.getParameter("search");
    results.put("day",cal_day);
    results.put("month",cal_month);
    results.put("year",cal_year);
    results.put("search",search_term);
    int _cal_day = 0;
    int _cal_month = 0;
    int _cal_year = 0;
    int errors = 0;
    if (cal_day!=null&&!cal_day.isEmpty()){
      try{
        _cal_day = Integer.parseInt(cal_day);
        day.setDay(_cal_day);
      }catch (Exception e){
        errors ++;
      }
    }
    if (cal_month!=null&&!cal_month.isEmpty()){
      try{
        _cal_month = Integer.parseInt(cal_month);
        day.setMonth(_cal_month);
      }catch (Exception e){
        errors ++;
      }
    }
    if (cal_year!=null&&!cal_year.isEmpty()){
      try{
        _cal_year = Integer.parseInt(cal_year);
        day.setYear(_cal_year);
      }catch (Exception e){
        errors ++;
      }
    }
    if (search_term==null){
      search_term = "";
    }
    if (!search_term.equals("") && (search_term.length()<2||search_term.length()>100)){
      errors++;
      results.put("searchError","Invalid search term");
    }
    List<Event> events = new ArrayList<>();
    if (errors ==0) {
      try {
        events = eventDAO.getAllEvent(day,search_term);
      } catch (Exception e) {
        events = new ArrayList<>();
      }
    }

    req.setAttribute("Events", events);
    req.setAttribute("results",results);

    if (view!=null&&view.equals("calendar")) {
      req.setAttribute("pageTitle", "All Events - Calendar");
      req.getRequestDispatcher("WEB-INF/Schedule_App/all-events-dynamic.jsp").forward(req, resp);
    }
    else {
      req.setAttribute("pageTitle", "All Events - List");
      req.getRequestDispatcher("WEB-INF/Schedule_App/all-events.jsp").forward(req, resp);
    }

  }
}
