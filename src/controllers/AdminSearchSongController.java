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
import utils.DefineUtil;

public class AdminSearchSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminSearchSongController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SongDAO songDAO = new SongDAO();
		request.setCharacterEncoding("UTF-8");
		String searchText = request.getParameter("searchText");
		if("".equals(searchText)) {
			request.setAttribute("searchText", searchText);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/index.jsp");
			rd.forward(request, response);
		}
		System.out.println("từ kháo tìm kiếm:"+searchText);
		List<Song> songListSearch = songDAO.searchItems(searchText);
		System.out.println(songListSearch.size());
		if(songListSearch.size()>0) {
			request.setAttribute("songListSearch", songListSearch);
			int numberOfItems = songListSearch.size();
			int numberOfPages = (int)Math.ceil((float)numberOfItems/DefineUtil.NUMBER_PER_PAGE);
			int curentPage=1;
			try {
				curentPage=Integer.parseInt(request.getParameter("page"));
			}catch (NumberFormatException e) {
				// TODO: handle exception
			}
			if(curentPage>numberOfPages || curentPage<1) {
				curentPage=1;
			}
			int offset = (curentPage-1)* DefineUtil.NUMBER_PER_PAGE;
			List<Song> songListByPageAdmin = songDAO.getItemsByPageAdmin(offset);
			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("curentPage", curentPage);
			request.setAttribute("offset", offset);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/index.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/index.jsp?msgS=4");
			rd.forward(request, response);
		}
	}

}
