package controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import constant.CommonConstant;
import dao.SongDAO;
import model.Song;
import utils.AuthUtil;
import utils.FileUtil;

@MultipartConfig
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddSongController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SongDAO songDAO = new SongDAO();
		String name = request.getParameter("name");
		
		int cat_id = Integer.parseInt(request.getParameter("category"));
		
		String preview_text = request.getParameter("preview");
		String detail_text = request.getParameter("detail");
		

			// xử lý upload file
			Part filePart = request.getPart("picture");

			if ("".equals(filePart.getSubmittedFileName())) {
				String tb = "Vui lòng chọn hình ảnh";
				request.setAttribute("tb", tb);
			
			}

			String fileName = FileUtil.rename(filePart.getSubmittedFileName());
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
				System.out.println("file:"+filePath);

			}

			// lấy ra đường dẫn đến file ảnh
			/*
			 * String pathPic = request.getContextPath() + File.separator +
			 * CommonConstant.DIR_UPLOAD + File.separator + fileName;
			 */
			

			Song song = new Song(0, name, preview_text, detail_text, null, fileName, 0, cat_id);

			int addSong = songDAO.addItem(song);
			if (addSong > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=1");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/song/add?msg=0");
				return;
			}
		}

		
		
	
}
