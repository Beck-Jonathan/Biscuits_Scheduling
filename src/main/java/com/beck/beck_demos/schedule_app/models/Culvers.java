package com.beck.beck_demos.schedule_app.models;

import com.beck.beck_demos.shared.MyValidators;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Culvers implements Comparable<Culvers> {
  private String Culvers_ID;
  private String User_ID;
  private String Name;
  private String WebAddress;
  private boolean Is_Active;

  public Culvers(){}

  public Culvers(String Culvers_ID, String User_ID, String Name, String WebAddress, boolean Is_Active) {

    this.Culvers_ID = Culvers_ID;
    this.User_ID = User_ID;
    this.Name = Name;
    this.WebAddress = WebAddress;
    this.Is_Active = Is_Active;
  }

  public Culvers(String Culvers_ID) {

    this.Culvers_ID = Culvers_ID;
  }

  /**
   * <p> Gets the Culvers_ID of the associated Culvers object </p>
   * @return the Culvers_ID of this Culvers object.
   */
  public String getCulvers_ID() {
    return Culvers_ID;
  }

  /**
   * <p> Sets the Culvers_ID of the associated Culvers object </p>
   * @param Culvers_ID the culvers_id of the culvers,
   * throws IllegalArgumentException if Culvers_ID under 3 characters or longer than 36 characters
   */
  public void setCulvers_ID(String Culvers_ID) {
    if(Culvers_ID.length()<36){
      throw new IllegalArgumentException("Culvers_ID is too short.");
    }
    if(Culvers_ID.length()>36){
      throw new IllegalArgumentException("Culvers_ID is too long.");
    }
    this.Culvers_ID = Culvers_ID;
  }

  /**
   * <p> Gets the User_ID of the associated Culvers object </p>
   * @return the User_ID of this Culvers object.
   */
  public String getUser_ID() {
    return User_ID;
  }

  /**
   * <p> Sets the User_ID of the associated Culvers object </p>
   * @param User_ID the user_id of the culvers,
   * throws IllegalArgumentException if User_ID under 3 characters or longer than 36 characters
   */
  public void setUser_ID(String User_ID) {
    if(User_ID.length()<36){
      throw new IllegalArgumentException("User_ID is too short.");
    }
    if(User_ID.length()>36){
      throw new IllegalArgumentException("User_ID is too long.");
    }
    this.User_ID = User_ID;
  }

  /**
   * <p> Gets the Name of the associated Culvers object </p>
   * @return the Name of this Culvers object.
   */
  public String getName() {
    return Name;
  }

  /**
   * <p> Sets the Name of the associated Culvers object </p>
   * @param Name the name of the culvers,
   * throws IllegalArgumentException if Name under 3 characters or longer than 255 characters
   */
  public void setName(String Name) {
    Name = Name.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Name.length()<4){
      throw new IllegalArgumentException("Name is too short.");
    }
    if(Name.length()>255){
      throw new IllegalArgumentException("Name is too long.");
    }
    this.Name = Name;
  }

  /**
   * <p> Gets the WebAddress of the associated Culvers object </p>
   * @return the WebAddress of this Culvers object.
   */
  public String getWebAddress() {
    return WebAddress;
  }

  /**
   * <p> Sets the WebAddress of the associated Culvers object </p>
   * @param WebAddress the webaddress of the culvers,
   * throws IllegalArgumentException if WebAddress under 3 characters or longer than 255 characters
   */
  public void setWebAddress(String WebAddress) {
    if (!WebAddress.toLowerCase().contains("culvers")){
      throw new IllegalArgumentException("not a Culver's web address.");
    }
    if (!WebAddress.startsWith("https://")){
      throw new IllegalArgumentException("missing HTTPS:// prefix");
    }
    Matcher matcher = MyValidators.websitePattern.matcher(WebAddress);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Not a valid website");
    }
    this.WebAddress = WebAddress;
  }

  /**
   * <p> Gets the Is_Active of the associated Culvers object </p>
   * @return the Is_Active of this Culvers object.
   */
  public boolean getIs_Active() {
    return Is_Active;
  }

  /**
   * <p> Sets the Is_Active of the associated Culvers object </p>
   * @param Is_Active the is_active of the culvers,
   * throws IllegalArgumentException if Is_Active is outside of a logical range
   */
  public void setIs_Active(boolean Is_Active) {
    this.Is_Active = Is_Active;
  }
  @Override
  public int compareTo(@NotNull Culvers o) {
    if (this.Culvers_ID.compareTo(o.Culvers_ID)<0){
      return -1;
    }
    else if(this.Culvers_ID.compareTo(o.Culvers_ID) > 0){
      return 1;
    }
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0){
      return 1;
    }
    if (this.Name.compareTo(o.Name)<0){
      return -1;
    }
    else if(this.Name.compareTo(o.Name) > 0){
      return 1;
    }
    if (this.WebAddress.compareTo(o.WebAddress)<0){
      return -1;
    }
    else if(this.WebAddress.compareTo(o.WebAddress) > 0){
      return 1;
    }
    if (!this.Is_Active&&o.Is_Active){
      return -1;
    }
    if (this.Is_Active&&!o.Is_Active){
      return 1;
    }
    return 0;
  }

}

