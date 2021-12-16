package com.teamred.action;

import java.util.HashMap;
import java.util.Map;

import com.teamred.dao.UserDao;
import com.teamred.util.PathParser;
import com.teamred.util.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

public class ActionFactory {

	private static ActionFactory instance;

	private Map<String, Action> actions;

	private UserDao userDao;

	private ActionFactory() {
		userDao = new UserDao();
		
		actions = new HashMap<>();

		actions.put("GETindex", new GetIndexAction());

		actions.put("GETusers", new GetUsersAction(userDao));
		
		actions.put("GETuser", new GetUserAction(userDao));
		actions.put("PUTusers", new PutUsersAction(userDao));
		actions.put("POSTusers", new PostUsersAction(userDao));
		actions.put("DELETEusers", new DeleteUsersAction(userDao));		
	}

	public static ActionFactory getInstance() {
		if (instance == null) {
			return new ActionFactory();
		} else {
			return instance;
		}
	}

	public Action getAction(HttpServletRequest request) {
        //parses the name of the entry point path like this, "users", from this (/users, /users/123, etc)
		String actionName = PathParser.getPathEntryPoint(request.getPathInfo());

		//request methon that came from the browser
		String requestMethod = request.getMethod();

		//additinal information about request method
		String actualHttpMethod = request.getParameter("actualHttpMethod");

		//if additional information is true - rename requestMethod
		if ((actualHttpMethod != null) && 
			((actualHttpMethod.equals(RequestMethod.PUT.name()) || actualHttpMethod.equals(RequestMethod.DELETE.name())))) {
			requestMethod = actualHttpMethod;
		}

		//building actionName
 		switch (RequestMethod.valueOf(requestMethod)) {
			case GET :  {
				actionName = RequestMethod.GET.name().concat(actionName);
				break;
			}
			case POST : {
				actionName = RequestMethod.POST.name().concat(actionName);
				break;
			}
			case PUT : {
				actionName = RequestMethod.PUT.name().concat(actionName);
				break;
			}
			case DELETE : {
				actionName = RequestMethod.DELETE.name().concat(actionName);
				break;
			}
			default :{
				actionName = "GETindex";
				break;
			}
		}

		//process cases when there is no action available
		Action action = actions.get(actionName);

		return action;
	}
}