package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.ContactDAO;
import dao.UserDAO;
import model.Category;
import utils.AuthUtil;

public class AdminDeleteContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteContactController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		ContactDAO contactDAO = new ContactDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		int delContact = contactDAO.delItem(id);
		doPost(request, response);
		if(delContact>0) {
			response.sendRedirect(request.getContextPath()+"/admin/contact/index?msg=3");
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/contact/index?msg=0");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContactDAO contactDAO= new ContactDAO();
		int delContact=0;
		String[]contactID = request.getParameterValues("deleteContact");
		for(int i=0;i<contactID.length;i++) {
			int id = Integer.parseInt(contactID[i].toString());
			 delContact = contactDAO.delItem(id);
			
		}
		if(delContact>0) {
			response.sendRedirect(request.getContextPath()+"/admin/contact/index?msg=3");
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/contact/index?msg=0");
		}
		
	}

}
