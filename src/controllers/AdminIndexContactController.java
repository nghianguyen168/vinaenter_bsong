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
import model.Category;
import model.Contact;
import model.User;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexContactController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)==false) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		ContactDAO contactDAO = new ContactDAO();
		List<Contact> contactList = contactDAO.getItems();
		request.setAttribute("contactList", contactList);
	
		int numberOfItems = contactList.size();
		int numberOfPages = (int)(numberOfItems/DefineUtil.NUMBER_PER_PAGE_USER);
		if(numberOfPages*5<contactList.size()) {
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
		int offset = (curentPage  - 1)* DefineUtil.NUMBER_PER_PAGE_USER;
		List<Contact> contactListByPage = contactDAO.getItemsByPageAdmin(offset);
		request.setAttribute("contactListByPage", contactListByPage);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("curentPage", curentPage);
		request.setAttribute("offset", offset);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
