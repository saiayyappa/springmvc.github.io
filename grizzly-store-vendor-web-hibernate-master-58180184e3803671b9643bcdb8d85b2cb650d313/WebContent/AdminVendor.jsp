<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.grizzly.pojo.ProductPojo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="VendorListProducts.css" />
<link rel="stylesheet" type="text/css" href="AdminListProducts.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<%
			String role = (String) session.getAttribute("role");
			
			String userName = (String) session.getAttribute("userName");
			
			if(role.equals("Admin"))
			{
%>
<div id="display">
<img alt="The Grizzly Store" src="D:\Eclipse-workspace\grizzly-store-vendor-web\WebContent\GrizzlyLogo2.PNG" height="80" width="300" id="image" >
<input id="search" type="text" name="Search" placeholder="What are you looking for?" />
<h3>Welcome,<%=userName %></h3>
<form action="StoreServlet" method="get" >
<input type="submit" name="logout" value="Logout" />
</form>
</div>
<div id="iframe">
<iframe src="Details.jsp" height="700" width="200">
</iframe>
</div>
<div>
<a href="ListProducts.jsp" target="listproducts" id="products"><strong>PRODUCTS</strong></a>
<a href="StoreServlet?addProduct" target="listproducts" >
<input type="submit" name="addProduct" value="Add Product" id="add" />
</a>
</div>
<iframe id="iframe2" src="ListProducts.jsp" width="85%" height="598" name="listproducts"></iframe>
<% 			}
			else
			{
						
%>
<div id="display">
<img alt="The Grizzly Store" src="D:\Eclipse-workspace\grizzly-store-vendor-web\WebContent\GrizzlyLogo2.PNG" height="80" width="300" id="image" >
<input id="search" type="text" name="Search" placeholder="What are you looking for?" />
<h3>Welcome,<%=userName %></h3>
<form action="StoreServlet" method="get" >
<input type="submit" name="logout" value="Logout" />
</form>
</div>
<div>
<iframe id="iframe" src="Details.jsp" height="700" width="200">
</iframe>
<div>
<a href="VendorProductList.jsp" target="listproducts"><strong>PRODUCTS</strong></a>
<a href="InventoryProductList.jsp" target="listproducts" ><strong>INVENTORY</strong></a>
</div>
<iframe id="Vendoriframe" src="VendorProductList.jsp" width="81%" height="598" name="listproducts"></iframe>
</div>
<%  } %>
</body>
</html>