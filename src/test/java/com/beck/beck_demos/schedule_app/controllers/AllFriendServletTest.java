package com.beck.beck_demos.schedule_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.schedule_app.data_fakes.FriendDAO_Fake;
import com.beck.beck_demos.schedule_app.data_fakes.UserDAO_Fake;
import com.beck.beck_demos.schedule_app.models.Friend;
import com.beck.beck_demos.schedule_app.models.Friend_VM;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class AllFriendServletTest {
  private static final String PAGE="WEB-INF/schedule_app/All_Friend_Line.jsp";
  AllFriendServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;


  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AllFriendServlet();
    servlet.init(new FriendDAO_Fake());
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
    String desired_redirect = "/schedule_app_in";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "/schedule_app_in";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a logged in user is able to retrieve all of the Friend_Line objects. </p>
   */
  @Test
  public void testLoggedInUserGetsAllFriend_Lines() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Friend_VM> friend_lines = (List<Friend_VM>) request.getAttribute("Friend_Lines");
    assertNotNull(friend_lines);
    assertEquals(20,friend_lines.size());
  }

  /**
   <p> Test that getting all Friend_Line can filter. </p>
   */
  @Test
  public void testLoggedInUserCanFilterFriend_LinesByUser_One() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    String User_One= null;
    request.setParameter("User_One",User_One);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Friend_VM> friend_lines = (List<Friend_VM>) request.getAttribute("Friend_Lines");
    assertNotNull(friend_lines);
    assertEquals(20,friend_lines.size());
  }

  /**
   <p> Test that getting all Friend_Line can filter. </p>
   */
  @Test
  public void testLoggedInUserCanFilterFriend_LinesByUser_Two() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    String User_Two= null;
    request.setParameter("User_Two",User_Two);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Friend_VM> friend_lines = (List<Friend_VM>) request.getAttribute("Friend_Lines");
    assertNotNull(friend_lines);
    assertEquals(20,friend_lines.size());
  }

  /**
   <p> Test that getting all Friend_Line can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearch() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    String searchTerm = "";
    request.setParameter("search",searchTerm);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Friend_VM> friend_lines = (List<Friend_VM>) request.getAttribute("Friend_Lines");
    assertNotNull(friend_lines);
    assertEquals(20,friend_lines.size());
  }

  /**
   <p> Test that getting all Friend_Line can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearchAndReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    String searchTerm = "";
    request.setParameter("search",searchTerm);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Friend_VM> friend_lines = (List<Friend_VM>) request.getAttribute("Friend_Lines");
    assertNotNull(friend_lines);
    assertEquals(0,friend_lines.size());
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AllFriendServlet();
    servlet.init();
  }

}







