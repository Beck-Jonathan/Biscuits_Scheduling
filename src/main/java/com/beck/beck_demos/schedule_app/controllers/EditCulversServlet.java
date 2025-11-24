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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet Viuw/Edit from the Culvers table
 Created By Jonathan Beck 11/21/2025
 ***************/
/**
 * Servlet implementation class EditCulversServlet
 *
 * <p>This servlet handles the editing of {@link Culvers} entries in the application.
 * It is mapped to the URL pattern <code>/editCulvers</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing a single Culverss. Allows editing of appropriate fields. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for editing a Culvers. Validates input,
 * attempts update of the the database via {@link CulversDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful updates redirect to the list of all Culvers.</p
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

@WebServlet("/editCulvers")
public class EditCulversServlet extends HttpServlet{
  private iCulversDAO culversDAO;
  @Override
  public void init()  {
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

    String mode = req.getParameter("mode");
    String primaryKey = "";
    try{
      primaryKey = req.getParameter("culversid");
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    Culvers culvers= new Culvers();
    try{
      culvers.setCulvers_ID(primaryKey);
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
      resp.sendRedirect("all-culverss");
      return;
    }
    try{
      culvers=culversDAO.getCulversByPrimaryKey(culvers);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
      culvers= null;
    }
    if (culvers==null || culvers.getCulvers_ID()==null){
      resp.sendRedirect("all-Culverss");
      return;
    }
    session.setAttribute("culvers",culvers);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Culvers");

    req.getRequestDispatcher("WEB-INF/Schedule_App/EditCulvers.jsp").forward(req, resp);
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

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
    List<User> allUsers = null;


//to get the old Culvers
    Culvers _oldCulvers= (Culvers)session.getAttribute("culvers");
    if (_oldCulvers==null){
      resp.sendRedirect("all-culverss");
      return;
    }
//to get the new Culvers's info

    String _Name = req.getParameter("inputculversName");
    if (_Name!=null){
      _Name=_Name.trim();
    }
    String _WebAddress = req.getParameter("inputculversWebAddress");
    if (_WebAddress!=null){
      _WebAddress=_WebAddress.trim();
    }
    String _Is_Active = req.getParameter("inputculversIs_Active");
    if (_Is_Active!=null){
      _Is_Active=_Is_Active.trim();
    }

    results.put("Name",_Name);
    results.put("WebAddress",_WebAddress);
    results.put("Is_Active",_Is_Active);
    Culvers _newCulvers = new Culvers();
    int errors =0;

    try {
      _newCulvers.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("culversUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newCulvers.setName(_Name);
    } catch(Exception e) {results.put("culversNameerror", e.getMessage());
      errors++;
    }
    try {
      _newCulvers.setWebAddress(_WebAddress);
    } catch(Exception e) {results.put("culversWebAddresserror", e.getMessage());
      errors++;
    }
    try {
      _newCulvers.setIs_Active(Boolean.parseBoolean(_Is_Active));
    } catch(Exception e) {results.put("culversIs_Activeerror", e.getMessage());
      errors++;
    }
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=culversDAO.update(_oldCulvers,_newCulvers);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Culvers updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Culvers");
        return;
      } else {
        results.put("dbStatus","Culvers Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit Culvers");
    req.getRequestDispatcher("WEB-INF/Schedule_App/EditCulvers.jsp").forward(req, resp);
  }
}

