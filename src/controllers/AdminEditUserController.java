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

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditUserController() {
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
		HttpSession session = request.getSession();
		User isUser = (User) session.getAttribute("isUser");
		User user = userDAO.getItem(id);

		request.setAttribute("user", user);
		// Chỉ addmin được sửa user, hoặc chính user đó sửa của mình
		if (user.getRole_id() == 1 || (id == user.getId())) {

			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
			rd.forward(request, response);
		} else {
			// Không có quyền sửa
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=7");
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		RolesDao rolesDao = new RolesDao();
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		 String new_password="";
		if(request.getParameter("password")!=null) {
			new_password = StringUtil.md5(request.getParameter("password")); 
			int updatePassword = userDAO.editPassword(id, new_password);
		}
		  
		String fullname = request.getParameter("fullname");

		String[] roleID = null;
		if (request.getParameterValues("roleID") != null) {
			roleID = request.getParameterValues("roleID");
		}
		int delOldRoleID = rolesDao.delItem(id);

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
			if (roleID != null) {
				for (int i = 0; i < roleID.length; i++) {
					int role_id = Integer.parseInt(roleID[i].toString());
					System.out.println("id:" + id + "role:" + role_id);
					int editRole = rolesDao.addItems(id, role_id);
				}

			}

			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=2");
			return;

		}

		else {
			response.sendRedirect(request.getContextPath() + "/admin/user/edit?msg=0&id=" + id);
			return;
		}

	}

}
