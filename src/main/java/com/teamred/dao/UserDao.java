package com.teamred.dao;

import java.util.ArrayList;
import java.util.List;

import com.teamred.model.User;

public class UserDao {

    public List<User> getUsers() {
        return userGenerator(1000, "Max", "pass", 20);
    }    
    
    public User getUser(Integer userId) {
        List<User> users = userGenerator(1000, "Max", "pass", 20);

        return users.stream().filter(user -> userId.equals(user.getUserId())).findFirst().get();
    }

    public User addUser(Integer userId) {
        List<User> users = userGenerator(1000, "Max", "pass", 20);

        return users.stream().filter(user -> userId.equals(user.getUserId())).findFirst().get();
    }     

    public User updateUser(Integer userId) {
        List<User> users = userGenerator(1000, "Max", "pass", 20);

        return users.stream().filter(user -> userId.equals(user.getUserId())).findFirst().get();
    }    

    public User deleteUser(Integer userId) {
        List<User> users = userGenerator(1000, "Max", "pass", 20);

        return users.stream().filter(user -> userId.equals(user.getUserId())).findFirst().get();
    }    

    private List<User> userGenerator(int userCount, String defaultUserName, String defaultPassword, Integer defaultAge) {
        List<User> users = new ArrayList<>(userCount);

        for (int i=0; i<userCount; i++ ) {
            User user = new User ();
                user.setUserId(Integer.valueOf(i));
                user.setUserName(String.join(" ", defaultUserName, String.valueOf(i)));
                user.setPassword(String.join(" ", defaultPassword, String.valueOf(i)));
                user.setUserAge(Integer.valueOf(defaultAge + i));                
            users.add(user);
        }

        return users;
    }
}
