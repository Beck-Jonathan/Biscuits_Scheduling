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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet Viuw/Edit from the Palette table
 Created By Jonathan Beck 12/17/2025
 ***************/
/**
 * Servlet implementation class EditPaletteServlet
 *
 * <p>This servlet handles the editing of {@link Palette} entries in the application.
 * It is mapped to the URL pattern <code>/editPalette</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing a single Palettes. Allows editing of appropriate fields. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for editing a Palette. Validates input,
 * attempts update of the the database via {@link PaletteDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful updates redirect to the list of all Palette.</p
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

@WebServlet("/editPalette")
public class EditPaletteServlet extends HttpServlet{
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
      primaryKey = req.getParameter("paletteid");
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    Palette palette= new Palette();
    try{
      palette.setPalette_ID(primaryKey);
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
      resp.sendRedirect("all-palettes");
      return;
    }
    try{
      palette=paletteDAO.getPaletteByPrimaryKey(palette);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
      palette= null;
    }
    if (palette==null || palette.getPalette_ID()==null){
      resp.sendRedirect("all-Palettes");
      return;
    }
    session.setAttribute("palette",palette);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Palette");

    req.getRequestDispatcher("WEB-INF/Schedule_App/EditPalette.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
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

//to get the old Palette
    Palette _oldPalette= (Palette)session.getAttribute("palette");
    if (_oldPalette==null){
      resp.sendRedirect("all-palettes");
      return;
    }
//to get the new Palette's info

    String _LineNo = req.getParameter("inputpaletteLineNo");
    if (_LineNo!=null){
      _LineNo=_LineNo.trim();
    }
    String _Color1 = req.getParameter("inputpaletteColor1");
    if (_Color1!=null){
      _Color1=_Color1.trim();
    }
    String _Color2 = req.getParameter("inputpaletteColor2");
    if (_Color2!=null){
      _Color2=_Color2.trim();
    }
    String _Color3 = req.getParameter("inputpaletteColor3");
    if (_Color3!=null){
      _Color3=_Color3.trim();
    }
    String _Color4 = req.getParameter("inputpaletteColor4");
    if (_Color4!=null){
      _Color4=_Color4.trim();
    }
    String _Color5 = req.getParameter("inputpaletteColor5");
    if (_Color5!=null){
      _Color5=_Color5.trim();
    }
    String _Color6 = req.getParameter("inputpaletteColor6");
    if (_Color6!=null){
      _Color6=_Color6.trim();
    }
    String _Color7 = req.getParameter("inputpaletteColor7");
    if (_Color7!=null){
      _Color7=_Color7.trim();
    }
    String _Color8 = req.getParameter("inputpaletteColor8");
    if (_Color8!=null){
      _Color8=_Color8.trim();
    }
    String _Color9 = req.getParameter("inputpaletteColor9");
    if (_Color9!=null){
      _Color9=_Color9.trim();
    }
    String _Color10 = req.getParameter("inputpaletteColor10");
    if (_Color10!=null){
      _Color10=_Color10.trim();
    }
    String _Color11 = req.getParameter("inputpaletteColor11");
    if (_Color11!=null){
      _Color11=_Color11.trim();
    }
    String _Color12 = req.getParameter("inputpaletteColor12");
    if (_Color12!=null){
      _Color12=_Color12.trim();
    }

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
    Palette _newPalette = new Palette();
    int errors =0;

    try {
      _newPalette.setLineNo(Integer.valueOf(_LineNo));
    } catch(Exception e) {results.put("paletteLineNoerror", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor1(new Color(_Color1));
    } catch(Exception e) {results.put("paletteColor1error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor2(new Color(_Color2));
    } catch(Exception e) {results.put("paletteColor2error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor3(new Color(_Color3));
    } catch(Exception e) {results.put("paletteColor3error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor4(new Color(_Color4));
    } catch(Exception e) {results.put("paletteColor4error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor5(new Color(_Color5));
    } catch(Exception e) {results.put("paletteColor5error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor6(new Color(_Color6));
    } catch(Exception e) {results.put("paletteColor6error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor7(new Color(_Color7));
    } catch(Exception e) {results.put("paletteColor7error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor8(new Color(_Color8));
    } catch(Exception e) {results.put("paletteColor8error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor9(new Color(_Color9));
    } catch(Exception e) {results.put("paletteColor9error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor10(new Color(_Color10));
    } catch(Exception e) {results.put("paletteColor10error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor11(new Color(_Color11));
    } catch(Exception e) {results.put("paletteColor11error", e.getMessage());
      errors++;
    }
    try {
      _newPalette.setColor12(new Color(_Color12));
    } catch(Exception e) {results.put("paletteColor12error", e.getMessage());
      errors++;
    }
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=paletteDAO.update(_oldPalette,_newPalette);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Palette updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Palettes");
        return;
      } else {
        results.put("dbStatus","Palette Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit Palette");
    req.getRequestDispatcher("WEB-INF/Schedule_App/EditPalette.jsp").forward(req, resp);
  }
}

