package com.beck.beck_demos.schedule_app.controllers;



/******************
 Create the Servlet  For  signing in
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
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 <p> A servlet to manage sign in.  <br/>
 doGet to display  the login page <br/>
 doPost to validate and log in the user</p>
 */
@WebServlet("/schedule_in")
public class UserSignInServlet extends HttpServlet{

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
    HttpSession session = req.getSession();
    Object currentPageObject = session.getAttribute("currentPage");
    String currentPage = "budget_home";
    if (currentPageObject !=null) {
      currentPage = session.getAttribute("currentPage").toString();
    }
    req.setAttribute("pageTitle", "Log in!");
    User user = (User)session.getAttribute("User_C");

    if (user!=null&&user.getRoles().contains("User")){
      resp.sendRedirect(currentPage);
      return;
    }

    req.getRequestDispatcher("WEB-INF/Schedule_App/UserSignIn.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    Map<String, String> results = new HashMap<>();
    Object currentPageObject = session.getAttribute("currentPage");
    String currentPage = "schedule_home";
    if (currentPageObject !=null) {
      currentPage = session.getAttribute("currentPage").toString();
    }
    req.setAttribute("pageTitle", "Log in!");
    User _user = (User)session.getAttribute("User_C");
    if (_user!=null){
      resp.sendRedirect(currentPage);
      return;
    }
    req.setAttribute("pageTitle", "Log in!");


    String _User_Name = req.getParameter("inputuserUser_Name");
    if (_User_Name!=null) {
      _User_Name=_User_Name.trim();
    }
    String _User_PW = req.getParameter("inputuserUser_PW");
    if (_User_PW!=null) {
      _User_PW=_User_PW.trim();
    }
    char[] _User_PWChar = new char[0];
    if (_User_PW!=null && !_User_PW.isEmpty()) {
       _User_PWChar = _User_PW.toCharArray();

    }
    User user = new User();
    int errors =0;
    try {
      user.setUser_Name(_User_Name);
    } catch(Exception e) {
      results.put("userUser_Nameerror", e.getMessage());
      errors++;
    }
    try {
      user.setUser_PW(_User_PW.toCharArray());
    } catch(Exception e) {
      results.put("userUser_PWerror", e.getMessage());
      errors++;
    }


    results.put("User_Name",_User_Name);
    results.put("User_PW",_User_PW);


    String id ="";
    try {
      String hashed = userDAO.get_pw(_User_Name);
      if(!BCrypt.checkpw(_User_PW,hashed)){
        results.put("loginFail", "Login Failed, please verify your username and password");
      }
      else{
        session.removeAttribute("loginFail");
        id=userDAO.getUserIDByUserName(_User_Name);
        user.setUser_ID(id);
        user=userDAO.getUserByPrimaryKey(user);
        user.setUser_PW(null);
        List<String> roles = userDAO.getUser_Roles(user);
        user.setRoles(roles);
        results.put("dbStatus",user.getEmail());

        session.setAttribute("User_C",user);

        currentPage =  session.getAttribute("currentPage").toString();

        resp.sendRedirect(currentPage);


        return;
      }

    }
    catch (Exception ex){
      results.put("loginFail", "Login Failed, please verify your username and password");
      results.put("dbError", "Database Error");
    }

    if (id.equals("")){
      results.put("dbStatus1","Login Failed, please verify your username and password");
    }
    session.setAttribute("dbStatus","Login Fail!");
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Log in! ");
    resp.sendRedirect(currentPage);
    //req.getRequestDispatcher("WEB-INF/Budget_App/Login.jsp").forward(req, resp);

  }
}

