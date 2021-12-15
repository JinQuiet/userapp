package com.teamred.action;

import java.util.List;

import com.teamred.dao.UserDao;
import com.teamred.model.User;
import com.teamred.util.PathParser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UsersAction implements Action {
    
    UserDao userDao;

    public UsersAction(){
        this.userDao = new UserDao();
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<User> users = userDao.getUsers();
		request.setAttribute("users", users);

		String userId = PathParser.getPathParameter(request.getPathInfo());

		if (userId!=null) {

			User user = userDao.getUser(Integer.parseInt(userId));

			request.setAttribute("user", user);			
			return "user";
		}

		return "users";
	}
}