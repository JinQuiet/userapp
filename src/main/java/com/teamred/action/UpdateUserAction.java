package com.teamred.action;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamred.dao.Dao;
import com.teamred.json.Payload;
import com.teamred.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateUserAction implements Action {
    
    Dao<User> userDao;

	public UpdateUserAction(Dao<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//{"userId":"10", "userName":"Json", "password":"pass", "userAges":"55"}
		try {
			ObjectMapper mapper = new ObjectMapper();

			//should pass data into the map and wire values to the User through validators
			User user = mapper.readValue(request.getInputStream(), User.class);

			user = userDao.update(user); 

			//return updated user back if everything is fine
			//need error handling
			String json = mapper.writeValueAsString(user);

			System.out.println("ResultingJSONstring = " + json);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			ObjectMapper mapper = new ObjectMapper();

			Payload<String> payload = new Payload<String>("error", new HashMap<String, String>());
			String json = mapper.writeValueAsString(payload);

			System.out.println("ResultingJSONstring = " + json);			
			
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);				
		}

		return "200";
	}
}