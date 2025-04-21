package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data_fakes.PersonDAO_Fake;
import com.beck.beck_demos.schedule_app.models.Person;
import com.beck.beck_demos.schedule_app.models.Person_VM;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EditPersonServletTest {
  private static final String PAGE="WEB-INF/WFTDA_debug/Edit_Person.jsp";
  EditPersonServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditPersonServlet();
    servlet.init(new PersonDAO_Fake());
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
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException {
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
   <p> Test that a logged in user is able to retreive a specific one of the Person objects. </p>
   */
  @Test
  public void testGetOnePersonGetsOnePerson_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);

    String Person_ID= "HJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd";
    request.setParameter("personid",Person_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Person person = (Person) session.getAttribute("person");
    assertNotNull(person);
    assertEquals(Person_ID,person.getPerson_ID());
  }

  /**
   <p> Test that getting one Person can fail. </p>
   */
  @Test
  public void testGetOnePersonCanFail() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);

    String Person_ID= null;
    request.setParameter("personid",Person_ID);

    request.setSession(session);
    servlet.doGet(request,response);
    Person person = (Person) session.getAttribute("person");
    assertNull(person.getPerson_ID());
    assertNull(person.getFirst_Name());
    assertNull(person.getLast_Name());
    assertNull(person.getDescription());
  }

  /**
   <p> Test that we are able to update Person objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Person
    Person person = new Person();
    person.setPerson_ID("HJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd");
    person.setFirst_Name("testPerson");
    person.setLast_Name("testPerson");
    person.setDescription("testPerson");
    session.setAttribute("person",person);
//create a new albums parameters
    request.setParameter("inputpersonPerson_ID","HJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd");
    request.setParameter("inputpersonFirst_Name","TestValue");
    request.setParameter("inputpersonLast_Name","TestValue");
    request.setParameter("inputpersonDescription","TestValue");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Person_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Person_Updated);
    assertEquals("Person updated",Person_Updated);
    assertNotEquals("",Person_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingPerson objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Person_IDError = results.get("personPerson_IDerror");
    String First_NameError = results.get("personFirst_Nameerror");
    String Last_NameError = results.get("personLast_Nameerror");
    String DescriptionError = results.get("personDescriptionerror");
    assertNotEquals("",Person_IDError);
    assertNotNull(Person_IDError);
    assertNotEquals("",First_NameError);
    assertNotNull(First_NameError);
    assertNotEquals("",Last_NameError);
    assertNotNull(Last_NameError);
    assertNotEquals("",DescriptionError);
    assertNotNull(DescriptionError);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditPersonServlet();
    servlet.init();
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update Person objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Person
    Person person = new Person();
    person.setPerson_ID("HJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd");
    person.setFirst_Name("DUPLICATE");
    person.setLast_Name("DUPLICATE");
    person.setDescription("DUPLICATE");
    session.setAttribute("person",person);
//create a new albums parameters
    request.setParameter("inputpersonPerson_ID","HJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd");
    request.setParameter("inputpersonFirst_Name","DUPLICATE");
    request.setParameter("inputpersonLast_Name","DUPLICATE");
    request.setParameter("inputpersonDescription","DUPLICATE");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Person_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Person_Updated);
    assertEquals("Person Not Updated",Person_Updated);
    assertNotEquals("",Person_Updated);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old Person
    Person person = new Person();
    person.setPerson_ID("HJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd");
    person.setFirst_Name("EXCEPTION");
    person.setLast_Name("EXCEPTION");
    person.setDescription("EXCEPTION");
    session.setAttribute("person",person);
//create a new albums parameters
    request.setParameter("inputpersonPerson_ID","HJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd");
    request.setParameter("inputpersonFirst_Name","EXCEPTION");
    request.setParameter("inputpersonLast_Name","EXCEPTION");
    request.setParameter("inputpersonDescription","EXCEPTION");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Person_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Person_Updated);
    assertNotEquals("",Person_Updated);
    assertEquals("Person Not Updated",Person_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}
