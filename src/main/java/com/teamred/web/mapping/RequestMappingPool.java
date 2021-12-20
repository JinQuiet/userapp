package com.teamred.web.mapping;

import java.util.ArrayList;
import java.util.List;

public class RequestMappingPool {

    private static List<RequestMapping> requestMapping = new ArrayList<RequestMapping>();

    static {
        //list AllUsers
        RequestMapping getUsers = new RequestMapping();
        getUsers.setActionName("getUsers");
        getUsers.setPath("/users");
        getUsers.setContentType("application/json");
        getUsers.setRequestMethod(RequestMethod.GET.name());
        getUsers.setPathParameters(new String[]{});        

        //Create 1 (one) user
        RequestMapping createUser = new RequestMapping();
        createUser.setActionName("createUser");
        createUser.setPath("/users");
        createUser.setContentType("application/json");        
        createUser.setRequestMethod(RequestMethod.POST.name());
        createUser.setPathParameters(new String[]{"userId"});           
        
        //get 1 (one) user data
        RequestMapping getUser = new RequestMapping();
        getUser.setActionName("getUser");
        getUser.setPath("/users/%integer%");
        getUser.setContentType("application/json");        
        getUser.setRequestMethod(RequestMethod.GET.name());
        getUser.setPathParameters(new String[]{"userId"});

        //update 1 (one) user data
        RequestMapping updateUser = new RequestMapping();
        updateUser.setActionName("updateUser");
        updateUser.setPath("/users/%integer%");
        updateUser.setContentType("application/json");        
        updateUser.setRequestMethod(RequestMethod.PUT.name());
        updateUser.setPathParameters(new String[]{"userId"});        

        //delete 1 (one) user data
        RequestMapping deleteUser = new RequestMapping();
        deleteUser.setActionName("deleteUser");
        deleteUser.setPath("/users/%integer%");
        deleteUser.setContentType("application/json");        
        deleteUser.setRequestMethod(RequestMethod.DELETE.name());
        deleteUser.setPathParameters(new String[]{"userId"});            

        //get data for a new user creation (stub with defauld values)
        RequestMapping newUser = new RequestMapping();
        newUser.setActionName("newUser");
        newUser.setPath("/users/newuser");
        newUser.setContentType("application/json");
        newUser.setRequestMethod(RequestMethod.GET.name());
        newUser.setPathParameters(new String[]{});        

        requestMapping.add(getUsers);
        requestMapping.add(createUser);
        requestMapping.add(getUser);
        requestMapping.add(updateUser);
        requestMapping.add(deleteUser);
        requestMapping.add(newUser);

    }

    public static List<RequestMapping> getRequestMapping() {
        return requestMapping;
    }
}