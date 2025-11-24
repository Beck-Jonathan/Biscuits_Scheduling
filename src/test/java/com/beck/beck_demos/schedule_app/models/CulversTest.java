package com.beck.beck_demos.schedule_app.models;

import com.beck.beck_demos.schedule_app.models.Culvers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CulversTest {
  private Culvers _culvers;
  @BeforeEach
  public void setup(){
    _culvers = new Culvers();
  }
  @AfterEach
  public void teardown(){
    _culvers = null;
  }

  /**
   <p> Tests That the default constructor for the Culvers object works </p>
   */
  @Test
  public void testCulversDefaultConstructorSetsNoVariables(){
    Culvers _culvers= new Culvers();
    Assertions.assertNull(_culvers.getCulvers_ID());
    Assertions.assertNull(_culvers.getUser_ID());
    Assertions.assertNull(_culvers.getName());
    Assertions.assertNull(_culvers.getWebAddress());
    Assertions.assertFalse(_culvers.getIs_Active());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Culvers object works </p>
   */
  @Test
  public void testCulversParameterizedConstructorSetsAllVariables(){
    Culvers _culvers= new Culvers(
        "OsAWPlMxFfpZSwsPvZpiRHMgGIcUKZBpnC",
        "TWOmamLfZCHySIXbhwlmiIciVjLeARGFMb",
        "wDbvjUAAfFVdCSmrTsCPeTmWqaafIvBljLTykMWlhTlhsqIwMDvvcQCsmZUJTqSHZrbgfwqPLeJhiKixombCwMScSTxSytUkXPUXZykApsjFjqSUBWtVTCKdrvMknvRNoJaVnpCHScbfrdspMBVyLUOVrDdIvIYcRnfkhUmCkcHMEbrwjQrpwMGXxxKgUIsIapNEfbwofOfyBQxYIjgTbSjVPqEKZNElerLdPsWZJRxthvAIMNhlnPSFWeMAb",
        "OfFNnrLEJXXmwVOZEbQvGWTwmFlNIjNNbihgUCdbVrHBVluyLjiypKAmYpAgvjmxWtuSAWfDVNkPcrQKgbcmbivAXNpnEQNWoiGUiDUmvfBLJggLNTrvRWNoghgyPHICbERrYolPxMEJHSpvUqJiGsgEOglDAdFHuSGHOXBkVVqDtPdXULxBkjcMIDBKtCjWxuREIIHiXdqnWFhmjQVWBfLshpKvcrNaDGprcAFyUTgZTXJwkUyvOFLRdByOJ",
        true
    );
    Assertions.assertEquals("OsAWPlMxFfpZSwsPvZpiRHMgGIcUKZBpnC",_culvers.getCulvers_ID());
    Assertions.assertEquals("TWOmamLfZCHySIXbhwlmiIciVjLeARGFMb",_culvers.getUser_ID());
    Assertions.assertEquals("wDbvjUAAfFVdCSmrTsCPeTmWqaafIvBljLTykMWlhTlhsqIwMDvvcQCsmZUJTqSHZrbgfwqPLeJhiKixombCwMScSTxSytUkXPUXZykApsjFjqSUBWtVTCKdrvMknvRNoJaVnpCHScbfrdspMBVyLUOVrDdIvIYcRnfkhUmCkcHMEbrwjQrpwMGXxxKgUIsIapNEfbwofOfyBQxYIjgTbSjVPqEKZNElerLdPsWZJRxthvAIMNhlnPSFWeMAb",_culvers.getName());
    Assertions.assertEquals("OfFNnrLEJXXmwVOZEbQvGWTwmFlNIjNNbihgUCdbVrHBVluyLjiypKAmYpAgvjmxWtuSAWfDVNkPcrQKgbcmbivAXNpnEQNWoiGUiDUmvfBLJggLNTrvRWNoghgyPHICbERrYolPxMEJHSpvUqJiGsgEOglDAdFHuSGHOXBkVVqDtPdXULxBkjcMIDBKtCjWxuREIIHiXdqnWFhmjQVWBfLshpKvcrNaDGprcAFyUTgZTXJwkUyvOFLRdByOJ",_culvers.getWebAddress());
    Assertions.assertTrue(_culvers.getIs_Active());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Culvers object works </p>
   */
  @Test
  public void testCulversKeyedParameterizedConstructorSetsKeyedVariables(){
    Culvers _culvers= new Culvers(
        "KidfYlWufpAWJlleGKNdNwuVaydxVFtIiR"
    );
    Assertions.assertEquals("KidfYlWufpAWJlleGKNdNwuVaydxVFtIiR",_culvers.getCulvers_ID());
    Assertions.assertNull(_culvers.getUser_ID());
    Assertions.assertNull(_culvers.getName());
    Assertions.assertNull(_culvers.getWebAddress());
    Assertions.assertFalse(_culvers.getIs_Active());
  }

  /**
   <p> Tests That the Setters for the Culvers.Culvers_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCulversThrowsIllegalArgumentExceptionIfCulvers_IDTooShort(){
    String Culvers_ID = "Sb";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_culvers.setCulvers_ID(Culvers_ID);});
  }

  /**
   <p> Tests That the Setters for the Culvers.Culvers_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCulversThrowsIllegalArgumentExceptionIfCulvers_IDTooLong(){
    String Culvers_ID = "cBbPfZtZmhVrDuuwliPqmtRhZZADMSncdbegOv";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_culvers.setCulvers_ID(Culvers_ID);});
  }

  /**
   <p> Tests That the Setters for the Culvers.Culvers_ID field work </p>
   */
  @Test
  public void testSetCulvers_IDSetsCulvers_ID(){
    String Culvers_ID = "b38aaf6b-56db-49f0-b63e-a5995e803322";
    _culvers.setCulvers_ID(Culvers_ID);
    Assertions.assertEquals(Culvers_ID,_culvers.getCulvers_ID());
  }

  /**
   <p> Tests That the Setters for the Culvers.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCulversThrowsIllegalArgumentExceptionIfUser_IDTooShort(){
    String User_ID = "Gv";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_culvers.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Culvers.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCulversThrowsIllegalArgumentExceptionIfUser_IDTooLong(){
    String User_ID = "WtoGWQSnurkrCyNGyZZbQgrrVhPbTmsoCuccEl";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_culvers.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Culvers.User_ID field work </p>
   */
  @Test
  public void testSetUser_IDSetsUser_ID(){
    String User_ID = "088e7f33-cdc5-48e7-af9f-241705f9ff08";
    _culvers.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID,_culvers.getUser_ID());
  }

  /**
   <p> Tests That the Setters for the Culvers.Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCulversThrowsIllegalArgumentExceptionIfNameTooShort(){
    String Name = "NQ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_culvers.setName(Name);});
  }

  /**
   <p> Tests That the Setters for the Culvers.Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCulversThrowsIllegalArgumentExceptionIfNameTooLong(){
    String Name = "exTSQYLWAYGfTDIgYvudNJXecoomqhafcCOjkHFlbNLqCRdBxRgXnvhrrVdIkAjBtvgopqfvsYllOLkTiPyLNRCrfMVxElfpRQjFpJolrCOGidKsoCPAmbtnIdhqHluwfnKZVOnlwbLEPjWpmHKCAqTDAuKOgiMSpEXSFCLBPcbXVFgZTvpVUTRbiurwLtyiFnEynpEYFFaMVlqyAiCynZGmkdLFxUViFktuoMHiGFFMUyADoQRiQjoSdycqpVWPZ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_culvers.setName(Name);});
  }

  /**
   <p> Tests That the Setters for the Culvers.Name field work </p>
   */
  @Test
  public void testSetNameSetsName(){
    String Name = "OnGKaxVXgexGGxQjEuXWKGbqPiiwtjLvejauRdEQUseGRqDndyhyqaWvoBLVlUEUTEqFpHEcsQJXNFqvbwvjFEkMITRsLBvHUxwIaurxQNbuTZJXIdOYTdvVmlGyEHGksyEnUoMFxesYwCinsRsvdSbmmIlxLGDJFcmoBSsfRUoDQPNIMnOfAbgZGOraXObcKGqckgPRXSihurtOVMnccLIaLemjDGSgFHfVKyMiJTkYSyfJrPmAbtUPXFsNR";
    _culvers.setName(Name);
    Assertions.assertEquals(Name,_culvers.getName());
  }

  /**
   <p> Tests That the Setters for the Culvers.WebAddress field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCulversThrowsIllegalArgumentExceptionIfWebAddressTooShort(){
    String WebAddress = "AN";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_culvers.setWebAddress(WebAddress);});
  }

  /**
   <p> Tests That the Setters for the Culvers.WebAddress field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCulversThrowsIllegalArgumentExceptionIfWebAddressTooLong(){
    String WebAddress = "ApkOrKhoWtCnpmRnDtNChgMRFmXGgrTXOXQhbxcgrZdnESDwTSwlWPhjYJSUnfciDpUkfJdBKHnWwEKkRRPsNhFGPyojpPIMGNfgQUEUPdnLENJYKbfesdUXRtHoibNYEecTwSBlyVkdePRWFsaaTCKJsDJNuLwfJpqeSdmsNkkmBcjioFgqkbYJEOrgyFmaNNJwnZoZYFCakFGPnYvWgaEkOUlLDnvoJavjDQlxkxMRBmVYWKgpJagELFDOURGkT";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_culvers.setWebAddress(WebAddress);});
  }

  /**
   <p> Tests That the Setters for the Culvers.WebAddress field work </p>
   */
  @Test
  public void testSetWebAddressSetsWebAddress(){
    String WebAddress = "https://www.culvers.com/Cedar-rapids";
    _culvers.setWebAddress(WebAddress);
    Assertions.assertEquals(WebAddress,_culvers.getWebAddress());
  }

  /**
   <p> Tests That the Setters for the Culvers.Is_Active field work </p>
   */
  @Test
  public void testCulversSetsIs_ActiveasFalse(){
    boolean status = false;
    _culvers.setIs_Active(status);
    Assertions.assertEquals(status, _culvers.getIs_Active());
  }

  /**
   <p> Tests That the Setters for the Culvers.Is_Active field work </p>
   */
  @Test
  public void testCulversSetsIs_ActiveasTrue(){
    boolean status = true;
    _culvers.setIs_Active(status);
    Assertions.assertEquals(status, _culvers.getIs_Active());
  }


  /**
   <p> Tests That the CompareTo Method for the Culvers object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    Culvers smaller = new Culvers();
    Culvers bigger = new Culvers();
//to compare a smaller and larger Culvers_ID
    smaller.setCulvers_ID("b38aaf6b-56db-49f0-b63e-a5995e803322");
    bigger.setCulvers_ID("c38aaf6b-56db-49f0-b63e-a5995e803322");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Culvers_ID as equal.
    smaller.setCulvers_ID("c38aaf6b-56db-49f0-b63e-a5995e803322");
//to compare a smaller and larger User_ID
    smaller.setUser_ID("c6f2b623-a75e-4707-88db-c801c22a9c58");
    bigger.setUser_ID("d6f2b623-a75e-4707-88db-c801c22a9c58");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID("d6f2b623-a75e-4707-88db-c801c22a9c58");
//to compare a smaller and larger Name
    smaller.setName("aaaa");
    bigger.setName("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Name as equal.
    smaller.setName("bbbb");
//to compare a smaller and larger WebAddress
    smaller.setWebAddress("https://www.culvers.com/1");
    bigger.setWebAddress("https://www.culvers.com/2");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the WebAddress as equal.
    smaller.setWebAddress("https://www.culvers.com/2");
//to compare a smaller and larger Is_Active
    smaller.setIs_Active(false);
    bigger.setIs_Active(true);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Is_Active as equal.
    smaller.setIs_Active(true);
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

