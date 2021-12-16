package com.teamred.action;

import com.teamred.dao.Dao;
import com.teamred.model.User;

import com.teamred.util.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PostUsersAction implements Action {

	Dao<User> userDao;

	public PostUsersAction(Dao<User> userDao) {
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

		user = userDao.add(user);

		// populate model with the fresh data from the database
		user = userDao.get(user.getUserId());

		request.setAttribute("user", user);

		// return view name /users/{new user id} in this case
		return "/users/" + String.valueOf(user.getUserId());
	}
}