package com.beck.beck_demos.schedule_app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import com.beck.beck_demos.schedule_app.data_fakes.EventDAO_Fake;
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

class EditEventServletTest  {
  private static final String PAGE="WEB-INF/WFTDA_debug/Edit_Event.jsp";
  EditEventServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException, ParseException {

    servlet = new EditEventServlet();
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
  public void TestLoggedInUserGets200OnDoGetWithValidID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    String Event_ID= "stSawjFeLaTErGYxLOrrixdXgGONQmJhFhxm";
    request.setParameter("eventid",Event_ID);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Test That a logged in user gets a 200 status on doPost </p>
   */
  @Test
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
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
   <p> Tests That the user will received a 302 status on doPost if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
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
   <p> Test that a user in the wrong role will get a 302 on a doPost </p>
   */
  @Test
  public void TestWrongRoleGets302onDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "schedule_in";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a logged in user is able to retreive a specific one of the Event objects. </p>
   */
  @Test
  public void testGetOneEventGetsOneEvent_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);

    String Event_ID= "stSawjFeLaTErGYxLOrrixdXgGONQmJhFhxm";
    request.setParameter("eventid",Event_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Event event = (Event) session.getAttribute("event");
    assertNotNull(event);
    assertEquals(Event_ID,event.getEvent_ID());
  }

  /**
   <p> Test that getting one Event can fail. </p>
   */
  @Test
  public void testGetOneEventCanFail() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);

    String Event_ID= "stSawjFeLaTErGYxLOrrixdXgGONQmJhFhdd";
    request.setParameter("eventid",Event_ID);

    request.setSession(session);
    servlet.doGet(request,response);
    Event event = (Event) session.getAttribute("event");
    int status = response.getStatus();
    String redirectLocation = response.getRedirectedUrl();
    assertNull(event);
    assertEquals(302, status);
    assertEquals(redirectLocation,"all-events");

  }

  /**
   <p> Test that we are able to update Event objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Event
    Event event = new Event();
    event.setEvent_ID("stSawjFeLaTErGYxLOrrixdXgGONQmJhFhxm");
    event.setName("testEvent");
    event.setDate(new Date());
    event.setLocation("testEvent");
    event.setDescription("testEvent");
    event.setLength(23.4d);
    event.setDecision("Skipping");
    event.setPaid("Yes");
    session.setAttribute("event",event);
//create a new albums parameters
    request.setParameter("inputeventEvent_ID","stSawjFeLaTErGYxLOrrixdXgGONQmJhFhxm");
    request.setParameter("inputeventName","TestValue");
    request.setParameter("inputeventDate_Time","12-31-06 06:21:22");
    request.setParameter("inputeventLocation","TestValue");
    request.setParameter("inputeventDescription","TestValue");
    request.setParameter("inputeventLength","243.6");
    request.setParameter("inputeventUnit","days");
    request.setParameter("inputeventDecision","Skipping");
    request.setParameter("inputeventPaid","Yes");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Event_Updated);
    assertEquals("Event updated",Event_Updated);
    assertNotEquals("",Event_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingEvent objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_IDError = results.get("eventEvent_IDerror");
    String NameError = results.get("eventNameerror");
    String Date_TimeError = results.get("eventDate_Timeerror");
    String LocationError = results.get("eventLocationerror");
    String DescriptionError = results.get("eventDescriptionerror");
    String LengthError = results.get("eventLengtherror");
    String DecisionError = results.get("eventDecisionerror");
    String PaidError = results.get("eventPaiderror");
    assertNotEquals("",Event_IDError);
    assertNotNull(Event_IDError);
    assertNotEquals("",NameError);
    assertNotNull(NameError);
    assertNotEquals("",Date_TimeError);
    assertNotNull(Date_TimeError);
    assertNotEquals("",LocationError);
    assertNotNull(LocationError);
    assertNotEquals("",DescriptionError);
    assertNotNull(DescriptionError);
    assertNotEquals("",LengthError);
    assertNotNull(LengthError);
    assertNotEquals("",DecisionError);
    assertNotNull(DecisionError);
    assertNotEquals("",PaidError);
    assertNotNull(PaidError);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditEventServlet();
    servlet.init();
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update Event objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Event
    Event event = new Event();
    event.setEvent_ID("stSawjFeLaTErGYxLOrrixdXgG6NQmJhFhxm");
    event.setName("DUPLICATE");
    event.setDate(new Date());
    event.setLocation("DUPLICATE");
    event.setDescription("DUPLICATE");
    event.setLength( 23.42d);
    event.setDecision("Skipping");
    event.setPaid("No");
    session.setAttribute("event",event);
//create a new albums parameters
    request.setParameter("inputeventEvent_ID","stSawjFeLa5ErGYxLOrrixdXgGONQmJhFhxm");
    request.setParameter("inputeventName","DUPLICATE");
    request.setParameter("inputeventDate_Time","12-31-06 06:21:22");
    request.setParameter("inputeventLocation","DUPLICATE");
    request.setParameter("inputeventDescription","DUPLICATE");
    request.setParameter("inputeventLength","243.6");
    request.setParameter("inputeventUnit","days");
    request.setParameter("inputeventDecision","Skipping");
    request.setParameter("inputeventPaid","Yes");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Event_Updated);
    assertEquals("Event Not Updated",Event_Updated);
    assertNotEquals("",Event_Updated);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Event
    Event event = new Event();
    event.setEvent_ID("EXCEPTION");
    event.setName("EXCEPTION");
    event.setLocation("EXCEPTION");
    event.setDescription("EXCEPTION");
    event.setDecision("Going");
    event.setPaid("Yes");
    session.setAttribute("event",event);
//create a new albums parameters
    request.setParameter("inputeventEvent_ID","EXCEPTION");
    request.setParameter("inputeventName","EXCEPTION");
    request.setParameter("inputeventLocation","EXCEPTION");
    request.setParameter("inputeventDescription","EXCEPTION");
    request.setParameter("inputeventDecision","Skipping");
    request.setParameter("inputeventPaid","Yes");

    request.setParameter("inputeventDate_Time","2024-05-04 12:22");
    request.setParameter("inputeventLength","23.4d");
    request.setParameter("inputeventUnit","days");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Event_Updated);
    assertNotEquals("",Event_Updated);
    assertEquals("Event Not Updated",Event_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

