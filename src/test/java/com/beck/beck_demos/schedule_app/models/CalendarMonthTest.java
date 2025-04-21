package com.beck.beck_demos.schedule_app.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalendarMonthTest {

  private CalendarMonth _calendarmonth;
  @BeforeEach
  public void setup(){
    _calendarmonth = new CalendarMonth();
  }
  @AfterEach
  public void teardown(){
    _calendarmonth = null;
  }

  /**
   <p> Tests That the default constructor for the CalendarMonth object works </p>
   */
  @Test
  public void testCalendarMonthDefaultConstructorSetsNoVariables(){
    CalendarMonth _calendarmonth= new CalendarMonth();
    Assertions.assertNull(_calendarmonth.getName());
  }

  /**
   <p> Tests That the Parameterized Constructor for the CalendarMonth object works </p>
   */
  @Test
  public void testCalendarMonthParameterizedConstructorSetsAllVariables(){
    CalendarMonth _calendarmonth= new CalendarMonth( "jyVJVYfiQEEvwEYUWDZFKQr" );
    Assertions.assertEquals("jyVJVYfiQEEvwEYUWDZFKQr",_calendarmonth.getName());
    Assertions.assertNull(_calendarmonth.getDays());
  }




  /**
   <p> Tests That the Setters for the CalendarMonth.CalendarMonth_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCalendarMonthThrowsIllegalArgumentExceptionIfCalendarMonth_IDTooShort(){
    String CalendarMonth_ID = "Dp";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_calendarmonth.setName(CalendarMonth_ID);});
  }

  /**
   <p> Tests That the Setters for the CalendarMonth.CalendarMonth_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCalendarMonthThrowsIllegalArgumentExceptionIfCalendarMonth_IDTooLong(){
    String CalendarMonth_ID = "nsaeoFRBEBQGgPVKIlYIquKtoBT";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_calendarmonth.setName(CalendarMonth_ID);});
  }

  /**
   <p> Tests That the Setters for the CalendarMonth.CalendarMonth_ID field work </p>
   */
  @Test
  public void testSetCalendarMonth_IDSetsCalendarMonth_ID(){
    String CalendarMonth_ID = "dyyYVGZEKbVmxyQOvetSCCY";
    _calendarmonth.setName(CalendarMonth_ID);
    Assertions.assertEquals(CalendarMonth_ID,_calendarmonth.getName());
  }


  /**
   <p> Tests That the CompareTo Method for the CalendarMonth object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    CalendarMonth smaller = new CalendarMonth();
    CalendarMonth bigger = new CalendarMonth();
//to compare a smaller and larger CalendarMonth_ID
    smaller.setName("aaaa");
    bigger.setName("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the CalendarMonth_ID as equal.
    smaller.setName("bbbb");
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}



