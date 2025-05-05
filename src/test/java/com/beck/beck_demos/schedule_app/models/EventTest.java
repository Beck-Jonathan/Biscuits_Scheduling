package com.beck.beck_demos.schedule_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class EventTest {
  private Event _event;
  @BeforeEach
  public void setup(){
    _event = new Event();
  }
  @AfterEach
  public void teardown(){
    _event = null;
  }

  /**
   <p> Tests That the default constructor for the Event object works </p>
   */
  @Test
  public void testEventDefaultConstructorSetsNoVariables(){
    Event _event= new Event();
    Assertions.assertNull(_event.getEvent_ID());
    Assertions.assertNull(_event.getName());
    Assertions.assertNull(_event.getDate());
    Assertions.assertNull(_event.getLocation());
    Assertions.assertNull(_event.getDescription());
    Assertions.assertNull(_event.getLength());
    Assertions.assertNull(_event.getDecision());
    Assertions.assertNull(_event.getPaid());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Event object works </p>
   */
  @Test
  public void testEventParameterizedConstructorSetsAllVariables(){
    Date date = new Date();
    Event _event= new Event(
        "NkegijfhuVnAYDVBaDQNYssImPAQYDVHNk",
        "ZHgiCPAPQhfDZopKZWNpqtNevkdvvHQGUnUNxYdbZGMawNLCjAtccmaeEfljuJhHCpLYoMOEmBtIxYsMHVkBtgTUEugVQWnpLV",
        date
        ,
        "hTyYfFBNsnmJVAvFZHSWWkUISDCuvhKLBAqPDhnrddZKYQLkAIbhJGviJpcrFyJxNKDecOskiHdkiLHJDZmqjSUwOdePLxlxVC",
        "ZPVNaFsgwoGhhqhGeQdUFYveykeenWTaTFnxrVwapJfQDinPslotQndtFHBRBaHTHIktHmugnPOPUuQvqeefDQJDlNuOobvvRwsHGqIGATJqFvpFjDAgkglXyxywWnVCkobVpmSfBGMJDFJWarbHKrGFXiuCitYQulIBkRknSYdxxsNVWjPMNRfKmOgroXqqpgGcjmxEwpgJBmLvmYfxaiolkIFBVjWGYONJbfwbTeFPSEYKisKxMSxnYnQeQW",
        9.16,
        "Yes",
        "Yes"
    );
    Assertions.assertEquals("NkegijfhuVnAYDVBaDQNYssImPAQYDVHNk",_event.getEvent_ID());
    Assertions.assertEquals("ZHgiCPAPQhfDZopKZWNpqtNevkdvvHQGUnUNxYdbZGMawNLCjAtccmaeEfljuJhHCpLYoMOEmBtIxYsMHVkBtgTUEugVQWnpLV",_event.getName());
    Assertions.assertEquals(date,_event.getDate());
    Assertions.assertEquals("hTyYfFBNsnmJVAvFZHSWWkUISDCuvhKLBAqPDhnrddZKYQLkAIbhJGviJpcrFyJxNKDecOskiHdkiLHJDZmqjSUwOdePLxlxVC",_event.getLocation());
    Assertions.assertEquals("ZPVNaFsgwoGhhqhGeQdUFYveykeenWTaTFnxrVwapJfQDinPslotQndtFHBRBaHTHIktHmugnPOPUuQvqeefDQJDlNuOobvvRwsHGqIGATJqFvpFjDAgkglXyxywWnVCkobVpmSfBGMJDFJWarbHKrGFXiuCitYQulIBkRknSYdxxsNVWjPMNRfKmOgroXqqpgGcjmxEwpgJBmLvmYfxaiolkIFBVjWGYONJbfwbTeFPSEYKisKxMSxnYnQeQW",_event.getDescription());
    Assertions.assertEquals(9.16,_event.getLength());
    Assertions.assertEquals("Yes",_event.getDecision());
    Assertions.assertEquals("Yes",_event.getPaid());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Event object works </p>
   */
  @Test
  public void testEventKeyedParameterizedConstructorSetsKeyedVariables(){
    Event _event= new Event(
        "NodvwUBpMUCtKmMIQiThKWxNhSeHNEgkxo"
    );
    Assertions.assertEquals("NodvwUBpMUCtKmMIQiThKWxNhSeHNEgkxo",_event.getEvent_ID());
    Assertions.assertNull(_event.getName());
    Assertions.assertNull(_event.getDate());
    Assertions.assertNull(_event.getLocation());
    Assertions.assertNull(_event.getDescription());
    Assertions.assertNull(_event.getLength());
    Assertions.assertNull(_event.getDecision());
    Assertions.assertNull(_event.getPaid());
  }

  /**
   <p> Tests That the Setters for the Event.Event_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfEvent_IDTooShort(){
    String Event_ID = "qS";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setEvent_ID(Event_ID);});
  }

  /**
   <p> Tests That the Setters for the Event.Event_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfEvent_IDTooLong(){
    String Event_ID = "PPraBQQbikkkkkdflmwCYfWjfhLEHxwNZnXQwYEHRpv";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setEvent_ID(Event_ID);});
  }

  /**
   <p> Tests That the Setters for the Event.Event_ID field work </p>
   */
  @Test
  public void testSetEvent_IDSetsEvent_ID(){
    String Event_ID = "aaFvXjubiRihIcdaUnZDDqnRShTiokciQmOx";
    _event.setEvent_ID(Event_ID);
    Assertions.assertEquals(Event_ID,_event.getEvent_ID());
  }

  /**
   <p> Tests That the Setters for the Event.Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfNameTooShort(){
    String Name = "Pa";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setName(Name);});
  }

  /**
   <p> Tests That the Setters for the Event.Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfNameTooLong(){
    String Name = "OfkOOoUomVsbsvdKsVcswwYKoUcEMtnqFCSLCNMpithCfWesCdnQoeZtiQFkTZWafaqWqjkcufXoOtntRONsmERTvkjwNfEkECfmEL";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setName(Name);});
  }

  /**
   <p> Tests That the Setters for the Event.Name field work </p>
   */
  @Test
  public void testSetNameSetsName(){
    String Name = "IdnTQMKvZIHTmmmFmyrVXtTEKrIGNiBjIPwDZBFFkxQOPQTeOVZwWCRnStXRCdITSPSVbGsdbqwIhFttFCNMmKFYuglXowFAIx";
    _event.setName(Name);
    Assertions.assertEquals(Name,_event.getName());
  }

  /**
   <p> Tests That the Setters for the Event.Location field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfLocationTooShort(){
    String Location = "DD";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setLocation(Location);});
  }

  /**
   <p> Tests That the Setters for the Event.Location field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfLocationTooLong(){
    String Location = "UQIhLwPdwjcdUQxEPHPIKLqKBADgvupklTBQNpoxwJmfGvrumLGwpAZvLvAnlJcdNLtZTLOhWaTvkEZugeWiBqQerxJWdqHWePBSqt";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setLocation(Location);});
  }

  /**
   <p> Tests That the Setters for the Event.Location field work </p>
   */
  @Test
  public void testSetLocationSetsLocation(){
    String Location = "hIcscOLvTqDsNpMTcoNFQmiLZUYJsThjhXwhVfCoImOOaXuYVgbqpKWoqJwHVIPoWtZoLQkeuwLnANpRDGiamRoecAXFiQcERG";
    _event.setLocation(Location);
    Assertions.assertEquals(Location,_event.getLocation());
  }

  /**
   <p> Tests That the Setters for the Event.description field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfdescriptionTooShort(){
    String description = "LO";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setDescription(description);});
  }

  /**
   <p> Tests That the Setters for the Event.description field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfdescriptionTooLong(){
    String description = "YcCvRDFYaNccsyfeIdrWhvklbncVrvlDiFiTAPjVvuLofxakaGENaRexqEjvNcuCuKrNmBlBMmHdomRpMxJBdlqKOSLkufaismDyFiTBRYGAeZkIUcAtqRsbWhJMcvZuAyJvnHYlHSaQXtXTQlaahhLHFerEoKjZbMbyTFJaBGQOnOehTYRjgBmdvwlqqtSKmoJbNbeGyTdPrVALFsZoUrIMoKOWvdolLiwFBYALVXtqhMhtVUMUZRpvjwhfFiTdnH";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setDescription(description);});
  }

  /**
   <p> Tests That the Setters for the Event.description field work </p>
   */
  @Test
  public void testSetdescriptionSetsdescription(){
    String description = "XSXVUgmnuiXiiuaZJqPfOdQSQhpCLFQawyrhfuMROyjOJEvMIiHsCHFUrUceAKgIdITXxMTolZWlGxdHrpFpaJWTwkVElyXncSnQIfhSjfwGOyHgFqbZlmDBwjOgWTRURTahYHOZkbMhLVaVFsaLIaAMGKkbxmvJhJayfiTAAKqTPbJrbmGMkSTaOWfwlCZkChWoFNqqIoVltJYcWCypwwqMXXSgCOIWcNslDPvODKwoTNNUVyZtEkbhoJFQfy";
    _event.setDescription(description);
    Assertions.assertEquals(description,_event.getDescription());
  }

  /**
   <p> Tests That the Setters for the Event.Length field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testEventThrowsIllegalArgumentExceptionIfLengthTooSmall(){
    double Length = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setLength(Length);});
  }

  /**
   <p> Tests That the Setters for the Event.Length field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testEventThrowsIllegalArgumentExceptionIfLengthTooBig(){
    double Length = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setLength(Length);});
  }

  /**
   <p> Tests That the Setters for the Event.Length field work </p>
   */
  @Test
  public void testEventSetsLength(){
    double Length = 1555;
    _event.setLength(Length);
    Assertions.assertEquals(Length, _event.getLength());
  }

  /**
   <p> Tests That the Setters for the Event.Descision field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfDecisionInvalid(){
    String Descision = "Test";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setDecision(Descision);});
  }


  /**
   <p> Tests That the Setters for the Event.Descision field work </p>
   */
  @Test
  public void testSetDescisionSetsDescision(){
    String Descision = "Skipping";
    _event.setDecision(Descision);
    Assertions.assertEquals(Descision,_event.getDecision());
    Descision = "Going";
    _event.setDecision(Descision);
    Assertions.assertEquals(Descision,_event.getDecision());
    Descision = "Maybe";
    _event.setDecision(Descision);
    Assertions.assertEquals(Descision,_event.getDecision());
  }



  /**
   <p> Tests That the Setters for the Event.Paid field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testEventThrowsIllegalArgumentExceptionIfPaidInvalid(){
    String Paid = "Test";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_event.setPaid(Paid);});
  }

  /**
   <p> Tests That the Setters for the Event.Paid field work </p>
   */
  @Test
  public void testSetPaidSetsPaid(){
    String Paid = "Yes";
    _event.setPaid(Paid);
    Assertions.assertEquals(Paid,_event.getPaid());
    Paid = "No";
    _event.setPaid(Paid);
    Assertions.assertEquals(Paid,_event.getPaid());
    Paid = "N/A";
    _event.setPaid(Paid);
    Assertions.assertEquals(Paid,_event.getPaid());
  }


  /**
   <p> Tests That the CompareTo Method for the Event object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Event smaller = new Event();
    Event bigger = new Event();
//to compare a smaller and larger Event_ID
    smaller.setEvent_ID("PPraBQQbikkkkkdflmwCYfWjfhLEHxwNZnXQ");
    bigger.setEvent_ID("QQraBQQbikkkkkdflmwCYfWjfhLEHxwNZnXQ");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Event_ID as equal.
    smaller.setEvent_ID("QQraBQQbikkkkkdflmwCYfWjfhLEHxwNZnXQ");
//to compare a smaller and larger Name
    smaller.setName("aaaa");
    bigger.setName("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Name as equal.
    smaller.setName("bbbb");
//to compare a smaller and larger Date
    smaller.setDate(df.parse("01/01/2023"));
    bigger.setDate(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Date as equal.
    smaller.setDate(df.parse("01/01/2024"));
//to compare a smaller and larger Location
    smaller.setLocation("aaaa");
    bigger.setLocation("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Location as equal.
    smaller.setLocation("bbbb");
//to compare a smaller and larger description
    smaller.setDescription("aaaa");
    bigger.setDescription("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the description as equal.
    smaller.setDescription("bbbb");
//to compare a smaller and larger Length
    smaller.setLength(10.23d);
    bigger.setLength(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Length as equal.
    smaller.setLength(14.12d);
//to compare a smaller and larger Descision
    smaller.setDecision("Going");
    bigger.setDecision("Skipping");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Descision as equal.
    smaller.setDecision("Skipping");
//to compare a smaller and larger Paid
    smaller.setPaid("No");
    bigger.setPaid("Yes");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Paid as equal.
    smaller.setPaid("Yes");
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

