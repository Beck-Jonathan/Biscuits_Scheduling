package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.CalendarMonth;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/AJAXPOKEMON")
public class pkmnExperiment extends HttpServlet {
  private iEventDAO eventDAO;

  @Override
  public void init() {
    eventDAO = new EventDAO();
  }

  public void init(iEventDAO eventDAO) {
    this.eventDAO = eventDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_C");
    if (user == null || !user.getRoles().contains("User")) {
      resp.sendRedirect("schedule_in");
      return;
    }
    List<Event> events = new ArrayList<>();
    try {
      events = eventDAO.getPokemonEvents();
    } catch (Exception e) {
      events = new ArrayList<>();
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

      output = mapper.writeValueAsString(days);



    PrintWriter out = resp.getWriter();
    resp.setHeader("Content-Type", "application/json;charset=UTF-8");
    resp.setCharacterEncoding("UTF-8");
    out.print(output);
    out.flush();
  }
}





