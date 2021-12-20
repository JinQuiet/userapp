package com.teamred.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamred.dao.Dao;
import com.teamred.json.Payload;
import com.teamred.model.User;
import com.teamred.validator.ValidationChain;
import com.teamred.validator.ValidationResult;
import com.teamred.validator.Validator;
import com.teamred.validator.impl.IntegerValidator;
import com.teamred.web.util.WebUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetUserAction implements Action {

    Dao<User> userDao;

	public GetUserAction(Dao<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String[] pathTokens = WebUtils.tokenizePath(request.getPathInfo());
		String userIdToken = pathTokens[1];
		Integer userId = Integer.parseInt(userIdToken);

		ValidationChain<String> dataValidationChain = new ValidationChain<String>();

		Validator<String> iv = new IntegerValidator("pathParameter.userId");
			ValidationResult vr = dataValidationChain.nextLink(userIdToken, iv).resolve();

		Payload<User> payload = null;
		String json = "";
		
		try {
			if (vr.isValid()) {
				//populate model wth the data from the database
				User user = userDao.get(userId);

				payload = new Payload<User>(user, dataValidationChain.getErrorMessages());
							
				ObjectMapper mapper = new ObjectMapper();
				json = mapper.writeValueAsString(payload);

				System.out.println("ResultingJSONstring = " + json);

				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} else {
				payload = new Payload<User>(null, dataValidationChain.getErrorMessages());
							
				ObjectMapper mapper = new ObjectMapper();
				json = mapper.writeValueAsString(payload);

				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);				
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();            				
		}


		return "200";
	}
}