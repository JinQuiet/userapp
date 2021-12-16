package com.teamred.action;

import com.teamred.dao.Dao;
import com.teamred.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUsersAction implements Action {
	private static final Logger LOGGER = LogManager.getLogger(DeleteUsersAction.class);
    
    Dao<User> userDao;

	public DeleteUsersAction(Dao<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = userDao.get(Integer.parseInt(request.getParameter("userId")));
		Integer deleteId = user.getUserId();
		user = userDao.delete(deleteId);
		LOGGER.info("user deleted");

		//return view name
		return "/users";
	}
}