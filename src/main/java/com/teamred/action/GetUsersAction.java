package com.teamred.action;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamred.dao.Dao;
import com.teamred.json.Payload;
import com.teamred.model.User;
import com.teamred.validator.ValidationChain;
import com.teamred.validator.ValidationResult;
import com.teamred.validator.Validator;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetUsersAction implements Action {

    Dao<User> userDao;

	public GetUsersAction(Dao<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//populate model wth the data from the database
		List<User> users = userDao.getAll();

		ValidationChain<List<User>> dataValidationChain = new ValidationChain<List<User>>();
		
		//validator checks if the list is ok to send back to the client 
		Validator<List<User>> validator = new Validator<List<User>>() {
			private ValidationResult validationResult;
			@Override
			public ValidationResult validate(List<User> t) {
				validationResult = ValidationResult.invalid("Users List is Empty");

				if ((t!=null) && (t.size()>0))
					validationResult = ValidationResult.valid();

				return validationResult;
			}
			@Override
			public String getValidationTarget() {
				return "users.list";
			}
		};

		ValidationResult vr = dataValidationChain.nextLink(users, validator).resolve();

		if (vr.isValid()) {
			//populate model wth the data from the database

		Payload<List<User>> payload = new Payload<List<User>>(users, dataValidationChain.getErrorMessages());

			try {
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(payload);

				System.out.println("ResultingJSONstring = " + json);

				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

	}

		return "200";
	}
}