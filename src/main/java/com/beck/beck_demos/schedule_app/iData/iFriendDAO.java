package com.beck.beck_demos.schedule_app.iData;

import com.beck.beck_demos.schedule_app.models.Friend;
import com.beck.beck_demos.schedule_app.models.Friend_VM;

import java.sql.SQLException;
import java.util.List;

public interface iFriendDAO {

  /**
   * DAO Method to add Friend_Line objects
   * @param _friend the friend to be added
   * @param user_id the id of the current user
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Friend _friend, String user_id) throws SQLException;
  /**
   * DAO Method to retrieve by Foreign Key Friend_Line objects
   * @return List of Friend_Line
   * @author Jonathan Beck
   */
  List<Friend_VM> getFriendebyUser(String User_ID) throws SQLException;
  int approveFriend(String friend_id, String user_id) throws SQLException;
  int deleteFriend(String friend_id, String user_id) throws SQLException;
}
