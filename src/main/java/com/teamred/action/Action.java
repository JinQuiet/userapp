package com.teamred.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
