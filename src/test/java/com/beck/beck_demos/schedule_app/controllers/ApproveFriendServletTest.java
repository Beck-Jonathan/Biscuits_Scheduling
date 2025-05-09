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

class ApproveFriendServletTest {
  private static final String PAGE="WEB-INF/schedule_app/Delete_Person_Event_Line.jsp";
  ApproveFriendServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;


  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new ApproveFriendServlet();
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
    user.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
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
   <p> Test that the deactivation servlet can deactivate a Friend_Line </p>
   */
  @Test
  public void TestApproveCanApprove() throws ServletException, IOException {
    User user = new User();
    user.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    request.setAttribute("mode","approve");

    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("friend_lineid","3f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    request.setParameter("mode","approve");
    servlet.doGet(request,response);
    String jsonString = response.getContentAsString();
    assertEquals(1,jsonString);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
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
   <p> Test that the deactivate servlet will fail if the Friend_Line is already insactive. </p>
   */
  @Test
  public void TestActivateCanFailIfAlreadyActive() throws ServletException, IOException {
    User user = new User();
    user.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Friend_Lineid","3f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    request.setParameter("mode","0");
    servlet.doGet(request,response);
    String status = response.getContentAsString();
    assertEquals(0,status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestDeActivateCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    user.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Friend_Lineid","5f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    request.setParameter("mode","0");
    servlet.doGet(request,response);
    String status = response.getContentAsString();

    assertEquals(0,status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestActivateCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    user.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Friend_Lineid","xxxxxxxxxxxxx");
    request.setParameter("mode","1");
    servlet.doGet(request,response);
    String status = response.getContentAsString();

    assertEquals(0,status);
  }

  /**
   <p> Test that the activation servlet can active a Friend_Line </p>
   */
  @Test
  public void TestactivateCanActivate() throws ServletException, IOException {
    User user = new User();
    user.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Friend_Lineid","3f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    request.setParameter("mode","1");
    servlet.doGet(request,response);
    String status = response.getContentAsString();
    assertEquals(1,status);
  }

  /**
   <p> Test that the activation servlet can fail if the Friend_Line is already active </p>
   */
  @Test
  public void TestActivateFailIfAlreadyTrue() throws ServletException, IOException {
    User user = new User();
    user.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    List<String> roles = new ArrayList<>();
    roles.add("User");

    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Friend_Lineid","2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    request.setParameter("mode","1");
    servlet.doGet(request,response);
    String status = response.getContentAsString();
    assertEquals(0,status);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new ApproveFriendServlet();
    servlet.init();
  }

}

