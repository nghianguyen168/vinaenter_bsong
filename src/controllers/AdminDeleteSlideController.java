package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CategoryDAO;
import dao.SlideDao;
import dao.SongDAO;
import model.Category;
import utils.AuthUtil;
import utils.SlideList;

public class AdminDeleteSlideController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteSlideController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int delSong=0;
		SongDAO songDAO = new SongDAO();
		SlideDao slideDao = new SlideDao();
		int id = Integer.parseInt(request.getParameter("id"));
		
		int delSlide = slideDao.delItem(id);
	

			
		if (delSlide > 0) {
			response.sendRedirect(request.getContextPath() + "/amdin/slide/index?msg=3");
		} else {
			response.sendRedirect(request.getContextPath() + "/amdin/slide/index?msg=0");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
