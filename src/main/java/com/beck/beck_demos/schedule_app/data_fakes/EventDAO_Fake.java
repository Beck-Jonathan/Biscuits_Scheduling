package com.beck.beck_demos.schedule_app.data_fakes;

import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.sql.SQLException;
import java.util.List;

public class EventDAO_Fake implements iEventDAO {
  private  List<Event> events;
  public EventDAO_Fake() throws ParseException {
    events = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date date1 = sdf.parse("05/05/2025");
    Date date2 = sdf.parse("05/06/2025");
    Date date3 = sdf.parse("06/05/2025");
    Date date4 = sdf.parse("05/05/2026");

    Event event0 = new Event("stSawjFeLaTErGYxLOrrixdXgGONQmJhFhxm","10bff3c1-9278-4152-b384-13d3acdde559", "call", date1, "FiyXAEdm", "bLHvSOIv", 32.1, "Going", "Yes");
    Event event1 = new Event("suqWRmraiODoymCjCbTDoBPLjhfmFGvXMAud","10bff3c1-9278-4152-b384-13d3acdde559", "call", date2, "LcoYRSAx", "ycoDfsKi", 21.7, "Skipping", "No");
    Event event2 = new Event("oaravCCPTRemxAoTysPQXkfmWFOhXRDSNBBo","10bff3c1-9278-4152-b384-13d3acdde559", "qHxNKSbb", date3, "eQvxAUye", "NwjdQCeF", 65.84, "Skipping", "Yes");
    Event event3 = new Event("fdjmnVFGdakoRahRsmRhkdEAkJqwmoJYLROn","10bff3c1-9278-4152-b384-13d3acdde559", "TKKNftsa", date4, "hhJmsHmE", "wiYvufgB", 28.7, "Going", "No");
    events.add(event0);
    events.add(event1);
    events.add(event2);
    events.add(event3);
    Collections.sort(events);
  }
  @Override
  public int add(Event _event) throws SQLException {
    if (duplicateKey(_event)){
      return 0;
    }
    if (exceptionKey(_event)){
      throw new SQLException("error");
    }
    int size = events.size();
    events.add(_event);
    int newsize = events.size();
    return newsize-size;
  }

  @Override
  public List<Event> getAllEvent(CalendarDay day, String searchTerm, String user_id) throws SQLException {
    List<Event> _events = new ArrayList<>();

    if (day.getYear()==0 && day.getMonth()==0 && day.getDay()==0){
      _events.addAll(events);
    }
    else if (day.getDay()==0&day.getMonth()==0) {
      for (Event e : events) {

      if (e.getDate().getYear()+1900==(day.getYear())) {
        _events.add(e);
       }
      }
    }
    else if (day.getDay()==0) {
      for (Event e : events) {
        if (e.getDate().getYear()+1900==day.getYear() && e.getDate().getMonth()==day.getMonth()) {
          _events.add(e);
        }
      }
    }
    else {
      for (Event e : events) {
        if (e.getDate().getYear()+1900==day.getYear() && e.getDate().getMonth()==day.getMonth()&& e.getDate().getDate()==day.getDay()) {
          _events.add(e);
        }
      }
    }
    List<Event> filteredEvents = new ArrayList<>();
    if (!searchTerm.isEmpty()) {
      for (Event e : _events) {
        if (e.getName().contains(searchTerm) || e.getDescription().contains(searchTerm)) {
          filteredEvents.add(e);
        }
      }
    }
    else {
      filteredEvents.addAll(_events);
    }

    return filteredEvents;
  }

  @Override
  public Event getEventByPrimaryKey(Event _event) throws SQLException {
    Event result = null;
    for (Event event : events) {
      if (event.getEvent_ID().equals(_event.getEvent_ID())){
        result = event;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Event not found");
    }
    return result;
  }

  @Override
  public int update(Event oldEvent, Event newEvent) throws SQLException {
    int location =-1;
    if (duplicateKey(oldEvent)){
      return 0;
    }
    if (exceptionKey(oldEvent)){
      throw new SQLException("error");
    }
    for (int i=0;i<events.size();i++){
      if (events.get(i).getEvent_ID().equals(oldEvent.getEvent_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    events.set(location,newEvent);
    return 1;
  }

  @Override
  public int addBatch(List<Event> _events) throws SQLException {
    int result = 0;
    for (Event event : _events) {
      if (event.getUser_ID()!=null || !event.getUser_ID().isEmpty()){
        events.add(event);
      }
      else {
        throw new SQLException("User ID can not be empty");
      }
      result ++;
    }
    return result;
  }

  @Override
  public List<Event> getEventsFromFile(File file,String user_id) throws Exception {
    return List.of();
  }

  @Override
  public int deleteEvent(String eventID) throws SQLException{
    int size = events.size();
    int location =-1;
    for (int i=0;i<events.size();i++){
      if (events.get(i).getEvent_ID().equals(eventID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    events.remove(location);
    int newsize = events.size();
    return size-newsize;
  }

  @Override
  public List<Event> getCulversFlavors(List<String> Culvers, int month) throws Exception {
    return List.of();
  }

  @Override
  public List<Event> getFoodMenu(List<String> Schools, int month) throws Exception {
    return List.of();
  }

  @Override
  public List<Event> getPokemonEvents(int month, int year) throws Exception {
    return List.of();
  }

  @Override
  public int writeEventToFile(List<Event> Events, String path) throws IOException {
    return 0;
  }

  @Override
  public int writeEventToSQLInsert(List<Event> Events, String path) throws IOException {
    return 0;
  }

  

  private boolean duplicateKey(Event _event){
    return _event.getName().equals("DUPLICATE");
  }
  private boolean exceptionKey(Event _event){
    return _event.getName().equals("EXCEPTION");
  }
}
