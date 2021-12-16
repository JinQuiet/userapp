package com.teamred.filter;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

// import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/*"})	
public class DispatcherServletFIlter extends HttpFilter {
	private static final long serialVersionUID = 1L;
	
	private static final String CONTEXT_ROOT = "/";
	private static final String URI_PAGES = "/pages";
	
	private static final String INDEX_PAGE = "/index";

	

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.getContextPath();
		request.getRequestURI();
		request.getPathInfo();
		request.getServletPath();	


		//real path to the resource we're trying to get
		String path = request.getRequestURI().substring(request.getContextPath().length());

		//if it's a context root it goes to "/pages/index/" 
		//in other cases it goes to provided path
		String servletForvardPath = path.equals(CONTEXT_ROOT) ? (URI_PAGES + INDEX_PAGE) : (URI_PAGES + path);
		
		//this part forwards to the static content of the app. Styles and stuff
		if (path.startsWith("/static/")) {
			request.getRequestDispatcher("/WEB-INF/classes" + path).forward(request, response); 
		//this part forwars to the *.jps pages
		} else {
			request.getRequestDispatcher(servletForvardPath).forward(request, response);
		}		
		
		//don't need this part anymore. I don't use filter chain here since I've already forvarded the requests
		//chain.doFilter(request, response); // Goes to default servlet.					
	}
}