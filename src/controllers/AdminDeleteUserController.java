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
import utils.AuthUtil;

public class AdminDeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteUserController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		UserDAO userDAO = new UserDAO();
		UserRoleDAO userRoleDAO = new UserRoleDAO();
		//try catch id
		int id = Integer.parseInt(request.getParameter("id"));
		
		User user = userDAO.getItem(id);
		
		HttpSession session = request.getSession();
		User isUser = (User)session.getAttribute("isUser");
		
		if(user.getRole_id()==1) {
			//Không được xóa admin
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=8");
		} else {
			if(isUser.getRole_id()==1) {
				//được xóa những người dùng kh
				int delUser = userDAO.delItem(id);
				int delUserRole = userRoleDAO.delItem(id);
				if(delUser>0) {
					response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=3");
				} else {
					response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=0");
				}
			} else {
				
				//Không được phép xóa,kể cả chính minhd
				response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=8");
			}
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

}
