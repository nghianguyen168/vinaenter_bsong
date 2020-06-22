package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDAO;
import dao.RolesDao;
import dao.UserDAO;
import model.Category;
import model.User;
import utils.AuthUtil;
import utils.StringUtil;

public class AdminProfileUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminProfileUserController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (AuthUtil.checkLogin(request, response) == false) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		int id=0;
		if(request.getParameter("id")!=null) {
			 id = Integer.parseInt(request.getParameter("id"));
		}
		System.out.println(id);
	

		HttpSession session = request.getSession();
		User isUser = (User) session.getAttribute("isUser");
		User user = userDAO.getItem(id);

		request.setAttribute("user", user);

		// Chỉ addmin được sửa user, hoặc chính user đó sửa của mình
		

			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/profile.jsp?id="+id);
			rd.forward(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		RolesDao rolesDao = new RolesDao();
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		/* String password = StringUtil.md5(request.getParameter("password")); */
		String fullname = request.getParameter("fullname");

		String[] roleID = null;
		if (request.getParameterValues("roleID") != null) {
			roleID = request.getParameterValues("roleID");
		}
		
		User user = new User(id, username, "", fullname);

		User checkTrungUser = userDAO.checkTrungUser(username);
		/*
		 * User checkAdmin = userDAO.checkAdmin(role_id); if(role_id==1){ User userErr =
		 * new User(id,username, password, fullname,role_id);
		 * request.setAttribute("userErr", userErr); RequestDispatcher rd =
		 * request.getRequestDispatcher("/views/admin/user/edit.jsp?msg=4");
		 * rd.forward(request, response); return; } else {
		 */
//			User userErr = new User(username, password, fullname,role_id);
		int editUser = userDAO.editItem(user);

		if (editUser > 0) {
		

			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=2");
			return;

		}

		else {
			response.sendRedirect(request.getContextPath() + "/admin/user/edit?msg=0&id=" + id);
			return;
		}

	}

}
