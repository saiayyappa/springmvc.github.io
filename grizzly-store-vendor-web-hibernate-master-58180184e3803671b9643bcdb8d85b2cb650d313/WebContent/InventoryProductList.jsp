<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.grizzly.pojo.ProductPojo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="VendorListProducts.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<table>
<tr>
<th>Product List</th>
<th>ID</th>
<th>In Stock</th>
<th>Req</th>
<th>Buffer</th>
<th>Price/Item</th>
<th>Rating</th>
</tr>
<%
	

	ArrayList inventoryList = (ArrayList) session.getAttribute("inventoryList");
	Iterator iterator = inventoryList.iterator();
	
	while(iterator.hasNext())
	{
		ProductPojo product = (ProductPojo)iterator.next();
%>
<tr>
<td><%=product.getProductName() %></td>
<td><%=product.getProductId() %></td>
<td><%=product.getProductStock() %></td>
<td><%=product.getProductRequired() %></td>
<td><%=product.getProductBuffer() %></td>
<td><%=product.getProductPrice() %></td>
<td><%=product.getProductRating() %></td>
<td>
<a href="UpdateServlet?productId=<%=product.getProductId()%>&productBuffer=<%=product.getProductBuffer()%>&productStock=<%=product.getProductStock()%>"><input type="submit" name="manage" value="Manage" /></a>
</td>
</tr>
<%	} %>
</table>
</body>
</html>