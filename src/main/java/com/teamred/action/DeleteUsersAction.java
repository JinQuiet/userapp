package com.teamred.action;

import com.teamred.dao.UserDao;
import com.teamred.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteUsersAction implements Action {
    
    UserDao userDao;

	public DeleteUsersAction(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = userDao.getUser(Integer.parseInt(request.getParameter("userId")));
		Integer deleteId = user.getUserId();
		user = userDao.deleteUser(deleteId);

		//return view name
		return "/users";
	}
}