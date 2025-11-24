package com.beck.beck_demos.schedule_app.iData;

import com.beck.beck_demos.schedule_app.models.Culvers;

import java.sql.SQLException;
import java.util.List;

public interface iCulversDAO {
  /**
   * DAO Method to add Culvers objects
   * @param _culvers the Culvers to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Culvers _culvers) throws SQLException;
  /**
   * DAO Method to retrieve all Culvers objects
   * @return List of Culvers
   * @author Jonathan Beck
   */
  List<Culvers> getAllCulvers(int offset, int limit, String search, String User_ID) throws SQLException;

  /**
   * DAO Method to delete Culvers objects
   * @param Culvers_ID the Culvers to be deactivated
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int deleteCulvers( String Culvers_ID) throws SQLException;
  /**
   * DAO Method to undelete Culvers objects
   * @param Culvers_ID the Culvers to be undeleted
   * @return number of records undeleted
   * @author Jonathan Beck
   */
  int undeleteCulvers( String Culvers_ID) throws SQLException;

  /**
   * DAO Method to update Culvers objects
   * @param oldCulvers the Culvers to be updated
   * @param newCulvers the updated version of the Culvers
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Culvers oldCulvers, Culvers newCulvers) throws SQLException;

  /**
   * DAO Method to retrieve by Primary Key Culvers objects
   * @return List of Culvers
   * @author Jonathan Beck
   */
  Culvers getCulversByPrimaryKey(Culvers _culvers) throws SQLException;

  int getCulversCount(String Search_term, String User_ID) throws SQLException;
  List<Culvers> getActiveCulversByUserID(String User_ID) throws SQLException;
}
