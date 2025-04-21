package com.beck.beck_demos.schedule_app.controllers;


import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/upload_events")
@MultipartConfig(

    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
/****************** <br/>
 Create the Servlet For uploading a tsv of events for adding to The  Event table
 doGet to view the upload bo
 doPost to process the file
 Created By Jonathan Beck 4/8/2025 <br/>
 **************  */
public class AddEventFromFile extends HttpServlet {
  private static final String UPLOAD_DIR = "uploads";
  private iEventDAO eventDAO;

  @Override
  public void init() throws ServletException {
    eventDAO = new EventDAO();
  }

  public void init(iEventDAO eventDAO) throws ServletException {
    this.eventDAO = eventDAO;
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String Role = "User";

    User user = (User) session.getAttribute("User_C");
    if (user == null || !user.getRoles().contains("User")) {
      resp.sendRedirect("schedule_in");
      return;
    }
    session.setAttribute("currentPage", req.getRequestURL());
    req.setAttribute("pageTitle", "Budget Home");
    req.getRequestDispatcher("WEB-INF/Schedule_App/upload_events.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_C");
    if (user == null || !user.getRoles().contains("User")) {
      resp.sendRedirect("schedule_in");
      return;
    }
    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = applicationPath + UPLOAD_DIR;
    File fileSaveDir = new File(uploadFilePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }
    Map<String, String> results = new HashMap<>();
    String fileName = "";
    Part filePart = req.getPart("upload_events");
    Collection<Part> x = req.getParts();
    int y = 0;
    if (filePart != null) {

      fileName = filePart.getSubmittedFileName();
      File checkFile = new File(uploadFilePath + File.separator + fileName);
      if (checkFile.exists()) {
        int zz = 0;
        checkFile.delete();
      }
    } else {
      results.put("FileEmptyError", "File is empty");
    }
    try {
      for (Part part : req.getParts()) {
        part.write(uploadFilePath + File.separator + fileName);

      }
    } catch (Exception ex) {
      results.put("dbStatus", ex.getMessage());
      req.setAttribute("results", results);
      req.setAttribute("pageTitle", "Upload a file ");
      req.getRequestDispatcher("WEB-INF/Schedule_App/upload_events.jsp").forward(req, resp);
      return;
    }
    File uploadedFile = new File(uploadFilePath + File.separator + fileName);
    List<Event> events = null;

    try {
      events = eventDAO.getEventsFromFile(uploadedFile);
    } catch (Exception e) {
      results.put("dbError", e.getMessage());
      session.setAttribute("currentPage", req.getRequestURL());
      req.setAttribute("pageTitle", "Schedule Home");
      req.getRequestDispatcher("WEB-INF/Schedule_App/upload_events.jsp").forward(req, resp);

      return;
    }
    int NewEvents = 0;
    int oldEvents = 0;
    int totalEvents = events.size();

    try {
      NewEvents = eventDAO.addBatch(events);
    } catch (Exception e) {
      results.put("dbStatus", e.getMessage());
    }

    oldEvents = totalEvents - NewEvents;

    uploadedFile.delete();
    results.put("AddedCount", "You uploaded " + totalEvents + " events. " + NewEvents + " of them were new. " + oldEvents + " of them were old, and not added to the database.");
    session.setAttribute("results", results);

    resp.sendRedirect("schedule_home");

  }
}
