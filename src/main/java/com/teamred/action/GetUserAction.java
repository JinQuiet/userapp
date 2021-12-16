package com.teamred.action;

import com.teamred.dao.UserDao;
import com.teamred.model.User;
import com.teamred.util.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetUserAction implements Action {
    
    UserDao userDao;

	public GetUserAction(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//create new user
		User user = new User();
		user.setUserId(12);
		user.setUserName("Username");
		user.setPassword("password");
		user.setUserAge(Integer.valueOf(18));

			// Set form action attribute for the user form
			request.setAttribute("actionMode", RequestMethod.POST.name());

			//populate model wth the data from the database			
			request.setAttribute("user", user);

			//return view name
			return "user";
	}
}