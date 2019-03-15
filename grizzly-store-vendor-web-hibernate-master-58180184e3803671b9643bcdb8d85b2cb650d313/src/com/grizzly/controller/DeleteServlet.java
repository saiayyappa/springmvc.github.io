package com.grizzly.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.grizzly.dao.UserHibernateDao;
import com.grizzly.exception.ApplicationException;
import com.grizzly.service.ProductService;
import com.grizzly.service.ProductServiceImpl;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Logger logger = Logger.getLogger("grizzly-store-vendor-web");
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  if(request.getParameter("id") != null)
	  {
		String id = request.getParameter("id");
	    
		int deleteId = Integer.parseInt(id);
		
		boolean check = true;
		
			try 
			{
				ProductService productService = new ProductServiceImpl();
				check = productService.deleteProduct(deleteId);
			} 
			
			catch (ApplicationException ae) 
			{	
				request.setAttribute("error", ae.getMessage());
				logger.error(ae.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
				dispatcher.forward(request, response);
				
			}
		
		if(check)
		{
			ArrayList productList = new ArrayList();
			
			try 
			{
				ProductService productService = new ProductServiceImpl();
				productList = productService.getProducts();
			} 
			
			catch (ApplicationException ae) 
			{
				request.setAttribute("error", ae.getMessage());
				logger.error(ae.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
				dispatcher.forward(request, response);
			}
			
			HttpSession session = request.getSession(false);
			session.setAttribute("productList", productList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListProducts.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserError.jsp");
			dispatcher.forward(request, response);
		}
	  }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
