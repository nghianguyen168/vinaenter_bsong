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
import dao.UserDAO;
import dao.UserRoleDAO;
import model.Category;
import model.User;
import utils.StringUtil;

public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminLoginController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		UserRoleDAO userRoleDAO = new UserRoleDAO();
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		//String username = StringUtil.md5(request.getParameter("username"));
		//String password = request.getParameter("password");
		String password =  StringUtil.md5(request.getParameter("password"));
		User isUser = userDAO.checkUser(username, password);
		if (isUser!=null) {
			HttpSession session = request.getSession();
			int user_id = isUser.getId();
			 List<User> RoleList = userDAO.getRoleList(user_id);
			 session.setAttribute("isUser", isUser);
			//RequestDispatcher rd = request.getRequestDispatcher("/views/admin/index.jsp");
			//rd.forward(request, response);
			response.sendRedirect(request.getContextPath()+"/admin");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/auth/login?msg=0");
			return;
		}
	}

}
