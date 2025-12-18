package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.PaletteDAO;
import com.beck.beck_demos.schedule_app.iData.iPaletteDAO;
import com.beck.beck_demos.schedule_app.models.CalendarMonth;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.User;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AJAXSCHOOL")
public class AllSchoolMealsAJAXServlet extends HttpServlet {
  private iEventDAO eventDAO;
  @Override
  public void init() {
    eventDAO = new EventDAO();
  }
  public void init(iEventDAO eventDAO){
    this.eventDAO = eventDAO;
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_C");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("schedule_in");
      return;
    }
    CalendarDay day = new CalendarDay();
    session.setAttribute("currentPage",req.getRequestURL());


    String cal_month = req.getParameter("month");
    //String cal_year = req.getParameter("year");
    List<String> schools = new ArrayList<>();
    schools.add("cooledge");

    int _cal_month = 0;
    int _cal_year = 0;
    int errors = 0;


    if (cal_month!=null&&!cal_month.isEmpty()){
      try{
        _cal_month = Integer.parseInt(cal_month);
        day.setMonth(_cal_month);
      }catch (Exception e){
        errors ++;
      }
    }
//    if (cal_year!=null&&!cal_year.isEmpty()){
//      try{
//        _cal_year = Integer.parseInt(cal_year);
//        day.setYear(_cal_year);
//      }catch (Exception e){
//        errors ++;
//      }
//    }


    List<Event> events = new ArrayList<>();
    if (errors==0) {
      try {
        events = eventDAO.getFoodMenu(schools, _cal_month);
      } catch (Exception e) {
        events = new ArrayList<>();
      }
    }


    CalendarMonth month = new CalendarMonth();
    List<CalendarDay> days = new ArrayList<>();
    for (int i =0;i<31;i++){
      CalendarDay day1 = new CalendarDay();
      day1.setDay(i+1);
      days.add(day1);
    }
    for (Event event : events) {
      for (int i =0;i<31;i++){
        if (event.getDate().getDate()==i){
          days.get(i).addEvent(event);
        }
      }
    }
    int x=0;
    ObjectMapper mapper = new ObjectMapper();
    String output="";
    // convert user object to json string and return it
    if (errors==0) {
      output = mapper.writeValueAsString(days);
    }
    else {
      output = "Invalid Search";
    }

    PrintWriter out = resp.getWriter();
    resp.setHeader("Content-Type", "application/json;charset=UTF-8");
    resp.setCharacterEncoding("UTF-8");
    out.print(output);
    out.flush();
  }
}