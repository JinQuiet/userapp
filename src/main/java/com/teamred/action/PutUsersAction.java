package com.teamred.action;

import com.teamred.dao.Dao;
import com.teamred.model.User;

import com.teamred.util.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PutUsersAction implements Action {
    
    Dao<User> userDao;

	public PutUsersAction(Dao<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Set form action attribute for the user form
			request.setAttribute("actionMode", RequestMethod.PUT.name());

			User user = userDao.get(Integer.parseInt(request.getParameter("userId")));
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password"));
			user.setUserAge(Integer.parseInt(request.getParameter("userAge")));			

			//populate model wth the data from the database			
			user = userDao.update(user);
			request.setAttribute("user", user);

			//return view name /users/{id} in this case
			return request.getPathInfo();
	}
}