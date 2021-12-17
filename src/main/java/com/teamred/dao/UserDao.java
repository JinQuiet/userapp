package com.teamred.dao;

import java.util.ArrayList;
import java.util.List;

import com.teamred.model.User;

public class UserDao implements Dao<User>{

    public List<User> getItems() {
        return userGenerator(100, "Max", "pass", 20);
    }    
    
    public User get(Integer userId) {

        List<User> users = userGenerator(100, "Max", "pass", 20);

        return users.stream().filter(user -> userId.equals(user.getUserId())).findFirst().get();
    }

    public User add(User user) {
        List<User> users = userGenerator(100, "Max", "pass", 20);

        //id is added by database
        user.setUserId(Integer.valueOf(13));
        users.add(user);

        return user;
    }     

    public User update(User user) {
        List<User> users = userGenerator(1000, "Max", "pass", 20);

        Integer userId = user.getUserId();

        return users.stream().filter(userFilter -> userId.equals(userFilter.getUserId())).findFirst().get();
    }    

    public User delete(Integer userId) {
        List<User> users = userGenerator(100, "Delete", "noPass", 0);

        return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().get();
    }    

    private List<User> userGenerator(int userCount, String defaultUserName, String defaultPassword, Integer defaultAge) {
        List<User> users = new ArrayList<>(userCount);

        for (int i=0; i<userCount; i++ ) {
            User user = new User ();
                user.setUserId(Integer.valueOf(i+1));
                user.setUserName(String.join(" ", defaultUserName, String.valueOf(i)));
                user.setPassword(String.join(" ", defaultPassword, String.valueOf(i)));
                user.setUserAge(Integer.valueOf(defaultAge + i));                
            users.add(user);
        }

        return users;
    }
}