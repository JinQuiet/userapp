package com.teamred.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamred.dao.Dao;
import com.teamred.json.Payload;
import com.teamred.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NewUserAction implements Action {
    
    Dao<User> userDao;

	public NewUserAction(Dao<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //making dummy data for the user on the form
        User user = new User();
        user.setUserName("MyName");
        user.setUserAge(18);
        user.setPassword("superpassword");

        Payload<User> payload = new Payload<User>(user, null);

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
			
		return "200";
	}
}