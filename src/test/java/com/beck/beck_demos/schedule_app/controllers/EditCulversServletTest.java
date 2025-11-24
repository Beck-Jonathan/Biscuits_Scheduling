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

public class EditCulversServletTest {
  private static final String PAGE="WEB-INF/schedule_app/Edit_Culvers.jsp";
  EditCulversServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditCulversServlet();
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
  public void TestLoggedInUserGets302OnDoGetWithNoCulversSet() throws ServletException, IOException{
    User user = new User();
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  /**
   <p> Test That a logged in user gets a 200 status on doPost </p>
   */
  @Test
  public void TestLoggedInUserGets302nDoPostWithNoCulversSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
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
    session = request.getSession(false);
    assertNull(session);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
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
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
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
   <p> Test that a logged in user is able to retrieve a specific one of the Culvers objects. </p>
   */
  @Test
  public void testGetOneCulversGetsOneCulvers_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    String Culvers_ID = "OYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG";
    request.setParameter("culversid",Culvers_ID);
    servlet.doGet(request,response);
    Culvers culvers = (Culvers) session.getAttribute("culvers");
    assertNotNull(culvers);
    assertEquals(Culvers_ID,culvers.getCulvers_ID());
  }

  /**
   <p> Test that getting one Culvers can fail. </p>
   */
  @Test
  public void testGetOneCulversCanFailAndUserIsRedirected() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    String Culvers_ID = "";
    request.setParameter("culversid",Culvers_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Culvers culvers = (Culvers) session.getAttribute("culvers");
    assertNull(culvers);
    assertEquals(302,response.getStatus());
  }

  /**
   <p> Test that we are able to update Culvers objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Culvers
    Culvers culvers = new Culvers();
    culvers.setCulvers_ID("OYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    culvers.setUser_ID("3e985ecd-319c-4e44-ab6a-45e755d98c90");
    culvers.setName("testCulvers");
    culvers.setWebAddress("https://www.testCulvers.com");
    culvers.setIs_Active(true);
    session.setAttribute("culvers",culvers);
//create a new Culverss parameters
    request.setParameter("inputculversName","TestValue");
    request.setParameter("inputculversWebAddress","https://www.Testculvers.com");
    request.setParameter("inputculversIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Culvers_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Culvers_Updated);
    assertEquals("Culvers updated",Culvers_Updated);
    assertNotEquals("",Culvers_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingCulvers objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    Culvers culvers = new Culvers();
    session.setAttribute("culvers",culvers);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    String NameError = results.get("culversNameerror");
    String WebAddressError = results.get("culversWebAddresserror");

    assertNotEquals("",NameError);
    assertNotNull(NameError);
    assertNotEquals("",WebAddressError);
    assertNotNull(WebAddressError);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update Culvers objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Culvers
    Culvers culvers = new Culvers();
    culvers.setCulvers_ID("DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    culvers.setUser_ID("DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    culvers.setName("DUPLICATE");
    culvers.setWebAddress("https://www.culvers.com/DUPLICATE");
    culvers.setIs_Active(true);
    session.setAttribute("culvers",culvers);
//create a new Culvers parameters

    request.setParameter("inputculversName","DUPLICATE");
    request.setParameter("inputculversWebAddress","https://www.culvers.com/DUPLICATE");
    request.setParameter("inputculversIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Culvers_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Culvers_Updated);
    assertEquals("Culvers Not Updated",Culvers_Updated);
    assertNotEquals("",Culvers_Updated);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Culvers
    Culvers culvers = new Culvers();
    culvers.setCulvers_ID("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    culvers.setUser_ID("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    culvers.setName("EXCEPTION");
    culvers.setWebAddress("https://www.culvers.com/EXCEPTION");
    culvers.setIs_Active(true);
    session.setAttribute("culvers",culvers);
//create a new Culverss parameters

    request.setParameter("inputculversName","EXCEPTION");
    request.setParameter("inputculversWebAddress","https://www.culvers.com/EXCEPTION");
    request.setParameter("inputculversIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Culvers_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Culvers_Updated);
    assertNotEquals("",Culvers_Updated);
    assertEquals("Culvers Not Updated",Culvers_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditCulversServlet();
    servlet.init();
  }

}

