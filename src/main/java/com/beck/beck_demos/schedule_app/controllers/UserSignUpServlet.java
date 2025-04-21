package com.beck.beck_demos.schedule_app.controllers;



/******************
 Create the Servlet  For adding to The  User table
 Created By Jonathan Beck 4/9/2025

 ***************/
import com.beck.beck_demos.schedule_app.data.*;
import com.beck.beck_demos.schedule_app.iData.iUserDAO;
import com.beck.beck_demos.schedule_app.models.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 <p> A servlet to manage sign up.  <br/>
 doGet to display  the sign up page <br/>
 doPost to validate and register the user</p>
 */

@WebServlet("/schedulewithus")
public class UserSignUpServlet extends HttpServlet{
  private iUserDAO userDAO;

  @Override
  public void init() throws ServletException {
    userDAO = new UserDAO();
  }
  public void init(iUserDAO userDAO){
    this.userDAO = userDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Join Us!");
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_C");
    if (user!=null&&!user.getRoles().contains("User")){
      resp.sendRedirect("/schedule_home");
      return;
    }if (user!=null&&user.getRoles().contains("User")){
      resp.sendRedirect("/schedule_home");
      return;
    }


    req.getRequestDispatcher("WEB-INF/Schedule_App/UserSignUp.jsp").forward(req, resp);  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id;
    req.setAttribute("pageTitle", "Join Us!");
    HttpSession session = req.getSession();
    User _user = (User) session.getAttribute("User_C");
    if (_user!=null&&!_user.getRoles().contains("User")){
      resp.sendRedirect("/schedule_home");
      return;
    }if (_user!=null&&_user.getRoles().contains("User")){
      resp.sendRedirect("/schedule_home");
      return;
    }

    String _User_Name = req.getParameter("inputuserUser_Name");
    String _User_PW = req.getParameter("inputuserUser_PW");
    String _User_PW2 = req.getParameter("inputuserUser_PW2");
    char[] _User_PWChar = new char[0];
    if (_User_PW != null) {
    _User_PWChar = _User_PW.toCharArray();
   }

    String _Email = req.getParameter("inputuserEmail");


    Map<String, String> results = new HashMap<>();
    results.put("User_Name3",_User_Name);
    results.put("User_PW3",_User_PW);
    results.put("User_PW2",_User_PW2);

    results.put("Email",_Email);


    User user = new User();

    int errors =0;

    try {
      user.setUser_Name(_User_Name);
    } catch(Exception e) {
      results.put("userUser_Nameerror3", e.getMessage());
      errors++;
    }
    boolean usernameFree = false;
    try {
      usernameFree = userDAO.usernameFree(_User_Name);
    } catch (Exception e) {
      results.put("dbStatus",e.getMessage());
      errors++;
    }
    if (!usernameFree){
      results.put("userUser_Nameerror3", "This username is already taken.");
      errors++;
    }
    try {
      user.setUser_PW(_User_PWChar);
    } catch(IllegalArgumentException e) {
      results.put("userUser_PWerror3", e.getMessage());
      errors++;
    }
    if (_User_PW2 != null && _User_PW != null) {
      if (!_User_PW.equals(_User_PW2)) {
        results.put("userUser_PW2error", "Passwords do not match");
        errors++;
      }
    }

    try {
      user.setEmail(_Email);
    } catch(IllegalArgumentException e) {
      results.put("userEmailerror", e.getMessage());
      errors++;
    }
    boolean emailFree = true;
    try {
      emailFree = userDAO.emailFree(_Email);
    } catch (Exception e) {
      results.put("dbStatus",e.getMessage());
      errors++;
    }
    if (!emailFree){
      results.put("userEmailerror", "This email  is already taken.");
      errors++;
    }


    int result=0;
    if (errors==0){
      try{
        result=userDAO.add(user);

        session.setAttribute("User_C",user);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","User Added");
        try {
          id  = userDAO.getUserID(_Email);
          userDAO.addRole("User",id);

          results.put("UserID", String.valueOf(id));
          //TwoFA twofa = new TwoFA(id);
          //TwoFADAO.add(twofa);
          //String code = TwoFADAO.getTwoFAById(id);
          //EmailService.send2faCode_Roller(code,_Email);

          user.setUser_ID(id);
          user=userDAO.getUserByPrimaryKey(user);
          user.setUser_PW(null);
          List<String> roles = userDAO.getUser_Roles(user);
          user.setRoles(roles);
          results.put("dbStatus","User Added");
          session.setAttribute("UserID",id);
          session.setAttribute("User_C",user);
          req.setAttribute("results",results);
          resp.sendRedirect("schedule_home");
          return;
        } catch (Exception e) {

          results.put("dbError","User Not Added");
        }
      } else {
        results.put("dbStatus","User Not Added");

      }
    }
    else {
      results.put("dbStatus","User Not Added");
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Budget With Us ");
    req.getRequestDispatcher("WEB-INF/Schedule_App/UserSignUp.jsp").forward(req, resp);


  }
}

