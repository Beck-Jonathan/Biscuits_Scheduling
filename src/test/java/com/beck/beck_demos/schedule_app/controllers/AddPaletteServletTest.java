package com.beck.beck_demos.schedule_app.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.schedule_app.data_fakes.PaletteDAO_Fake;
import com.beck.beck_demos.schedule_app.models.Palette;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddPaletteServletTest {
  private static final String PAGE="WEB-INF/schedule_app/Add_Palette.jsp";
  AddPaletteServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws Exception {

    servlet = new AddPaletteServlet();
    servlet.init(new PaletteDAO_Fake());
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
  public void TestLoggedInUserGets200OnDoPostWithNoFieldsSet() throws ServletException, IOException{
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
   <p> Test that error messages are sent for each field for addingPalette objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("2a0e31fe-2a5f-4e3e-8976-54124577b4ba");

    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");


    String LineNoError = results.get("paletteLineNoerror");
    String Color1Error = results.get("paletteColor1error");
    String Color2Error = results.get("paletteColor2error");
    String Color3Error = results.get("paletteColor3error");
    String Color4Error = results.get("paletteColor4error");
    String Color5Error = results.get("paletteColor5error");
    String Color6Error = results.get("paletteColor6error");
    String Color7Error = results.get("paletteColor7error");
    String Color8Error = results.get("paletteColor8error");
    String Color9Error = results.get("paletteColor9error");
    String Color10Error = results.get("paletteColor10error");
    String Color11Error = results.get("paletteColor11error");
    String Color12Error = results.get("paletteColor12error");

    assertNotEquals("",LineNoError);
    assertNotNull(LineNoError);
    assertNotEquals("",Color1Error);
    assertNotNull(Color1Error);
    assertNotEquals("",Color2Error);
    assertNotNull(Color2Error);
    assertNotEquals("",Color3Error);
    assertNotNull(Color3Error);
    assertNotEquals("",Color4Error);
    assertNotNull(Color4Error);
    assertNotEquals("",Color5Error);
    assertNotNull(Color5Error);
    assertNotEquals("",Color6Error);
    assertNotNull(Color6Error);
    assertNotEquals("",Color7Error);
    assertNotNull(Color7Error);
    assertNotEquals("",Color8Error);
    assertNotNull(Color8Error);
    assertNotEquals("",Color9Error);
    assertNotNull(Color9Error);
    assertNotEquals("",Color10Error);
    assertNotNull(Color10Error);
    assertNotEquals("",Color11Error);
    assertNotNull(Color11Error);
    assertNotEquals("",Color12Error);
    assertNotNull(Color12Error);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Tests That We can add to the database if all input fields are validated  </p>
   */
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("2a0e31fe-2a5f-4e3e-8976-54124577b4ba");
    session.setAttribute("User_C",user);
    request.setSession(session);

    request.setParameter("inputpaletteLineNo","406");
    request.setParameter("inputpaletteColor1",  "#A0B0C4");
    request.setParameter("inputpaletteColor2",  "#7F8A9D");
    request.setParameter("inputpaletteColor3",  "#D94A3C");
    request.setParameter("inputpaletteColor4",  "#3CB371");
    request.setParameter("inputpaletteColor5",  "#556B2F");
    request.setParameter("inputpaletteColor6",  "#4682B4");
    request.setParameter("inputpaletteColor7",  "#C71585");
    request.setParameter("inputpaletteColor8",  "#FF8C00");
    request.setParameter("inputpaletteColor9",  "#2F4F4F");
    request.setParameter("inputpaletteColor10", "#8A2BE2");
    request.setParameter("inputpaletteColor11", "#BC8F8F");
    request.setParameter("inputpaletteColor12", "#20B2AA");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Palette_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Palette_Added);
    assertEquals("Palette Added",Palette_Added);
    assertNotEquals("",Palette_Added);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("2a0e31fe-2a5f-4e3e-8976-54124577b4ba");

    session.setAttribute("User_C",user);
    request.setSession(session);

    request.setParameter("inputpaletteLineNo","406");
    request.setParameter("inputpaletteColor1",  "#CCCCCC");
    request.setParameter("inputpaletteColor2",  "#DDDDDD");
    request.setParameter("inputpaletteColor3",  "#D94A3C");
    request.setParameter("inputpaletteColor4",  "#3CB371");
    request.setParameter("inputpaletteColor5",  "#556B2F");
    request.setParameter("inputpaletteColor6",  "#4682B4");
    request.setParameter("inputpaletteColor7",  "#C71585");
    request.setParameter("inputpaletteColor8",  "#FF8C00");
    request.setParameter("inputpaletteColor9",  "#2F4F4F");
    request.setParameter("inputpaletteColor10", "#8A2BE2");
    request.setParameter("inputpaletteColor11", "#BC8F8F");
    request.setParameter("inputpaletteColor12", "#20B2AA");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Palette_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Palette_Added);
    assertEquals("Palette Not Added",Palette_Added);
    assertNotEquals("",Palette_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test that Palette objects with duplicate primary keys don't get added, and proper error handling exists. </p>
   */
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("2a0e31fe-2a5f-4e3e-8976-54124577b4ba");

    session.setAttribute("User_C",user);
    request.setSession(session);

    request.setParameter("inputpaletteLineNo","406");
    request.setParameter("inputpaletteColor1",  "#AAAAAA");
    request.setParameter("inputpaletteColor2",  "#BBBBBB");
    request.setParameter("inputpaletteColor3",  "#D94A3C");
    request.setParameter("inputpaletteColor4",  "#3CB371");
    request.setParameter("inputpaletteColor5",  "#556B2F");
    request.setParameter("inputpaletteColor6",  "#4682B4");
    request.setParameter("inputpaletteColor7",  "#C71585");
    request.setParameter("inputpaletteColor8",  "#FF8C00");
    request.setParameter("inputpaletteColor9",  "#2F4F4F");
    request.setParameter("inputpaletteColor10", "#8A2BE2");
    request.setParameter("inputpaletteColor11", "#BC8F8F");
    request.setParameter("inputpaletteColor12", "#20B2AA");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Palette_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Palette_Added);
    assertEquals("Palette Not Added",Palette_Added);
    assertNotEquals("",Palette_Added);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddPaletteServlet();
    servlet.init();
  }

}

