package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.SongDAO;
import model.Category;
import model.Song;
import utils.AuthUtil;

public class AdminIndexSlideController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public AdminIndexSlideController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/slide/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
