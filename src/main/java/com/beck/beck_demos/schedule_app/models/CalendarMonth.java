package com.beck.beck_demos.schedule_app.models;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CalendarMonth implements Comparable<CalendarMonth>{
  private String Name;
  private List<CalendarDay> Days;
  public CalendarMonth() {

  }
  public CalendarMonth(String _name){
    this.Name = _name;
  }
  public CalendarMonth(String _name, List<CalendarDay> _days) {
    this.Name = _name;
    this.Days = _days;
  }
  public String getName() {
    return Name;
  }

  public void setName(String name) {
    if(name.length()<4){
      throw new IllegalArgumentException("name is too short.");
    }
    if(name.length()>25){
      throw new IllegalArgumentException("name is too long.");
    }
    this.Name = name;
  }

  public List<CalendarDay> getDays() {
    return Days;
  }

  public void setDays(List<CalendarDay> days) {
    if(days.size()<28){
      throw new IllegalArgumentException("days is too short.");
    }
    if(days.size()>32){
      throw new IllegalArgumentException("days is too long.");
    }
    this.Days = days;
  }
  public void addDay(CalendarDay day) {
    Days.add(day);
  }
  @Override
  public int compareTo(@NotNull CalendarMonth o) {
    if (this.getName().compareTo(o.getName())<0){
      return -1;
    }
    else if(this.getName().compareTo(o.getName()) > 0){
      return 1;
    }
    if (this.getDays()==null&&o.getDays()==null){
      return 0;
    }
    if (this.Days==null){
      return -1;
    }
    if (o.getDays()==null){
      return 1;
    }
    if (this.getDays().size()-(o.getDays().size())<0){
      return -1;
    }
    else if(this.getDays().size()-(o.getDays().size())>0){
      return 1;
    }

    return 0;
  }
}
