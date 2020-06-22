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
import model.Song;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexSongController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		SongDAO songDAO = new SongDAO();
		List<Song> songList = songDAO.getItemsAdmin();
		request.setAttribute("songList", songList);
		
	
		
		int numberOfItems = songDAO.getItemsAdmin().size();
		int numberOfPages = (int)(numberOfItems/DefineUtil.NUMBER_PER_PAGE);
		if(numberOfPages*DefineUtil.NUMBER_PER_PAGE<songDAO.getItemsAdmin().size()) {
			numberOfPages+=1;
		}
		int curentPage=1;
		try {
			curentPage=Integer.parseInt(request.getParameter("page"));
		}catch (NumberFormatException e) {
			// TODO: handle exception
		}
		if(curentPage>numberOfPages || curentPage<1) {
			curentPage=1;
		}
		int offset = (curentPage  - 1)* DefineUtil.NUMBER_PER_PAGE;
		List<Song> songListByPageAdmin = songDAO.getItemsByPageAdmin(offset);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("curentPage", curentPage);
		request.setAttribute("offset", offset);
		request.setAttribute("numberOfItems", numberOfItems);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
