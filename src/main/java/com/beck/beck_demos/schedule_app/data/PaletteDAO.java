package com.beck.beck_demos.schedule_app.data;

import com.beck.beck_demos.schedule_app.iData.iPaletteDAO;
import com.beck.beck_demos.schedule_app.models.Color;
import com.beck.beck_demos.schedule_app.models.Palette;

import java.sql.SQLException;
import java.util.List;
import com.beck.beck_demos.schedule_app.models.Palette;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import static com.beck.beck_demos.schedule_app.data.Database.getConnection;

public class PaletteDAO implements iPaletteDAO {
  @Override
  public int add(Palette _palette) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Palette( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")){
          statement.setString(1,_palette.getUser_ID());
          statement.setInt(2,_palette.getLineNo());
          statement.setString(3,_palette.getColor1().getCode());
          statement.setString(4,_palette.getColor2().getCode());
          statement.setString(5,_palette.getColor3().getCode());
          statement.setString(6,_palette.getColor4().getCode());
          statement.setString(7,_palette.getColor5().getCode());
          statement.setString(8,_palette.getColor6().getCode());
          statement.setString(9,_palette.getColor7().getCode());
          statement.setString(10,_palette.getColor8().getCode());
          statement.setString(11,_palette.getColor9().getCode());
          statement.setString(12,_palette.getColor10().getCode());
          statement.setString(13,_palette.getColor11().getCode());
          statement.setString(14,_palette.getColor12().getCode());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Palette. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Palette. Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public List<Palette> getPalettebyUser(String User_ID) throws SQLException {
    List<Palette> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_Palette_by_User(?)}")) {
          statement.setString(1,User_ID);

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Palette_ID = resultSet.getString("Palette_Palette_ID");
              String _User_ID = resultSet.getString("Palette_User_ID");
              Integer LineNo = resultSet.getInt("Palette_LineNo");
              String Color1 = resultSet.getString("Palette_Color1");
              String Color2 = resultSet.getString("Palette_Color2");
              String Color3 = resultSet.getString("Palette_Color3");
              String Color4 = resultSet.getString("Palette_Color4");
              String Color5 = resultSet.getString("Palette_Color5");
              String Color6 = resultSet.getString("Palette_Color6");
              String Color7 = resultSet.getString("Palette_Color7");
              String Color8 = resultSet.getString("Palette_Color8");
              String Color9 = resultSet.getString("Palette_Color9");
              String Color10 = resultSet.getString("Palette_Color10");
              String Color11 = resultSet.getString("Palette_Color11");
              String Color12 = resultSet.getString("Palette_Color12");
              String User_User_ID = resultSet.getString("User_User_ID");
              String User_User_Name = resultSet.getString("User_User_Name");
              String User_User_PW = resultSet.getString("User_User_PW");
              String User_Email = resultSet.getString("User_Email");
              Palette _palette = new Palette( Palette_ID, _User_ID, LineNo, Color1, Color2, Color3, Color4, Color5, Color6, Color7, Color8, Color9, Color10, Color11, Color12);
              result.add(_palette);
            }
          } catch (Exception e) {
            throw new RuntimeException("Could not retrieve Palettes. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Palettes. Try again later");
    }
    return result;
  }

  @Override
  public int update(Palette oldPalette, Palette newPalette) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Palette(? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldPalette.getPalette_ID());
          statement.setString(2,oldPalette.getUser_ID());
          statement.setInt(3,oldPalette.getLineNo());
          statement.setInt(4,newPalette.getLineNo());
          statement.setString(5,oldPalette.getColor1().getCode());
          statement.setString(6,newPalette.getColor1().getCode());
          statement.setString(7,oldPalette.getColor2().getCode());
          statement.setString(8,newPalette.getColor2().getCode());
          statement.setString(9,oldPalette.getColor3().getCode());
          statement.setString(10,newPalette.getColor3().getCode());
          statement.setString(11,oldPalette.getColor4().getCode());
          statement.setString(12,newPalette.getColor4().getCode());
          statement.setString(13,oldPalette.getColor5().getCode());
          statement.setString(14,newPalette.getColor5().getCode());
          statement.setString(15,oldPalette.getColor6().getCode());
          statement.setString(16,newPalette.getColor6().getCode());
          statement.setString(17,oldPalette.getColor7().getCode());
          statement.setString(18,newPalette.getColor7().getCode());
          statement.setString(19,oldPalette.getColor8().getCode());
          statement.setString(20,newPalette.getColor8().getCode());
          statement.setString(21,oldPalette.getColor9().getCode());
          statement.setString(22,newPalette.getColor9().getCode());
          statement.setString(23,oldPalette.getColor10().getCode());
          statement.setString(24,newPalette.getColor10().getCode());
          statement.setString(25,oldPalette.getColor11().getCode());
          statement.setString(26,newPalette.getColor11().getCode());
          statement.setString(27,oldPalette.getColor12().getCode());
          statement.setString(28,newPalette.getColor12().getCode());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Palette . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public int deletePalette(String Palette_ID) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Palette( ?)}")){
          statement.setString(1,Palette_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Palette. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Palette. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public Palette getPaletteByPrimaryKey(Palette _palette) throws SQLException {
    Palette result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_Palette(?)}")) {
        statement.setString(1, _palette.getPalette_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            String Palette_ID = resultSet.getString("Palette_Palette_ID");
            String User_ID = resultSet.getString("Palette_User_ID");
            Integer LineNo = resultSet.getInt("Palette_LineNo");
            String Color1 = resultSet.getString("Palette_Color1");
            String Color2 = resultSet.getString("Palette_Color2");
            String Color3 = resultSet.getString("Palette_Color3");
            String Color4 = resultSet.getString("Palette_Color4");
            String Color5 = resultSet.getString("Palette_Color5");
            String Color6 = resultSet.getString("Palette_Color6");
            String Color7 = resultSet.getString("Palette_Color7");
            String Color8 = resultSet.getString("Palette_Color8");
            String Color9 = resultSet.getString("Palette_Color9");
            String Color10 = resultSet.getString("Palette_Color10");
            String Color11 = resultSet.getString("Palette_Color11");
            String Color12 = resultSet.getString("Palette_Color12");
            String User_User_ID = resultSet.getString("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");
            String User_User_PW = resultSet.getString("User_User_PW");
            String User_Email = resultSet.getString("User_Email");
            result = new Palette( Palette_ID, User_ID, LineNo, Color1, Color2, Color3, Color4, Color5, Color6, Color7, Color8, Color9, Color10, Color11, Color12);}
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public int updateSingleColor(Palette _palette, int index) throws SQLException {
    int result = 0;
    Color color = null;
    switch (index) {
      case 1:
        color = _palette.getColor1();
        break;
      case 2:
        color = _palette.getColor2();
        break;
      case 3:
        color = _palette.getColor3();
        break;
      case 4:
        color = _palette.getColor4();
        break;
      case 5:
        color = _palette.getColor5();
        break;
      case 6:
        color = _palette.getColor6();
        break;
      case 7:
        color = _palette.getColor7();
        break;
      case 8:
        color = _palette.getColor8();
        break;
      case 9:
        color = _palette.getColor9();
        break;
      case 10:
        color = _palette.getColor10();
        break;
      case 11:
        color = _palette.getColor11();
        break;
      case 12:
        color = _palette.getColor12();
        break;
      default:
        color = null; // or some fallback color
        break;
    }
    if (color ==null){
      return 0;
    }

    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_single_Color(?,?,?,?)}"))
        {
          statement.setString(1,_palette.getPalette_ID());
          statement.setString(2,_palette.getUser_ID());
          statement.setInt(3,index);
          statement.setString(4,color.getCode());

          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Palette . Try again later");
        }
      }
    }
    return result;
  }
}
