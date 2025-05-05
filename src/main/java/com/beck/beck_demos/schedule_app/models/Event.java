package com.beck.beck_demos.schedule_app.models;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Event implements Comparable<Event> {
  private String Event_ID;
  private String Name;
  private Date Date;
  private String Location;
  private String Description;
  private Double Length;
  private String Decision;
  private String Paid;

  public Event(){}

  public Event(String Event_ID, String Name, Date Date, String Location, String description, Double Length, String Decision, String Paid) {

    this.Event_ID = Event_ID;
    this.Name = Name;
    this.Date = Date;
    this.Location = Location;
    this.Description = description;
    this.Length = Length;
    this.Decision = Decision;
    this.Paid = Paid;
  }

  public Event(String Event_ID) {

    this.Event_ID = Event_ID;
  }

  /**
   * <p> Gets the Event_ID of the associated Event object </p>
   * @return the Event_ID of this Event object.
   */
  public String getEvent_ID() {
    return Event_ID;
  }

  /**
   * <p> Sets the Event_ID of the associated Event object </p>
   * @param Event_ID the event_id of the event,
   * throws IllegalArgumentException if Event_ID under 3 characters or longer than 36 characters
   */
  public void setEvent_ID(String Event_ID) {
    Event_ID = Event_ID.replaceAll("[^.,!()-A-Za-z0-9 - ]","");
    if(Event_ID.length()<4){
      throw new IllegalArgumentException("Event_ID is too short.");
    }
    if(Event_ID.length()>36){
      throw new IllegalArgumentException("Event_ID is too long.");
    }
    this.Event_ID = Event_ID;
  }

  /**
   * <p> Gets the Name of the associated Event object </p>
   * @return the Name of this Event object.
   */
  public String getName() {
    return Name;
  }

  /**
   * <p> Sets the Name of the associated Event object </p>
   * @param Name the name of the event,
   * throws IllegalArgumentException if Name under 3 characters or longer than 100 characters
   */
  public void setName(String Name) {
    Name = Name.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Name.length()<4){
      throw new IllegalArgumentException("Name is too short.");
    }
    if(Name.length()>100){
      throw new IllegalArgumentException("Name is too long.");
    }
    this.Name = Name;
  }

  /**
   * <p> Gets the Date of the associated Event object </p>
   * @return the Date of this Event object.
   */
  public Date getDate() {
    return Date;
  }

  /**
   * <p> Sets the Date of the associated Event object </p>
   * @param Date the date of the event,
   * throws IllegalArgumentException if Date is outside of a logical range
   */
  public void setDate(Date Date) {
    this.Date = Date;
  }

  /**
   * <p> Gets the Location of the associated Event object </p>
   * @return the Location of this Event object.
   */
  public String getLocation() {
    return Location;
  }

  /**
   * <p> Sets the Location of the associated Event object </p>
   * @param Location the location of the event,
   * throws IllegalArgumentException if Location under 3 characters or longer than 100 characters
   */
  public void setLocation(String Location) {
    Location = Location.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Location.length()<4){
      throw new IllegalArgumentException("Location is too short.");
    }
    if(Location.length()>100){
      throw new IllegalArgumentException("Location is too long.");
    }
    this.Location = Location;
  }

  /**
   * <p> Gets the description of the associated Event object </p>
   * @return the description of this Event object.
   */
  public String getDescription() {
    return Description;
  }

  /**
   * <p> Sets the description of the associated Event object </p>
   * @param description the description of the event,
   * throws IllegalArgumentException if description under 3 characters or longer than 256 characters
   */
  public void setDescription(String description) {
    description = description.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(description.length()<4){
      throw new IllegalArgumentException("description is too short.");
    }
    if(description.length()>256){
      throw new IllegalArgumentException("description is too long.");
    }
    this.Description = description;
  }

  /**
   * <p> Gets the Length of the associated Event object </p>
   * @return the Length of this Event object.
   */
  public Double getLength() {
    return Length;
  }

  /**
   * <p> Sets the Length of the associated Event object </p>
   * @param Length the length of the event,
   * throws IllegalArgumentException if Length is outside of a logical range
   */
  public void setLength(Double Length) {
    if (Length<0||Length>10000){
      throw new IllegalArgumentException("Length Can Not Be Negative");
    }
    this.Length = Length;
  }

  /**
   * <p> Gets the Descision of the associated Event object </p>
   * @return the Descision of this Event object.
   */
  public String getDecision() {
    return Decision;
  }

  /**
   * <p> Sets the Descision of the associated Event object </p>
   * @param Descision the descision of the event,
   * throws IllegalArgumentException if Descision under 3 characters or longer than 6 characters
   */
  public void setDecision(String Descision) {
    if (Descision.equals("Going")||Descision.equals("Skipping")||Descision.equals("Maybe")) {
      this.Decision = Descision;
    } else {
      throw new IllegalArgumentException("invalid");
    }

  }

  /**
   * <p> Gets the Paid of the associated Event object </p>
   * @return the Paid of this Event object.
   */
  public String getPaid() {
    return Paid;
  }

  /**
   * <p> Sets the Paid of the associated Event object </p>
   * @param Paid the paid of the event,
   * throws IllegalArgumentException if Paid under 3 characters or longer than 6 characters
   */
  public void setPaid(String Paid) {
    if (Paid.equals("Yes")||Paid.equals("No")||Paid.equals("N/A")) {
      this.Paid = Paid;
    } else {
      throw new IllegalArgumentException("invalid");
    }
  }
  @Override
  public int compareTo(@NotNull Event o) {
    if (this.Event_ID.compareTo(o.Event_ID)<0){
      return -1;
    }
    else if(this.Event_ID.compareTo(o.Event_ID) > 0){
      return 1;
    }
    if (this.Name.compareTo(o.Name)<0){
      return -1;
    }
    else if(this.Name.compareTo(o.Name) > 0){
      return 1;
    }
    if (this.Date.compareTo(o.Date)<0){
      return -1;
    }
    else if(this.Date.compareTo(o.Date) > 0){
      return 1;
    }
    if (this.Location.compareTo(o.Location)<0){
      return -1;
    }
    else if(this.Location.compareTo(o.Location) > 0){
      return 1;
    }
    if (this.Description.compareTo(o.Description)<0){
      return -1;
    }
    else if(this.Description.compareTo(o.Description) > 0){
      return 1;
    }
    if (this.Length.compareTo(o.Length)<0){
      return -1;
    }
    else if(this.Length.compareTo(o.Length) > 0){
      return 1;
    }
    if (this.Decision.compareTo(o.Decision)<0){
      return -1;
    }
    else if(this.Decision.compareTo(o.Decision) > 0){
      return 1;
    }
    if (this.Paid.compareTo(o.Paid)<0){
      return -1;
    }
    else if(this.Paid.compareTo(o.Paid) > 0){
      return 1;
    }
    return 0;
  }

}
