package com.teamred.action;

import java.util.HashMap;
import java.util.Map;

import com.teamred.util.PathParser;

import jakarta.servlet.http.HttpServletRequest;

public class ActionFactory {

	private static ActionFactory instance;

	private Map<String, Action> actions;

	private ActionFactory() {
		actions = new HashMap<>();

		actions.put("index", new IndexAction());
		actions.put("users", new UsersAction());
	}

	public static ActionFactory getInstance() {
		if (instance == null) {
			return new ActionFactory();
		} else {
			return instance;
		}
	}

	public Action getAction(HttpServletRequest request) {

        //parses the name of the action like this, "users", from this (/users, /users/123, etc)
		String actionName = PathParser.getPathEntryPoint(request.getPathInfo());
		
		// pulls action, by name, from the hashTable of actions
		return actions.get(actionName);
	}
}