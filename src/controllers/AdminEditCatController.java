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
import model.Category;
import utils.AuthUtil;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
			
		
		CategoryDAO categoryDAO = new CategoryDAO();
		int id= Integer.parseInt(request.getParameter("id"));
		Category cat = categoryDAO.getItemById(id);
		request.setAttribute("cat", cat);
		
		int sumCat = categoryDAO.getSumCat();
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/category/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int id= Integer.parseInt(request.getParameter("id"));
		Category cat = new Category(id, name);
		int editCat = categoryDAO.editItem(cat);
		if(editCat>0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg=2");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/cat/add?msg=0&id="+id);
			return;
		}
		
	}

}
