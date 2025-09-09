package com.beck.beck_demos.schedule_app.data;

import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;



import static com.beck.beck_demos.schedule_app.data.Database.getConnection;

public class EventDAO implements iEventDAO {
  @Override
  public int add(Event _event) throws SQLException {
    int numRowsAffected = 0;
    try (Connection connection = getConnection()) {

      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Event( ?, ?, ?, ?, ?, ?, ?,?)}")) {
          statement.setString(1, _event.getName());
          statement.setString(2, _event.getUser_ID());
          java.sql.Timestamp date = new java.sql.Timestamp(_event.getDate().getTime());
          statement.setTimestamp(3, date);
          statement.setString(4, _event.getLocation());
          statement.setString(5, _event.getDescription());
          statement.setDouble(6, _event.getLength());
          statement.setString(7, _event.getDecision());
          statement.setString(8, _event.getPaid());
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
  public List<Event> getAllEvent(CalendarDay day, String searchTerm, String user_ID) throws SQLException {
    List<Event> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Event(?,?,?,?,?)}")) {
          statement.setString(1,user_ID);
          statement.setInt(2, day.getMonth());
          statement.setInt(3, day.getDay());
          statement.setInt(4, day.getYear());
          statement.setString(5, searchTerm);

          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Event_ID = resultSet.getString("Event_Event_ID");
              String user_id = resultSet.getString("Event_User_ID");
              String Name = resultSet.getString("Event_Name");
              Date Date = resultSet.getTimestamp("Event_Date");
              String Location = resultSet.getString("Event_Location");
              String description = resultSet.getString("Event_description");
              Double Length = resultSet.getDouble("Event_Length");
              String Decision = resultSet.getString("Event_Decision");
              String Paid = resultSet.getString("Event_Paid");
              Event _event = new Event(Event_ID,user_id, Name, Date, Location, description, Length, Decision, Paid);
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
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Event(?)}")) {
        statement.setString(1, _event.getEvent_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            String Event_ID = resultSet.getString("Event_Event_ID");
            String user_id = resultSet.getString("Event_User_ID");
            String Name = resultSet.getString("Event_Name");
            Date Date_Time = resultSet.getTimestamp("Event_Date_Time");
            String Location = resultSet.getString("Event_Location");
            String Description = resultSet.getString("Event_Description");
            Double Length = resultSet.getDouble("Event_Length");
            String Decision = resultSet.getString("Event_Decision");
            String Paid = resultSet.getString("Event_Paid");
            result = new Event(Event_ID, user_id, Name, Date_Time, Location, Description, Length, Decision, Paid);
          }
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
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_Event(? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
          statement.setString(1, oldEvent.getEvent_ID());
          statement.setString(2, oldEvent.getName());
          statement.setString(3, newEvent.getName());
          java.sql.Timestamp oldDate = new java.sql.Timestamp(oldEvent.getDate().getTime());

          java.sql.Timestamp newDate = new java.sql.Timestamp(newEvent.getDate().getTime());

          statement.setTimestamp(4, oldDate);
          statement.setTimestamp(5, newDate);
          statement.setString(6, oldEvent.getLocation());
          statement.setString(7, newEvent.getLocation());
          statement.setString(8, oldEvent.getDescription());
          statement.setString(9, newEvent.getDescription());
          statement.setDouble(10, oldEvent.getLength());
          statement.setDouble(11, newEvent.getLength());
          statement.setString(12, oldEvent.getDecision());
          statement.setString(13, newEvent.getDecision());
          statement.setString(14, oldEvent.getPaid());
          statement.setString(15, newEvent.getPaid());
          result = statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Event . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public int addBatch(List<Event> events) throws SQLException {
    int numRowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        for (Event _event : events) {
          try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Event( ?,?, ?, ?, ?, ?, ?, ?)}")) {
            statement.setString(1, _event.getName());
            statement.setString(2, _event.getUser_ID());

            java.sql.Timestamp date = new java.sql.Timestamp(_event.getDate().getTime());
            statement.setTimestamp(3, date);
            statement.setString(4, _event.getLocation());
            statement.setString(5, _event.getDescription());
            statement.setDouble(6, _event.getLength());
            statement.setString(7, _event.getDecision());
            statement.setString(8, _event.getPaid());
            int updated = statement.executeUpdate();
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
  public List<Event> getEventsFromFile(File file, String user_id) throws Exception {
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
        String length = (String) parts.get(6);
        ArrayList parts2 = new ArrayList(List.of(length.split(" ")));
        double count = Double.parseDouble((String) parts2.get(0));
        String units = (String) parts2.get(1);
        if (units.toLowerCase().equals("minutes")) {
          count = count / 60;
        }
        if (units.toLowerCase().equals("days")) {
          count = count * 24;
        }
        String decision = (String) parts.get(7);
        String paid = (String) parts.get(8);
        String location = (String) parts.get(9);
        Event _event = new Event("",user_id ,name, parsed, location, description, count, decision, paid);
        result.add(_event);
        // read next line
        line = reader.readLine();
      }

      reader.close();

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage() + "\n\nCould not add Events. Try again later");
    }
    return result;
  }

  @Override
  public int deleteEvent(String eventID) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Event( ?)}")) {
          statement.setString(1, eventID);
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



  @Override
  public List<Event> getCulversFlavors(List<String> Cities, int month) throws Exception {
    List<Event> flavors = new ArrayList<>();
    String result = "";
    for (String location : Cities) {
      String uril = "https://www.culvers.com/restaurants/" + location + "?tab=next";
      try {
        try (Scanner scanner = new Scanner(new URL(uril).openStream(),
            StandardCharsets.UTF_8.toString())) {
          scanner.useDelimiter("\\A");
          result = scanner.next();
          int dataStart = result.indexOf("NEXT_DATA");
          String anchors = result.substring(dataStart);
          int endScriptIndex = anchors.indexOf("</script>");
          String anchors2 = anchors.substring(37, endScriptIndex);
          JSONObject overall = new JSONObject(anchors2);
          JSONObject props = overall.getJSONObject("props");
          JSONObject pageProps = props.getJSONObject("pageProps");
          JSONObject _page = pageProps.getJSONObject("page");
          JSONObject customData = _page.getJSONObject("customData");
          JSONObject restaurantCalendar = customData.getJSONObject("restaurantCalendar");
          JSONArray _flavors = restaurantCalendar.getJSONArray("flavors");
          for (Object o : _flavors) {
            JSONObject flavor = (JSONObject) o;
            String date = flavor.getString("onDate");
            date = date.substring(0, date.indexOf('T'));
            String Name = "Culvers in " + location + ".";
            SimpleDateFormat Simple = new SimpleDateFormat("yyyy-MM-dd");
            Date Date_Time = Simple.parse(date);
            String Description = flavor.getString("title");
            Double Length = 1d;
            String Decision = "Maybe";
            String Paid = "No";
            Event _event = new Event("","", Name, Date_Time, location, Description, Length, Decision, Paid);
            if (_event.getDate().getMonth() == month) {
              flavors.add(_event);
            }
          }
        } catch (Exception e) {
          flavors = new ArrayList<>();
          return flavors;
        }
      } catch (Exception e) {
        flavors = new ArrayList<>();
        return flavors;
      }
    }
    return flavors;
  }

  @Override
  public List<Event> getFoodMenu(List<String> Schools, int _month) throws Exception {
    //https://{yourDistrict}.api.nutrislice.com/menu/api/weeks/school/{yourSchool}/menu-type/{yourMeal}/{year}/{month}/{day}/?format=json`
    List<String> mealNames = Arrays.asList("breakfast","lunch");
    List<Event> meals = new ArrayList<>();
    Date date = new Date();
    Dotenv dotenv = Dotenv.load();
    String disctict = "cr";
    String school = "coolidge";

    String year = String.valueOf(((Integer) date.getYear()) + 1900);
    String month = String.valueOf(_month+1);
    //String day = ((Integer) date.getDate()).toString();

    SimpleDateFormat Simple = new SimpleDateFormat("yyyy-MM-dd");
    //List<MissingPiece> setPieces = new ArrayList<>();
    String result = "";
    for (String mealname : mealNames) {
      Integer day = 1;
      String meal = mealname;
      while (day <= 32) {
        String uril = "https://" + disctict + ".api.nutrislice.com/menu/api/weeks/school/" + school + "/menu-type/" + meal + "/" + year + "/" + month + "/" + day + "/?format=json";
        try {
          try (Scanner scanner = new Scanner(new URL(uril).openStream(),
              StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            result = scanner.next();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray daysArray = jsonObject.getJSONArray("days");
            for (int i = 0; i < daysArray.length(); i++) {
              JSONObject day4 = daysArray.getJSONObject(i);
              String fastTime = day4.getString("date");
              Date Start_Date_Time = Simple.parse(fastTime);
              if (meal.equals("breakfast")){
                Start_Date_Time.setHours(8);
                Start_Date_Time.setMinutes(30);
              }
              else{
                Start_Date_Time.setHours(11);
                Start_Date_Time.setMinutes(30);
              }

              int y =0;
              if (Start_Date_Time.getDay() == 0 || Start_Date_Time.getDay() == 6 || Start_Date_Time.getMonth()!=_month) {
                int z = Start_Date_Time.getMonth();
                continue;
              }

              JSONArray menu_items = day4.getJSONArray("menu_items");
              String todaysFood = "";

              for (int j = 0; j < menu_items.length(); j++) {
                try {
                  JSONObject menu_item2 = menu_items.getJSONObject(j);
                  JSONObject food = menu_item2.getJSONObject("food");
                  String name = food.getString("name");

                  todaysFood = todaysFood + name + "  ";

                } catch (Exception e) {
                  continue;
                }
              }
              String Name = "School "+mealname+" at West Willow";
              String location = "West Willow";
              String Description = todaysFood;
              Double Length = 0d;
              String Decision = "Maybe";
              String Paid = "No";
              if (!Description.isEmpty()) {
                Event _event = new Event("", "", Name, Start_Date_Time, location, Description, Length, Decision, Paid);
                meals.add(_event);
              }
            }
          }
        } catch (Exception e) {
          meals = new ArrayList<>();
          return meals;
        }
        day = day + 7;
      }
    }
    int x = meals.size();
    return meals;

  }

  @Override
  public List<Event> getPokemonEvents(int month, int year) throws Exception {
    SimpleDateFormat Simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    List<Event> pokemonEvents = new ArrayList<>();
    String result = "";
    int i = 0;
    String uril = "https://raw.githubusercontent.com/bigfoott/ScrapedDuck/data/events.json";
    try {
      try (Scanner scanner = new Scanner(new URL(uril).openStream(),
          StandardCharsets.UTF_8.toString())) {
        scanner.useDelimiter("\\A");
        result = scanner.next();
        JSONArray array = new JSONArray(result);
        for (Object o : array) {
          i++;

          JSONObject pkmnEvent = (JSONObject) o;
          String Name = pkmnEvent.getString("name");
          Object _start_date = pkmnEvent.get("start");
          Object _end_date = pkmnEvent.get("end");
          Date Start_Date_Time = new Date();
          Date End_Date_Time = new Date();
          double Length=0d;
          boolean thisMonth=true;
          if (_start_date != JSONObject.NULL) {
            String date = _start_date.toString();

            date=date.replace("T"," ");
            Start_Date_Time = Simple.parse(date);
            int start_month = Start_Date_Time.getMonth();
            int start_year = Start_Date_Time.getYear()+1900;
            if (start_month != month) {
              thisMonth=false;
              continue;
            }
            if (start_year != year) {
              thisMonth=false;
              continue;
            }
          }
          if (_end_date != JSONObject.NULL) {
            String date = _end_date.toString();

            date=date.replace("T"," ");

            End_Date_Time = Simple.parse(date);


          }
          if (thisMonth) {
          if (_start_date != JSONObject.NULL&& _end_date != JSONObject.NULL){
            Length = End_Date_Time.getTime()-Start_Date_Time.getTime();
            Length=Length/1000/60/60;
          }
          String Description = pkmnEvent.getString("heading");

          String Decision = "Maybe";
          String Paid = "No";
          String location = "home";

            Event _event = new Event("", "",Name, Start_Date_Time, location, Description, Length, Decision, Paid);

            pokemonEvents.add(_event);
          }
        }
      } catch (Exception e) {
        pokemonEvents = new ArrayList<>();
        return pokemonEvents;
      }
    } catch (Exception e) {
      pokemonEvents = new ArrayList<>();
      return pokemonEvents;
    }
    int x = 0;
    return pokemonEvents;
  }

  @Override
  public int writeEventToFile(List<Event> Events, String path) throws IOException {
    int result = 0;
    File file = new File(path);
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      file.createNewFile();
    }
    PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);
    writer.println("Event_ID\tuser_id\tName\tDate\tLocation\tDescription\tLength\tDecision\tPaid");
    for (Event _event : Events) {
      writer.print(_event.getEvent_ID());
      writer.print("\t" +"'"+ _event.getUser_ID()+"'");
      writer.print("\t" +"'"+ _event.getName()+"'");
      writer.print("\t" + _event.getDate());
      writer.print("\t" + _event.getLocation());
      writer.print("\t" + _event.getDescription());
      writer.print("\t" + _event.getLength());
      writer.print("\t" + _event.getDecision());
      writer.print("\t" + _event.getPaid());
      writer.print("\n");
      result++;
    }
    writer.close();
    return result;
  }

  @Override
  public int writeEventToSQLInsert(List<Event> Events, String path) throws IOException {
    int result = 0;
    File file = new File(path);
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      file.createNewFile();
    }
    PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);
    writer.println("INSERT\t INTO \tEvent\t(Event_ID,\tuser_id,\tName,\tDate,\tLocation,\tDescription,\tLength,\tDecision,\tPaid)\n");
    writer.println("VALUES\n");
    for (Event _event : Events) {
      writer.print("(");
      writer.print(_event.getEvent_ID());
      writer.print(" , " + _event.getUser_ID());
      writer.print(" , " + _event.getName());
      writer.print(" , " + _event.getDate());
      writer.print(" , " + _event.getLocation());
      writer.print(" , " + _event.getDescription());
      writer.print(" , " + _event.getLength());
      writer.print(" , " + _event.getDecision());
      writer.print(" , " + _event.getPaid());
      writer.print(")\n");
      result++;
    }
    writer.close();
    return result;

  }
}
