package com.beck.beck_demos.schedule_app.models;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CalendarDay implements Comparable<CalendarDay> {
  private Integer Day;
  private Integer Month;
  private Integer Year;
  private List<Event> Events;

  public CalendarDay(Integer day,Integer month , Integer year,List<Event> events) {
    this.Day = day;
    this.Month = month;
    this.Year = year;
    this.Events = events;
  }
  public CalendarDay() {
    this.Day =0;
    this.Month =0;
    this.Year =0;
    this.Events =new ArrayList<>();
  }


  public List<Event> getEvents() {
    return Events;
  }

  public void setEvents(List<Event> events) {
    this.Events = events;
  }

  public Integer getDay() {
    return Day;
  }

  public void setDay(Integer day) {
    if (day<0||day>32){
      throw new IllegalArgumentException("Day Can Not Be Negative");
    }
    this.Day = day;
  }
  public void addEvent(Event event) {
    Events.add(event);
  }

  public Integer getMonth() {
    return Month;
  }

  public void setMonth(Integer month) {
   if (month<0||month>12){
      throw new IllegalArgumentException("Month Can Not Be Negative");
    }
    this.Month = month;
  }

  public Integer getYear() {
    return Year;
  }

  public void setYear(Integer year) {
    if (year<0||year>3000){
      throw new IllegalArgumentException("Year Can Not Be Negative");
    }
    this.Year = year;
  }

  @Override
  public int compareTo(@NotNull CalendarDay o) {

    if (this.Day-o.Day<0){
      return -1;
    }
    else if(this.Day-o.Day > 0){
      return 1;
    }
    if (this.Month-o.Month<0){
      return -1;
    }
    else if(this.Month-o.Month > 0){
      return 1;
    }
    if (this.Year-o.Year<0){
      return -1;
    }
    else if(this.Year-o.Year > 0){
      return 1;
    }
    return 0;
  }
}
