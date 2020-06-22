package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.PreparedStatement;

import dao.ContactDAO;
import model.Contact;
import utils.AuthUtil;
import utils.DBConnectionUtil;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicContactController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		doPost(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
		rd.forward(request, response);
		
		
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tb="";
		request.setCharacterEncoding("UTF-8");
		ContactDAO contactDAO = new ContactDAO();
		if(request.getParameter("name")!=""&&request.getParameter("email")!=""&request.getParameter("website")!=""&request.getParameter("message")!="") {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		
		int cont = contactDAO.insertToContact(name, email, website, message);
		tb = "Đã gửi thông tin thành công!";
		request.setAttribute("tb", tb);
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
			rd.forward(request, response);
		
			
		}
		
	    }
	
		
		
		
		
	
	
	

}
