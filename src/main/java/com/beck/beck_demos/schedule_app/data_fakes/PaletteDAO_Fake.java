package com.beck.beck_demos.schedule_app.data_fakes;

import com.beck.beck_demos.schedule_app.iData.iPaletteDAO;
import com.beck.beck_demos.schedule_app.models.Color;
import com.beck.beck_demos.schedule_app.models.Palette;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaletteDAO_Fake implements iPaletteDAO {
  private List<Palette> paletteVMs;
  public PaletteDAO_Fake() throws Exception {
    paletteVMs = new ArrayList<>();
    Palette palette0 = new Palette(
        "a3cdc15c-3d75-4ec0-a3a9-bf38a7e5b3b6", "9a655300-935b-42d6-926f-88d1995977f2", 15,
        "#A0B0C4", "#7F8A9D", "#D94A3C", "#3CB371", "#556B2F", "#4682B4",
        "#C71585", "#FF8C00", "#2F4F4F", "#8A2BE2", "#BC8F8F", "#20B2AA"
    );

    Palette palette1 = new Palette(
        "OnfkorpoNAMRsqQJKPGUwBCUyTagjVWCbWrL", "9a655300-935b-42d6-926f-88d1995977f2", 61,
        "#1E90FF", "#B22222", "#228B22", "#FFD700", "#483D8B", "#FF69B4",
        "#008080", "#A52A2A", "#5F9EA0", "#D2691E", "#9ACD32", "#FF4500"
    );

    Palette palette2 = new Palette(
        "mRvWmJBKhFusJMxOqeQOQxSldrYPagpKXQgs", "9a655300-935b-42d6-926f-88d1995977f2", 33,
        "#2E8B57", "#6A5ACD", "#CD5C5C", "#4682B4", "#DAA520", "#556B2F",
        "#8B4513", "#7B68EE", "#FF6347", "#3CB371", "#B0C4DE", "#8FBC8F"
    );

    Palette palette3 = new Palette(
        "ereymqYqIvgMTehFfbBCUXTYiOePGqTacUrn", "9a655300-935b-42d6-926f-88d1995977f2", 37,
        "#6495ED", "#DC143C", "#2F4F4F", "#F08080", "#9ACD32", "#6B8E23",
        "#FF7F50", "#483D8B", "#40E0D0", "#8B008B", "#BDB76B", "#5F9EA0"
    );

    Palette palette4 = new Palette(
        "OFNprXPafxqboWNakptjImjEPfnEHCTcgJYu", "9a655300-935b-42d6-926f-88d1995977f2", 51,
        "#8A2BE2", "#00CED1", "#FF1493", "#228B22", "#D2B48C", "#1E90FF",
        "#A0522D", "#20B2AA", "#FFDEAD", "#B22222", "#556B2F", "#9370DB"
    );

    Palette palette5 = new Palette(
        "BDMndYwFcnmWTPvqVZcZmUKGvfVBtfRWnxHB", "9a655300-935b-42d6-926f-88d1995977f2", 69,
        "#7FFFD4", "#FF8C00", "#9932CC", "#2E8B57", "#CD853F", "#4682B4",
        "#FF6347", "#8FBC8F", "#6A5ACD", "#BC8F8F", "#00FA9A", "#A52A2A"
    );

    Palette palette6 = new Palette(
        "dTOsQvrBbOsYhgUTouinjfblyXLxxMQkEsBH", "7b55ec9c-d961-483d-91dc-4a6742240a9e", 37,
        "#4169E1", "#B03060", "#3CB371", "#FFDAB9", "#8B0000", "#5F9EA0",
        "#DA70D6", "#556B2F", "#FF4500", "#87CEFA", "#2F4F4F", "#D8BFD8"
    );

    Palette palette7 = new Palette(
        "KRkEnjcWgqHdPwmnnnWZnLVVwDEAFNPCarPf", "7b55ec9c-d961-483d-91dc-4a6742240a9e", 53,
        "#00BFFF", "#CD5C5C", "#9ACD32", "#8B4513", "#4682B4", "#D2691E",
        "#6B8E23", "#FF69B4", "#20B2AA", "#A0522D", "#7B68EE", "#B8860B"
    );

    Palette palette8 = new Palette(
        "gLpbYEOLTTYVedsriQcGndifwcIVClmVpBLK", "7b55ec9c-d961-483d-91dc-4a6742240a9e", 64,
        "#483D8B", "#FF7F50", "#2E8B57", "#DAA520", "#8A2BE2", "#5F9EA0",
        "#CD853F", "#6495ED", "#FF1493", "#6A5ACD", "#556B2F", "#40E0D0"
    );

    Palette palette9 = new Palette(
        "tJamAcRiFvhJimcaehMRjTWqffmxwxVQJRUe", "7b55ec9c-d961-483d-91dc-4a6742240a9e", 66,
        "#1E90FF", "#B22222", "#228B22", "#FFD700", "#483D8B", "#FF69B4",
        "#008080", "#A52A2A", "#5F9EA0", "#D2691E", "#9ACD32", "#FF4500"
    );

    Palette palette10 = new Palette(
        "sYwyGMPDDobFTNdZJtFMSCBDvZgIeewGWVne", "6b20c381-1946-4b33-9812-6d052dd1d9ef", 50,
        "#7FFFD4", "#FF8C00", "#9932CC", "#2E8B57", "#CD853F", "#4682B4",
        "#FF6347", "#8FBC8F", "#6A5ACD", "#BC8F8F", "#00FA9A", "#A52A2A"
    );

    Palette palette11 = new Palette(
        "mDZtkfmsFkOYSShXfQYNPAifKWmgEOpascos", "6b20c381-1946-4b33-9812-6d052dd1d9ef", 21,
        "#4169E1", "#B03060", "#3CB371", "#FFDAB9", "#8B0000", "#5F9EA0",
        "#DA70D6", "#556B2F", "#FF4500", "#87CEFA", "#2F4F4F", "#D8BFD8"
    );

    paletteVMs.add(palette0);
    paletteVMs.add(palette1);
    paletteVMs.add(palette2);
    paletteVMs.add(palette3);
    paletteVMs.add(palette4);
    paletteVMs.add(palette5);
    paletteVMs.add(palette6);
    paletteVMs.add(palette7);
    paletteVMs.add(palette8);
    paletteVMs.add(palette9);
    paletteVMs.add(palette10);
    paletteVMs.add(palette11);
    Collections.sort(paletteVMs);

  }

  @Override
  public int add(Palette _palette) throws SQLException {
    if (duplicateKey(_palette)){
      return 0;
    }
    if (exceptionKey(_palette)){
      throw new SQLException("error");
    }
    int size = paletteVMs.size();

    paletteVMs.add(_palette);
    int newsize = paletteVMs.size();
    return newsize-size;
  }

  @Override
  public List<Palette> getPalettebyUser(String User_ID) throws SQLException {
    List<Palette> results = new ArrayList<>();
    if (User_ID == null){
      return results;
    }
    for (Palette palette : paletteVMs){
      if ((!User_ID.isEmpty()&& !palette.getUser_ID().equals(User_ID)))
      {
        continue;
      }

      results.add(palette);
    }
    return results;
  }

  @Override
  public int update(Palette oldPalette, Palette newPalette) throws SQLException {
    int location =-1;
    if (duplicateKey(oldPalette)){
      return 0;
    }
    if (exceptionKey(oldPalette)){
      throw new SQLException("error");
    }
    for (int i=0;i<paletteVMs.size();i++){
      if (paletteVMs.get(i).getPalette_ID().equals(oldPalette.getPalette_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }

    paletteVMs.set(location,newPalette);
    return 1;
  }

  @Override
  public int deletePalette(String Palette_ID) throws SQLException {
    int size = paletteVMs.size();
    int location =-1;
    for (int i=0;i<paletteVMs.size();i++){
      if (paletteVMs.get(i).getPalette_ID().equals(Palette_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException("Unable To Find Palette.");
    }
    paletteVMs.remove(location);
    int newsize = paletteVMs.size();
    return size-newsize;
  }

  @Override
  public Palette getPaletteByPrimaryKey(Palette _palette) throws SQLException {
    Palette result = null;
    for (Palette palette : paletteVMs) {
      if (palette.getPalette_ID().equals(_palette.getPalette_ID())){
        result = palette;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Palette not found");
    }
    return result;
  }

  @Override
  public int updateSingleColor(Palette _palette, int index) throws SQLException {
    Palette result = null;
    if (exceptionKey(_palette)){
      throw new SQLException("");
    }
    for (Palette palette : paletteVMs) {
      if (palette.getPalette_ID().equals(_palette.getPalette_ID())){
        result = palette;
        break;
      }
    }
    if (result ==null){
    return 0;
    }

    Color color = null;
    switch (index) {
      case 1:
        color = _palette.getColor1();
        result.setColor1(color);
        break;
      case 2:
        color = _palette.getColor2();
        result.setColor2(color);
        break;
      case 3:
        color = _palette.getColor3();
        result.setColor3(color);
        break;
      case 4:
        color = _palette.getColor4();
        result.setColor4(color);
        break;
      case 5:
        color = _palette.getColor5();
        result.setColor5(color);
        break;
      case 6:
        color = _palette.getColor6();
        result.setColor6(color);
        break;
      case 7:
        color = _palette.getColor7();
        result.setColor7(color);
        break;
      case 8:
        color = _palette.getColor8();
        result.setColor8(color);
        break;
      case 9:
        color = _palette.getColor9();
        result.setColor9(color);
        break;
      case 10:
        color = _palette.getColor10();
        result.setColor10(color);
        break;
      case 11:
        color = _palette.getColor11();
        result.setColor11(color);
        break;
      case 12:
        color = _palette.getColor12();
        result.setColor12(color);
        break;
      default:
        color = null; // or some fallback color
        break;
    }
    if (color ==null){
      return 0;
    }

    return 1;
  }

  private boolean duplicateKey(Palette _palette){
    return _palette.getColor1().getCode().equals("#AAAAAA") &&_palette.getColor2().getCode().equals("#BBBBBB");
  }
  private boolean exceptionKey(Palette _palette){
    return _palette.getColor1().getCode().equals("#CCCCCC") ;
  }
}
