package com.beck.beck_demos.schedule_app.data;

import com.beck.beck_demos.schedule_app.iData.iFriendDAO;
import com.beck.beck_demos.schedule_app.models.Friend;
import com.beck.beck_demos.schedule_app.models.Friend_VM;
import com.beck.beck_demos.schedule_app.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.beck.beck_demos.schedule_app.data.Database.getConnection;

public class FriendDAO implements iFriendDAO {
  @Override
  public int add(Friend _friend, String user_id) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Friend( ?, ?)}")){
          statement.setString(1,user_id);
          statement.setString(2,_friend.getFriend());


          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Friend_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Friend_Line. Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public List<Friend_VM> getFriendebyUser(String User_ID) throws SQLException {
    List<Friend_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_Friend_by_User(?)}")) {
          statement.setString(1,User_ID);
          //statement.setString(2,Search_param);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String User_One = resultSet.getString("Friend_Line_Other_User");
              String User_Two = resultSet.getString("Friend_Line_User_Two");
              String _User_ID = resultSet.getString("User_ID");

              String other_user = "";

              if (User_One.equals(User_ID)) {
                other_user = User_Two;

              }
              if (User_Two.equals(User_ID)) {
                other_user = User_One;

              }
              String Status = resultSet.getString("Friend_Line_Status");
              Date Last_Updated = resultSet.getDate("Friend_Line_Last_Updated");
              String User_User_Name = resultSet.getString("User_User_Name");
              String User_Email = resultSet.getString("User_Email");
              User user = new User();
              user.setUser_Name(User_User_Name);
              user.setEmail(User_Email);
              user.setUser_ID(_User_ID);
              Friend _friend = new Friend( other_user, Status, Last_Updated);
              Friend_VM friend_vm = new Friend_VM(_friend);
              friend_vm.setUser2(user);
              result.add(friend_vm);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Friend_Lines. Try again later");
    }
    return result;
  }
  /**
   * DAO Method to approve friend requests
   * @param friend_id the friend to be accepted
   * @param user_id the current user
   * @return number of records approved
   * @author Jonathan Beck
   */
  @Override
  public int approveFriend(String friend_id, String user_id) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_approve_Friend_Line( ?,?)}")){
          statement.setString(1,friend_id);
          statement.setString(2,user_id);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Friend_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Friend_Line. Try again later");
    }
    return rowsAffected;
  }
  /**
   * DAO Method to delete Friend_Line objects
   * @param friend_id the friend to be deleted
   * @param user_id the current user
   * @return number of records deleted
   * @author Jonathan Beck
   */

  @Override
  public int deleteFriend(String friend_id, String user_id) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_delete_Friend_Line( ?,?)}")){
          statement.setString(1,friend_id);
          statement.setString(2,user_id);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Friend_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Friend_Line. Try again later");
    }
    return rowsAffected;
  }
}
