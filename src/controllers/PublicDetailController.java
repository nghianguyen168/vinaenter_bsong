package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SongDAO;
import model.Song;
import utils.AuthUtil;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicDetailController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		SongDAO songDAO = new SongDAO();
		int cid = Integer.parseInt(request.getParameter("cid"));
		int did = Integer.parseInt(request.getParameter("did"));
		
		int updateViews = songDAO.updateViews(did);
		
		Song song = songDAO.getItem(did);
		request.setAttribute("song", song);
		
		List<Song> relatedList = songDAO.getRelatedList(cid,did);
		request.setAttribute("relatedList", relatedList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/detail.jsp");
		rd.forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
