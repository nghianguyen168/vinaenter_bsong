package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.CategoryDAO;
import dao.UserDAO;
import model.Category;
import model.Song;
import model.User;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexUserController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		UserDAO userDAO = new UserDAO();
		List<User> userList = userDAO.getItems();
		
		

		int numberOfItems = userList.size();
		int numberOfPages = (int)(numberOfItems/DefineUtil.NUMBER_PER_PAGE_USER);
		if(numberOfPages*DefineUtil.NUMBER_PER_PAGE_USER<userList.size()) {
			numberOfPages+=1;
		}
		System.out.println("Số trang:"+numberOfPages);
		int curentPage=1;
		try {
			curentPage=Integer.parseInt(request.getParameter("page"));
		}catch (NumberFormatException e) {
			// TODO: handle exception
		}
		if(curentPage>numberOfPages || curentPage<1) {
			curentPage=1;
		}
		int offset = (curentPage  - 1)* DefineUtil.NUMBER_PER_PAGE_USER;
		List<User> userListByPage = userDAO.getItemsByPageAdmin(offset);
		request.setAttribute("userListByPage", userListByPage);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("numberOfItems", numberOfItems);
		request.setAttribute("curentPage", curentPage);
		request.setAttribute("offset", offset);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

}
