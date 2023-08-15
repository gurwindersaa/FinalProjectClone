/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Category;
import models.Item;
import models.Role;
import models.User;

/**
 *
 * @author gurwi
 */
public class UserServices {
    
    public User get(String email) {
        UserDB userdb = new UserDB();
        User user = userdb.get(email);
        return user;
    }
    
    public List<User> getAll() throws Exception {
        UserDB userdb = new UserDB();
        List<User> users = userdb.getAll();
        return users;
    }
    
    public void insert(String email, String fname, String lname, String password, Role role) throws Exception {
        boolean active = true;
        User user = new User(email, active, fname, lname, password);
        user.setRole(role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(String email, boolean active, String fname, String lname, String password) throws Exception {
        UserDB userDB = new UserDB();
        RoleDB roledb = new RoleDB();
        User user = userDB.get(email);
        user.setActive(true);
        user.setEmail(email);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setPassword(password);
        userDB.update(user);
        
    }
    
 
    
}
