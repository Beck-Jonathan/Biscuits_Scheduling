package com.beck.beck_demos.schedule_app.models;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PersonTest {
  private Person _person;
  @BeforeEach
  public void setup(){
    _person = new Person();
  }
  @AfterEach
  public void teardown(){
    _person = null;
  }

  /**
   <p> Tests That the default constructor for the Person object works </p>
   */
  @Test
  public void testPersonDefaultConstructorSetsNoVariables(){
    Person _person= new Person();
    Assertions.assertNull(_person.getPerson_ID());
    Assertions.assertNull(_person.getFirst_Name());
    Assertions.assertNull(_person.getLast_Name());
    Assertions.assertNull(_person.getDescription());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Person object works </p>
   */
  @Test
  public void testPersonParameterizedConstructorSetsAllVariables(){
    Person _person= new Person(
        "rnPRTRjJXQvsSdwQnapCZrDVMifXATWgqV",
        "OMgOBPmwwlRNrJtmYAIyyktEdfSI",
        "DsOBwVjcOtQDyqNmxfhVOmKWdnsr",
        "DMumJJBsZtFZpFmIpuQubSaGOpTIvnhAXAlviSDkEMnrJBvFddscwsTfuThkFoEBpSpZqpVhwNsOJnXsbCXjDTihDFyGlnBHBD"
    );
    Assertions.assertEquals("rnPRTRjJXQvsSdwQnapCZrDVMifXATWgqV",_person.getPerson_ID());
    Assertions.assertEquals("OMgOBPmwwlRNrJtmYAIyyktEdfSI",_person.getFirst_Name());
    Assertions.assertEquals("DsOBwVjcOtQDyqNmxfhVOmKWdnsr",_person.getLast_Name());
    Assertions.assertEquals("DMumJJBsZtFZpFmIpuQubSaGOpTIvnhAXAlviSDkEMnrJBvFddscwsTfuThkFoEBpSpZqpVhwNsOJnXsbCXjDTihDFyGlnBHBD",_person.getDescription());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Person object works </p>
   */
  @Test
  public void testPersonKeyedParameterizedConstructorSetsKeyedVariables(){
    Person _person= new Person(
        "GETeihZYMCvAgQYNWEHgpeqNmjZBmTUwTj"
    );
    Assertions.assertEquals("GETeihZYMCvAgQYNWEHgpeqNmjZBmTUwTj",_person.getPerson_ID());
    Assertions.assertNull(_person.getFirst_Name());
    Assertions.assertNull(_person.getLast_Name());
    Assertions.assertNull(_person.getDescription());
  }

  /**
   <p> Tests That the Setters for the Person.Person_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testPersonThrowsIllegalArgumentExceptionIfPerson_IDTooShort(){
    String Person_ID = "Pd";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_person.setPerson_ID(Person_ID);});
  }

  /**
   <p> Tests That the Setters for the Person.Person_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testPersonThrowsIllegalArgumentExceptionIfPerson_IDTooLong(){
    String Person_ID = "HglCYYoTdCtbdXyqZfwbtNSLosQDUqqUAEcYnw";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_person.setPerson_ID(Person_ID);});
  }

  /**
   <p> Tests That the Setters for the Person.Person_ID field work </p>
   */
  @Test
  public void testSetPerson_IDSetsPerson_ID(){
    String Person_ID = "HJoyrkkDBwPdfdEqnFNAZOahDjOXYWXfhodd";
    _person.setPerson_ID(Person_ID);
    Assertions.assertEquals(Person_ID,_person.getPerson_ID());
  }

  /**
   <p> Tests That the Setters for the Person.First_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testPersonThrowsIllegalArgumentExceptionIfFirst_NameTooShort(){
    String First_Name = "Sl";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_person.setFirst_Name(First_Name);});
  }

  /**
   <p> Tests That the Setters for the Person.First_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testPersonThrowsIllegalArgumentExceptionIfFirst_NameTooLong(){
    String First_Name = "uUalqSbEMLkuMnTwhtOQoBebaDcuqRSv";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_person.setFirst_Name(First_Name);});
  }

  /**
   <p> Tests That the Setters for the Person.First_Name field work </p>
   */
  @Test
  public void testSetFirst_NameSetsFirst_Name(){
    String First_Name = "ZmNbuycpLbEGkHXdxGOlRTZcQUom";
    _person.setFirst_Name(First_Name);
    Assertions.assertEquals(First_Name,_person.getFirst_Name());
  }

  /**
   <p> Tests That the Setters for the Person.Last_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testPersonThrowsIllegalArgumentExceptionIfLast_NameTooShort(){
    String Last_Name = "jF";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_person.setLast_Name(Last_Name);});
  }

  /**
   <p> Tests That the Setters for the Person.Last_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testPersonThrowsIllegalArgumentExceptionIfLast_NameTooLong(){
    String Last_Name = "xPhyxJVxxKisoRtxnGSJHKDsuMPSACYN";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_person.setLast_Name(Last_Name);});
  }

  /**
   <p> Tests That the Setters for the Person.Last_Name field work </p>
   */
  @Test
  public void testSetLast_NameSetsLast_Name(){
    String Last_Name = "uDywsmxGenxpxclooaRtQlhJJgZa";
    _person.setLast_Name(Last_Name);
    Assertions.assertEquals(Last_Name,_person.getLast_Name());
  }

  /**
   <p> Tests That the Setters for the Person.Description field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testPersonThrowsIllegalArgumentExceptionIfDescriptionTooShort(){
    String Description = "WU";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_person.setDescription(Description);});
  }

  /**
   <p> Tests That the Setters for the Person.Description field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testPersonThrowsIllegalArgumentExceptionIfDescriptionTooLong(){
    String Description = "tnJZVuIWFvlZUXQcYUDlEgXBqnwxGMbFuJZRZsYElXLyOolVpICQIiCUETZaWrUfKwxJuyAgsBbyKbPDnlZwOEoEeKASJSjenWfdGR";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_person.setDescription(Description);});
  }

  /**
   <p> Tests That the Setters for the Person.Description field work </p>
   */
  @Test
  public void testSetDescriptionSetsDescription(){
    String Description = "ICAISUOxGonrkjAjEipUogIiiqUWKcZYdXvFlTjVLsUxtngatPwSUqZcdyrmUUUHRDdSVGxdmeTNsZbBJRCEfJijXVIyFqbjDO";
    _person.setDescription(Description);
    Assertions.assertEquals(Description,_person.getDescription());
  }


  /**
   <p> Tests That the CompareTo Method for the Person object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    Person smaller = new Person();
    Person bigger = new Person();
//to compare a smaller and larger Person_ID
    smaller.setPerson_ID("KDJFADKFJSA5412345DJFDKANFA215458123");
    bigger.setPerson_ID("ZDJFADKFJSA5412345DJFDKANFA215458123");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Person_ID as equal.
    smaller.setPerson_ID("ZDJFADKFJSA5412345DJFDKANFA215458123");
//to compare a smaller and larger First_Name
    smaller.setFirst_Name("aaaa");
    bigger.setFirst_Name("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the First_Name as equal.
    smaller.setFirst_Name("bbbb");
//to compare a smaller and larger Last_Name
    smaller.setLast_Name("aaaa");
    bigger.setLast_Name("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Last_Name as equal.
    smaller.setLast_Name("bbbb");
//to compare a smaller and larger Description
    smaller.setDescription("aaaa");
    bigger.setDescription("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Description as equal.
    smaller.setDescription("bbbb");
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

