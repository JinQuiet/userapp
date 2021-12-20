package com.teamred.action;

import java.util.HashMap;
import java.util.Map;

import com.teamred.dao.Dao;
import com.teamred.dao.UserDao;
import com.teamred.model.User;

public class ActionFactory {

	private static ActionFactory instance;

	private Map<String, Action> actions;

	private Dao<User> userDao;

	private ActionFactory() {
		userDao = new UserDao();
		
		actions = new HashMap<>();

		actions.put("newUser", new NewUserAction(userDao));		

		actions.put("getUser", new GetUserAction(userDao));
		actions.put("getUsers", new GetUsersAction(userDao));
		
		actions.put("createUser", new CreateUserAction(userDao));
		actions.put("updateUser", new UpdateUserAction(userDao));		
		actions.put("deleteUser", new DeleteUserAction(userDao));				

	}

	public static ActionFactory getInstance() {
		if (instance == null) {
			return new ActionFactory();
		} else {
			return instance;
		}
	}

	public Action getAction(String actionName) {

		System.out.println("====Entered ActionFactory");

		return actions.get(actionName);
	}
}