package com.teamred.controller;

import java.io.IOException;

import com.teamred.action.Action;
import com.teamred.action.ActionFactory;
import com.teamred.web.mapping.RequestMapping;
import com.teamred.web.mapping.RequestMappingPool;
import com.teamred.web.util.WebUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DispatcherServlet", urlPatterns = { "/actions/*" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionFactory actionFactory = ActionFactory.getInstance();				

		System.out.println("====Dispatcher Servlet");

		request.getRequestURI();
		request.getContextPath();
		request.getPathInfo();

		RequestMapping requestMpping = WebUtils.getMapping(RequestMappingPool.getRequestMapping(), request);
	
		try {

			//get necessary action
			Action action = actionFactory.getAction(requestMpping.getActionName());
			//get view name based on the action
			
			//String view = action.execute(request, response);
			action.execute(request, response);

		} catch (Exception e) {
			//request.getRequestDispatcher(URI_JSP_HOME + "/error" + URI_EXTENSION).forward(request, response);
			throw new ServletException("Executing action failed.", e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}	

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Dispatcher servlet for the UserApp";
	}
}