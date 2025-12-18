package com.beck.beck_demos.schedule_app.controllers;
import com.beck.beck_demos.schedule_app.data.CulversDAO;
import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.data.PaletteDAO;
import com.beck.beck_demos.schedule_app.iData.iPaletteDAO;
import com.beck.beck_demos.schedule_app.models.*;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

/****************** <br/>
 Create the Servlet  For adding to The  Event table
 doGet view all events
 Created By Jonathan Beck 4/9/2025 <br/>
 **************  */
@WebServlet("/all-events")
public class AllEventServlet extends HttpServlet {
  private iEventDAO eventDAO;
  private CulversDAO culversDAO;
  private iPaletteDAO paletteDAO;
  @Override
  public void init() {
    eventDAO = new EventDAO();
    culversDAO = new CulversDAO();
    paletteDAO = new PaletteDAO();
  }
  public void init(iEventDAO eventDAO,iPaletteDAO paletteDAO){
    this.eventDAO = eventDAO;
    this.culversDAO = new CulversDAO();
    this.paletteDAO = paletteDAO;
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
    List<Culvers> culvers = new ArrayList<>();
    List<Palette> palettes = new ArrayList<>();
    if (errors ==0) {
      try {
        events = eventDAO.getAllEvent(day,search_term, user.getUser_ID());
        culvers = culversDAO.getActiveCulversByUserID(user.getUser_ID());
        palettes = paletteDAO.getPalettebyUser(user.getUser_ID());
        user.setCulvers(culvers);
        user.setPalettes(palettes);
      } catch (Exception e) {
        events = new ArrayList<>();
      }
    }

    req.setAttribute("results",results);
    req.setAttribute("Events",events);
    if (view!=null&&view.equals("calendar")) {

      req.setAttribute("pageTitle", "All Events - Calendar");
      req.getRequestDispatcher("WEB-INF/Schedule_App/all-events-dynamic.jsp").forward(req, resp);
    }
    else {
      List<Event> PastEvents = new ArrayList<>();
      List<Event> CurrentEvents = new ArrayList<>();
      List<Event> FutureEvents = new ArrayList<>();
      Instant Now = (new Date()).toInstant();
      for (Event event : events) {
        Instant eventInstant=event.getDate().toInstant();
        if (Duration.between(Now,eventInstant).toDays()<0){
          PastEvents.add(event);
        }
        else if (Duration.between(Now,eventInstant).toDays()<60){
          CurrentEvents.add(event);
        }
        else {
          FutureEvents.add(event);
        }
      }

      req.setAttribute("PastEvents", PastEvents);
      req.setAttribute("CurrentEvents", CurrentEvents);
      req.setAttribute("FutureEvents", FutureEvents);
      req.setAttribute("pageTitle", "All Events - List");
      req.getRequestDispatcher("WEB-INF/Schedule_App/all-events.jsp").forward(req, resp);
    }

  }
}
