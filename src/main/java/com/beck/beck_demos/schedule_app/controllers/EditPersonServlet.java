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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/******************
 Create the Servlet Viuw/Edit from the Person table
 Created By Jonathan Beck 4/9/2025
 ***************/

@WebServlet("/editPerson")
public class EditPersonServlet  extends HttpServlet{
  private iPersonDAO personDAO;
  @Override
  public void init() {
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

    String mode = req.getParameter("mode");
    String primaryKey = "";
    try{
      primaryKey = req.getParameter("personid");
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    Person person= new Person();
    try{
      person.setPerson_ID(primaryKey);
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
    }
    try{
      person=personDAO.getPersonByPrimaryKey(person);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    session.setAttribute("person",person);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Person");
    req.getRequestDispatcher("WEB-INF/WFTDA_debug/EditPerson.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
//to get the old Person
    Person _oldPerson= (Person)session.getAttribute("person");
//to get the new event's info
    String _Person_ID = req.getParameter("inputpersonPerson_ID");
    if (_Person_ID!=null){
      _Person_ID=_Person_ID.trim();
    }
    String _First_Name = req.getParameter("inputpersonFirst_Name");
    if (_First_Name!=null){
      _First_Name=_First_Name.trim();
    }
    String _Last_Name = req.getParameter("inputpersonLast_Name");
    if (_Last_Name!=null){
      _Last_Name=_Last_Name.trim();
    }
    String _Description = req.getParameter("inputpersonDescription");
    if (_Description!=null){
      _Description=_Description.trim();
    }
    results.put("Person_ID",_Person_ID);
    results.put("First_Name",_First_Name);
    results.put("Last_Name",_Last_Name);
    results.put("Description",_Description);
    Person _newPerson = new Person();
    int errors =0;
    try {
      _newPerson.setPerson_ID(_Person_ID);
    } catch(Exception e) {results.put("personPerson_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newPerson.setFirst_Name(_First_Name);
    } catch(Exception e) {results.put("personFirst_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _newPerson.setLast_Name(_Last_Name);
    } catch(Exception e) {results.put("personLast_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _newPerson.setDescription(_Description);
    } catch(Exception e) {results.put("personDescriptionerror", e.getMessage());
      errors++;
    }
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=personDAO.update(_oldPerson,_newPerson);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Person updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Persons");
        return;
      } else {
        results.put("dbStatus","Person Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Person ");
    req.getRequestDispatcher("WEB-INF/WFTDA_debug/EditPerson.jsp").forward(req, resp);
  }
}

