package com.teamred.controller;

import java.io.IOException;

import com.teamred.action.Action;
import com.teamred.action.ActionFactory;
import com.teamred.util.RequestMethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "DispatcherServlet", urlPatterns = { "/pages/*" })
public class DispatcherServlet extends HttpServlet {
	private static final Logger LOGGER = LogManager.getLogger(DispatcherServlet.class);
	private static final long serialVersionUID = 1L;

	private static ActionFactory actionFactory;
	
	private static final String TEMPLATES_HOME_PATH = "/WEB-INF/classes/templates/";
	private static final String VIEW_FILE_EXTENSION = ".jsp";

	@Override
	public void init() throws ServletException {
		super.init();

		actionFactory = ActionFactory.getInstance();
	}

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

			//get necessary action
			Action action = actionFactory.getAction(request);
			//get view name based on the action
			String view = action.execute(request, response);

			if (request.getMethod().equals(RequestMethod.GET.name())) {

				request.getRequestDispatcher(TEMPLATES_HOME_PATH + view + VIEW_FILE_EXTENSION).forward(request, response);

			} else {
				response.sendRedirect(request.getContextPath() + view); // We'd like to fire redirect in case of a view change as result of the
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
		LOGGER.info("Servlet's doGet() called");
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
		LOGGER.info("Servlet's doPost() called");
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Dispatcher servlet for the UserApp";
	}
}