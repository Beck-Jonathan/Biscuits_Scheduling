package com.beck.beck_demos.schedule_app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PaletteTest {

  private Palette _palette;

  @BeforeEach
  public void setup() {
    _palette = new Palette();
  }

  @AfterEach
  public void teardown() {
    _palette = null;
  }

  @Test
  public void testPaletteDefaultConstructorSetsNoVariables() {
    Palette _palette = new Palette();
    assertNull(_palette.getPalette_ID());
    assertNull(_palette.getUser_ID());
    assertNull(_palette.getLineNo());
    assertNull(_palette.getColor1());
    assertNull(_palette.getColor2());
    assertNull(_palette.getColor3());
    assertNull(_palette.getColor4());
    assertNull(_palette.getColor5());
    assertNull(_palette.getColor6());
    assertNull(_palette.getColor7());
    assertNull(_palette.getColor8());
    assertNull(_palette.getColor9());
    assertNull(_palette.getColor10());
    assertNull(_palette.getColor11());
    assertNull(_palette.getColor12());
  }

  @Test
  public void testPaletteParameterizedConstructorSetsAllVariables() throws Exception {
    Palette _palette = new Palette(
        "DjREmolGtVqQwUrAYmyFolokLUMtLgcgvIKj",
        "sPOuImwtGRTXaNDOwSRcWsyJJgQpWEPbPe",
        8398,
        new Color("#AABBCC"),
        new Color("#112233"),
        new Color("#445566"),
        new Color("#778899"),
        new Color("#FFAA00"),
        new Color("#00AAFF"),
        new Color("#ABCDEF"),
        new Color("#13579B"),
        new Color("#2468AC"),
        new Color("#DEADBE"),
        new Color("#BEEF00"),
        new Color("#C0FFEE")
    );

    assertEquals("#AABBCC", _palette.getColor1().getCode());
    assertEquals("#112233", _palette.getColor2().getCode());
    assertEquals("#445566", _palette.getColor3().getCode());
    assertEquals("#778899", _palette.getColor4().getCode());
    assertEquals("#FFAA00", _palette.getColor5().getCode());
    assertEquals("#00AAFF", _palette.getColor6().getCode());
    assertEquals("#ABCDEF", _palette.getColor7().getCode());
    assertEquals("#13579B", _palette.getColor8().getCode());
    assertEquals("#2468AC", _palette.getColor9().getCode());
    assertEquals("#DEADBE", _palette.getColor10().getCode());
    assertEquals("#BEEF00", _palette.getColor11().getCode());
    assertEquals("#C0FFEE", _palette.getColor12().getCode());
  }

  // ---------------- COLOR 1 ----------------

  @Test
  public void testColor1TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor1(new Color("#AA")));
  }

  @Test
  public void testColor1TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor1(new Color("#AABBCCDDEE")));
  }

  @Test
  public void testSetColor1() throws Exception {
    Color c = new Color("#AABBCC");
    _palette.setColor1(c);
    assertEquals(c, _palette.getColor1());
  }

  // ---------------- COLOR 2 ----------------

  @Test
  public void testColor2TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor2(new Color("#BB")));
  }

  @Test
  public void testColor2TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor2(new Color("#1122334455")));
  }

  @Test
  public void testSetColor2() throws Exception {
    Color c = new Color("#112233");
    _palette.setColor2(c);
    assertEquals(c, _palette.getColor2());
  }

  // ---------------- COLOR 3 ----------------

  @Test
  public void testColor3TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor3(new Color("#CC")));
  }

  @Test
  public void testColor3TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor3(new Color("#4455667788")));
  }

  @Test
  public void testSetColor3() throws Exception {
    Color c = new Color("#445566");
    _palette.setColor3(c);
    assertEquals(c, _palette.getColor3());
  }

  // ---------------- COLOR 4 ----------------

  @Test
  public void testSetColor4() throws Exception {
    Color c = new Color("#778899");
    _palette.setColor4(c);
    assertEquals(c, _palette.getColor4());
  }
  @Test
  public void testColor4TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor4(new Color("#CC")));
  }

  @Test
  public void testColor4TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor4(new Color("#4455667788")));
  }

  // ---------------- COLOR 5 ----------------

  @Test
  public void testSetColor5() throws Exception {
    Color c = new Color("#FFAA00");
    _palette.setColor5(c);
    assertEquals(c, _palette.getColor5());
  }
  @Test
  public void testColor5TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor5(new Color("#CC")));
  }

  @Test
  public void testColor5TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor5(new Color("#4455667788")));
  }

  // ---------------- COLOR 6 ----------------

  @Test
  public void testSetColor6() throws Exception {
    Color c = new Color("#00AAFF");
    _palette.setColor6(c);
    assertEquals(c, _palette.getColor6());
  }
  @Test
  public void testColor6TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor6(new Color("#CC")));
  }

  @Test
  public void testColor6TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor6(new Color("#4455667788")));
  }

  // ---------------- COLOR 7 ----------------

  @Test
  public void testSetColor7() throws Exception {
    Color c = new Color("#ABCDEF");
    _palette.setColor7(c);
    assertEquals(c, _palette.getColor7());
  }
  @Test
  public void testColor7TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor7(new Color("#CC")));
  }

  @Test
  public void testColor7TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor7(new Color("#4455667788")));
  }

  // ---------------- COLOR 8 ----------------

  @Test
  public void testSetColor8() throws Exception {
    Color c = new Color("#13579B");
    _palette.setColor8(c);
    assertEquals(c, _palette.getColor8());
  }
  @Test
  public void testColor8TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor8(new Color("#CC")));
  }

  @Test
  public void testColor8TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor8(new Color("#4455667788")));
  }



  // ---------------- COLOR 9 ----------------

  @Test
  public void testSetColor9() throws Exception {
    Color c = new Color("#2468AC");
    _palette.setColor9(c);
    assertEquals(c, _palette.getColor9());
  }
  @Test
  public void testColor9TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor9(new Color("#CC")));
  }

  @Test
  public void testColor9TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor9(new Color("#4455667788")));
  }


  // ---------------- COLOR 10 ----------------

  @Test
  public void testSetColor10() throws Exception {
    Color c = new Color("#DEADBE");
    _palette.setColor10(c);
    assertEquals(c, _palette.getColor10());
  }

  @Test
  public void testColor10TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor10(new Color("#CC")));
  }

  @Test
  public void testColor10TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor10(new Color("#4455667788")));
  }
  // ---------------- COLOR 11 ----------------

  @Test
  public void testSetColor11() throws Exception {
    Color c = new Color("#BEEF00");
    _palette.setColor11(c);
    assertEquals(c, _palette.getColor11());
  }
  @Test
  public void testColor11TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor11(new Color("#CC")));
  }

  @Test
  public void testColor11TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor11(new Color("#4455667788")));
  }

  // ---------------- COLOR 12 ----------------

  @Test
  public void testSetColor12() throws Exception {
    Color c = new Color("#C0FFEE");
    _palette.setColor12(c);
    assertEquals(c, _palette.getColor12());
  }
  @Test
  public void testColor12TooShort() {
    assertThrows(Exception.class,
        () -> _palette.setColor12(new Color("#CC")));
  }

  @Test
  public void testColor12TooLong() {
    assertThrows(Exception.class,
        () -> _palette.setColor12(new Color("#4455667788")));
  }

  // ---------------- COMPARE TO ----------------

  @Test
  public void testCompareTo() throws Exception {
    Palette smaller = new Palette();
    Palette bigger = new Palette();

    smaller.setPalette_ID("aaaa");
    bigger.setPalette_ID("bbbb");
    assertTrue(smaller.compareTo(bigger) < 0);

    smaller.setPalette_ID("bbbb");
    smaller.setUser_ID("2a0e31fe-2a5f-4e3e-8976-54124577b4ba");
    bigger.setUser_ID("3a0e31fe-2a5f-4e3e-8976-54124577b4ba");
    assertTrue(smaller.compareTo(bigger) < 0);

    smaller.setUser_ID("3a0e31fe-2a5f-4e3e-8976-54124577b4ba");
    smaller.setLineNo(10);
    bigger.setLineNo(20);
    assertTrue(smaller.compareTo(bigger) < 0);

    smaller.setLineNo(20);

    // Color1
    smaller.setColor1(new Color("#AAAAAA"));
    bigger.setColor1(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor1(new Color("#BBBBBB"));

// Color2
    smaller.setColor2(new Color("#AAAAAA"));
    bigger.setColor2(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor2(new Color("#BBBBBB"));

// Color3
    smaller.setColor3(new Color("#AAAAAA"));
    bigger.setColor3(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor3(new Color("#BBBBBB"));

// Color4
    smaller.setColor4(new Color("#AAAAAA"));
    bigger.setColor4(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor4(new Color("#BBBBBB"));

// Color5
    smaller.setColor5(new Color("#AAAAAA"));
    bigger.setColor5(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor5(new Color("#BBBBBB"));

// Color6
    smaller.setColor6(new Color("#AAAAAA"));
    bigger.setColor6(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor6(new Color("#BBBBBB"));

// Color7
    smaller.setColor7(new Color("#AAAAAA"));
    bigger.setColor7(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor7(new Color("#BBBBBB"));

// Color8
    smaller.setColor8(new Color("#AAAAAA"));
    bigger.setColor8(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor8(new Color("#BBBBBB"));

// Color9
    smaller.setColor9(new Color("#AAAAAA"));
    bigger.setColor9(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor9(new Color("#BBBBBB"));

// Color10
    smaller.setColor10(new Color("#AAAAAA"));
    bigger.setColor10(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor10(new Color("#BBBBBB"));

// Color11
    smaller.setColor11(new Color("#AAAAAA"));
    bigger.setColor11(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor11(new Color("#BBBBBB"));

// Color12
    smaller.setColor12(new Color("#AAAAAA"));
    bigger.setColor12(new Color("#BBBBBB"));
    assertTrue(smaller.compareTo(bigger) < 0);
    smaller.setColor12(new Color("#BBBBBB"));
  }
}
