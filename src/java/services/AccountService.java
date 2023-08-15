package services;

import dataaccess.UserDB;
import models.Role;
import models.User;

public class AccountService {
    
    public User login(String email, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if(user.getActive() == true){
            if (password.equals(user.getPassword())) {
                return user;
            }
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    public String Role(String email){
              UserDB userDB = new UserDB();

       try {
            User user = userDB.get(email);
          Role role = user.getRole();
          String roleType = role.getRoleName();
          return roleType;
        } catch (Exception e) {
        }
       return null;
  }
    
       public void deactivate(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(false);
        userDB.update(user);
    }
       
        public void activate(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(true);
        userDB.update(user);
    }
        
         public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        if (user != null) {
            userDB.delete(user);
        }
    }
}
