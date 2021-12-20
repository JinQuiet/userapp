package com.teamred.action;

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

		Payload<User> payload = null;
		String json = "";

		try {
			ObjectMapper mapper = new ObjectMapper();

			//should pass data into the map and wire values to the User through validators
			User user = mapper.readValue(request.getInputStream(), User.class);

			User oldUser = userDao.get(user.getUserId());

			if (oldUser != null) {
				user = userDao.update(user);

				payload = new Payload<User>(user, null);

				json = mapper.writeValueAsString(payload);

				//return updated user back if everything is fine
				//need error handling

				System.out.println("ResultingJSONstring = " + json);

				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);

			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
		} catch (Exception ex) {

			ObjectMapper mapper = new ObjectMapper();

			Payload<String> payloadOne = new Payload<String>("error", null);

			json = mapper.writeValueAsString(payloadOne);

			System.out.println("ResultingJSONstring = " + json);

			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

			ex.printStackTrace();
		}

		return "200";
	}
}