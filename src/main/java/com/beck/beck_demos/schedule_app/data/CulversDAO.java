package com.beck.beck_demos.schedule_app.data;

import com.beck.beck_demos.schedule_app.iData.iCulversDAO;
import com.beck.beck_demos.schedule_app.models.Culvers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
import com.beck.beck_demos.schedule_app.models.Culvers;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import static com.beck.beck_demos.schedule_app.data.Database.getConnection;

public class CulversDAO implements iCulversDAO {

  @Override
  public int add(Culvers _culvers) throws SQLException {
    int numRowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Culvers( ?, ?, ?)}")) {
          statement.setString(1, _culvers.getUser_ID());
          statement.setString(2, _culvers.getName());
          statement.setString(3, _culvers.getWebAddress());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Culvers. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Culvers. Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public List<Culvers> getAllCulvers(int offset, int limit, String search, String User_ID) throws SQLException {
    List<Culvers> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_Culvers(?,?,?,?)}")) {
          statement.setInt(1, offset)
          ;
          statement.setInt(2, limit);
          statement.setString(3, search);
          statement.setString(4, User_ID);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Culvers_ID = resultSet.getString("Culvers_Culvers_ID");
              String _User_ID = resultSet.getString("Culvers_User_ID");
              String Name = resultSet.getString("Culvers_Name");
              String WebAddress = resultSet.getString("Culvers_WebAddress");
              boolean Is_Active = resultSet.getBoolean("Culvers_Is_Active");
              String User_User_ID = resultSet.getString("User_User_ID");
              String User_User_Name = resultSet.getString("User_User_Name");
              String User_User_PW = resultSet.getString("User_User_PW");
              String User_Email = resultSet.getString("User_Email");
              Culvers _culvers = new Culvers(Culvers_ID, _User_ID, Name, WebAddress, Is_Active);
              result.add(_culvers);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Culverss. Try again later");
    }
    return result;
  }

  @Override
  public int deleteCulvers(String Culvers_ID) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_deactivate_Culvers( ?)}")) {
          statement.setString(1, Culvers_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Culvers. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Culvers. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int undeleteCulvers(String Culvers_ID) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_activate_Culvers( ?)}")) {
          statement.setString(1, Culvers_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore Culvers. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore Culvers. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int update(Culvers oldCulvers, Culvers newCulvers) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_Culvers(? ,?,?,?,?,?,?,?)}")) {
          statement.setString(1, oldCulvers.getCulvers_ID());
          statement.setString(2, oldCulvers.getUser_ID());
          statement.setString(3, oldCulvers.getName());
          statement.setString(4, newCulvers.getName());
          statement.setString(5, oldCulvers.getWebAddress());
          statement.setString(6, newCulvers.getWebAddress());
          statement.setBoolean(7, oldCulvers.getIs_Active());
          statement.setBoolean(8, newCulvers.getIs_Active());
          result = statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Culvers . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public Culvers getCulversByPrimaryKey(Culvers _culvers) throws SQLException {
    Culvers result = null;
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_Culvers(?)}")) {
        statement.setString(1, _culvers.getCulvers_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            String Culvers_ID = resultSet.getString("Culvers_Culvers_ID");
            String User_ID = resultSet.getString("Culvers_User_ID");
            String Name = resultSet.getString("Culvers_Name");
            String WebAddress = resultSet.getString("Culvers_WebAddress");
            boolean Is_Active = resultSet.getBoolean("Culvers_Is_Active");
            String User_User_ID = resultSet.getString("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");
            String User_User_PW = resultSet.getString("User_User_PW");
            String User_Email = resultSet.getString("User_Email");
            result = new Culvers(Culvers_ID, User_ID, Name, WebAddress, Is_Active);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public int getCulversCount(String Search_term, String User_ID) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_count_by_all_Culvers(?,?)}")) {
          statement.setString(1, Search_term);
          statement.setString(2, User_ID);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              result = resultSet.getInt(1);
            }
          }
        }
      }
        } catch (Exception e) {
          throw new RuntimeException("Could not retrieve Culverss. Try again later");
        }
    return result;
  }

  @Override
  public List<Culvers> getActiveCulversByUserID(String User_ID) throws SQLException {
    List<Culvers> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_Culvers_by_User(?)}")) {
          statement.setString(1,User_ID);

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Culvers_ID = resultSet.getString("Culvers_Culvers_ID");
              String _User_ID = resultSet.getString("Culvers_User_ID");
              String Name = resultSet.getString("Culvers_Name");
              String WebAddress = resultSet.getString("Culvers_WebAddress");
              boolean Is_Active = resultSet.getBoolean("Culvers_Is_Active");
              String User_User_ID = resultSet.getString("User_User_ID");
              String User_User_Name = resultSet.getString("User_User_Name");
              String User_User_PW = resultSet.getString("User_User_PW");
              String User_Email = resultSet.getString("User_Email");
              Culvers _culvers = new Culvers( Culvers_ID, _User_ID, Name, WebAddress, Is_Active);
              result.add(_culvers);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Culverss. Try again later");
    }
    return result;
  }
}
