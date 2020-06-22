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

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicIndexController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(request.getServletContext().getRealPath(""));
		CategoryDAO categoryDAO = new CategoryDAO();
		
		
		List<Category> catList = categoryDAO.getItems();
		request.setAttribute("catList", catList);
		
		
		SongDAO songDAO = new SongDAO();
		List<Song> songList = songDAO.getItems();
		request.setAttribute("songList", songList);
		
		/*
		 * int start=0; int end=3;
		 */
		int numberOfItems = songList.size();
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
		
	
		List<Song> songListByPage = songDAO.getItemsByPage(offset);
		request.setAttribute("songListByPage", songListByPage);
		
		
	
		List<Song> songListNews = songDAO.getItemsNews();
		
		
		List<Song> maxViewsList = songDAO.getMaxViews();
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/index.jsp");
		rd.forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SongDAO songDAO = new SongDAO();
		request.setCharacterEncoding("UTF-8");
		String searchText = request.getParameter("search");
		System.out.println("tìm"+searchText);
		List<Song> songListSearch = songDAO.searchItems(searchText);
		if(songListSearch.size()>0) {
			request.setAttribute("songListSearch", songListSearch);
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/index.jsp");
			rd.forward(request, response);
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/index.jsp?msgS=0)");
			rd.forward(request, response);
		}
	}

}
