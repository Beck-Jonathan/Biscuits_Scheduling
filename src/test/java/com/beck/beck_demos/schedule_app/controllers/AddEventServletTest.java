package com.beck.beck_demos.schedule_app.controllers;

import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.beck.beck_demos.schedule_app.data_fakes.EventDAO_Fake;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.Event_VM;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.json.Json;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;
import org.jsoup.*;
import org.jsoup.select.Elements;


class AddEventServletTest {

  private static final String PAGE="WEB-INF/WFTDA_debug/Add_Event.jsp";
  AddEventServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException, ParseException {

    servlet = new AddEventServlet();
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
   <p> Test that error messages are sent for each field for addingEvent objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    String NameError = results.get("eventNameerror");
    String DateError = results.get("eventDateerror");
    String LocationError = results.get("eventLocationerror");
    String descriptionError = results.get("eventdescriptionerror");
    String LengthError = results.get("eventLengtherror");
    String DescisionError = results.get("eventDescisionerror");
    String PaidError = results.get("eventPaiderror");
    assertNotEquals("",NameError);
    assertNotNull(NameError);
    assertNotEquals("",DateError);
    assertNotNull(DateError);
    assertNotEquals("",LocationError);
    assertNotNull(LocationError);
    assertNotEquals("",descriptionError);
    assertNotNull(descriptionError);
    assertNotEquals("",LengthError);
    assertNotNull(LengthError);
    assertNotEquals("",DescisionError);
    assertNotNull(DescisionError);
    assertNotEquals("",PaidError);
    assertNotNull(PaidError);
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
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("inputeventName","TestValue");
    request.setParameter("inputeventDate","12-31-06 06:21:22");
    request.setParameter("inputeventLocation","TestValue");
    request.setParameter("inputeventdescription","TestValue");
    request.setParameter("inputeventLength","243.6");
    request.setParameter("inputeventUnit","days");
    request.setParameter("inputeventDescision","Skipping");
    request.setParameter("inputeventPaid","Yes");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Event_Added);
    assertEquals("Event Added",Event_Added);
    assertNotEquals("",Event_Added);
  }
  @Test
  public void TestAddCanAddRecurringDailyWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("inputeventName","TestValue");
    request.setParameter("inputeventDate","12-31-06 06:21:22");
    request.setParameter("inputeventLocation","TestValue");
    request.setParameter("inputeventdescription","TestValue");
    request.setParameter("inputeventLength","243.6");
    request.setParameter("inputeventUnit","days");
    request.setParameter("inputeventDescision","Skipping");
    request.setParameter("inputeventPaid","Yes");
    request.setParameter("inputEventRecur","day");
    request.setParameter("inputEventRecur","4");
    request.setParameter("inputEventPeriod","day");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Event_Added);
    assertEquals("4 Events Added",Event_Added);
    assertNotEquals("",Event_Added);
  }
  @Test
  public void TestAddCanAddRecurringWeeklyWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("inputeventName","TestValue");
    request.setParameter("inputeventDate","12-31-06 06:21:22");
    request.setParameter("inputeventLocation","TestValue");
    request.setParameter("inputeventdescription","TestValue");
    request.setParameter("inputeventLength","243.6");
    request.setParameter("inputeventUnit","days");
    request.setParameter("inputeventDescision","Skipping");
    request.setParameter("inputeventPaid","Yes");
    request.setParameter("inputEventRecur","day");
    request.setParameter("inputEventRecur","12");
    request.setParameter("inputEventPeriod","week");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Event_Added);
    assertEquals("12 Events Added",Event_Added);
    assertNotEquals("",Event_Added);
  }
  @Test
  public void TestAddCanAddRecurringMonthlyWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("inputeventName","TestValue");
    request.setParameter("inputeventDate","12-31-06 06:21:22");
    request.setParameter("inputeventLocation","TestValue");
    request.setParameter("inputeventdescription","TestValue");
    request.setParameter("inputeventLength","243.6");
    request.setParameter("inputeventUnit","days");
    request.setParameter("inputeventDescision","Skipping");
    request.setParameter("inputeventPaid","Yes");
    request.setParameter("inputEventRecur","5");
    request.setParameter("inputEventPeriod","month");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Event_Added);
    assertEquals("5 Events Added",Event_Added);
    assertNotEquals("",Event_Added);
  }
  @Test
  public void TestAddCanAddWRecurringYearlyWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("inputeventName","TestValue");
    request.setParameter("inputeventDate","12-31-06 06:21:22");
    request.setParameter("inputeventLocation","TestValue");
    request.setParameter("inputeventdescription","TestValue");
    request.setParameter("inputeventLength","243.6");
    request.setParameter("inputeventUnit","days");
    request.setParameter("inputeventDescision","Skipping");
    request.setParameter("inputeventPaid","Yes");
    request.setParameter("inputEventRecur","6");
    request.setParameter("inputEventPeriod","year");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Event_Added);
    assertEquals("6 Events Added",Event_Added);
    assertNotEquals("",Event_Added);
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
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("inputeventName","EXCEPTION");
    request.setParameter("inputeventDate","12-31-06 06:21:22");
    request.setParameter("inputeventLocation","TestValue");
    request.setParameter("inputeventdescription","TestValue");
    request.setParameter("inputeventLength","243.6");
    request.setParameter("inputeventUnit","days");
    request.setParameter("inputeventDescision","Skipping");
    request.setParameter("inputeventPaid","Yes");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Event_Added);
    assertEquals("Event Not Added",Event_Added);
    assertNotEquals("",Event_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test that Event objects with duplicate primary keys don't get added, and proper error handling exists. </p>
   */
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    request.setParameter("inputeventName","DUPLICATE");
    request.setParameter("inputeventDate","12-31-06 06:21:22");
    request.setParameter("inputeventLocation","TestValue");
    request.setParameter("inputeventdescription","TestValue");
    request.setParameter("inputeventLength","243.6");
    request.setParameter("inputeventUnit","days");
    request.setParameter("inputeventDescision","Skipping");
    request.setParameter("inputeventPaid","Yes");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Event_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Event_Added);
    assertEquals("Event Not Added",Event_Added);
    assertNotEquals("",Event_Added);
  }
  @Test
  public void testCulversNonSense() throws IOException {
    List<Event> flavors = new ArrayList<>();
    String uril ="https://www.google.com";
    List<String> locations = new ArrayList<>();
    locations.add("hiawatha");
    locations.add("marion");
    locations.add("cedar-rapids");
    String result = "xx";
    for(String location : locations) {
      uril = "https://www.culvers.com/restaurants/" + location + "?tab=next";
      try {
        try (Scanner scanner = new Scanner(new URL(uril).openStream(),
            StandardCharsets.UTF_8.toString())) {
          scanner.useDelimiter("\\A");
          result = scanner.next();
          int dataStart = result.indexOf("NEXT_DATA");
          int resultLength = result.length();

          String anchors = result.substring(dataStart);
          int endScriptIndex = anchors.indexOf("</script>");
          String anchors2 = anchors.substring(37, endScriptIndex);
          JSONObject overall = new JSONObject(anchors2);
          Iterator<String> overallKeys = overall.keys();
          JSONObject props = overall.getJSONObject("props");
          Iterator<String> propKeys = props.keys();
          JSONObject pageProps = props.getJSONObject("pageProps");
          Iterator<String> pagePropKeys = pageProps.keys();
          JSONObject _page = pageProps.getJSONObject("page");
          Iterator<String> _pageKeys = _page.keys();
          JSONObject customData = _page.getJSONObject("customData");
          Iterator<String> customDataKeys = customData.keys();
          JSONObject restaurantCalendar = customData.getJSONObject("restaurantCalendar");
          Iterator<String> restaurantCalendarKeys = restaurantCalendar.keys();
          JSONArray _flavors = restaurantCalendar.getJSONArray("flavors");
          for (Object o : _flavors) {
            JSONObject flavor = (JSONObject) o;
            String date = flavor.getString("onDate");
            date = date.substring(0, date.indexOf('T'));
            String Name = "Culvers in " + anchors2 + ".";
            SimpleDateFormat Simple = new SimpleDateFormat("yyyy-MM-dd");
            Date Date_Time = Simple.parse(date);
            ;
            String Description = flavor.getString("title");
            Double Length = 1d;
            String Decision = "Maybe";
            String Paid = "No";
            Event _event = new Event("", Name, Date_Time, location, Description, Length, Decision, Paid);

            flavors.add(_event);

          }
        } catch (Exception e) {
          result = "error";
        }

        System.out.println(result);

      }catch (Exception e){

      }
    }
    int zzz=0;
    }



  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddEventServlet();
    servlet.init();
  }

}



