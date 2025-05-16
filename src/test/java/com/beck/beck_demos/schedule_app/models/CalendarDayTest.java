package com.beck.beck_demos.schedule_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarDayTest {
  private CalendarDay _calendarday;
  @BeforeEach
  public void setup(){
    _calendarday = new CalendarDay();
  }
  @AfterEach
  public void teardown(){
    _calendarday = null;
  }

  /**
   <p> Tests That the default constructor for the CalendarDay object works </p>
   */
  @Test
  public void testCalendarDayDefaultConstructorSetsNoVariables(){
    CalendarDay _calendarday= new CalendarDay();

    Assertions.assertEquals(0,_calendarday.getDay());
    Assertions.assertEquals(0,_calendarday.getMonth());
    Assertions.assertEquals(0,_calendarday.getYear());
  }

  /**
   <p> Tests That the Parameterized Constructor for the CalendarDay object works </p>
   */
  @Test
  public void testCalendarDayParameterizedConstructorSetsAllVariables(){
    List<Event> events = new ArrayList<Event>();
    CalendarDay _calendarday= new CalendarDay(
        6218,
        5729,
        316,
        events
    );

    Assertions.assertEquals(6218,_calendarday.getDay());
    Assertions.assertEquals(5729,_calendarday.getMonth());
    Assertions.assertEquals(316,_calendarday.getYear());
    Assertions.assertEquals(events,_calendarday.getEvents());
  }





  /**
   <p> Tests That the Setters for the CalendarDay.Day field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCalendarDayThrowsIllegalArgumentExceptionIfDayTooSmall(){
    int Day = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_calendarday.setDay(Day);});
  }

  /**
   <p> Tests That the Setters for the CalendarDay.Day field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCalendarDayThrowsIllegalArgumentExceptionIfDayTooBig(){
    int Day = 33;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_calendarday.setDay(Day);});
  }

  /**
   <p> Tests That the Setters for the CalendarDay.Day field work </p>
   */
  @Test
  public void testCalendarDaySetsDay(){
    int Day = 20;
    _calendarday.setDay(Day);
    Assertions.assertEquals(Day, _calendarday.getDay());
  }

  /**
   <p> Tests That the Setters for the CalendarDay.Month field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCalendarDayThrowsIllegalArgumentExceptionIfMonthTooSmall(){
    int Month = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_calendarday.setMonth(Month);});
  }

  /**
   <p> Tests That the Setters for the CalendarDay.Month field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCalendarDayThrowsIllegalArgumentExceptionIfMonthTooBig(){
    int Month = 13;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_calendarday.setMonth(Month);});
  }

  /**
   <p> Tests That the Setters for the CalendarDay.Month field work </p>
   */
  @Test
  public void testCalendarDaySetsMonth(){
    int Month = 7;
    _calendarday.setMonth(Month);
    Assertions.assertEquals(Month, _calendarday.getMonth());
  }

  /**
   <p> Tests That the Setters for the CalendarDay.Year field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCalendarDayThrowsIllegalArgumentExceptionIfYearTooSmall(){
    int Year = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_calendarday.setYear(Year);});
  }

  /**
   <p> Tests That the Setters for the CalendarDay.Year field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCalendarDayThrowsIllegalArgumentExceptionIfYearTooBig(){
    int Year = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_calendarday.setYear(Year);});
  }

  /**
   <p> Tests That the Setters for the CalendarDay.Year field work </p>
   */
  @Test
  public void testCalendarDaySetsYear(){
    int Year = 2024;
    _calendarday.setYear(Year);
    Assertions.assertEquals(Year, _calendarday.getYear());
  }


  /**
   <p> Tests That the CompareTo Method for the CalendarDay object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    CalendarDay smaller = new CalendarDay();
    CalendarDay bigger = new CalendarDay();

//to compare a smaller and larger Day
    smaller.setDay(10);
    bigger.setDay(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Day as equal.
    smaller.setDay(20);
//to compare a smaller and larger Month
    smaller.setMonth(10);
    bigger.setMonth(11);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Month as equal.
    smaller.setMonth(11);
//to compare a smaller and larger Year
    smaller.setYear(10);
    bigger.setYear(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Year as equal.
    smaller.setYear(20);
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

