package com.beck.beck_demos.schedule_app.data_fakes;

import com.beck.beck_demos.schedule_app.iData.iFriendDAO;
import com.beck.beck_demos.schedule_app.models.Friend;
import com.beck.beck_demos.schedule_app.models.Friend_VM;
import com.beck.beck_demos.schedule_app.models.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FriendDAO_Fake implements iFriendDAO {
  private List<Friend_VM> friendVMs;
  public FriendDAO_Fake(){
  friendVMs = new ArrayList<>();
    User user1 = new User();
    user1.setUser_ID("72017ae6-968a-4bbd-904b-5c902ed9c072");
    User user2 = new User();
    user2.setUser_ID("a8c98f4d-5341-4b5e-869d-cb39db0a9854");
    User user3 = new User();
    user3.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
  Friend friend0 = new Friend( "2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5", "eJSvKDVv", new Date());
  Friend friend1 = new Friend( "cVJEYneL", "DiTUpLca", new Date());
  Friend friend2 = new Friend( "yyTjsuHC", "baJTxcnK", new Date());
  Friend friend3 = new Friend( "CLIiddsS", "aSxQSGSc", new Date());
  Friend friend4 = new Friend( "DBLiBBqt", "rVVWVUnE", new Date());
  Friend friend5 = new Friend("DBLiBBqt", "qEGgmjNB", new Date());
  Friend friend6 = new Friend( "DBLiBBqt", "kFgnTGNw", new Date());
  Friend friend7 = new Friend( "DBLiBBqt", "IaUKceMV", new Date());
  Friend friend8 = new Friend( "BDcCIxsi", "MDfkZmNo", new Date());
  Friend friend9 = new Friend( "saBTYNrp", "hNGxHNRG", new Date());
  Friend friend10 = new Friend( "WWAcVbdf", "IZmyjeDB", new Date());
  Friend friend11 = new Friend( "OdtGCAyw", "gdMGXHPC", new Date());
  Friend friend12 = new Friend( "FGZmobWh", "GYZWdryZ", new Date());
  Friend friend13 = new Friend( "FGZmobWh", "anRjTlpY", new Date());
  Friend friend14 = new Friend( "FGZmobWh", "cejotoIV", new Date());
  Friend friend15 = new Friend( "FGZmobWh", "fxolFhrn", new Date());
  Friend friend16 = new Friend( "eySgkayI", "TgiKcNpQ", new Date());
  Friend friend17 = new Friend( "dVcBvrAw", "kCfbJtri", new Date());
  Friend friend18 = new Friend( "mEyfXXJI", "dqcaCGtd", new Date());
  Friend friend19 = new Friend( "WnjGBWRA", "CJbYTwbh", new Date());
  Friend friend20 = new Friend( "VvcFmiWc", "tDxxOGXH", new Date());
  Friend friend21 = new Friend( "VvcFmiWc", "AUdETgLQ", new Date());
  Friend friend22 = new Friend( "VvcFmiWc", "hhejUKEb", new Date());
  Friend friend23 = new Friend( "VvcFmiWc", "QCoZZaVH", new Date());
  Friend_VM friend_VM0= new Friend_VM(friend0);
    friend_VM0.setUser1(user1);
  Friend_VM friend_VM1= new Friend_VM(friend1);
    friend_VM1.setUser1(user1);
  Friend_VM friend_VM2= new Friend_VM(friend2);
    friend_VM2.setUser1(user1);
  Friend_VM friend_VM3= new Friend_VM(friend3);
    friend_VM3.setUser1(user1);
  Friend_VM friend_VM4= new Friend_VM(friend4);
    friend_VM4.setUser1(user1);
  Friend_VM friend_VM5= new Friend_VM(friend5);
    friend_VM5.setUser1(user1);
  Friend_VM friend_VM6= new Friend_VM(friend6);
    friend_VM6.setUser1(user1);
  Friend_VM friend_VM7= new Friend_VM(friend7);
    friend_VM7.setUser1(user1);
  Friend_VM friend_VM8= new Friend_VM(friend8);
    friend_VM8.setUser1(user1);
  Friend_VM friend_VM9= new Friend_VM(friend9);
    friend_VM9.setUser1(user2);
  Friend_VM friend_VM10= new Friend_VM(friend10);
    friend_VM10.setUser1(user2);
  Friend_VM friend_VM11= new Friend_VM(friend11);
    friend_VM11.setUser1(user2);
  Friend_VM friend_VM12= new Friend_VM(friend12);
    friend_VM12.setUser1(user2);
  Friend_VM friend_VM13= new Friend_VM(friend13);
    friend_VM13.setUser1(user2);
  Friend_VM friend_VM14= new Friend_VM(friend14);
    friend_VM14.setUser1(user2);
  Friend_VM friend_VM15= new Friend_VM(friend15);
    friend_VM15.setUser1(user2);
  Friend_VM friend_VM16= new Friend_VM(friend16);
    friend_VM16.setUser1(user2);
  Friend_VM friend_VM17= new Friend_VM(friend17);
    friend_VM17.setUser1(user3);
  Friend_VM friend_VM18= new Friend_VM(friend18);
    friend_VM18.setUser1(user3);
  Friend_VM friend_VM19= new Friend_VM(friend19);
    friend_VM19.setUser1(user3);
  Friend_VM friend_VM20= new Friend_VM(friend20);
    friend_VM20.setUser1(user3);
  Friend_VM friend_VM21= new Friend_VM(friend21);
    friend_VM21.setUser1(user3);
  Friend_VM friend_VM22= new Friend_VM(friend22);
    friend_VM22.setUser1(user3);
  Friend_VM friend_VM23= new Friend_VM(friend23);
    friend_VM23.setUser1(user3);
friendVMs.add(friend_VM0);
friendVMs.add(friend_VM1);
friendVMs.add(friend_VM2);
friendVMs.add(friend_VM3);
friendVMs.add(friend_VM4);
friendVMs.add(friend_VM5);
friendVMs.add(friend_VM6);
friendVMs.add(friend_VM7);
friendVMs.add(friend_VM8);
friendVMs.add(friend_VM9);
friendVMs.add(friend_VM10);
friendVMs.add(friend_VM11);
friendVMs.add(friend_VM12);
friendVMs.add(friend_VM13);
friendVMs.add(friend_VM14);
friendVMs.add(friend_VM15);
friendVMs.add(friend_VM16);
friendVMs.add(friend_VM17);
friendVMs.add(friend_VM18);
friendVMs.add(friend_VM19);
friendVMs.add(friend_VM20);
friendVMs.add(friend_VM21);
friendVMs.add(friend_VM22);
friendVMs.add(friend_VM23);
Collections.sort(friendVMs);
}

  @Override
  public int add(Friend _friend, String user_id) throws SQLException {
    if (duplicateKey(_friend)){
      return 0;
    }
    if (exceptionKey(_friend)){
      throw new SQLException("error");
    }
    int size = friendVMs.size();
    Friend_VM friend_VM = new Friend_VM(_friend);
    friendVMs.add(friend_VM);
    int newsize = friendVMs.size();
    return newsize-size;
  }

  @Override
  public List<Friend_VM> getFriendebyUser(String User_ID) throws SQLException {
    List<Friend_VM> results = new ArrayList<>();
    for (Friend_VM friend : friendVMs){
      if (friend.getFriend().equals(User_ID)||friend.getUser1().getUser_ID().equals(User_ID)){
        results.add(friend);
      }
    }
    return results;
  }

  @Override
  public int approveFriend(String friend_id, String user_id) throws SQLException {

    int result =-1;
    for (Friend_VM friendVM : friendVMs) {
      if (
          (friendVM.getUser1().getUser_ID().equals(friend_id) && friendVM.getUser2().getUser_ID().equals(user_id))
              || (friendVM.getUser1().getUser_ID().equals(user_id) && friendVM.getUser2().getUser_ID().equals(friend_id))) {
        friendVM.setStatus("approved");
        result = 1;
        try {
          friendVM.setLast_Updated(new Date());
        } catch (ParseException e) {
        }
        break;
      }
    }
    if (result==-1){
      throw new SQLException();
    }
    return result;
  }

  @Override
  public int deleteFriend(String friend_id, String user_id) throws SQLException {
    int size = friendVMs.size();
    int location =-1;
    for (int i=0;i<friendVMs.size();i++){
      if ((friendVMs.get(i).getUser1().getUser_ID().equals(friend_id)&&friendVMs.get(i).getUser2().getUser_ID().equals(user_id))
          ||(friendVMs.get(i).getUser1().getUser_ID().equals(user_id)&&friendVMs.get(i).getUser2().getUser_ID().equals(friend_id)) ){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    friendVMs.remove(location);
    int newsize = friendVMs.size();
    return size-newsize;
  }

  private boolean duplicateKey(Friend _friend){
    return _friend.getFriend()!=null&&_friend.getFriend().contains("DUPLICATE");
  }
  private boolean exceptionKey(Friend _friend){
    return  _friend.getFriend()!=null&&_friend.getFriend().contains("EXCEPTION");
  }
}
