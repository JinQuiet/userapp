package com.teamred.action;

import java.util.List;

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

		Payload<List<User>> payload = null;
		String json="";
		
		try {
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

				ObjectMapper mapper = new ObjectMapper();

				payload = new Payload<List<User>>(users, dataValidationChain.getErrorMessages());
				json = mapper.writeValueAsString(payload);

				System.out.println("ResultingJSONstring = " + json);

				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			
			} else {
				payload = new Payload<List<User>>(users, dataValidationChain.getErrorMessages());
				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}

		} catch (Exception ex) {
			
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);            
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);            
				
				ex.printStackTrace();   
		}

			return "200";
		}
}