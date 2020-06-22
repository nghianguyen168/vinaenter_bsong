package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import model.Category;
import utils.AuthUtil;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddCatController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/category/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		Category checkTrungCat = categoryDAO.checkTrungCat(name);
		if(checkTrungCat!=null) {
			request.setAttribute("name", name);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/category/add.jsp?msg=5");
			rd.forward(request, response);
		} else {
		
			Category cat = new Category(0, name);
			int addCat = categoryDAO.addItem(cat);
			if(addCat>0) {
				response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg=1");
				return;
			} else {
				response.sendRedirect(request.getContextPath()+"/admin/cat/add?msg=0");
				return;
			}
		}
		
	}

}
