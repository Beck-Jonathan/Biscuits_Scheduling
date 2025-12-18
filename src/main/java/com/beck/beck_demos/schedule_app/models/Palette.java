package com.beck.beck_demos.schedule_app.models;

import org.jetbrains.annotations.NotNull;

public class Palette  implements Comparable<Palette > {
  private String Palette_ID;
  private String User_ID;
  private Integer LineNo;
  private Color Color1;
  private Color Color2;
  private Color Color3;
  private Color Color4;
  private Color Color5;
  private Color Color6;
  private Color Color7;
  private Color Color8;
  private Color Color9;
  private Color Color10;
  private Color Color11;
  private Color Color12;

  public Palette (){}

  public Palette (String Palette_ID, String User_ID, Integer LineNo, Color Color1, Color Color2, Color Color3, Color Color4, Color Color5, Color Color6, Color Color7, Color Color8, Color Color9, Color Color10, Color Color11, Color Color12) {
    this.Palette_ID = Palette_ID;
    this.User_ID = User_ID;
    this.LineNo = LineNo;
    this.Color1 = Color1;
    this.Color2 = Color2;
    this.Color3 = Color3;
    this.Color4 = Color4;
    this.Color5 = Color5;
    this.Color6 = Color6;
    this.Color7 = Color7;
    this.Color8 = Color8;
    this.Color9 = Color9;
    this.Color10 = Color10;
    this.Color11 = Color11;
    this.Color12 = Color12;
  }
  public Palette (String Palette_ID, String User_ID, Integer LineNo, String Color1, String Color2, String Color3, String Color4, String Color5, String Color6, String Color7, String Color8, String Color9, String Color10, String Color11, String Color12) throws Exception {
    this.Palette_ID = Palette_ID;
    this.User_ID = User_ID;
    this.LineNo = LineNo;
    this.Color1 = new Color(Color1);
    this.Color2 = new Color(Color2);
    this.Color3 = new Color(Color3);
    this.Color4 = new Color(Color4);
    this.Color5 = new Color(Color5);
    this.Color6 = new Color(Color6);
    this.Color7 = new Color(Color7);
    this.Color8 = new Color(Color8);
    this.Color9 = new Color(Color9);
    this.Color10 = new Color(Color10);
    this.Color11 = new Color(Color11);
    this.Color12 = new Color(Color12);
  }

  /**
   * <p> Gets the Palette_ID of the associated Palette  object </p>
   * @return the Palette_ID of this Palette  object.
   */
  public String getPalette_ID() {
    return Palette_ID;
  }

  /**
   * <p> Sets the Palette_ID of the associated Palette  object </p>
   * @param Palette_ID the palette_id of the palette ,
   * throws IllegalArgumentException if Palette_ID under 3 characters or longer than 36 characters
   */
  public void setPalette_ID(String Palette_ID) {
    Palette_ID = Palette_ID.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Palette_ID.length()<4){
      throw new IllegalArgumentException("Palette_ID is too short.");
    }
    if(Palette_ID.length()>36){
      throw new IllegalArgumentException("Palette_ID is too long.");
    }
    this.Palette_ID = Palette_ID;
  }

  /**
   * <p> Gets the User_ID of the associated Palette  object </p>
   * @return the User_ID of this Palette  object.
   */
  public String getUser_ID() {
    return User_ID;
  }

  /**
   * <p> Sets the User_ID of the associated Palette  object </p>
   * @param User_ID the user_id of the palette ,
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
   * <p> Gets the LineNo of the associated Palette  object </p>
   * @return the LineNo of this Palette  object.
   */
  public Integer getLineNo() {
    return LineNo;
  }

  /**
   * <p> Sets the LineNo of the associated Palette  object </p>
   * @param LineNo the lineno of the palette ,
   * throws IllegalArgumentException if LineNo is negative or greater than 10,000
   */
  public void setLineNo(Integer LineNo) {
    if (LineNo<0||LineNo>10000){
      throw new IllegalArgumentException("LineNo Can Not Be Negative");
    }
    this.LineNo = LineNo;
  }

  /**
   * <p> Gets the Color1 of the associated Palette  object </p>
   * @return the Color1 of this Palette  object.
   */
  public Color getColor1() {
    return Color1;
  }

  /**
   * <p> Sets the Color1 of the associated Palette  object </p>
   * @param Color1 the color1 of the palette ,
   * throws IllegalArgumentException if Color1 under 3 characters or longer than 7 characters
   */
  public void setColor1(Color Color1) {
    this.Color1 = Color1;
  }

  /**
   * <p> Gets the Color2 of the associated Palette  object </p>
   * @return the Color2 of this Palette  object.
   */
  public Color getColor2() {
    return Color2;
  }

  /**
   * <p> Sets the Color2 of the associated Palette  object </p>
   * @param Color2 the color2 of the palette ,
   * throws IllegalArgumentException if Color2 under 3 characters or longer than 8 characters
   */
  public void setColor2(Color Color2) {
    this.Color2 = Color2;
  }

  /**
   * <p> Gets the Color3 of the associated Palette  object </p>
   * @return the Color3 of this Palette  object.
   */
  public Color getColor3() {
    return Color3;
  }

  /**
   * <p> Sets the Color3 of the associated Palette  object </p>
   * @param Color3 the color3 of the palette ,
   * throws IllegalArgumentException if Color3 under 3 characters or longer than 9 characters
   */
  public void setColor3(Color Color3) {
    this.Color3 = Color3;
  }

  /**
   * <p> Gets the Color4 of the associated Palette  object </p>
   * @return the Color4 of this Palette  object.
   */
  public Color getColor4() {
    return Color4;
  }

  /**
   * <p> Sets the Color4 of the associated Palette  object </p>
   * @param Color4 the color4 of the palette ,
   * throws IllegalArgumentException if Color4 under 3 characters or longer than 10 characters
   */
  public void setColor4(Color Color4) {
    this.Color4 = Color4;
  }

  /**
   * <p> Gets the Color5 of the associated Palette  object </p>
   * @return the Color5 of this Palette  object.
   */
  public Color getColor5() {
    return Color5;
  }

  /**
   * <p> Sets the Color5 of the associated Palette  object </p>
   * @param Color5 the color5 of the palette ,
   * throws IllegalArgumentException if Color5 under 3 characters or longer than 11 characters
   */
  public void setColor5(Color Color5) {
    this.Color5 = Color5;
  }

  /**
   * <p> Gets the Color6 of the associated Palette  object </p>
   * @return the Color6 of this Palette  object.
   */
  public Color getColor6() {
    return Color6;
  }

  /**
   * <p> Sets the Color6 of the associated Palette  object </p>
   * @param Color6 the color6 of the palette ,
   * throws IllegalArgumentException if Color6 under 3 characters or longer than 12 characters
   */
  public void setColor6(Color Color6) {
    this.Color6 = Color6;
  }

  /**
   * <p> Gets the Color7 of the associated Palette  object </p>
   * @return the Color7 of this Palette  object.
   */
  public Color getColor7() {
    return Color7;
  }

  /**
   * <p> Sets the Color7 of the associated Palette  object </p>
   * @param Color7 the color7 of the palette ,
   * throws IllegalArgumentException if Color7 under 3 characters or longer than 13 characters
   */
  public void setColor7(Color Color7) {
    this.Color7 = Color7;
  }

  /**
   * <p> Gets the Color8 of the associated Palette  object </p>
   * @return the Color8 of this Palette  object.
   */
  public Color getColor8() {
    return Color8;
  }

  /**
   * <p> Sets the Color8 of the associated Palette  object </p>
   * @param Color8 the color8 of the palette ,
   * throws IllegalArgumentException if Color8 under 3 characters or longer than 14 characters
   */
  public void setColor8(Color Color8) {
    this.Color8 = Color8;
  }

  /**
   * <p> Gets the Color9 of the associated Palette  object </p>
   * @return the Color9 of this Palette  object.
   */
  public Color getColor9() {
    return Color9;
  }

  /**
   * <p> Sets the Color9 of the associated Palette  object </p>
   * @param Color9 the color9 of the palette ,
   * throws IllegalArgumentException if Color9 under 3 characters or longer than 15 characters
   */
  public void setColor9(Color Color9) {
    this.Color9 = Color9;
  }

  /**
   * <p> Gets the Color10 of the associated Palette  object </p>
   * @return the Color10 of this Palette  object.
   */
  public Color getColor10() {
    return Color10;
  }

  /**
   * <p> Sets the Color10 of the associated Palette  object </p>
   * @param Color10 the color10 of the palette ,
   * throws IllegalArgumentException if Color10 under 3 characters or longer than 16 characters
   */
  public void setColor10(Color Color10) {
    this.Color10 = Color10;
  }

  /**
   * <p> Gets the Color11 of the associated Palette  object </p>
   * @return the Color11 of this Palette  object.
   */
  public Color getColor11() {
    return Color11;
  }

  /**
   * <p> Sets the Color11 of the associated Palette  object </p>
   * @param Color11 the color11 of the palette ,
   * throws IllegalArgumentException if Color11 under 3 characters or longer than 17 characters
   */
  public void setColor11(Color Color11) {
    this.Color11 = Color11;
  }

  /**
   * <p> Gets the Color12 of the associated Palette  object </p>
   * @return the Color12 of this Palette  object.
   */
  public Color getColor12() {
    return Color12;
  }

  /**
   * <p> Sets the Color12 of the associated Palette  object </p>
   * @param Color12 the color12 of the palette ,
   * throws IllegalArgumentException if Color12 under 3 characters or longer than 18 characters
   */
  public void setColor12(Color Color12) {
    this.Color12 = Color12;
  }
  @Override
  public int compareTo(@NotNull Palette  o) {
    if (this.Palette_ID.compareTo(o.Palette_ID)<0){
      return -1;
    }
    else if(this.Palette_ID.compareTo(o.Palette_ID) > 0){
      return 1;
    }
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0){
      return 1;
    }
    if (this.LineNo.compareTo(o.LineNo)<0){
      return -1;
    }
    else if(this.LineNo.compareTo(o.LineNo) > 0){
      return 1;
    }
    if (this.Color1.compareTo(o.Color1)<0){
      return -1;
    }
    else if(this.Color1.compareTo(o.Color1) > 0){
      return 1;
    }
    if (this.Color2.compareTo(o.Color2)<0){
      return -1;
    }
    else if(this.Color2.compareTo(o.Color2) > 0){
      return 1;
    }
    if (this.Color3.compareTo(o.Color3)<0){
      return -1;
    }
    else if(this.Color3.compareTo(o.Color3) > 0){
      return 1;
    }
    if (this.Color4.compareTo(o.Color4)<0){
      return -1;
    }
    else if(this.Color4.compareTo(o.Color4) > 0){
      return 1;
    }
    if (this.Color5.compareTo(o.Color5)<0){
      return -1;
    }
    else if(this.Color5.compareTo(o.Color5) > 0){
      return 1;
    }
    if (this.Color6.compareTo(o.Color6)<0){
      return -1;
    }
    else if(this.Color6.compareTo(o.Color6) > 0){
      return 1;
    }
    if (this.Color7.compareTo(o.Color7)<0){
      return -1;
    }
    else if(this.Color7.compareTo(o.Color7) > 0){
      return 1;
    }
    if (this.Color8.compareTo(o.Color8)<0){
      return -1;
    }
    else if(this.Color8.compareTo(o.Color8) > 0){
      return 1;
    }
    if (this.Color9.compareTo(o.Color9)<0){
      return -1;
    }
    else if(this.Color9.compareTo(o.Color9) > 0){
      return 1;
    }
    if (this.Color10.compareTo(o.Color10)<0){
      return -1;
    }
    else if(this.Color10.compareTo(o.Color10) > 0){
      return 1;
    }
    if (this.Color11.compareTo(o.Color11)<0){
      return -1;
    }
    else if(this.Color11.compareTo(o.Color11) > 0){
      return 1;
    }
    if (this.Color12.compareTo(o.Color12)<0){
      return -1;
    }
    else if(this.Color12.compareTo(o.Color12) > 0){
      return 1;
    }
    return 0;
  }

}

