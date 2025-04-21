package com.beck.beck_demos.schedule_app.iData;

import com.beck.beck_demos.schedule_app.models.Person;

import java.sql.SQLException;
import java.util.List;

public interface iPersonDAO {
  /**
   * DAO Method to add Person objects
   * @param _person the Person to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Person _person) throws SQLException;
  /**
   * DAO Method to retreive by Foreign Key Person objects
   * @return List of Person
   * @author Jonathan Beck
   */
  Person getPersonByPrimaryKey(Person _person) throws SQLException;
  /**
   * DAO Method to update Person objects
   * @param oldPerson the Person to be updated
   * @param newPerson the updated version of the Person
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Person oldPerson, Person newPerson) throws SQLException;
  /**
   * DAO Method to retreive all Person objects
   * @return List of Person
   * @author Jonathan Beck
   */
  List<Person> getAllPerson() throws SQLException;
  /**
   * DAO Method to retreive all Person objects
   * @return List of Person
   * @author Jonathan Beck
   */

  List<Person> getDistinctPersonForDropdown() throws SQLException;
  /**
   * DAO Method to delete Person objects
   * @param Person_ID the Person to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int deletePerson( String Person_ID) throws SQLException;
}
