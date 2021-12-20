package com.teamred.action;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamred.dao.Dao;
import com.teamred.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateUserAction implements Action {

	Dao<User> userDao;

	public CreateUserAction(Dao<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//{"userId":"10", "userName":"Json", "password":"pass", "userAges":"55"}
		try {
			ObjectMapper mapper = new ObjectMapper();

			//should pass data into the map and wire values to the User through validators
			User user = mapper.readValue(request.getInputStream(), User.class);

			user = userDao.add(user); 

			//return updated user back if everything is fine
			//need error handling
			String json = mapper.writeValueAsString(user);

			System.out.println("ResultingJSONstring = " + json);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "200";
	}
}