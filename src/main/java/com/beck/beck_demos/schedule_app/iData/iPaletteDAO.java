package com.beck.beck_demos.schedule_app.iData;

import com.beck.beck_demos.schedule_app.models.Palette;

import java.sql.SQLException;
import java.util.List;

public interface iPaletteDAO {
  /**
   * DAO Method to add Palette objects
   * @param _palette the Palette to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Palette _palette) throws SQLException;
  /**
   * DAO Method to retrieve by Foreign Key Palette objects
   * @return List of Palette
   * @author Jonathan Beck
   */
  public List<Palette> getPalettebyUser(String User_ID) throws SQLException;
  /**
   * DAO Method to update Palette objects
   * @param oldPalette the Palette to be updated
   * @param newPalette the updated version of the Palette
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Palette oldPalette, Palette newPalette) throws SQLException;
  /**
   * DAO Method to delete Palette objects
   * @param Palette_ID the Palette to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int deletePalette(String Palette_ID) throws SQLException;
  /**
   * DAO Method to retrieve by Foreign Key Palette objects
   * @return List of Palette
   * @author Jonathan Beck
   */
  Palette getPaletteByPrimaryKey(Palette _palette) throws SQLException;
}
