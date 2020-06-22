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
import dao.SongDAO;
import model.Category;
import utils.AuthUtil;

public class AdminDeleteSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteSongController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		SongDAO songDAO = new SongDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		
		Path path = Paths.get(request.getServletContext().getRealPath("") + "/files/" + songDAO.getItem(id).getPicture());
		System.out.println("xóa:" + path);
		Files.delete(path);

		int delSong = songDAO.delItem(id);

			
		if (delSong > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=3");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=0");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
