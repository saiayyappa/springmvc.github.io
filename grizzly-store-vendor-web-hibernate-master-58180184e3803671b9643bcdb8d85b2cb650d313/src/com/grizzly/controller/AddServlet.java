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
import com.grizzly.pojo.ProductPojo;
import com.grizzly.service.ProductService;
import com.grizzly.service.ProductServiceImpl;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Logger logger = Logger.getLogger("grizzly-store-vendor-web");
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("addProduct") != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("AddProduct.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("AddProducttoDB") != null)
		{
			ProductPojo product = new ProductPojo();
			
			String productName = request.getParameter("name");
			String productBrand = request.getParameter("brand");
			String productCategory = request.getParameter("category");
			String rating = request.getParameter("rating");
			String buffer = request.getParameter("buffer");
			String price = request.getParameter("price");
			
			//if(CheckValidation.checkCharacter(productName)==true && CheckValidation.checkCharacter(productBrand)==true && CheckValidation.checkDecimal(rating)==true && CheckValidation.checkDigit(buffer)==true && CheckValidation.checkDecimal(price) == true)
			//{
				product.setProductName(productName);
			
				product.setProductBrand(productBrand);
			
				product.setProductCategory(productCategory);
			
				double productRating =  Double.parseDouble(rating);
				product.setProductRating(productRating);
			
				int productBuffer =  Integer.parseInt(buffer);
				product.setProductBuffer(productBuffer);
			
				double productPrice = Double.parseDouble(price);
				product.setProductPrice(productPrice);
			//}
			/*else
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("WrongInputs.html");
				dispatcher.forward(request, response);
			}*/
			
			try {
				
				ProductService productService = new ProductServiceImpl();
				int id = productService.addProduct(product);
				request.setAttribute("idtoadd", id);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("AddSuccess.jsp");
				dispatcher.forward(request, response);
				
			} 
			
			catch (ApplicationException e) 
			{
			
				request.setAttribute("error", e.getMessage());
				logger.error(e.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
				dispatcher.forward(request, response);
	
			}
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
