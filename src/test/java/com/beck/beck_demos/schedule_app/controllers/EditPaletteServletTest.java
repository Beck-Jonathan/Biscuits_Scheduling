package com.beck.beck_demos.schedule_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.schedule_app.data_fakes.PaletteDAO_Fake;
import com.beck.beck_demos.schedule_app.models.Color;
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

public class EditPaletteServletTest {
  private static final String PAGE="WEB-INF/schedule_app/Edit_Palette.jsp";
  EditPaletteServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws Exception {

    servlet = new EditPaletteServlet();
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
  public void TestLoggedInUserGets302OnDoGetWithNoPaletteSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("6b20c381-1946-4b33-9812-6d052dd1d9ef");
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  /**
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestLoggedInUserGets200OnDoGetWithPaletteSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("6b20c381-1946-4b33-9812-6d052dd1d9ef");
    session.setAttribute("User_C",user);
    request.setParameter("paletteid","KRkEnjcWgqHdPwmnnnWZnLVVwDEAFNPCarPf");
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Test That a logged in user gets a 200 status on doPost </p>
   */
  @Test
  public void TestLoggedInUserGets302nDoPostWithNoPaletteSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
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
   <p> Test that a logged in user is able to retrieve a specific one of the Palette objects. </p>
   */
  @Test
  public void testGetOnePaletteGetsOnePalette_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);

    String Palette_ID= "KRkEnjcWgqHdPwmnnnWZnLVVwDEAFNPCarPf";
    request.setParameter("paletteid",Palette_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Palette palette = (Palette) session.getAttribute("palette");
    assertNotNull(palette);
    assertEquals(Palette_ID,palette.getPalette_ID());
  }

  /**
   <p> Test that getting one Palette can fail. </p>
   */
  @Test
  public void testGetOnePaletteCanFailAndUserIsRedirected() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    String Palette_ID= null;
    request.setParameter("paletteid",Palette_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Palette palette = (Palette) session.getAttribute("palette");
    assertNull(palette);
    assertEquals(302,response.getStatus());
  }

  /**
   <p> Test that we are able to update Palette objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws Exception {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Palette
    Palette palette = new Palette();
    palette.setPalette_ID("KRkEnjcWgqHdPwmnnnWZnLVVwDEAFNPCarPf");
    palette.setUser_ID("6b20c381-1946-4b33-9812-6d052dd1d9ef");
    palette.setLineNo(43);
    palette.setColor1(new Color("#A0B0C4"));
    palette.setColor2(new Color("#7F8A9D"));
    palette.setColor3(new Color("#D94A3C"));
    palette.setColor4(new Color("#3CB371"));
    palette.setColor5(new Color("#556B2F"));
    palette.setColor6(new Color("#4682B4"));
    palette.setColor7(new Color("#C71585"));
    palette.setColor8(new Color("#FF8C00"));
    palette.setColor9(new Color("#2F4F4F"));
    palette.setColor10(new Color("#8A2BE2"));
    palette.setColor11(new Color("#BC8F8F"));
    palette.setColor12(new Color("#20B2AA"));
    session.setAttribute("palette",palette);
//create a new Palettes parameters

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
    String Palette_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Palette_Updated);
    assertEquals("Palette updated",Palette_Updated);
    assertNotEquals("",Palette_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingPalette objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    Palette palette = new Palette();
    session.setAttribute("palette",palette);
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
   <p> Test that a duplicate primary key will get caught when trying to update Palette objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws Exception {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Palette
    Palette palette = new Palette();
    palette.setPalette_ID("DUPLICATE");
    palette.setUser_ID("DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    palette.setLineNo(43);
    palette.setColor1(new Color("#A0B0C4"));
    palette.setColor2(new Color("#7F8A9D"));
    palette.setColor3(new Color("#D94A3C"));
    palette.setColor4(new Color("#3CB371"));
    palette.setColor5(new Color("#556B2F"));
    palette.setColor6(new Color("#4682B4"));
    palette.setColor7(new Color("#C71585"));
    palette.setColor8(new Color("#FF8C00"));
    palette.setColor9(new Color("#2F4F4F"));
    palette.setColor10(new Color("#8A2BE2"));
    palette.setColor11(new Color("#BC8F8F"));
    palette.setColor12(new Color("#20B2AA"));
    session.setAttribute("palette",palette);
//create a new Palette parameters
    request.setParameter("inputpalettePalette_ID","DUPLICATE");
    request.setParameter("inputpaletteUser_ID","DUPLICATE");
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
    String Palette_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Palette_Updated);
    assertEquals("Palette Not Updated",Palette_Updated);
    assertNotEquals("",Palette_Updated);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLException() throws Exception {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Palette
    Palette palette = new Palette();
    palette.setPalette_ID("EXCEPTION");
    palette.setUser_ID("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    palette.setLineNo(43);
    palette.setColor1(new Color("#A0B0C4"));
    palette.setColor2(new Color("#7F8A9D"));
    palette.setColor3(new Color("#D94A3C"));
    palette.setColor4(new Color("#3CB371"));
    palette.setColor5(new Color("#556B2F"));
    palette.setColor6(new Color("#4682B4"));
    palette.setColor7(new Color("#C71585"));
    palette.setColor8(new Color("#FF8C00"));
    palette.setColor9(new Color("#2F4F4F"));
    palette.setColor10(new Color("#8A2BE2"));
    palette.setColor11(new Color("#BC8F8F"));
    palette.setColor12(new Color("#20B2AA"));
    session.setAttribute("palette",palette);
//create a new Palettes parameters
    request.setParameter("inputpalettePalette_ID","EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    request.setParameter("inputpaletteUser_ID","EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
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
    String Palette_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Palette_Updated);
    assertNotEquals("",Palette_Updated);
    assertEquals("Palette Not Updated",Palette_Updated);
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
    servlet = new EditPaletteServlet();
    servlet.init();
  }

}

