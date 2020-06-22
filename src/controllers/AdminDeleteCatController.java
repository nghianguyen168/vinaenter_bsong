package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.SongDAO;
import model.Category;
import utils.AuthUtil;

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteCatController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		CategoryDAO categoryDAO = new CategoryDAO();
		SongDAO songDAO = new SongDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		int delCat = categoryDAO.delItem(id);
		int delSongByCatId = songDAO.delItemByCatId(id);
		if(delCat>0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg=0");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

}
