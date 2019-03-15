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
import com.grizzly.pojo.LoginPojo;
import com.grizzly.pojo.ProductPojo;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Logger logger = Logger.getLogger("grizzly-store-vendor-web");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("logout") != null)
		{
			HttpSession session = request.getSession(false);
			session.invalidate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.html");
			dispatcher.forward(request, response);
					
		}
		
		else if(request.getParameter("addProduct") != null)
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
				
				int id = UserHibernateDao.addProduct(product);
				request.setAttribute("idtoadd", id);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("AddSuccess.jsp");
				dispatcher.forward(request, response);
				
			} 
			
			catch (ApplicationException e) 
			{
			
				request.setAttribute("error", e.getMessage());
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
				dispatcher.forward(request, response);
				logger.error(e);
			}
			
		}
				
		else if(request.getParameter("id") != null)
		{
			
			String id = request.getParameter("id");
		    
			int deleteId = Integer.parseInt(id);
			
			boolean check = true;
			
				try 
				{
					check = UserHibernateDao.deleteProduct(deleteId);
				} 
				
				catch (ApplicationException ae) 
				{	
					request.setAttribute("error", ae.getMessage());
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
					dispatcher.forward(request, response);
					
				}
			
			if(check)
			{
				ArrayList productList = new ArrayList();
				
				try 
				{
					productList = UserHibernateDao.getProducts();
				} 
				
				catch (ApplicationException ae) 
				{
					request.setAttribute("error", ae.getMessage());
					
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		if(request.getParameter("login") != null)
		{
			String userName = request.getParameter("user");
			String password = request.getParameter("pass");
			
			
			if(userName != "" && password != "")
			{
				LoginPojo login = new LoginPojo();
				
				login.setUserName(userName);
				login.setPassword(password);
				
				StringBuilder builder = new StringBuilder();
				
				try {
				 builder.append(UserHibernateDao.checkUser(login));
				}
				
				catch(ApplicationException ae)
				{
					request.setAttribute("error", ae.getMessage());
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
					dispatcher.forward(request, response);
				}
				
				String check = builder.toString();
				
				if(check.equals("Blocked"))
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("UserBlocked.html");
					dispatcher.forward(request, response);
				}
				
				else if(check.equals("TryAgain"))
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("UserError.html");
					dispatcher.forward(request, response);
							
				}
				
				else 
				{
					String role = check;
					
					HttpSession session = request.getSession();
		
					int id = 0;
					
					try 
					{
						id = UserHibernateDao.getId(userName,password);
					} 
					
					catch (ApplicationException e1) 
					{
						
						request.setAttribute("error", e1.getMessage());
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
						dispatcher.forward(request, response);	
					}
					
					session.setAttribute("userName", userName);
					session.setAttribute("role",role);
					session.setAttribute("id", id);
					
					if(role.equals("Admin"))
					{
						ArrayList productList = new ArrayList();
						
						try 
						{
							productList = UserHibernateDao.getProducts();
	
							session.setAttribute("productList", productList);
							
						} 
						
						catch (ApplicationException e) 
						{
							request.setAttribute("error", e.getMessage());
							
							RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
							dispatcher.forward(request, response);	
						}
					}
					
					else
					{
						ArrayList inventoryProductList = new ArrayList();
						ArrayList inventoryList = new ArrayList();
						
						try 
						{
							inventoryProductList = UserHibernateDao.getVendorProducts();
							inventoryList = UserHibernateDao.getInventoryProducts();
							
							session.setAttribute("productList", inventoryProductList);
							session.setAttribute("inventoryList", inventoryList);
							
						} 
						
						catch (ApplicationException e) 
						{
							request.setAttribute("error", e.getMessage());
							
							RequestDispatcher dispatcher = request.getRequestDispatcher("SQLError.jsp");
							dispatcher.forward(request, response);
						}
						
					}
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("AdminVendor.jsp");
					dispatcher.forward(request, response);
				}	
			}
			
			else
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("UserError.html");
				dispatcher.forward(request, response);
			}
			
			
		  }
	
	}
}


