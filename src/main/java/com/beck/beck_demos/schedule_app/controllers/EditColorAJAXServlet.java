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
import java.io.PrintWriter;
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

@WebServlet("/editColorAJAX")
public class EditColorAJAXServlet extends HttpServlet{
  private iPaletteDAO paletteDAO;

  @Override
  public void init() {
    paletteDAO = new PaletteDAO();

  }
  public void init(iPaletteDAO paletteDAO){
    this.paletteDAO = paletteDAO;

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int result=0;
//To restrict this page based on privilege level
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_C");
    if (user==null||!user.getRoles().contains("User")){
      result=-1;
      writeResult(resp,result);
      return;
    }
    String primaryKey = "";
    int index=0;
    try{
      primaryKey = req.getParameter("paletteid");
      index = Integer.parseInt(req.getParameter("index"));
    }catch (Exception e) {
      result=-2;
      writeResult(resp,result);
      return;
    }
    Palette palette= new Palette();
    try{
      palette.setPalette_ID(primaryKey);
    } catch (Exception e){
      result=-3;
      writeResult(resp,result);
      return;
    }
    try{
      palette=paletteDAO.getPaletteByPrimaryKey(palette);
    } catch (SQLException e) {
      result=-4;
      writeResult(resp,result);
      return;
    }
    String _Color = req.getParameter("inputpaletteColor");
    if (_Color!=null){
      _Color=_Color.trim();
    }
    else {
      result=-5;
      writeResult(resp,result);
      return;
    }
    Color color = null;
    try{
      color = new Color(_Color);
    }catch (Exception e){
      result=-6;
      writeResult(resp,result);
      return;
    }
    switch (index) {
      case 1:
        palette.setColor1(color);
        break;
      case 2:
        palette.setColor2(color);
        break;
      case 3:
        palette.setColor3(color);
        break;
      case 4:
        palette.setColor4(color);
        break;
      case 5:
        palette.setColor5(color);
        break;
      case 6:
        palette.setColor6(color);
        break;
      case 7:
        palette.setColor7(color);
        break;
      case 8:
        palette.setColor8(color);
        break;
      case 9:
        palette.setColor9(color);
        break;
      case 10:
        palette.setColor10(color);
        break;
      case 11:
        palette.setColor11(color);
        break;
      case 12:
        palette.setColor12(color);
        break;
      default:
        result=-7;
        writeResult(resp,result);
        return;
    }
//to update the database
      try{
        result=paletteDAO.updateSingleColor(palette,index);
      }catch(Exception ex){
        result=-8;
        writeResult(resp,result);
        return;
      }
    writeResult(resp,result);
    return;

  }
  private void writeResult(HttpServletResponse resp, int result) throws IOException {
    PrintWriter out = resp.getWriter();
    resp.setHeader("Content-Type", "application/json;charset=UTF-8");
    resp.setCharacterEncoding("UTF-8");
    out.print(result);
    out.flush();
  }
}

