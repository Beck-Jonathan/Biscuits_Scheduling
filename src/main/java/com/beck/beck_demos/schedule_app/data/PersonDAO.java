package com.beck.beck_demos.schedule_app.data;

import com.beck.beck_demos.schedule_app.iData.iPersonDAO;
import com.beck.beck_demos.schedule_app.models.Person;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static com.beck.beck_demos.schedule_app.data.Database.getConnection;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class PersonDAO implements iPersonDAO {
  /**
   * DAO Method to add Person objects
   * @param _person the Person to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  @Override
  public int add(Person _person) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Person( ?, ?, ?)}")){
          statement.setString(1,_person.getFirst_Name());
          statement.setString(2,_person.getLast_Name());
          statement.setString(3,_person.getDescription());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Person. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Person. Try again later");
    }
    return numRowsAffected;
  }

  /**
   * DAO Method to retreive by ID Person objects
   * @param _person the Person to be retreived
   * @return List of Person
   * @author Jonathan Beck
   */
  @Override
  public Person getPersonByPrimaryKey(Person _person) throws SQLException {
    Person result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Person(?)}")) {
        statement.setString(1, _person.getPerson_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){String Person_ID = resultSet.getString("Person_Person_ID");
            String First_Name = resultSet.getString("Person_First_Name");
            String Last_Name = resultSet.getString("Person_Last_Name");
            String Description = resultSet.getString("Person_Description");
            result = new Person( Person_ID, First_Name, Last_Name, Description);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public int update(Person oldPerson, Person newPerson) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Person(? ,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldPerson.getPerson_ID());
          statement.setString(2,oldPerson.getFirst_Name());
          statement.setString(3,newPerson.getFirst_Name());
          statement.setString(4,oldPerson.getLast_Name());
          statement.setString(5,newPerson.getLast_Name());
          statement.setString(6,oldPerson.getDescription());
          statement.setString(7,newPerson.getDescription());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Person . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public List<Person> getAllPerson() throws SQLException {
    List<Person> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Person()}")) {

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {String Person_ID = resultSet.getString("Person_Person_ID");
              String First_Name = resultSet.getString("Person_First_Name");
              String Last_Name = resultSet.getString("Person_Last_Name");
              String Description = resultSet.getString("Person_Description");
              Person _person = new Person( Person_ID, First_Name, Last_Name, Description);
              result.add(_person);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Persons. Try again later");
    }
    return result;

  }

  @Override
  public List<Person> getDistinctPersonForDropdown() throws SQLException {
    List<Person> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_select_distinct_and_active_Person_for_dropdown()}")) {
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {String Person_ID = resultSet.getString("Person_Person_ID");
              Person _person = new Person( Person_ID);
              result.add(_person);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Persons. Try again later");
    }
    return result;
  }

  @Override
  public int deletePerson(String Person_ID) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Person( ?)}")){
          statement.setString(1,Person_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Person. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Person. Try again later");
    }
    return rowsAffected;
  }
}
