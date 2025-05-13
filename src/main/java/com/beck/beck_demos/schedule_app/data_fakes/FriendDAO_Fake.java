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
    user1.setUser_ID("2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    User user2 = new User();
    user2.setUser_ID("3f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    User user3 = new User();
    user3.setUser_ID("4f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
    User user4 = new User();
    user4.setUser_ID("5f0e741b-8ac3-48ec-b51a-9fdaad52f9b5");
  Friend friend0 = new Friend( "2f0e741b-8ac3-48ec-b51a-9fdaad52f9b5", "Pending - Sent", new Date());
  Friend friend1 = new Friend( "3f0e741b-8ac3-48ec-b51a-9fdaad52f9b5", "Pending -Received", new Date());
  Friend friend2 = new Friend( "4f0e741b-8ac3-48ec-b51a-9fdaad52f9b5", "Approved", new Date());

  Friend_VM friend_VM0= new Friend_VM(friend0);
    friend_VM0.setUser1(user1);
    friend_VM0.setUser2(user2);
  Friend_VM friend_VM1= new Friend_VM(friend1);
    friend_VM1.setUser1(user2);
    friend_VM1.setUser2(user1);
  Friend_VM friend_VM2= new Friend_VM(friend2);
    friend_VM2.setUser1(user1);
    friend_VM2.setUser2(user3);

friendVMs.add(friend_VM0);
friendVMs.add(friend_VM1);
friendVMs.add(friend_VM2);

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
