package com.beck.beck_demos.schedule_app.iData;

import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public interface iEventDAO {
  /**
   * DAO Method to add Event objects
   * @param _event the Event to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Event _event) throws SQLException;
  /**
   * DAO Method to retreive all Event objects
   * @return List of Event
   * @author Jonathan Beck
   */
  List<Event> getAllEvent(CalendarDay day, String searchTerm) throws SQLException;

  /**
   * DAO Method to retreive by Foreign Key Event objects
   * @return List of Event
   * @author Jonathan Beck
   */
  Event getEventByPrimaryKey(Event _event) throws SQLException;

  /**
   * DAO Method to update Event objects
   * @param oldEvent the Event to be updated
   * @param newEvent the updated version of the Event
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Event oldEvent, Event newEvent) throws SQLException;

  int addBatch(List<Event> events) throws SQLException;

  List<Event> getEventsFromFile(File file) throws Exception;

  int deleteEvent(String eventID) throws SQLException;

  List<Event> getCulversFlavors(List<String> Culvers, int month) throws Exception;
}
