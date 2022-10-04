package com.mhys.deprecated.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mhys.deprecated.dao.IGeneralDao;
import com.mhys.deprecated.dao.impl.GeneralDaoImpl;
import com.mhys.deprecated.pojo.User;
import com.mhys.util.Util;

/**
 * 
 * @author Matthew
 *
 */
@WebServlet("/login")
public class LoginSevlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(Util.UTF_8);

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (Util.checkNonEmpty(username) && Util.checkNonEmpty(password)) {
			
			GeneralDaoImpl<User> dao = new GeneralDaoImpl<>();
			dao.setTableName("t_user");
			User user = dao.queryByCondition("username = " + username);
			
			if (password == user.getPassword()) {
				request.getSession().setAttribute("username", username);
				request.getRequestDispatcher("main" + Util._JSP).forward(request, response);
			} else
				response.sendRedirect("login" + Util._JSP);
		} else
			response.sendRedirect("login" + Util._JSP);
	}
}
