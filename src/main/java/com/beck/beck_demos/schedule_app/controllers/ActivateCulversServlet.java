package com.beck.beck_demos.schedule_app.controllers;

/******************
 Create the Servlet For Deleteing from the Culvers table
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Servlet implementation class AddCulversServlet
 *
 * <p>This servlet handles the deletion of  {@link Culvers} entries in the application.
 * It is mapped to the URL pattern <code>/deleteCulvers</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: handles the request for deleting aCulvers via {@link CulversDAO}. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p> * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
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
@WebServlet("/deleteculvers")
public class ActivateCulversServlet extends HttpServlet {
  private iCulversDAO culversDAO;
  @Override
  public void init(){
    culversDAO = new CulversDAO();

  }
  public void init(iCulversDAO culversDAO){
    this.culversDAO = culversDAO;

  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_C");
    int errors = 0;
    if (user==null||!user.getRoles().contains("User")){
      session.invalidate();
      errors++;
    }


    String CulversID = req.getParameter("Culversid");
    int mode = -1;

    try {
      mode = Integer.parseInt(req.getParameter("mode"));
    } catch (NumberFormatException e) {
      errors ++;
    }

    Integer result = -1;
    if (errors==0) {
      if (mode == 0) {
        try {
          result = culversDAO.deleteCulvers(CulversID);
        } catch (Exception ex) {
          result =-2;
        }
      } else if (mode == 1) {
        try {
          result = culversDAO.undeleteCulvers(CulversID);
        } catch (Exception ex) {
          result =-2;
        }
      }
    }

      resp.setStatus(200);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write(result.toString());
      writer.flush();
      writer.close();
      return;
  }
}

