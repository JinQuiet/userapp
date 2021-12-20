package com.teamred.filter;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.teamred.web.util.WebUtils.*;

public class UsersFilter extends HttpFilter {

	private static final String URI_ACTIONS = "/actions";
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.getContextPath();
		request.getRequestURI();
		request.getPathInfo();
		request.getServletPath();				

		System.out.println("====Entered UsersFilter ");

			//real path to the resource we're trying to get
			String path = request.getRequestURI().substring(request.getContextPath().length());
			path = getCleanPath(path);		

			String servletForvardPath = URI_ACTIONS + path;	
			
			request.getRequestDispatcher(servletForvardPath).forward(request, response);

			//chain.doFilter(request, response);

		System.out.println("====Left UsersFilter");	
	}
}