package com.beck.beck_demos.schedule_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.schedule_app.data_fakes.CulversDAO_Fake;
import com.beck.beck_demos.schedule_app.models.Culvers;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class AllCulversServletTest {

  private static final String PAGE="WEB-INF/schedule_app/All_Culvers.jsp";
  AllCulversServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AllCulversServlet();
    servlet.init(new CulversDAO_Fake());
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
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
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
    session = request.getSession(false);
    assertNull(session);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
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
   <p> Test that getting all Culvers can filter. </p>
   */
  @Test
  public void testLoggedInUserCanFilterCulverssByUser_ID() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);

    servlet.doGet(request,response);
    List<Culvers> culverss = (List<Culvers>) request.getAttribute("Culverss");
    assertNotNull(culverss);
    assertEquals(5,culverss.size());
  }

  /**
   <p> Test that getting all Culvers can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearch() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);


    String searchTerm = "Iowa";
    request.setParameter("search",searchTerm);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Culvers> culverss = (List<Culvers>) request.getAttribute("Culverss");
    assertNotNull(culverss);
    assertEquals(3,culverss.size());
  }

  /**
   <p> Test that getting all Culvers can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearchAndReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);


    String searchTerm = "bananananana";
    request.setParameter("search",searchTerm);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Culvers> culverss = (List<Culvers>) request.getAttribute("Culverss");
    assertNotNull(culverss);
    assertEquals(0,culverss.size());
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AllCulversServlet();
    servlet.init();
  }

}