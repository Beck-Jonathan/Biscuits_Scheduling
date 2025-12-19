package com.beck.beck_demos.schedule_app.controllers;


import com.beck.beck_demos.schedule_app.data.PaletteDAO;
import com.beck.beck_demos.schedule_app.data.UserDAO;
import com.beck.beck_demos.schedule_app.iData.iUserDAO;
import com.beck.beck_demos.schedule_app.models.Color;
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
/******************
 Create the Servlet  For adding to The  Palette table
 Created By Jonathan Beck 12/17/2025
 ***************/
/**
 * Servlet implementation class AddPaletteServlet
 *
 * <p>This servlet handles the addition of new {@link Palette} entries in the application.
 * It is mapped to the URL pattern <code>/addPalette</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the form for adding a new Palette. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for adding a new Palette. Validates input,
 * attempts insertion into the database via {@link PaletteDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful insertions redirect to the list of all Palette.</p
 > * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
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

@WebServlet("/addPalette")
public class AddPaletteServlet extends HttpServlet{
  private iPaletteDAO paletteDAO;

  @Override
  public void init() {
    paletteDAO = new PaletteDAO();

  }
  public void init(iPaletteDAO paletteDAO){
    this.paletteDAO = paletteDAO;

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;

//add roles here
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
    req.setAttribute("pageTitle", "Add Palette");
    req.getRequestDispatcher("WEB-INF/Schedule_App/AddPalette.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_C");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("schedule_in");
      session.invalidate();
      return;
    }


    String _LineNo = req.getParameter("inputpaletteLineNo");
    if (_LineNo!=null) {
      _LineNo=_LineNo.trim();
    }
    String _Color1 = req.getParameter("inputpaletteColor1");
    if (_Color1!=null) {
      _Color1=_Color1.trim();
    }
    String _Color2 = req.getParameter("inputpaletteColor2");
    if (_Color2!=null) {
      _Color2=_Color2.trim();
    }
    String _Color3 = req.getParameter("inputpaletteColor3");
    if (_Color3!=null) {
      _Color3=_Color3.trim();
    }
    String _Color4 = req.getParameter("inputpaletteColor4");
    if (_Color4!=null) {
      _Color4=_Color4.trim();
    }
    String _Color5 = req.getParameter("inputpaletteColor5");
    if (_Color5!=null) {
      _Color5=_Color5.trim();
    }
    String _Color6 = req.getParameter("inputpaletteColor6");
    if (_Color6!=null) {
      _Color6=_Color6.trim();
    }
    String _Color7 = req.getParameter("inputpaletteColor7");
    if (_Color7!=null) {
      _Color7=_Color7.trim();
    }
    String _Color8 = req.getParameter("inputpaletteColor8");
    if (_Color8!=null) {
      _Color8=_Color8.trim();
    }
    String _Color9 = req.getParameter("inputpaletteColor9");
    if (_Color9!=null) {
      _Color9=_Color9.trim();
    }
    String _Color10 = req.getParameter("inputpaletteColor10");
    if (_Color10!=null) {
      _Color10=_Color10.trim();
    }
    String _Color11 = req.getParameter("inputpaletteColor11");
    if (_Color11!=null) {
      _Color11=_Color11.trim();
    }
    String _Color12 = req.getParameter("inputpaletteColor12");
    if (_Color12!=null) {
      _Color12=_Color12.trim();
    }
    Map<String, String> results = new HashMap<>();

    results.put("LineNo",_LineNo);
    results.put("Color1",_Color1);
    results.put("Color2",_Color2);
    results.put("Color3",_Color3);
    results.put("Color4",_Color4);
    results.put("Color5",_Color5);
    results.put("Color6",_Color6);
    results.put("Color7",_Color7);
    results.put("Color8",_Color8);
    results.put("Color9",_Color9);
    results.put("Color10",_Color10);
    results.put("Color11",_Color11);
    results.put("Color12",_Color12);
    Palette palette = new Palette();
    int errors =0;
    try {
      palette.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("paletteUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      palette.setLineNo(Integer.valueOf(_LineNo));
    } catch(Exception e) {results.put("paletteLineNoerror", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color1));
      palette.setColor1(c);
    } catch(Exception e) {results.put("paletteColor1error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color2));
      palette.setColor2(c);
    } catch(Exception e) {results.put("paletteColor2error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color3));
      palette.setColor3(c);
    } catch(Exception e) {results.put("paletteColor3error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color4));
      palette.setColor4(c);
    } catch(Exception e) {results.put("paletteColor4error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color5));
      palette.setColor5(c);
    } catch(Exception e) {results.put("paletteColor5error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color6));
      palette.setColor6(c);
    } catch(Exception e) {results.put("paletteColor6error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color7));
      palette.setColor7(c);
    } catch(Exception e) {results.put("paletteColor7error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color8));
      palette.setColor8(c);
    } catch(Exception e) {results.put("paletteColor8error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color9));
      palette.setColor9(c);
    } catch(Exception e) {results.put("paletteColor9error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color10));
      palette.setColor10(c);
    } catch(Exception e) {results.put("paletteColor10error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color11));
      palette.setColor11(c);
    } catch(Exception e) {results.put("paletteColor11error", e.getMessage());
      errors++;
    }
    try {
      Color c = new Color((_Color12));
      palette.setColor12(c);
    } catch(Exception e) {results.put("paletteColor12error", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=paletteDAO.add(palette);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Palette Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Palettes");
        return;
      } else {
        results.put("dbStatus","Palette Not Added");

      }
    }
    List<User> allUsers = null;
    
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add Palette");
    req.getRequestDispatcher("WEB-INF/Schedule_App/AddPalette.jsp").forward(req, resp);

  }
}

