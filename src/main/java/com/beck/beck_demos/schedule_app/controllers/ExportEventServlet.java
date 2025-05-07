package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Event table
 Created By Jonathan Beck 4/24/2025
 ***************/

@WebServlet("/exportEvent")
public class ExportEventServlet extends HttpServlet {
  private iEventDAO eventDAO;
  private static final String UPLOAD_DIR = "uploads";
  private ServletFileUpload uploader = null;
  @Override
  public void init() throws ServletException {
    eventDAO = new EventDAO();
    DiskFileItemFactory fileFactory = new DiskFileItemFactory();
    File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
    fileFactory.setRepository(filesDir);
    this.uploader = new ServletFileUpload(fileFactory);
  }
  public void init(iEventDAO eventDAO) {
    this.eventDAO = eventDAO;
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

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

    String filename = "output_"+user.getUser_Name()+"_Event"+".txt";
    String full_file = uploadFilePath + File.separator + filename;
    String mode = req.getParameter("mode");
    session.setAttribute("currentPage",req.getRequestURL());
    List<Event> events = null;
    boolean error = false;
    CalendarDay day = new CalendarDay();
    try {
      events = eventDAO.getAllEvent(day,"", user.getUser_ID());
    } catch (SQLException e) {
      error = true;
    }
    try {
      if (mode.equals("export")){
        eventDAO.writeEventToFile(events, full_file);
      }
      if (mode.equals("SQL")){
        eventDAO.writeEventToSQLInsert(events, full_file);
      }
    } catch (Exception e) {
      error = true;
    }
    resp.setContentType("APPLICATION/OCTET-STREAM");
    resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
    java.io.FileInputStream fileInputStream = new java.io.FileInputStream(uploadFilePath +File.separator+ filename);
    int i;
    while ((i=fileInputStream.read()) != -1) {
      resp.getOutputStream().write(i);
    }
    fileInputStream.close();
    File delFile = new File(uploadFilePath+File.separator+filename);
    delFile.delete();
  }
}

