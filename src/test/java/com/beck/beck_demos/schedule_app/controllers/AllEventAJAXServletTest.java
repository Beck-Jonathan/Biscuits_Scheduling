package com.beck.beck_demos.schedule_app.controllers;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;


import com.beck.beck_demos.schedule_app.data_fakes.EventDAO_Fake;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.Event_VM;
import com.beck.beck_demos.schedule_app.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class AllEventAJAXServletTest {

  private static final String PAGE="WEB-INF/WFTDA_debug/All_Event.jsp";
  AllEventAJAXServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException, ParseException {

    servlet = new AllEventAJAXServlet();
    servlet.init(new EventDAO_Fake());
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
    rd = new MockRequestDispatcher(PAGE);
  }


  /**
   <p> tear down by setting all variablges to null. </p>
   */
  @AfterEach
  public void teardown(){
    servlet=null;
    request=null;
    response=null;
    session=null;
    rd=null;
  }

  /**
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "schedule_in";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "schedule_in";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a logged in user is able to retreive all of the Event objects. </p>
   */
  @Test
  public void testLoggedInUserGetsAllEvents() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonString);
    ObjectMapper mapper = new ObjectMapper();
    List<CalendarDay> days = new ArrayList<>();
    String calendarDay = "";
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        calendarDay = jsonArray.get(i).toString();
        CalendarDay day = mapper.readValue(calendarDay, CalendarDay.class);
        days.add(day);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    int result_count = 0;
    for (CalendarDay day : days) {
      result_count += day.getEvents().size();
    }
   assertEquals(4,result_count);
  }
  /**
   <p> Test that a logged in user is able to retreive all of the Event objects. </p>
   */
  @Test
  public void testLoggedInUserCanFilterByYear() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);

    String cal_year = "2025";

    request.setParameter("year",cal_year);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonString);
    ObjectMapper mapper = new ObjectMapper();
    List<CalendarDay> days = new ArrayList<>();
    String calendarDay = "";
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        calendarDay = jsonArray.get(i).toString();
        CalendarDay day = mapper.readValue(calendarDay, CalendarDay.class);
        days.add(day);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    int result_count = 0;
    for (CalendarDay day : days) {
      result_count += day.getEvents().size();
    }
    assertEquals(3,result_count);
  }
  /**
   <p> Test that a logged in user is able to retreive all of the Event objects. </p>
   */
  @Test
  public void testLoggedInUserCanFilterByMonth() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);

    String cal_month = "04";
    String cal_year = "2025";
    request.setParameter("month",cal_month);
    request.setParameter("year",cal_year);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonString);
    ObjectMapper mapper = new ObjectMapper();
    List<CalendarDay> days = new ArrayList<>();
    String calendarDay = "";
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        calendarDay = jsonArray.get(i).toString();
        CalendarDay day = mapper.readValue(calendarDay, CalendarDay.class);
        days.add(day);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    int result_count = 0;
    for (CalendarDay day : days) {
      result_count += day.getEvents().size();
    }
    assertEquals(2,result_count);
  }
  /**
   <p> Test that a logged in user is able to retreive all of the Event objects. </p>
   */
  @Test
  public void testLoggedInUserCanFilterByDay() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    String cal_day = "5";
    String cal_month = "4";
    String cal_year = "2025";
    request.setParameter("day",cal_day);
    request.setParameter("month",cal_month);
    request.setParameter("year",cal_year);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonString);
    ObjectMapper mapper = new ObjectMapper();
    List<CalendarDay> days = new ArrayList<>();
    String calendarDay = "";
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        calendarDay = jsonArray.get(i).toString();
        CalendarDay day = mapper.readValue(calendarDay, CalendarDay.class);
        days.add(day);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    int result_count = 0;
    for (CalendarDay day : days) {
      result_count += day.getEvents().size();
    }
    assertEquals(1,result_count);
  }
  /**
   <p> Test that a logged in user is able to retreive all of the Event objects. </p>
   */
  @Test
  public void testLoggedInUserCanHandleEmptyResult() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    String cal_day = "5";
    String cal_month = "10";
    String cal_year = "2026";
    request.setParameter("day",cal_day);
    request.setParameter("month",cal_month);
    request.setParameter("year",cal_year);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonString);
    ObjectMapper mapper = new ObjectMapper();
    List<CalendarDay> days = new ArrayList<>();
    String calendarDay = "";
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        calendarDay = jsonArray.get(i).toString();
        CalendarDay day = mapper.readValue(calendarDay, CalendarDay.class);
        days.add(day);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    int result_count = 0;
    for (CalendarDay day : days) {
      result_count += day.getEvents().size();
    }
    assertEquals(0,result_count);
  }

  /**
   <p> Test that a logged in user is able to retreive all of the Event objects. </p>
   */
  @Test
  public void testInvalidValuesCauseEmptyResult() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    String cal_day = "big";
    String cal_month = "big";
    String cal_year = "big";
    request.setParameter("day",cal_day);
    request.setParameter("month",cal_month);
    request.setParameter("year",cal_year);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    assertEquals("Invalid Search",jsonString);
  }

  @Test
  public void testLoggedInUserCanFilterByDayAndSearchTerm() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    String cal_day = "5";
    String cal_month = "4";
    String cal_year = "2025";
    String search_Term = "call";
    request.setParameter("day",cal_day);
    request.setParameter("month",cal_month);
    request.setParameter("year",cal_year);
    request.setParameter("search",search_Term);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonString);
    ObjectMapper mapper = new ObjectMapper();
    List<CalendarDay> days = new ArrayList<>();
    String calendarDay = "";
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        calendarDay = jsonArray.get(i).toString();
        CalendarDay day = mapper.readValue(calendarDay, CalendarDay.class);
        days.add(day);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    int result_count = 0;
    for (CalendarDay day : days) {
      result_count += day.getEvents().size();
    }
    assertEquals(1,result_count);
  }
  @Test
  public void testLoggedInUserCanFilterBySearchTerm() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    String search_Term = "call";

    request.setParameter("search",search_Term);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonString);
    ObjectMapper mapper = new ObjectMapper();
    List<CalendarDay> days = new ArrayList<>();
    String calendarDay = "";
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        calendarDay = jsonArray.get(i).toString();
        CalendarDay day = mapper.readValue(calendarDay, CalendarDay.class);
        days.add(day);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    int result_count = 0;
    for (CalendarDay day : days) {
      result_count += day.getEvents().size();
    }
    assertEquals(2,result_count);
  }
  @Test
  public void TestSearchTermTooLongIsRedirected() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    String search_Term = "cafdkajfklsdajfkdsfasdfsafdsfldfsdafsadfdsafdsafsdafdsafsdafsdadsadfdsafsdafdsafsdafdsajfkldsajdksaslfdkfsdkldlfdslfdsdldsldskdsldslsfdsafdsll";

    request.setParameter("search",search_Term);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    assertEquals("Invalid Search",jsonString);
  }
  @Test
  public void TestSearchTermTooShortIsRedirected() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    String search_Term = "d";

    request.setParameter("search",search_Term);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    assertEquals("Invalid Search",jsonString);
  }
  @Test
  public void TestNoResultsForSearchTermIsHandledGracefully() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    String search_Term = "football";

    request.setParameter("search",search_Term);
    request.setSession(session);
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonString);
    ObjectMapper mapper = new ObjectMapper();
    List<CalendarDay> days = new ArrayList<>();
    String calendarDay = "";
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        calendarDay = jsonArray.get(i).toString();
        CalendarDay day = mapper.readValue(calendarDay, CalendarDay.class);
        days.add(day);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    int result_count = 0;
    for (CalendarDay day : days) {
      result_count += day.getEvents().size();
    }
    assertEquals(0,result_count);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AllEventAJAXServlet();
    servlet.init();
  }

}

