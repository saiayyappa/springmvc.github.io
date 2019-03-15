package com.grizzly.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/StoreServlet")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Logger logger = Logger.getLogger("grizzly-store-vendor-web");
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(request.getParameter("logout") != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginLogoutServlet");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("addProduct") != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("AddServlet");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("AddProducttoDB") != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("AddServlet");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("AddProducttoDB") != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("AddServlet");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("id") != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteServlet");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("update") != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateServlet");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("productId") != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateServlet");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("login") != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginLogoutServlet");
			dispatcher.forward(request, response);
		}
		
	}

}
