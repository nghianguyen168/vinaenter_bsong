package controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import constant.CommonConstant;
import dao.SongDAO;
import model.Slide;
import model.Song;
import dao.SlideDao;
import utils.AuthUtil;
import utils.FileUtil;
import utils.SlideList;

@MultipartConfig
public class AdminAddSlideController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AdminAddSlideController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/slide/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SongDAO songDAO = new SongDAO();
		SlideDao slideDao = new SlideDao();
		
		
		int id = Integer.parseInt(request.getParameter("song"));
		
		Slide checkTrungSlide = slideDao.checkTrung(id);
		if(checkTrungSlide!=null) {
			response.sendRedirect(request.getContextPath()+"/amdin/slide/add?msg=0");
		} else {
			Song song = songDAO.getItem(id);
			int addItemToSlide = slideDao.addItemToSlide(song);
			List<Slide> slideList = slideDao.getItems();
			if(addItemToSlide>0) {
				request.setAttribute("slideList", slideList);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/slide/index.jsp");
				rd.forward(request, response);
				
			}
		}
		
	
		
	}

		
		
	
}
