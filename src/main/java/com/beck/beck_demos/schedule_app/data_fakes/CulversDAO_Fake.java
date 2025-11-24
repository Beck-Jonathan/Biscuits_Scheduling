package com.beck.beck_demos.schedule_app.data_fakes;

import com.beck.beck_demos.schedule_app.iData.iCulversDAO;
import com.beck.beck_demos.schedule_app.models.Culvers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CulversDAO_Fake implements iCulversDAO {
  private List<Culvers> culversVMs;
  public CulversDAO_Fake() {
    culversVMs = new ArrayList<>();
    Culvers culvers0 = new Culvers("OYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG", "aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG", "Iowa", "yQLyfRws", true);
    Culvers culvers1 = new Culvers("SXLeGFBAKtJtSbYmPvlEnPomKgMDSlEvmTKF", "aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG", "xfHUMAFN", "Iowa", true);
    Culvers culvers2 = new Culvers("cYQQecZDohRpqfbhTcVxsmpdLtfcPqjwCTGu", "aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG", "xxIowa", "rrDQmXHi", false);
    Culvers culvers3 = new Culvers("PGhUNdrYfXITqxbBmyEIcOWgtSXuPMhiuSpg", "aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG", "tyRZcCod", "gYVHZJoi", false);
    Culvers culvers4 = new Culvers("iPiRKIHvtFEqtQbnSaBIhPwUykJnZcHoTwGK", "aYQMhXiJvWtCFrHGHCaHPRfaYEjPbEChWymG", "xVOjrBFY", "ISaXLbZU", false);
    Culvers culvers5 = new Culvers("twmFSsRdWsKvOkkUnyjsYrdeAtKwmOeefOQE", "aXLeGFBAKtJtSbYmPvlEnPomKgMDSlEvmTKF", "JxojDpLH", "vmneMBuh", false);
    Culvers culvers6 = new Culvers("GpqtaudHaKKPlWJIBdFkmnYPZBnemLFQYuQj", "aXLeGFBAKtJtSbYmPvlEnPomKgMDSlEvmTKF", "eFtmugvW", "tlOfyAFi", true);
    Culvers culvers7 = new Culvers("xKFrXVLxrQkjdcCWGMELjsMELnZAGiTRZtqP", "aXLeGFBAKtJtSbYmPvlEnPomKgMDSlEvmTKF", "oGqlSucO", "QQlGPVbe", true);
    Culvers culvers8 = new Culvers("SJHpkOErGtJuvQIovNLvqOXIXbybPvTJyZAq", "aXLeGFBAKtJtSbYmPvlEnPomKgMDSlEvmTKF", "KkJvVGVW", "TGIqWRsB", true);
    Culvers culvers9 = new Culvers("yVuVAYVTmRLcNJWhCQwBXAhcwGaUJWtxKlqw", "aXLeGFBAKtJtSbYmPvlEnPomKgMDSlEvmTKF", "vUgvoQQQ", "ZNgthTUS", false);
    Culvers culvers10 = new Culvers("FbXSfgRtsOiVdgWHwsBWVbBcptomOOurtljA", "adelNWBrVPVHUNorLjlxlGmwZKMOaignojAC", "sFSeSNRZ", "awWDKGvj", false);
    Culvers culvers11 = new Culvers("kdelNWBrVPVHUNorLjlxlGmwZKMOaignojAC", "adelNWBrVPVHUNorLjlxlGmwZKMOaignojAC", "SdaOETMG", "OJBlfrfi", false);
    Culvers culvers12 = new Culvers("fXFqJWHXRqZYXCMExFvBvJAWxBbUXIhhrnAW", "adelNWBrVPVHUNorLjlxlGmwZKMOaignojAC", "AddOnPKf", "OpHFBAio", false);
    Culvers culvers13 = new Culvers("kiKXDaEuRlVUkMRllNRGxrIvuoOEMfFTZvEX", "adelNWBrVPVHUNorLjlxlGmwZKMOaignojAC", "DwFXDSsT", "OscnnbcX", false);
    Culvers culvers14 = new Culvers("jPWlUgTKnIXaopgcgufQYDvgFDvrgYJbcvuZ", "adelNWBrVPVHUNorLjlxlGmwZKMOaignojAC", "dmZyLBLJ", "xUywGgmE", true);

    culversVMs.add(culvers0);
    culversVMs.add(culvers1);
    culversVMs.add(culvers2);
    culversVMs.add(culvers3);
    culversVMs.add(culvers4);
    culversVMs.add(culvers5);
    culversVMs.add(culvers6);
    culversVMs.add(culvers7);
    culversVMs.add(culvers8);
    culversVMs.add(culvers9);
    culversVMs.add(culvers10);
    culversVMs.add(culvers11);
    culversVMs.add(culvers12);
    culversVMs.add(culvers13);
    culversVMs.add(culvers14);
    Collections.sort(culversVMs);
  }

  @Override
  public int add(Culvers _culvers) throws SQLException {
    if (duplicateKey(_culvers)){
      return 0;
    }
    if (exceptionKey(_culvers)){
      throw new SQLException("error");
    }
    int size = culversVMs.size();
    culversVMs.add(_culvers);
    int newsize = culversVMs.size();
    return newsize-size;
  }

  @Override
  public List<Culvers> getAllCulvers(int offset, int limit, String search, String User_ID) throws SQLException {
    List<Culvers> results = new ArrayList<>();
    for (Culvers culvers : culversVMs){
      if ((User_ID.isEmpty()||culvers.getUser_ID().equals(User_ID)))
      {
        if (search.isEmpty() || culvers.getCulvers_ID().contains(search)|| culvers.getUser_ID().contains(search)|| culvers.getName().contains(search)|| culvers.getWebAddress().contains(search)){
          results.add(culvers);
        }
      }
    }
    return results;
  }

  @Override
  public int deleteCulvers(String Culvers_ID) throws SQLException {
    int location = -1;
    for (int i = 0; i < culversVMs.size(); i++) {
      if (culversVMs.get(i).getCulvers_ID().equals(Culvers_ID)) {
        location = i;
        break;
      }
    }
    if (location == -1) {
      throw new SQLException("Unable To Find Culvers.");
    }
    if (culversVMs.get(location).getIs_Active()) {
      culversVMs.get(location).setIs_Active(false);
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public int undeleteCulvers(String Culvers_ID) throws SQLException {
    int location = -1;
    for (int i = 0; i < culversVMs.size(); i++) {
      if (culversVMs.get(i).getCulvers_ID().equals(Culvers_ID)) {
        location = i;
        break;
      }
    }
    if (location == -1) {
      throw new SQLException("Unable To Find Culvers.");
    }
    if (!culversVMs.get(location).getIs_Active()) {
      culversVMs.get(location).setIs_Active(true);
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public int update(Culvers oldCulvers, Culvers newCulvers) throws SQLException {
    int location =-1;
    if (duplicateKey(oldCulvers)){
      return 0;
    }
    if (exceptionKey(oldCulvers)){
      throw new SQLException("error");
    }
    for (int i=0;i<culversVMs.size();i++){
      if (culversVMs.get(i).getCulvers_ID().equals(oldCulvers.getCulvers_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    culversVMs.set(location,newCulvers);
    return 1;
  }

  @Override
  public Culvers getCulversByPrimaryKey(Culvers _culvers) throws SQLException {
    Culvers result = null;
    for (Culvers culvers : culversVMs) {
      if (culvers.getCulvers_ID().equals(_culvers.getCulvers_ID())){
        result = culvers;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Culvers not found");
    }
    return result;
  }

  @Override
  public int getCulversCount(String search, String User_ID) throws SQLException {
    List<Culvers> results = new ArrayList<>();
    for (Culvers culvers : culversVMs){
      if ((User_ID.isEmpty()||culvers.getUser_ID().equals(User_ID)))
      {
        if (search.isEmpty() || culvers.getCulvers_ID().contains(search)|| culvers.getUser_ID().contains(search)|| culvers.getName().contains(search)|| culvers.getWebAddress().contains(search)){
          results.add(culvers);
        }
      }
    }
    return results.size();
  }

  @Override
  public List<Culvers> getActiveCulversByUserID(String User_ID) throws SQLException {
    return List.of();
  }

  private boolean duplicateKey(Culvers _culvers){

    if (_culvers.getCulvers_ID()!=null){
      return _culvers.getCulvers_ID().contains("DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    }
    return _culvers.getName().contains("DUPLICATE");
  }
  private boolean exceptionKey(Culvers _culvers){
    if (_culvers.getCulvers_ID()!=null){
      return _culvers.getCulvers_ID().contains("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    }
    return _culvers.getName().contains("EXCEPTION");
  }
}
