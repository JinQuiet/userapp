package com.teamred.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teamred.dao.UserDao;
import com.teamred.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DispatcherServlet", urlPatterns = { "/pages/*" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URI_JSP_HOME = "/WEB-INF/classes/templates/";
	private static final String URI_EXTENSION = ".jsp";

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestURI();
		request.getContextPath();
		request.getPathInfo();
	
		try {

			Action action = ActionFactory.getInstance().getAction(request);
			String view = action.execute(request, response);
			if (view.equals(request.getPathInfo().substring(1))) {

				request.getRequestDispatcher(URI_JSP_HOME + view + URI_EXTENSION).forward(request, response);

			} else {

				response.sendRedirect(view); // We'd like to fire redirect in case of a view change as result of the
												// action (PRG pattern).
			}

		} catch (Exception e) {
			//request.getRequestDispatcher(URI_JSP_HOME + "/error" + URI_EXTENSION).forward(request, response);
			throw new ServletException("Executing action failed.", e);

		}

	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}
}

class ActionFactory {

	private static ActionFactory instance;

	private Map<String, Action> actions;

	public ActionFactory() {
		actions = new HashMap<>();

		actions.put("index", new IndexAction());
		actions.put("users", new UsersAction());
	}

	public static ActionFactory getInstance() {
		if (instance == null) {
			return new ActionFactory();
		} else {
			return instance;
		}
	}

	public Action getAction(HttpServletRequest request) {
		// pulls action name from the request sting stripping leading "/" symbol
		return actions.get(request.getPathInfo().substring(1));
	}
}

interface Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

class IndexAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "index";
	}
}

class UsersAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<User> users= new UserDao().getUsers();
		request.setAttribute("users", users);

		return "users";
	}
}