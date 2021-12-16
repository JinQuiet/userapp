package com.teamred.action;

import java.util.List;

import com.teamred.dao.UserDao;
import com.teamred.model.User;
import com.teamred.util.PathParser;
import com.teamred.util.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PutUsersAction implements Action {
    
    UserDao userDao;

	public PutUsersAction(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Set form action attribute for the user form
			request.setAttribute("actionMode", RequestMethod.PUT.name());

			User user = userDao.getUser(Integer.parseInt(request.getParameter("userId")));
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password"));
			user.setUserAge(Integer.parseInt(request.getParameter("userAge")));			

			//populate model wth the data from the database			
			user = userDao.updateUser(user);
			request.setAttribute("user", user);

			//return view name
			return request.getPathInfo();
	}
}