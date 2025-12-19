package com.beck.beck_demos.schedule_app.controllers;

/******************
 Create the Servlet  For Viewing all of the  Palette table
 Created By Jonathan Beck 12/17/2025
 ***************/

import com.beck.beck_demos.schedule_app.data.PaletteDAO;
import com.beck.beck_demos.schedule_app.models.Palette;
import com.beck.beck_demos.schedule_app.models.User;
import com.beck.beck_demos.schedule_app.iData.iPaletteDAO;
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
 * Servlet implementation class AllPaletteServlet
 *
 * <p>This servlet handles the viewing of all {@link Palette} entries in the application.
 * It is mapped to the URL pattern <code>/all-Palettes</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing all Palettes from the database via {@link PaletteDAO}. Allows earching and filtering by foreign keys. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>*
 * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 12/17/2025</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.schedule_app.models.Palette
 * @see com.beck.beck_demos.schedule_app.data.PaletteDAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/all-Palettes")
public class AllPaletteServlet extends HttpServlet {
  private iPaletteDAO paletteDAO;

  @Override
  public void init()  {
    paletteDAO = new PaletteDAO();

  }
  public void init(iPaletteDAO paletteDAO){
    this.paletteDAO = paletteDAO;

  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
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



    session.setAttribute("currentPage",req.getRequestURL());
    List<Palette> palettes = null;
    try {

      palettes =paletteDAO.getPalettebyUser(user.getUser_ID());
    } catch (Exception e) {
      palettes = new ArrayList<>();
    }

    req.setAttribute("Palettes", palettes);
    req.setAttribute("pageTitle", "All Palettes");

    req.getRequestDispatcher("WEB-INF/Schedule_App/all-Palettes.jsp").forward(req,resp);

  }
}
