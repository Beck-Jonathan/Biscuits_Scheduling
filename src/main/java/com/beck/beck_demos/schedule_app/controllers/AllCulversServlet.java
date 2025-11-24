package com.beck.beck_demos.schedule_app.controllers;

/******************
 Create the Servlet  For Viewing all of the  Culvers table
 Created By Jonathan Beck 11/21/2025
 ***************/

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
/**
 * Servlet implementation class AllCulversServlet
 *
 * <p>This servlet handles the viewing of all {@link Culvers} entries in the application.
 * It is mapped to the URL pattern <code>/all-Culverss</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing all Culverss from the database via {@link CulversDAO}. Allows earching and filtering by foreign keys. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>*
 * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
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
@WebServlet("/all-Culvers")
public class AllCulversServlet extends HttpServlet {private iCulversDAO culversDAO;
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

    int errors = 0;
    HashMap<String,String> results = new HashMap<>();

    int culvers_count=0;
    int page_number=1;
    int page_size = 20;
    try {
      page_number = Integer.parseInt(req.getParameter("page"));
    } catch (Exception e){
      page_number=1;
    }
    session.setAttribute("culvers_page_number",page_number);
    int offset=(page_number-1)*(page_size);
    String search_term = req.getParameter("search");
    if (search_term==null){
      search_term ="";
    }
    if (!search_term.equals("") && (search_term.length()<2||search_term.length()>100)){
      errors++;
      results.put("searchError","Invalid search term");
    }
    session.setAttribute("currentPage",req.getRequestURL());
    List<Culvers> culverss = null;
    try {

      culvers_count = culversDAO.getCulversCount(search_term,user.getUser_ID());
      culverss =culversDAO.getAllCulvers(offset,page_size,search_term, user.getUser_ID());
    } catch (Exception e) {
      culverss = new ArrayList<>();
    }
    int total_pages = (culvers_count/page_size)+1;
    req.setAttribute("noOfPages", total_pages);
    req.setAttribute("currentPage", page_number);req.setAttribute("Culverss", culverss);
    req.setAttribute("pageTitle", "All Culvers");
    req.getRequestDispatcher("WEB-INF/Schedule_App/all-Culvers.jsp").forward(req,resp);

  }
}
