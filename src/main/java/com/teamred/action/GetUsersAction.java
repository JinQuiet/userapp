package com.teamred.action;

import java.util.List;

import com.teamred.dao.UserDao;
import com.teamred.model.User;
import com.teamred.util.PathParser;
import com.teamred.util.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetUsersAction implements Action {
    
    UserDao userDao;

	public GetUsersAction(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String userId = PathParser.getPathParameter(request.getPathInfo());

		if (userId != null) {
			// Set form action attribute for the user form
			request.setAttribute("actionMode", RequestMethod.PUT.name());

			//populate model wth the data from the database			
			User user = userDao.getUser(Integer.parseInt(userId));
			request.setAttribute("user", user);

			//return view name
			return "user";
		} else {
			//get all users
			List<User> users = userDao.getUsers();
			//populate model
			request.setAttribute("users", users);
			//return view name
			return "users";
		}
	}
}