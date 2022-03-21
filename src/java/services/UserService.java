package services;

import dataaccess.UserDB;
import java.util.*;
import models.User;


public class UserService 
{
    public List<User> getAll() throws Exception 
    {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    //get new user
    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }
    
    //insert parameters to user data
    public void insert(String email, boolean active, String fname, String lname, String password, int role) throws Exception {
        User user = new User(email, active, fname, lname, password, role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    //update existing user parameters
    public void update(String email, boolean active, String fname, String lname, String password, int role) throws Exception {
        User user = new User(email, active, fname, lname, password, role);
        UserDB userDB = new UserDB();
        userDB.update(user);
    }
    
    //delete existing user parameters
    public void delete(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        UserDB userDB = new UserDB();
        userDB.delete(user);
    }
}
