package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/******************
 Create the Servlet Viuw/Edit from the Event table
 doGet to view the selected event
 doPost to update the event
 Created By Jonathan Beck 4/10/2025
 ***************/

@WebServlet("/editEvent")
public class EditEventServlet extends HttpServlet{
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


    String primaryKey = "";
    try{
      primaryKey = req.getParameter("eventid");
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());

    }
    Event event= new Event();
    try{
      event.setEvent_ID(primaryKey);

    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
      resp.sendRedirect("all-events");
      return;
    }
    try{
      event=eventDAO.getEventByPrimaryKey(event);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
      event = new Event();
    }
    if (event==null || event.getEvent_ID()==null){
      resp.sendRedirect("all-events");
      return;
    }
    session.setAttribute("event",event);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Event");
    req.getRequestDispatcher("WEB-INF/Schedule_App/EditEvent.jsp").forward(req, resp);
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

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
//to get the old Event
    Event _oldEvent= (Event)session.getAttribute("event");
//to get the new event's info

    String _Name = req.getParameter("inputeventName");
    if (_Name!=null){
      _Name=_Name.trim();
    }
    String _Date_Time = req.getParameter("inputeventDate_Time");
    if (_Date_Time!=null){
      _Date_Time=_Date_Time.trim();
    }
    String _Location = req.getParameter("inputeventLocation");
    if (_Location!=null){
      _Location=_Location.trim();
    }
    String _Description = req.getParameter("inputeventDescription");
    if (_Description!=null){
      _Description=_Description.trim();
    }
    String _Length = req.getParameter("inputeventLength");
    if (_Length!=null){
      _Length=_Length.trim();
    }
    String _Unit = req.getParameter("inputeventUnit");
    if (_Unit!=null) {
      _Unit=_Unit.trim();
    }
    String _Decision = req.getParameter("inputeventDecision");
    if (_Decision!=null){
      _Decision=_Decision.trim();
    }
    String _Paid = req.getParameter("inputeventPaid");
    if (_Paid!=null){
      _Paid=_Paid.trim();
    }

    results.put("Name",_Name);
    results.put("Date_Time",_Date_Time);
    results.put("Location",_Location);
    results.put("Description",_Description);
    results.put("Length",_Length);
    results.put("Decision",_Decision);
    results.put("Paid",_Paid);
    Event _newEvent = new Event();
    int errors =0;
    try {
      _newEvent.setEvent_ID(_oldEvent.getEvent_ID());
    } catch(Exception e) {results.put("eventEvent_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newEvent.setName(_Name);
    } catch(Exception e) {results.put("eventNameerror", e.getMessage());
      errors++;
    }
    try {
      _Date_Time=_Date_Time.replace('T',' ');
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      Date date = formatter.parse(_Date_Time);
      _newEvent.setDate(date);

    } catch(Exception e) {results.put("eventDate_Timeerror", e.getMessage());
      errors++;
    }
    try {
      _newEvent.setLocation(_Location);
    } catch(Exception e) {results.put("eventLocationerror", e.getMessage());
      errors++;
    }
    try {
      _newEvent.setDescription(_Description);
    } catch(Exception e) {results.put("eventDescriptionerror", e.getMessage());
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
      _newEvent.setLength(length);
    } catch(Exception e) {results.put("eventLengtherror", e.getMessage());
      errors++;
    }
    try {
      _newEvent.setDecision(_Decision);
    } catch(Exception e) {results.put("eventDecisionerror", e.getMessage());
      errors++;
    }
    try {
      _newEvent.setPaid(_Paid);
    } catch(Exception e) {results.put("eventPaiderror", e.getMessage());
      errors++;
    }
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=eventDAO.update(_oldEvent,_newEvent);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Event updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-events");
        return;
      } else {
        results.put("dbStatus","Event Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Event ");
    req.getRequestDispatcher("WEB-INF/Schedule_App/EditEvent.jsp").forward(req, resp);
  }
}

