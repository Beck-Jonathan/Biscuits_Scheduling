package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.data.PaletteDAO;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.iData.iPaletteDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****************** <br/>
 Create the Servlet  For deleting from The  Event table
 doPost to delete an event
 Created By Jonathan Beck 4/9/2025 <br/>
 **************  */
@WebServlet("/deletePalette")
public class DeletePaletteServlet extends HttpServlet {
  private iPaletteDAO paletteDAO;
  @Override
  public void init(){
    paletteDAO = new PaletteDAO();
  }
  public void init(iPaletteDAO paletteDAO){
    this.paletteDAO = paletteDAO;
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
      resp.setStatus(200);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write("-3");
      writer.flush();
      writer.close();
      return;
    }
    String PaletteID = req.getParameter("paletteID");
    Integer result = 0;
    try{
      result = paletteDAO.deletePalette(PaletteID);
      if (result == 0){
        result=-1;
      }
    }
    catch(Exception ex){
      result=-2;
    }
      resp.setStatus(200);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write(result.toString());
      writer.flush();
      writer.close();
  }
}
