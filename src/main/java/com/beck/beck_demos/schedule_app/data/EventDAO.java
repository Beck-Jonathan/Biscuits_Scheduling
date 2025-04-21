package com.beck.beck_demos.schedule_app.data;

import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.beck.beck_demos.schedule_app.data.Database.getConnection;

public class EventDAO implements iEventDAO {
  @Override
  public int add(Event _event) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Event( ?, ?, ?, ?, ?, ?, ?)}")){
          statement.setString(1,_event.getName());
          java.sql.Timestamp date = new java.sql.Timestamp(_event.getDate().getTime());
          statement.setTimestamp(2,date);
          statement.setString(3,_event.getLocation());
          statement.setString(4,_event.getDescription());
          statement.setDouble(5,_event.getLength());
          statement.setString(6,_event.getDecision());
          statement.setString(7,_event.getPaid());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Event. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Event. Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public List<Event> getAllEvent(CalendarDay day, String searchTerm) throws SQLException {
    List<Event> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Event(?,?,?,?)}")) {
          statement.setInt(1,day.getMonth());
          statement.setInt(2,day.getDay());
          statement.setInt(3,day.getYear());
          statement.setString(4,searchTerm);

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {String Event_ID = resultSet.getString("Event_Event_ID");
              String Name = resultSet.getString("Event_Name");
              Date Date = resultSet.getTimestamp("Event_Date");
              String Location = resultSet.getString("Event_Location");
              String description = resultSet.getString("Event_description");
              Double Length = resultSet.getDouble("Event_Length");
              String Decision = resultSet.getString("Event_Decision");
              String Paid = resultSet.getString("Event_Paid");
              Event _event = new Event( Event_ID, Name, Date, Location, description, Length, Decision, Paid);
              result.add(_event);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Events. Try again later");
    }
    return result;
  }

  @Override
  public Event getEventByPrimaryKey(Event _event) throws SQLException {
    Event result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Event(?)}")) {
        statement.setString(1, _event.getEvent_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){String Event_ID = resultSet.getString("Event_Event_ID");
            String Name = resultSet.getString("Event_Name");
            Date Date_Time = resultSet.getTimestamp("Event_Date_Time");
            String Location = resultSet.getString("Event_Location");
            String Description = resultSet.getString("Event_Description");
            Double Length = resultSet.getDouble("Event_Length");
            String Decision = resultSet.getString("Event_Decision");
            String Paid = resultSet.getString("Event_Paid");
            result = new Event( Event_ID, Name, Date_Time, Location, Description, Length, Decision, Paid);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public int update(Event oldEvent, Event newEvent) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Event(? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldEvent.getEvent_ID());
          statement.setString(2,oldEvent.getName());
          statement.setString(3,newEvent.getName());
          java.sql.Timestamp oldDate = new java.sql.Timestamp(oldEvent.getDate().getTime());

          java.sql.Timestamp newDate = new java.sql.Timestamp(newEvent.getDate().getTime());

          statement.setTimestamp(4,oldDate);
          statement.setTimestamp(5,newDate);
          statement.setString(6,oldEvent.getLocation());
          statement.setString(7,newEvent.getLocation());
          statement.setString(8,oldEvent.getDescription());
          statement.setString(9,newEvent.getDescription());
          statement.setDouble(10,oldEvent.getLength());
          statement.setDouble(11,newEvent.getLength());
          statement.setString(12,oldEvent.getDecision());
          statement.setString(13,newEvent.getDecision());
          statement.setString(14,oldEvent.getPaid());
          statement.setString(15,newEvent.getPaid());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Event . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public int addBatch(List<Event> events) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        for (Event _event : events) {
          try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Event( ?, ?, ?, ?, ?, ?, ?)}")) {
            statement.setString(1, _event.getName());
            java.sql.Timestamp date = new java.sql.Timestamp(_event.getDate().getTime());
            statement.setTimestamp(2,date);
            statement.setString(3, _event.getLocation());
            statement.setString(4, _event.getDescription());
            statement.setDouble(5, _event.getLength());
            statement.setString(6, _event.getDecision());
            statement.setString(7, _event.getPaid());
            int updated =  statement.executeUpdate();
            numRowsAffected += updated;
            if (updated == 0) {
              throw new RuntimeException("Could not add Event. Try again later");
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Event. Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public List<Event> getEventsFromFile(File file) throws Exception {
    List<Event> result = new ArrayList<>();
    BufferedReader reader;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a");



    try {
      reader = new BufferedReader(new FileReader(file));
      String line = reader.readLine();


      //first line is just heading data
      line = reader.readLine();

        while (line != null) {
          ArrayList parts = new ArrayList(List.of(line.split("\t")));
          String name = (String) parts.get(1);
          String date = (String) parts.get(2);
          String time = (String) parts.get(3);
          String dateTime = date + " " + time;
          Date parsed = formatter.parse(dateTime);
          String description = (String) parts.get(5);
          String length =  (String) parts.get(6);
          ArrayList parts2 = new ArrayList(List.of(length.split(" ")));
          double count = Double.parseDouble((String) parts2.get(0));
          String units = (String) parts2.get(1);
          if (units.toLowerCase().equals("minutes")){
            count = count/60;
          }
          if (units.toLowerCase().equals("days")){
            count = count*24;
          }
          String decision = (String) parts.get(7);
          String paid = (String) parts.get(8);
          String location = (String) parts.get(9);
          Event _event = new Event("",name,parsed,location,description,count,decision,paid);
          result.add(_event);
          // read next line
          line = reader.readLine();
        }



      reader.close();

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage()+"\n\nCould not add Events. Try again later");
    }
    return result;
  }

  @Override
  public int deleteEvent(String eventID) throws SQLException{
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Event( ?)}")){
          statement.setString(1,eventID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Event. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Event. Try again later");
    }
    return rowsAffected;
  }

}
