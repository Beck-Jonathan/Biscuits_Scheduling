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

public class ActivateCulversServletTest {
  private static final String PAGE="WEB-INF/schedule_app/Delete_Culvers.jsp";
  ActivateCulversServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new ActivateCulversServlet();
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
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="-1";
    assertEquals(reply,expected);
  }

  /**
   <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(200,status);

    session = request.getSession(false);
    assertNull(session);
    String reply = response.getContentAsString();
    String expected="-1";
    assertEquals(reply,expected);
  }

  /**
   <p> Test that the deactivation servlet can deactivate a Culvers </p>
   */
  @Test
  public void TestDeactivateCanDeactivate() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Culversid","OYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="1";
    assertEquals(reply,expected);
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
    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="-1";
    assertEquals(reply,expected);
  }

  /**
   <p> Test that the deactivate servlet will fail if the Culvers is already insactive. </p>
   */
  @Test
  public void TestDeactivateFailIfAlreadyFalse() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Culversid","PGhUNdrYfXITqxbBmyEIcOWgtSXuPMhiuSpg");
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="0";
    assertEquals(reply,expected);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestDeActivateCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Culversid","xxxxxxxxxxxxx");
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="-2";
    assertEquals(reply,expected);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestActivateCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Culversid","xxxxxxxxxxxxx");
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="-2";
    assertEquals(reply,expected);
  }

  /**
   <p> Test that the activation servlet can active a Culvers </p>
   */
  @Test
  public void TestactivateCanActivate() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Culversid","PGhUNdrYfXITqxbBmyEIcOWgtSXuPMhiuSpg");
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="1";
    assertEquals(reply,expected);
  }

  /**
   <p> Test that the activation servlet can fail if the Culvers is already active </p>
   */
  @Test
  public void TestActivateFailIfAlreadyTrue() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("Culversid","OYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    request.setParameter("mode","1");
    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="0";
    assertEquals(reply,expected);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new ActivateCulversServlet();
    servlet.init();
  }

}

