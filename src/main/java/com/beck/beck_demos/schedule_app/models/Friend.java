package com.beck.beck_demos.schedule_app.models;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Friend implements Comparable<Friend>{
  private String Friend;
  private String Status;
  private Date Last_Updated;

  public Friend(){}

  public Friend( String Friend, String Status, Date Last_Updated) {
    this.Friend = Friend;
    this.Status = Status;
    this.Last_Updated = Last_Updated;
  }
  /**
   * <p> Gets the User_Two of the associated Friend_Line object </p>
   * @return the User_Two of this Friend_Line object.
   */
  public String getFriend() {
    return Friend;
  }

  /**
   * <p> Sets the User_Two of the associated Friend_Line object </p>
   * @param Friend the user_two of the friend_line,
   * throws IllegalArgumentException if User_Two under 3 characters or longer than 36 characters
   */
  public void setFriend(String Friend) {

    if(Friend.length()<4){
      throw new IllegalArgumentException("User_Two is too short.");
    }
    if(Friend.length()>36){
      throw new IllegalArgumentException("User_Two is too long.");
    }
    this.Friend = Friend;
  }
  /**
   * <p> Gets the Status of the associated Friend_Line object </p>
   * @return the Status of this Friend_Line object.
   */
  public String getStatus() {
    return Status;
  }

  /**
   * <p> Sets the Status of the associated Friend_Line object </p>
   * @param Status the status of the friend_line,
   * throws IllegalArgumentException if Status under 3 characters or longer than 25 characters
   */
  public void setStatus(String Status) {
    Status = Status.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Status.length()<4){
      throw new IllegalArgumentException("Status is too short.");
    }
    if(Status.length()>25){
      throw new IllegalArgumentException("Status is too long.");
    }
    this.Status = Status;
  }

  /**
   * <p> Gets the Last_Updated of the associated Friend_Line object </p>
   * @return the Last_Updated of this Friend_Line object.
   */
  public Date getLast_Updated() {
    return Last_Updated;
  }

  /**
   * <p> Sets the Last_Updated of the associated Friend_Line object </p>
   * @param Last_Updated the last_updated of the friend_line,
   * throws IllegalArgumentException if Last_Updated is outside of a logical range
   */
  public void setLast_Updated(Date Last_Updated) throws ParseException {
    String minDate = "01/01/1991";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date _minDate = df.parse(minDate);
    String maxDate = "12/31/2100";
    Date _maxDate = df.parse(maxDate);
    if (Last_Updated.compareTo(_minDate)<0){
      throw new IllegalArgumentException("Last_Updated Can Not Be Before 1991");
    }
    if (Last_Updated.compareTo(_maxDate)>0){
      throw new IllegalArgumentException("Last_Updated Can Not Be after 2100");
    }
    this.Last_Updated = Last_Updated;
  }
  @Override
  public int compareTo(@NotNull Friend o) {

    if (this.Friend.compareTo(o.Friend)<0){
      return -1;
    }
    else if(this.Friend.compareTo(o.Friend) > 0){
      return 1;
    }
    if (this.Status.compareTo(o.Status)<0){
      return -1;
    }
    else if(this.Status.compareTo(o.Status) > 0){
      return 1;
    }
    if (this.Last_Updated.compareTo(o.Last_Updated)<0){
      return -1;
    }
    else if(this.Last_Updated.compareTo(o.Last_Updated) > 0){
      return 1;
    }
    return 0;
  }

}
