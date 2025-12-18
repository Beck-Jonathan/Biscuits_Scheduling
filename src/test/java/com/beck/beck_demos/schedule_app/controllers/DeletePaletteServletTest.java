package com.beck.beck_demos.schedule_app.controllers;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

import com.beck.beck_demos.schedule_app.data_fakes.PaletteDAO_Fake;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeletePaletteServletTest {
  private static final String PAGE="WEB-INF/schedule_app/Edit_ModelSet.jsp";
  DeletePaletteServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws Exception {

    servlet = new DeletePaletteServlet();
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
  public void TestLoggedInUserGets302OnDoPostWithNoPaletteSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    servlet.doPost(request,response);
    PrintWriter writer = response.getWriter();
    String result = response.getContentAsString();
    assertEquals("-2",result);
  }



  /**
   <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    PrintWriter writer = response.getWriter();
    String result = response.getContentAsString();
    assertEquals(result,"-3");
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
    PrintWriter writer = response.getWriter();
    String result = response.getContentAsString();
    assertEquals(result,"-3");
  }





  /**
   <p> Test that getting one ModelSet can fail. </p>
   */
  @Test
  public void testGetOnePaletteCanFail() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    String ModelSet_ID= "araWQqLsjKYWKxRIKKcMqmgVDipmCPYtDKux";
    request.setParameter("modelsetid",ModelSet_ID);

    request.setSession(session);
    servlet.doPost(request,response);
    PrintWriter writer = response.getWriter();
    String result = response.getContentAsString();
    assertEquals(result,"-2");

  }

  /**
   <p> Test that we are able to update ModelSet objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestDeleteCanDeleteWithNoErrors() throws ServletException, IOException, ParseException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("a3cdc15c-3d75-4ec0-a3a9-bf38a7e5b3b6");
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old ModelSet

//create a new albums parameters
    request.setParameter("paletteID","a3cdc15c-3d75-4ec0-a3a9-bf38a7e5b3b6");



    servlet.doPost(request,response);
    PrintWriter writer = response.getWriter();
    String result = response.getContentAsString();
    assertEquals("1",result);
  }



  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new DeletePaletteServlet();
    servlet.init();
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update ModelSet objects. </p>
   */
  @Test
  public void testDeleteCanReturnZero() throws ServletException, IOException, ParseException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("2521deb6-1b42-40d9-8395-7de4c8ee69b9");
    session.setAttribute("User_C",user);
    request.setSession(session);

//create a new palette parameters
    request.setParameter("paletteID","araWQqLsjKYWKxRIKKcMqmgVDipmCPYtDKup");



    servlet.doPost(request,response);
    PrintWriter writer = response.getWriter();
    String result = response.getContentAsString();
    assertEquals("-2",result);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testDeleteCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("2521deb6-1b42-40d9-8395-7de4c8ee69b9");
    session.setAttribute("User_C",user);
    request.setSession(session);
//to set the old ModelSet

//create a new albums parameters
    request.setParameter("paletteID","araWQqLsjKYWKxRIKKcMqmgVDipmCPYtDKup");


    servlet.doPost(request,response);
    PrintWriter writer = response.getWriter();
    String result = response.getContentAsString();
    assertEquals(result,"-2");
  }

}

