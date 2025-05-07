package com.beck.beck_demos.schedule_app.models;

import com.beck.beck_demos.schedule_app.models.Friend;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class FriendTest {
  private Friend _friend;
  @BeforeEach
  public void setup(){
    _friend = new Friend();
  }
  @AfterEach
  public void teardown(){
    _friend = null;
  }

  /**
   <p> Tests That the default constructor for the Friend_Line object works </p>
   */
  @Test
  public void testFriend_LineDefaultConstructorSetsNoVariables(){
    Friend _friend= new Friend();

    Assertions.assertNull(_friend.getFriend());
    Assertions.assertNull(_friend.getStatus());
    Assertions.assertNull(_friend.getLast_Updated());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Friend_Line object works </p>
   */
  @Test
  public void testFriend_LineParameterizedConstructorSetsAllVariables(){
    Date date = new Date();
    Friend _friend= new Friend(

        "oaRsoivytcrMhocQrsOhehPRlQknLSZrhH",
        "OqVkgXOsTnbQrLpUHQyHxIm",
        date

    );
    Assertions.assertEquals("oaRsoivytcrMhocQrsOhehPRlQknLSZrhH",_friend.getFriend());
    Assertions.assertEquals("OqVkgXOsTnbQrLpUHQyHxIm",_friend.getStatus());
    Assertions.assertEquals(date,_friend.getLast_Updated());
  }



  /**
   <p> Tests That the Setters for the Friend_Line.User_One field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testFriend_LineThrowsIllegalArgumentExceptionIfUser_OneTooShort(){
    String User_One = "KY";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_friend.setFriend(User_One);});
  }

  /**
   <p> Tests That the Setters for the Friend_Line.User_One field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testFriend_LineThrowsIllegalArgumentExceptionIfUser_OneTooLong(){
    String User_One = "eVZQxUdnUEDQcDLujDwwOVKmxXgxvmyIEmSfoL";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_friend.setFriend(User_One);});
  }

  /**
   <p> Tests That the Setters for the Friend_Line.User_One field work </p>
   */
  @Test
  public void testSetUser_OneSetsUser_One(){
    String User_One = "rPdbZqDTPLMWyyFLteHGWHQRCtHIXWIkVF";
    _friend.setFriend(User_One);
    Assertions.assertEquals(User_One,_friend.getFriend());
  }

  /**
   <p> Tests That the Setters for the Friend_Line.User_Two field can throw exceptions with invalid inputs </p>
   */


  /**
   <p> Tests That the Setters for the Friend_Line.Status field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testFriend_LineThrowsIllegalArgumentExceptionIfStatusTooShort(){
    String Status = "Tj";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_friend.setStatus(Status);});
  }

  /**
   <p> Tests That the Setters for the Friend_Line.Status field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testFriend_LineThrowsIllegalArgumentExceptionIfStatusTooLong(){
    String Status = "bIFEVgoLgwhepYMCiGQyJofBNhr";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_friend.setStatus(Status);});
  }

  /**
   <p> Tests That the Setters for the Friend_Line.Status field work </p>
   */
  @Test
  public void testSetStatusSetsStatus(){
    String Status = "rUKYOnWgncGycyISUgVQNuM";
    _friend.setStatus(Status);
    Assertions.assertEquals(Status,_friend.getStatus());
  }

  /**
   <p> Tests That the Setters for the Friend_Line.Last_Updated field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testFriend_LineThrowsIllegalArgumentExceptionIfLast_UpdatedTooSmall() throws ParseException{
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_friend.setLast_Updated(date);});
  }

  /**
   <p> Tests That the Setters for the Friend_Line.Last_Updated field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testFriend_LineThrowsIllegalArgumentExceptionIfLast_UpdatedTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_friend.setLast_Updated(date);});
  }

  /**
   <p> Tests That the Setters for the Friend_Line.Last_Updated field work </p>
   */
  @Test
  public void testFriend_LineSetsLast_Updated() throws ParseException {
    String strDate = "7/5/2025";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _friend.setLast_Updated(date);
    Assertions.assertEquals(date, _friend.getLast_Updated());
  }


  /**
   <p> Tests That the CompareTo Method for the Friend_Line object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField()throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Friend smaller = new Friend();
    Friend bigger = new Friend();

//to compare a smaller and larger User_Two
    smaller.setFriend("aaaa");
    bigger.setFriend("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_Two as equal.
    smaller.setFriend("bbbb");
//to compare a smaller and larger Status
    smaller.setStatus("aaaa");
    bigger.setStatus("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Status as equal.
    smaller.setStatus("bbbb");
//to compare a smaller and larger Last_Updated
    smaller.setLast_Updated(df.parse("01/01/2023"));
    bigger.setLast_Updated(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Last_Updated as equal.
    smaller.setLast_Updated(df.parse("01/01/2024"));
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

