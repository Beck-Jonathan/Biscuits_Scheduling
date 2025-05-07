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

class AddFriendServletTest {
  private static final String PAGE="WEB-INF/schedule_app/Add_Friend_Line.jsp";
  AddFriendServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AddFriendServlet();
    servlet.init(new FriendDAO_Fake(),new UserDAO_Fake());
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
    user.setUser_ID("72017ae6-968a-4bbd-904b-5c902ed9c072");
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
   <p> Test That a logged in user gets a 200 status on doPost </p>
   */
  @Test
  public void TestLoggedInUserGets200nDoPostWithNoFriend_LineSet() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("72017ae6-968a-4bbd-904b-5c902ed9c072");
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
    user.setUser_ID("72017ae6-968a-4bbd-904b-5c902ed9c072");
    List<String> roles = new ArrayList<>();
    roles.add("Wrong Role");
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
    user.setUser_ID("72017ae6-968a-4bbd-904b-5c902ed9c072");
    List<String> roles = new ArrayList<>();
    roles.add("Wrong Role");
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
   <p> Test that error messages are sent for each field for addingFriend_Line objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("72017ae6-968a-4bbd-904b-5c902ed9c072");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_OneError = results.get("friend_lineUser_Oneerror");

    assertNotEquals("",User_OneError);
    assertNotNull(User_OneError);

    assertEquals(200,responseStatus);
  }

  /**
   <p> Tests That We can add to the database if all input fields are validated  </p>
   */
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("72017ae6-968a-4bbd-904b-5c902ed9c072");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setParameter("inputfriend_lineUser_One","test@gmail.com");

    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Friend_Line_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Friend_Line_Added);
    assertEquals("Friend_Line Added",Friend_Line_Added);
    assertNotEquals("",Friend_Line_Added);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("72017ae6-968a-4bbd-904b-5c902ed9c072");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setParameter("inputfriend_lineUser_One","exceptionEmail@gmail.com");

    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Friend_Line_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Friend_Line_Added);
    assertEquals("Friend_Line Not Added",Friend_Line_Added);
    assertNotEquals("",Friend_Line_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test that Friend_Line objects with duplicate primary keys don't get added, and proper error handling exists. </p>
   */
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("72017ae6-968a-4bbd-904b-5c902ed9c072");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("inputfriend_lineUser_One","duplicateEmail@gmail.com");

    servlet.doPost(request,response);
    request.setSession(session);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Friend_Line_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Friend_Line_Added);
    assertEquals("Friend_Line Not Added",Friend_Line_Added);
    assertNotEquals("",Friend_Line_Added);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddFriendServlet();
    servlet.init();
  }

}
