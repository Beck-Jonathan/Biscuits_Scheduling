package com.beck.beck_demos.schedule_app.models;

import org.jetbrains.annotations.NotNull;

import java.util.FormatterClosedException;

public class Color implements Comparable <Color>{
  private String code;
  public Color(String code) throws Exception {
    try {
      this.setCode(code);
    }
    catch(Exception e){
      throw new Exception("Invalid Color \n\n"+e.getMessage());
    }
  }
  public String getCode() {
    return code;
  }

  public void setCode(String code) throws Exception{
    if (code==null||code.length()!=7){
      throw new Exception("Invalid code");
    }
    this.code = code;
  }

  @Override
  public int compareTo(@NotNull Color o) {
    return this.code.compareTo(o.code);
  }
}
