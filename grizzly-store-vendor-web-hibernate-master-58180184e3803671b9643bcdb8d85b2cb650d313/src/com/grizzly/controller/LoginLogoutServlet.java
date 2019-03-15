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
import com.grizzly.service.LoginService;
import com.grizzly.service.LoginServiceImpl;
import com.grizzly.service.ProductService;
import com.grizzly.service.ProductServiceImpl;

@WebServlet("/LoginLogoutServlet")
public class LoginLogoutServlet extends HttpServlet {
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
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
				
				try
				{
					 LoginService loginService = new LoginServiceImpl();
					 builder.append(loginService.checkUser(login));
				}
				
				catch(ApplicationException ae)
				{
					request.setAttribute("error", ae.getMessage());
					logger.error(ae.getMessage());
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
						LoginService loginService = new LoginServiceImpl();
						id = loginService.getId(userName,password);
					} 
					
					catch (ApplicationException e1) 
					{
						
						request.setAttribute("error", e1.getMessage());
						logger.error(e1.getMessage());
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
							ProductService productService = new ProductServiceImpl();
							productList = productService.getProducts();
							session.setAttribute("productList", productList);
							
						} 
						
						catch (ApplicationException e) 
						{
							request.setAttribute("error", e.getMessage());
							logger.error(e.getMessage());
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
							ProductService productService = new ProductServiceImpl();
							inventoryProductList = productService.getVendorProducts();
							inventoryList = productService.getInventoryProducts();
							
							session.setAttribute("productList", inventoryProductList);
							session.setAttribute("inventoryList", inventoryList);
							
						} 
						
						catch (ApplicationException e) 
						{
							request.setAttribute("error", e.getMessage());
							logger.error(e.getMessage());
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


