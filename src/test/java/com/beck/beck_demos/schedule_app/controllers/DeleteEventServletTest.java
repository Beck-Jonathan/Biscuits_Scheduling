package com.beck.beck_demos.schedule_app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import com.beck.beck_demos.schedule_app.data_fakes.EventDAO_Fake;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 <p> A servlet that takes in a Event UUID and deletes it from the database.
 DoPost Only.</p>
 */
class DeleteEventServletTest {

  private static final String PAGE="WEB-INF/WFTDA_debug/Delete_Event.jsp";
  DeleteEventServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException, ParseException {

    servlet = new DeleteEventServlet();
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
   <p> Test that the deactivation servlet can deactivate a Event </p>
   */
  @Test
  public void TestDeleteCanDelete() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("eventid","stSawjFeLaTErGYxLOrrixdXgGONQmJhFhxm");
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(1,status);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
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
   <p> Test that the deactivate servlet will fail if the Event is already insactive. </p>
   */


  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestDeleteCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("eventid","stSawjFeLaTErGYxLOrrixdXgGONQmJhFhxs");
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String dbStatus = results.get("dbStatus");
    assertEquals("Unable To Find Event.",dbStatus);
    assertEquals(0,status);
  }


  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new DeleteEventServlet();
    servlet.init();
  }

}



