package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import constant.CommonConstant;
import dao.CategoryDAO;
import dao.SongDAO;
import model.Category;
import model.Song;
import utils.AuthUtil;
import utils.FileUtil;

@MultipartConfig
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditSongController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		SongDAO songDAO = new SongDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		Song song = songDAO.getItem(id);
		request.setAttribute("song", song);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SongDAO songDAO = new SongDAO();
		int id = Integer.parseInt(request.getParameter("id"));

		String name = request.getParameter("name");
		int cat_id = Integer.parseInt(request.getParameter("category"));
		String preview_text = request.getParameter("preview");
		String detail_text = request.getParameter("detail");
		// upload file
		Part filePart = request.getPart("picture");
		String fileName = filePart.getSubmittedFileName();
		if (!"".equals(fileName) ) {
			if(!"".equals(songDAO.getItem(id).getPicture())){
			Path path = Paths.get(request.getServletContext().getRealPath("") + "/files/" + songDAO.getItem(id).getPicture());
			System.out.println("xóa:" + path);
			Files.delete(path);
			
		}
		}

		
		if ("".equals(filePart.getSubmittedFileName())) {
			String tb = "Vui lòng chọn hình ảnh";
			fileName = songDAO.getItem(id).getPicture();
			request.setAttribute("tb", tb);
		}

		String filePath = "";
		if (!"".equals(fileName)) {

			// tạo thư mục upload chứa file\
			// tạo đường dẫn thực đến thư mục lưu
			String webPath = request.getServletContext().getRealPath("");

			/* String dirPath = webPath + DIR_UPLOAD; */
			String dirPath = webPath + CommonConstant.DIR_UPLOAD;
			File createDir = new File(dirPath);

			if (!createDir.exists()) {
				createDir.mkdir(); // jspd4/uploads chỉ tạo được một thử mục
			}

			filePath = dirPath + File.separator + fileName;
			System.out.println(filePath);
			// File.separator tự động lấy dấu /

			filePart.write(filePath);
			request.setAttribute("filePath", filePath);
			/*
			 * RequestDispatcher rd =
			 * request.getRequestDispatcher("/views/admin/song/index.jsp");
			 * rd.forward(request, response);
			 */
			System.out.println("file part:" + filePart);
			System.out.println("Upload file thành công");

		}

		Song song = new Song(id, name, preview_text, detail_text, null, fileName, 0, cat_id);
		int editSong = songDAO.editItem(song);
		if (editSong > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=2");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/song/edit?msg=0?id=" + id);
		}

	}

}
