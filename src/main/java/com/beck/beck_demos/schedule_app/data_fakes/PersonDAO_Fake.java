package com.beck.beck_demos.schedule_app.data_fakes;

import com.beck.beck_demos.schedule_app.iData.iPersonDAO;
import com.beck.beck_demos.schedule_app.models.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonDAO_Fake implements iPersonDAO {
  private  List<Person> persons;
  public PersonDAO_Fake(){
    persons = new ArrayList<>();
    Person person0 = new Person("HJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd", "wlqqtSKm", "oJbNbeGy", "TdPrVALF");
    Person person1 = new Person("BJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd", "KOWvdolL", "iwFBYALV", "XtqhMhtV");
    Person person2 = new Person("cJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd", "whfFiTdn", "HXSXVUgm", "nuiXiiua");
    Person person3 = new Person("DJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd", "SQhpCLFQ", "awyrhfuM", "ROyjOJEv");
    persons.add(person0);
    persons.add(person1);
    persons.add(person2);
    persons.add(person3);
    Collections.sort(persons);
  }
  @Override
  public int add(Person _person) throws SQLException {
    if (duplicateKey(_person)){
      return 0;
    }
    if (exceptionKey(_person)){
      throw new SQLException("error");
    }
    int size = persons.size();
    persons.add(_person);
    int newsize = persons.size();
    return newsize-size;
  }

  @Override
  public Person getPersonByPrimaryKey(Person _person) throws SQLException {
    Person result = null;
    for (Person person : persons) {
      if (person.getPerson_ID().equals(_person.getPerson_ID())){
        result = person;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Person not found");
    }
    return result;
  }

  @Override
  public int update(Person oldPerson, Person newPerson) throws SQLException {
    int location =-1;
    if (duplicateKey(oldPerson)){
      return 0;
    }
    if (exceptionKey(oldPerson)){
      throw new SQLException("error");
    }
    for (int i=0;i<persons.size();i++){
      if (persons.get(i).getPerson_ID().equals(oldPerson.getPerson_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    persons.set(location,newPerson);
    return 1;
  }

  @Override
  public List<Person> getAllPerson() throws SQLException {
    return persons;
  }

  @Override
  public List<Person> getDistinctPersonForDropdown() throws SQLException {
    return List.of();
  }

  @Override
  public int deletePerson(String Person_ID) throws SQLException {
    int size = persons.size();
    int location =-1;
    for (int i=0;i<persons.size();i++){
      if (persons.get(i).getPerson_ID().equals(Person_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    persons.remove(location);
    int newsize = persons.size();
    return size-newsize;
  }
  private boolean duplicateKey(Person _person){
    return _person.getFirst_Name().equals("DUPLICATE");
  }
  private boolean exceptionKey(Person _person){
    return _person.getFirst_Name().equals("EXCEPTION");
  }
}
