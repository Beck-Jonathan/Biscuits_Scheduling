package com.beck.beck_demos.schedule_app.data_fakes;

import com.beck.beck_demos.schedule_app.iData.iUserDAO;
import com.beck.beck_demos.schedule_app.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mindrot.jbcrypt.BCrypt.gensalt;

public class UserDAO_Fake implements iUserDAO {
  private  List<User> users;
  public UserDAO_Fake(){
    users = new ArrayList<>();
    User user0 = new User("ADJFADKFJSA5412345DJFDKANFA215458123", "dTSPJjCO", "XNbwbckK!!".toCharArray(), "mWpKVEYk@gmail.com");
    User user1 = new User("BDJFADKFJSA5412345DJFDKANFA215458123", "xHDApUMY", "jNQFPWTR".toCharArray(), "ekektUmd@gmail.com");
    User user2 = new User("CDJFADKFJSA5412345DJFDKANFA215458123", "xOqYpDHU", "jahcUHBA".toCharArray(), "vRmtjGFI@gmail.com");
    User user3 = new User("DDJFADKFJSA5412345DJFDKANFA215458123", "eRceDjmK", "soBHCkld".toCharArray(), "CWZkwrDf@gmail.com");
    User user4 = new User("EDJFADKFJSA5412345DJFDKANFA215458123", "eRceDjmKdd", "soBHCkld!!".toCharArray(), "error@gmail.com");
    User user5 = new User("FDJFADKFJSA5412345DJFDKANFA215458123", "eRceDdjmKdd", "soB2HCkld!!".toCharArray(), "test@gmail.com");
    User user6 = new User("DUPLICATESA5412345DJFDKANFA215458123", "eRceDdjmKdd", "soB2HCkld!!".toCharArray(), "duplicateEmail@gmail.com");
    User user7 = new User("EXCEPTIONxSA5412345JFDKANFA215458123", "eRceDdjmKdd", "soB2HCkld!!".toCharArray(), "exceptionEmail@gmail.com");
    users.add(user0);
    users.add(user1);
    users.add(user2);
    users.add(user3);
    users.add(user4);
    users.add(user5);
    users.add(user6);
    users.add(user7);
    Collections.sort(users);
  }
  @Override
  public int add(User _user) throws SQLException {
    if (duplicateKey(_user)){
      return 0;
    }
    if (exceptionKey(_user)){
      throw new SQLException("error");
    }
    String user_id= String.valueOf(new UUID(36,36));

    _user.setUser_ID(user_id);
    int startsize = users.size();
    users.add(_user);
    int endsize = users.size();
    return endsize - startsize;
  }

  @Override
  public String getUserID(String email) throws SQLException {
    String result = "";
    for (User user : users) {
      if (user.getEmail().equals(email)) {
        result = user.getUser_ID();
      }
    }
    return result;
  }

  @Override
  public User getUserByPrimaryKey(User _user) throws SQLException {
    User result = null;
    for (User user : users) {
      if (user.getUser_ID().equals(_user.getUser_ID())) {
        result = user;
      }
    }
    return result;
  }

  @Override
  public String get_pw(String username) throws SQLException {
    User _user = new User();
    _user.setUser_Name(username);
    if (exceptionKey(_user)){
      throw new SQLException("error");
    }

    String result = "";
    for (User user : users) {
      if (user.getUser_Name().equals(username)) {
        result = new String(user.getUser_PW());
        break;
      }
    }
    String hashPassword = BCrypt.hashpw(result, BCrypt.gensalt(12));
    return hashPassword;
  }

  @Override
  public String getUserIDByUserName(String username) throws Exception {
    if (username.equals("EXCEPTION")){
      throw new Exception("error");
    }
    String result = "";
    for (User user : users) {
      if (user.getUser_Name().equals(username)) {
        result = user.getUser_ID();
      }
    }
    return result;
  }


  @Override
  public boolean usernameFree(String username) throws SQLException {
    boolean result = true;
    for (User user : users) {
      if (user.getUser_Name().equals(username)) {
        result=false;
        break;
      }
    }
    return result;
  }



  @Override
  public boolean emailFree(String email) throws SQLException {
    boolean result = true;
    for (User user : users) {
      if (user.getEmail().equals(email)) {
        result=false;
        break;
      }
    }
    return result;
  }


  @Override
  public boolean deleteUser(int userID) throws SQLException {
    return false;
  }

  @Override
  public boolean resetPW(User user) throws SQLException {
    return false;
  }

  @Override
  public List<String> getUser_Roles(User _user) throws SQLException {
    return List.of();
  }

  @Override
  public int addRole(String role, String userID) {
    return 0;
  }

  @Override
  public int yearRange(int user_ID) throws SQLException {
    return 0;
  }
  private boolean duplicateKey(User _user){
    return _user.getUser_Name().equals("DUPLICATE");
  }
  private boolean exceptionKey(User _user){
    return _user.getUser_Name().equals("EXCEPTION");
  }
}
