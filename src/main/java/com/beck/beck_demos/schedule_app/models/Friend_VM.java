package com.beck.beck_demos.schedule_app.models;

public class Friend_VM extends Friend{
  private User User1;
  private User User2;

  public Friend_VM(){}

  public Friend_VM(Friend Friend){
    super( Friend.getFriend(), Friend.getStatus(), Friend.getLast_Updated());
  }

  public Friend_VM(Friend friend,User user1,User user2){
    super( friend.getFriend(),  friend.getStatus(),  friend.getLast_Updated());
    this.User1 = user1;
    this.User2 = user2;

  }

  public User getUser1() {
    return User1;
  }
  public void setUser1(User _user) {
    this.User1 = _user;
  }
  public User getUser2() {
    return User2;
  }
  public void setUser2(User _user) {
    this.User2 = _user;
  }
}
