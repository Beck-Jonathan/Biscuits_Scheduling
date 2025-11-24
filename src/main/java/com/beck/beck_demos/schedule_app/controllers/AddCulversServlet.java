package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.CulversDAO;
import com.beck.beck_demos.schedule_app.models.Culvers;
import com.beck.beck_demos.schedule_app.models.User;
import com.beck.beck_demos.schedule_app.iData.iCulversDAO;
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
/******************
 Create the Servlet  For adding to The  Culvers table
 Created By Jonathan Beck 11/21/2025
 ***************/
/**
 * Servlet implementation class AddCulversServlet
 *
 * <p>This servlet handles the addition of new {@link Culvers} entries in the application.
 * It is mapped to the URL pattern <code>/addCulvers</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the form for adding a new Culvers. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for adding a new Culvers. Validates input,
 * attempts insertion into the database via {@link CulversDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful insertions redirect to the list of all Culvers.</p
 > * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 11/21/2025</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.schedule_app.models.Culvers
 * @see com.beck.beck_demos.schedule_app.data.CulversDAO
 * @see jakarta.servlet.http.HttpServlet
 */

@WebServlet("/addCulvers")
public class AddCulversServlet extends HttpServlet{
  private iCulversDAO culversDAO;
  @Override
  public void init() {
    culversDAO = new CulversDAO();

  }
  public void init(iCulversDAO culversDAO){
    this.culversDAO = culversDAO;
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
      session.invalidate();
      resp.sendRedirect("schedule_in");
      return;
    }



    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Culvers");
    req.getRequestDispatcher("WEB-INF/Schedule_App/AddCulvers.jsp").forward(req, resp);
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
      session.invalidate();
      resp.sendRedirect("schedule_in");
      return;
    }


    String _Name = req.getParameter("inputculversName");
    if (_Name!=null) {
      _Name=_Name.trim();
    }
    String _WebAddress = req.getParameter("inputculversWebAddress");
    if (_WebAddress!=null) {
      _WebAddress=_WebAddress.trim();
    }
    String _Is_Active = req.getParameter("inputculversIs_Active");
    if (_Is_Active!=null) {
      _Is_Active=_Is_Active.trim();
    }
    Map<String, String> results = new HashMap<>();


    results.put("Name",_Name);
    results.put("WebAddress",_WebAddress);
    results.put("Is_Active",_Is_Active);
    Culvers culvers = new Culvers();
    int errors =0;
    try {
      culvers.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("culversUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      culvers.setName(_Name);
    } catch(Exception e) {results.put("culversNameerror", e.getMessage());
      errors++;
    }
    try {
      culvers.setWebAddress(_WebAddress);
    } catch(Exception e) {results.put("culversWebAddresserror", e.getMessage());
      errors++;
    }
    try {
      culvers.setIs_Active(Boolean.parseBoolean(_Is_Active));
    } catch (Exception e) {results.put("culversIs_Activeerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=culversDAO.add(culvers);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Culvers Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Culvers");
        return;
      } else {
        results.put("dbStatus","Culvers Not Added");

      }
    }

    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add Culvers");
    req.getRequestDispatcher("WEB-INF/Schedule_App/AddCulvers.jsp").forward(req, resp);

  }
}

