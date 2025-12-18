package com.beck.beck_demos.schedule_app.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import com.beck.beck_demos.schedule_app.data_fakes.EventDAO_Fake;
import com.beck.beck_demos.schedule_app.data_fakes.PaletteDAO_Fake;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.Event_VM;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class AllEventServletTest {

  private static final String PAGE="WEB-INF/WFTDA_debug/All_Event.jsp";
  AllEventServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws Exception {

    servlet = new AllEventServlet();
    servlet.init(new EventDAO_Fake(), new PaletteDAO_Fake());
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
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestSettingViewtoCalendarGetsUsCalendarView() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    request.setParameter("view","calendar");
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    String pageTitle= (String) request.getAttribute("pageTitle");
    assertEquals("All Events - Calendar",pageTitle);
    assertEquals(200,status);
  }

  /**
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestNotSettingViewtoCalendarGetsUsListView() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    String pageTitle= (String) request.getAttribute("pageTitle");
    assertEquals("All Events - List",pageTitle);
    assertEquals(200,status);
  }
  /**
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestNotSettingViewtoArbitraryValueGetsUsListView() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    request.setParameter("view","dfasfd");
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    String pageTitle= (String) request.getAttribute("pageTitle");
    assertEquals("All Events - List",pageTitle);
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
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(4,events.size());
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
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(3,events.size());
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

    String cal_month = "4";
    String cal_year = "2025";

    request.setParameter("month",cal_month);
    request.setParameter("year",cal_year);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(2,events.size());
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
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(1,events.size());
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
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(0,events.size());
  }

  @Test
  public void TestBadValuesReturnEmptyArray() throws ServletException, IOException{
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
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(0,events.size());
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AllEventServlet();
    servlet.init();
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
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(1,events.size());
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
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(2,events.size());
  }
  @Test
  public void TestSearchTermTooLongIsRedirected() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    String search_Term = "cafdkajfklsdajfkldsadfdsafsdafdsafsdafdsajfkldsajdksaslfdkfsdklddfdsafsdafsdafdsafdsfdsfdsafdsafdslfdslfdsdldsldskdsldslsfdsafdsll";

    request.setParameter("search",search_Term);
    request.setSession(session);
    servlet.doGet(request,response);
    HashMap<String,String> results = (HashMap<String, String>) request.getAttribute("results");
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    String searchError=results.get("searchError");
    assertNotNull(events);
    assertEquals(0,events.size());
    assertEquals("Invalid search term",searchError);
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
    HashMap<String,String> results = (HashMap<String, String>) request.getAttribute("results");
    String searchError=results.get("searchError");
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(0,events.size());
    assertEquals("Invalid search term",searchError);
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
    int responseCode = response.getStatus();
    List<Event_VM> events = (List<Event_VM>) request.getAttribute("Events");
    assertNotNull(events);
    assertEquals(0,events.size());
    assertEquals(200,responseCode);
  }


}

