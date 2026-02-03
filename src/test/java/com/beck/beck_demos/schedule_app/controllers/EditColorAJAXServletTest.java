package com.beck.beck_demos.schedule_app.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import static org.junit.jupiter.api.Assertions.*;

class EditColorAJAXServletTest {
  private static final String PAGE="WEB-INF/schedule_app/Edit_Palette.jsp";
  EditColorAJAXServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws Exception {

    servlet = new EditColorAJAXServlet();
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

  @Test
  public void testSituation1ProducesResultOfNeg1() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    //valid user and in the user rule (-1)
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
    request.setParameter("paletteid","");
    request.setParameter("index","1");
    //not null (-5) and valid color (-6) parameter
    request.setParameter("inputpaletteColor","");
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = "-1";
    assertEquals(expected,result);
  }
  @Test
  public void testSituation1AProducesResultOfNeg1() throws ServletException, IOException {

    //request.setSession(session);
    //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
    //request.setParameter("paletteid","");
    //request.setParameter("index","1");
    //not null (-5) and valid color (-6) parameter
    //request.setParameter("inputpaletteColor","");
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = "-1";
    assertEquals(expected,result);
  }
  @Test
  public void testSituation2ProducesResultOfNeg2() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    //valid user and in the user rule (-1)
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
    request.setParameter("paletteid","");
    request.setParameter("index","a");
    //not null (-5) and valid color (-6) parameter
    request.setParameter("inputpaletteColor","");
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = "-2";
    assertEquals(expected,result);

  }
  @Test
  public void testSituation3ProducesResultOfNeg3() throws IOException, ServletException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    //valid user and in the user rule (-1)
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
    request.setParameter("paletteid","");
    request.setParameter("index","1");
    //not null (-5) and valid color (-6) parameter
    request.setParameter("inputpaletteColor","");
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = "-3";
    assertEquals(expected,result);

  }
  @Test
  public void testSituation4ProducesResultOfNeg4() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    //valid user and in the user rule (-1)
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
    request.setParameter("paletteid","7b836119-493a-4b44-90a4-53207008d0f8");
    request.setParameter("index","1");
    //not null (-5) and valid color (-6) parameter
    request.setParameter("inputpaletteColor","");
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = "-4";
    assertEquals(expected,result);

  }
  @Test
  public void testSituation5ProducesResultOfNeg5() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    //valid user and in the user rule (-1)
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
    request.setParameter("paletteid","a3cdc15c-3d75-4ec0-a3a9-bf38a7e5b3b6");
    request.setParameter("index","1");
    //not null (-5) and valid color (-6) parameter
    //request.setParameter("inputpaletteColor","");
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = "-5";
    assertEquals(expected,result);

  }
  @Test
  public void testSituation6ProducesResultOfNeg6() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    //valid user and in the user rule (-1)
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
    request.setParameter("paletteid","a3cdc15c-3d75-4ec0-a3a9-bf38a7e5b3b6");
    request.setParameter("index","1");
    //not null (-5) and valid color (-6) parameter
    request.setParameter("inputpaletteColor","#GGGGG");
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = "-6";
    assertEquals(expected,result);

  }
  @Test
  public void testSituation7ProducesResultOfNeg7() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    //valid user and in the user rule (-1)
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
    request.setParameter("paletteid","a3cdc15c-3d75-4ec0-a3a9-bf38a7e5b3b6");
    request.setParameter("index","-1");
    //not null (-5) and valid color (-6) parameter
    request.setParameter("inputpaletteColor","#AAAAAA");
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = "-7";
    assertEquals(expected,result);

  }
  @Test
  public void testSituation8ProducesResultOfNeg8() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    //valid user and in the user rule (-1)
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_C",user);
    request.setSession(session);
    //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
    request.setParameter("paletteid","a3cdc15c-3d75-4ec0-a3a9-bf38a7e5b3b6");
    request.setParameter("index","1");
    //not null (-5) and valid color (-6) parameter
    request.setParameter("inputpaletteColor","#CCCCCC");
    servlet.doPost(request,response);
    String result = response.getContentAsString();
    String expected = "-8";
    assertEquals(expected,result);
  }
  @Test
  public void testSituation1ProducesResultOfPos1ForColor1() throws Exception {
    teardown();
    for (Integer i=1;i<13;i++) {
      setup();
      User user = new User();
      List<String> roles = new ArrayList<>();
      //valid user and in the user rule (-1)
      roles.add("User");
      user.setRoles(roles);
      session.setAttribute("User_C", user);
      request.setSession(session);
      //valid index as int (-2) valid palette id (-3), and existing palette id (-4) and index between 1 and 12 (-7)
      request.setParameter("paletteid", "a3cdc15c-3d75-4ec0-a3a9-bf38a7e5b3b6");
      request.setParameter("index", i.toString());
      //not null (-5) and valid color (-6) parameter
      request.setParameter("inputpaletteColor", "#AAAAAA");
      servlet.doPost(request, response);
      String result = response.getContentAsString();
      String expected = "1";
      assertEquals(expected, result);
      teardown();
    }
  }




  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditColorAJAXServlet();
    servlet.init();
  }



}