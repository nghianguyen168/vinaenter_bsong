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
import utils.StringUtil;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddUserController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		HttpSession session = request.getSession();
		User isUser = (User)session.getAttribute("isUser");
		
		//Chỉ có admin mới được thêm người dùng.
		if(isUser.getRole_id()!=1) {
			 response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=6");
			 return;
		}
				
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		UserRoleDAO userRoleDAO = new UserRoleDAO();
		List<User> userList = userDAO.getItems();
		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		String password = StringUtil.md5(request.getParameter("password"));
		String fullname = request.getParameter("fullname");
		/* int role_id = Integer.parseInt(request.getParameter("roles")); */
		String[]roleID = request.getParameterValues("roleID");
		
		
		User checkTrungUser = userDAO.checkTrungUser(username);
		
		if(checkTrungUser!=null) {
			User userErr = new User(username, password, fullname);
			request.setAttribute("userErr", userErr);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp?msg=5");
			rd.forward(request, response);
			return;
			//response.sendRedirect(request.getContextPath()+"/admin/user/add?msg=5");
		} /*
			 * else if(role_id==1) { User userErr = new User(username, password,
			 * fullname,role_id); request.setAttribute("userErr", userErr);
			 * RequestDispatcher rd =
			 * request.getRequestDispatcher("/views/admin/user/add.jsp?msg=4");
			 * rd.forward(request, response); return; }
			 */else {
			{
				 int addUser = userDAO.addItem(username,password,fullname);
				 if(addUser>0) {
					 response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=1");
					 int user_id = userDAO.getUserID(username);
					 for(int i=0;i<roleID.length;i++) {
							int role_id = Integer.parseInt(roleID[i].toString());
							int addRole = userRoleDAO.addItem(user_id, role_id);
							
						}
				
				 } else {
					 response.sendRedirect(request.getContextPath()+"/admin/user/add?msg=0");
				 }
			}
		}
		
	}

}
