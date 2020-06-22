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

public class AdminEditPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditPasswordController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (AuthUtil.checkLogin(request, response) == false) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();

		HttpSession session = request.getSession();
		User isUser = (User) session.getAttribute("isUser");
			
		// Chỉ addmin được sửa user, hoặc chính user đó sửa của mình
		
		
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/password.jsp");
			rd.forward(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		String password = StringUtil.md5(request.getParameter("password"));

		HttpSession session = request.getSession();
		
		int id=Integer.parseInt(request.getParameter("id"));
		User checkPass = userDAO.checkPassword(id,password);
		if(checkPass!=null) {
			String new_password =  StringUtil.md5(request.getParameter("newpassword"));
			int updatePassWord = userDAO.editPassword(id,new_password);
			System.out.println(updatePassWord);
			if(updatePassWord>0) {
				response.sendRedirect(request.getContextPath() + "/views/admin/user/profile.jsp?msg=1");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/views/admin/user/password.jsp?msg=0");
				return;
			}
			
		} else {
			response.sendRedirect(request.getContextPath() + "/views/admin/user/password.jsp?msg=0");
			return;
		}
	}

}
