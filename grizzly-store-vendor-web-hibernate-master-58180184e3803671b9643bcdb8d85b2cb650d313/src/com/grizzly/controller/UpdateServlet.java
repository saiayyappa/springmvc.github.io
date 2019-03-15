package com.grizzly.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.grizzly.dao.UserHibernateDao;
import com.grizzly.exception.ApplicationException;
import com.grizzly.service.ProductService;
import com.grizzly.service.ProductServiceImpl;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Logger logger = Logger.getLogger("grizzly-store-vendor-web");
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("update") != null)
		{
			int stock = Integer.parseInt(request.getParameter("stock"));
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				ProductService productService = new ProductServiceImpl();
				
				int update = productService.updateProduct(id,stock);
				
				if(update != 0)
				{
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("InventoryProductList.jsp");
					dispatcher.forward(request, response);
					
				}
			} 
			
			catch (ApplicationException e) 
			{
				
				logger.error(e.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		else if(request.getParameter("productId") != null) 
		{
			int productId = Integer.parseInt(request.getParameter("productId"));
			request.setAttribute("productId",productId);
			
			int productBuffer = Integer.parseInt(request.getParameter("productBuffer"));
			request.setAttribute("productBuffer",productBuffer);
			
			int productStock = Integer.parseInt(request.getParameter("productStock"));
			request.setAttribute("productStock",productStock);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateProduct.jsp");
			dispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
