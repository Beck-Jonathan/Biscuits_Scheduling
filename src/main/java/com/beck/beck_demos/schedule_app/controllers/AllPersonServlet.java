package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.PersonDAO;
import com.beck.beck_demos.schedule_app.iData.iPersonDAO;
import com.beck.beck_demos.schedule_app.models.Person;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/all-Persons")
public class AllPersonServlet  extends HttpServlet {private iPersonDAO personDAO;
  @Override
  public void init()  {
    personDAO = new PersonDAO();
  }
  public void init(iPersonDAO personDAO){
    this.personDAO = personDAO;
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_C");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("schedule_in");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    List<Person> persons = null;
    try {
      persons =personDAO.getAllPerson();
    } catch (Exception e) {
      persons = new ArrayList<>();
    }
    req.setAttribute("Persons", persons);
    req.setAttribute("pageTitle", "All Persons");
    req.getRequestDispatcher("WEB-INF/WFTDA_debug/all-Persons.jsp").forward(req,resp);

  }
}