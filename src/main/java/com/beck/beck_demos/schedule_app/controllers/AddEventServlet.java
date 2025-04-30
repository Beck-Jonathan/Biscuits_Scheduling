package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.User;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

/****************** <br/>
 Create the Servlet  For adding to The  Event table
 doGet to view the form
 doPost to process the form
 Created By Jonathan Beck 4/9/2025 <br/>
 **************  */

@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet{
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
    Map<String,String> results = new HashMap<>();
    String day = req.getParameter("day");
    String month = req.getParameter("month");
    String year = req.getParameter("year");
    Date now = new Date();
    Calendar calendar = Calendar.getInstance();
    Integer _day = 0;
    Integer _month = 0;
    Integer _year = 0;
    if (day!=null && !day.isEmpty()){
      try{
        _day=Integer.parseInt(day);
        if (_day>0&&_day<10){
          day="0"+_day;
        }
      }catch (Exception e) {
        _day = calendar.get(Calendar.DAY_OF_MONTH);
        day = ((Integer) calendar.get(Calendar.DAY_OF_MONTH)).toString();
        if (_day>0&&_day<10){
          day="0"+_day;
        }
      }
    }
    else {
      _day = calendar.get(Calendar.DAY_OF_MONTH);
      day = ((Integer) calendar.get(Calendar.DAY_OF_MONTH)).toString();
      if (_day>0&&_day<10){
        day="0"+_day;
      }
    }
    if (month!=null && !month.isEmpty()){
      try{
        _month=Integer.parseInt(month);
        if (_month>0&&_month<10){
          month="0"+_month;
        }

      }catch (Exception e) {
        _month =  calendar.get(Calendar.MONTH);
        month = ((Integer) calendar.get(Calendar.MONTH)).toString();
        if (_month<10){
          month = "0"+_month;
        }
      }
    } else {
      _month = calendar.get(Calendar.MONTH)+1;
      month = ((Integer) calendar.get(Calendar.MONTH)).toString();
      if (_month<10){
        month = "0"+_month;
      }
    }

    if (year!=null && !year.isEmpty()){
      try{
        _year=Integer.parseInt(year);

      }catch (Exception e) {

        year = ((Integer) calendar.get(Calendar.YEAR)).toString();

      }
    }
    else {
      year = ((Integer) calendar.get(Calendar.YEAR)).toString();
    }
    String date = year+"-"+month+"-"+day+"T08:00";
    results.put("Date",date);


    req.setAttribute("results",results);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Event");
    req.getRequestDispatcher("WEB-INF/Schedule_App/AddEvent.jsp").forward(req, resp);
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

    String _Event_ID = req.getParameter("inputeventEvent_ID");
    if (_Event_ID!=null) {
      _Event_ID=_Event_ID.trim();
    }
    String _Name = req.getParameter("inputeventName");
    if (_Name!=null) {
      _Name=_Name.trim();
    }
    String _Date = req.getParameter("inputeventDate");
    if (_Date!=null) {
      _Date=_Date.trim();
    }
    String _Location = req.getParameter("inputeventLocation");
    if (_Location!=null) {
      _Location=_Location.trim();
    }
    String _description = req.getParameter("inputeventdescription");
    if (_description!=null) {
      _description=_description.trim();
    }
    String _Length = req.getParameter("inputeventLength");
    if (_Length!=null) {
      _Length=_Length.trim();
    }
    String _Unit = req.getParameter("inputeventUnit");
    if (_Unit!=null) {
      _Unit=_Unit.trim();
    }
    String _Descision = req.getParameter("inputeventDescision");
    if (_Descision!=null) {
      _Descision=_Descision.trim();
    }
    String _Paid = req.getParameter("inputeventPaid");
    if (_Paid!=null) {
      _Paid=_Paid.trim();
    }
    String _numberRecurrences = req.getParameter("inputEventRecur");
    int recur=0;
    if (_numberRecurrences!=null) {
      try {
        recur = Integer.parseInt(_numberRecurrences);
      } catch (Exception e) {
        recur=1;
      }
    }
    String period = req.getParameter("inputEventPeriod");
    if (period!=null) {
      period = period.toLowerCase();
      if (!period.equals("day") && !period.equals("week") && !period.equals("month") &&  !period.equals("year")) {
        recur = 1;
      }


    }

    Map<String, String> results = new HashMap<>();
    results.put("Event_ID",_Event_ID);
    results.put("Name",_Name);
    results.put("Date",_Date);
    results.put("Location",_Location);
    results.put("description",_description);
    results.put("Length",_Length);
    results.put("Descision",_Descision);
    results.put("Paid",_Paid);
    Event event = new Event();
    int errors =0;
    try {
      event.setName(_Name);
    } catch(Exception e) {results.put("eventNameerror", e.getMessage());
      errors++;
    }
    try {
      _Date=_Date.replace('T',' ');
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      Date date = formatter.parse(_Date);
      event.setDate(date);
    } catch(Exception e) {results.put("eventDateerror", e.getMessage());
      errors++;
    }
    try {
      event.setLocation(_Location);
    } catch(Exception e) {results.put("eventLocationerror", e.getMessage());
      errors++;
    }
    try {
      event.setDescription(_description);
    } catch(Exception e) {results.put("eventdescriptionerror", e.getMessage());
      errors++;
    }
    try {
      Double length = Double.parseDouble(_Length);
      if(_Unit.toLowerCase().equals("days")){
        length = length*24d;
      }
      else if(_Unit.toLowerCase().equals("hours")){
        length = length;
      }
      else if(_Unit.toLowerCase().equals("minutes")){
        length = length/60d;
      }
      else {
        throw new IllegalArgumentException("invalid unit");
      }
      event.setLength(length);
    } catch(Exception e) {results.put("eventLengtherror", e.getMessage());
      errors++;
    }
    try {
      event.setDecision(_Descision);
    } catch(Exception e) {results.put("eventDescisionerror", e.getMessage());
      errors++;
    }
    try {
      event.setPaid(_Paid);
    } catch(Exception e) {results.put("eventPaiderror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0) {
      if (recur == 0) {
        try {
          result = eventDAO.add(event);
        } catch (Exception ex) {
          results.put("dbError", "Database Error");
        }
        if (result > 0) {
          results.put("dbStatus", "Event Added");
          req.setAttribute("results", results);
          resp.sendRedirect("all-events");
          return;
        } else {
          results.put("dbStatus", "Event Not Added");

        }
      }
      else {
        ArrayList<Event> events = new ArrayList<>();

        for (int i=0; i<recur; i++) {
          Event e = new Event();
          e.setName(event.getName());
          e.setDate(event.getDate());
          if (period.equals("day")) {
            e.setDate(DateUtils.addDays(e.getDate(),i));
          }
          else if (period.equals("week")) {
            e.setDate(DateUtils.addWeeks(e.getDate(),i));
          }
          else if (period.equals("month")) {
            e.setDate(DateUtils.addMonths(e.getDate(),i));
          }
          else if (period.equals("year")) {
            e.setDate(DateUtils.addYears(e.getDate(),i));
          }


          e.setLocation(event.getLocation());
          e.setDescription(event.getDescription());
          e.setLength(event.getLength());
          e.setDecision(event.getDecision());
          e.setPaid(event.getPaid());
          events.add(e);
        }
        try {
          result = eventDAO.addBatch(events);
        } catch (Exception ex) {
          results.put("dbError", "Database Error");
        }
        if (result == events.size()) {
          results.put("dbStatus", result+ " Events Added");
          req.setAttribute("results", results);
          resp.sendRedirect("all-events");
          return;
        } else {
          results.put("dbStatus", "Event(s) Not Added");

        }
      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Event ");
    req.getRequestDispatcher("WEB-INF/Schedule_App/AddEvent.jsp").forward(req, resp);

  }
}

