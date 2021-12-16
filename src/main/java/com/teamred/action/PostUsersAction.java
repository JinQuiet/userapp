package com.teamred.action;

import com.teamred.dao.UserDao;
import com.teamred.model.User;

import com.teamred.util.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PostUsersAction implements Action {

	UserDao userDao;

	public PostUsersAction(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Set form action attribute for the user form
		request.setAttribute("actionMode", RequestMethod.PUT.name());

		// populate new user with data and send it to userDao
		User user = new User();

		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setUserAge(Integer.parseInt(request.getParameter("userAge")));

		user = userDao.addUser(user);

		// populate model with the fresh data from the database
		user = userDao.getUser(user.getUserId());

		request.setAttribute("user", user);

		// return view name
		return "/users/" + String.valueOf(user.getUserId());
	}
}