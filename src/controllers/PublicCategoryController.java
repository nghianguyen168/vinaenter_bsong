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

public class PublicCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicCategoryController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		SongDAO songDAO = new SongDAO();
		

		int	cid = Integer.parseInt(request.getParameter("cid"));
	
		List<Song> songListById = songDAO.getsongListByIdById(cid);
		request.setAttribute("cid", cid);
		
		
		int numberOfItems = songListById.size();
		int numberOfPages = (int)(numberOfItems/DefineUtil.NUMBER_PER_PAGE);
		if(numberOfPages*DefineUtil.NUMBER_PER_PAGE<songListById.size()) {
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
		List<Song> songListByPage = songDAO.getItemsByPageCat(offset,cid);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("curentPage", curentPage);
		request.setAttribute("offset", offset);
		request.setAttribute("numberOfItems", numberOfItems);
		
		
		String cat_active="cat_active";
		request.setAttribute("cat_active", cat_active);
		request.setAttribute("songListByPage", songListByPage);
		request.setAttribute("cid", cid);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/cat.jsp");
		rd.forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
