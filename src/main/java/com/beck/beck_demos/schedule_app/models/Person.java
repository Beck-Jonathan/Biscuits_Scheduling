package com.beck.beck_demos.schedule_app.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person> {
  private String Person_ID;
  private String First_Name;
  private String Last_Name;
  private String Description;

  public Person(){}

  public Person(String Person_ID, String First_Name, String Last_Name, String Description) {

    this.Person_ID = Person_ID;
    this.First_Name = First_Name;
    this.Last_Name = Last_Name;
    this.Description = Description;
  }

  public Person(String Person_ID) {

    this.Person_ID = Person_ID;
  }

  /**
   * <p> Gets the Person_ID of the associated Person object </p>
   * @return the Person_ID of this Person object.
   */
  public String getPerson_ID() {
    return Person_ID;
  }

  /**
   * <p> Sets the Person_ID of the associated Person object </p>
   * @param Person_ID the person_id of the person,
   * throws IllegalArgumentException if Person_ID under 3 characters or longer than 36 characters
   */
  public void setPerson_ID(String Person_ID) {
    Person_ID = Person_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Person_ID.length()!=36) {
      throw new IllegalArgumentException("Person_ID is invalid.");
    }
    this.Person_ID = Person_ID;
  }

  /**
   * <p> Gets the First_Name of the associated Person object </p>
   * @return the First_Name of this Person object.
   */
  public String getFirst_Name() {
    return First_Name;
  }

  /**
   * <p> Sets the First_Name of the associated Person object </p>
   * @param First_Name the first_name of the person,
   * throws IllegalArgumentException if First_Name under 3 characters or longer than 30 characters
   */
  public void setFirst_Name(String First_Name) {
    First_Name = First_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(First_Name.length()<4){
      throw new IllegalArgumentException("First_Name is too short.");
    }
    if(First_Name.length()>30){
      throw new IllegalArgumentException("First_Name is too long.");
    }
    this.First_Name = First_Name;
  }

  /**
   * <p> Gets the Last_Name of the associated Person object </p>
   * @return the Last_Name of this Person object.
   */
  public String getLast_Name() {
    return Last_Name;
  }

  /**
   * <p> Sets the Last_Name of the associated Person object </p>
   * @param Last_Name the last_name of the person,
   * throws IllegalArgumentException if Last_Name under 3 characters or longer than 30 characters
   */
  public void setLast_Name(String Last_Name) {
    Last_Name = Last_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(Last_Name.length()<4){
      throw new IllegalArgumentException("Last_Name is too short.");
    }
    if(Last_Name.length()>30){
      throw new IllegalArgumentException("Last_Name is too long.");
    }
    this.Last_Name = Last_Name;
  }

  /**
   * <p> Gets the Description of the associated Person object </p>
   * @return the Description of this Person object.
   */
  public String getDescription() {
    return Description;
  }

  /**
   * <p> Sets the Description of the associated Person object </p>
   * @param Description the description of the person,
   * throws IllegalArgumentException if Description under 3 characters or longer than 100 characters
   */
  public void setDescription(String Description) {
    Description = Description.replaceAll("[^A-Za-z0-9 - ]","");
    if(Description.length()<4){
      throw new IllegalArgumentException("Description is too short.");
    }
    if(Description.length()>100){
      throw new IllegalArgumentException("Description is too long.");
    }
    this.Description = Description;
  }
  @Override
  public int compareTo(@NotNull Person o) {
    if (this.Person_ID.compareTo(o.Person_ID)<0){
      return -1;
    }
    else if(this.Person_ID.compareTo(o.Person_ID) > 0){
      return 1;
    }
    if (this.First_Name.compareTo(o.First_Name)<0){
      return -1;
    }
    else if(this.First_Name.compareTo(o.First_Name) > 0){
      return 1;
    }
    if (this.Last_Name.compareTo(o.Last_Name)<0){
      return -1;
    }
    else if(this.Last_Name.compareTo(o.Last_Name) > 0){
      return 1;
    }
    if (this.Description.compareTo(o.Description)<0){
      return -1;
    }
    else if(this.Description.compareTo(o.Description) > 0){
      return 1;
    }
    return 0;
  }

}

