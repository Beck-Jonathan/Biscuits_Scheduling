package com.beck.beck_demos.schedule_app.controllers;

import com.beck.beck_demos.schedule_app.models.Event;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class NutrisliceAPITest {
  @Test
  public void setup() {

    //https://{yourDistrict}.api.nutrislice.com/menu/api/weeks/school/{yourSchool}/menu-type/{yourMeal}/{year}/{month}/{day}/?format=json`
    List<Event> meals = new ArrayList<>();
    Date date = new Date();
    Dotenv dotenv = Dotenv.load();
    String disctict = "cr";
    String school = "coolidge";
    String meal = "lunch";
    String year = String.valueOf(((Integer) date.getYear()) + 1900);
    String month = String.valueOf(((Integer) date.getMonth()) + 1);
    //String day = ((Integer) date.getDate()).toString();
    Integer day = 1;
    SimpleDateFormat Simple = new SimpleDateFormat("yyyy-MM-dd");
    //List<MissingPiece> setPieces = new ArrayList<>();
    String result = "";
    while (day<=100) {
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
            if (Start_Date_Time.getDay() == 0 || Start_Date_Time.getDay() == 6) {
              continue;
            }

            JSONArray menu_items = day4.getJSONArray("menu_items");
            String todaysFood = "";

            for (int j = 0; j < menu_items.length(); j++) {
              try {
                JSONObject menu_item2 = menu_items.getJSONObject(j);
                JSONObject food = menu_item2.getJSONObject("food");
                String name = food.getString("name");

                todaysFood = todaysFood + name + "\n";

              } catch (Exception e) {
                continue;
              }
            }
            String Name = "School Lunch at Coolidge";
            String location = "Coolidge";
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
        //return meals;
      }
      day=day+7;
    }
    int x = meals.size();
    //return meals;
  }
}
