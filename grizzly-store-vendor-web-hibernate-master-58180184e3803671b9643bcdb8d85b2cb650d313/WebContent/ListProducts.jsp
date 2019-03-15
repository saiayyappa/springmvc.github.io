<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.grizzly.pojo.ProductPojo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="AdminListProducts.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<%
	ArrayList productList = (ArrayList) session.getAttribute("productList");
	Iterator iterator = productList.iterator();
%>
<table>
<tr>
<th>Product List</th>
<th>Brand</th>
<th>Category</th>
<th>Rating</th>
</tr>
<%	
	while(iterator.hasNext())
	{
		ProductPojo product = (ProductPojo)iterator.next();
%>
<tr>
<td><%=product.getProductName() %></td>
<td><%=product.getProductBrand() %></td>
<td><%=product.getProductCategory() %></td>
<td><%=product.getProductRating() %></td>
<td>
<input type="button" name="view" value="View" />
</td>
<td>
<input type="button" name="block" value="Block" />
</td>
<td>
<a href="StoreServlet?id=<%=product.getProductId() %>">
<input type="submit" name="delete" value="Delete" />
</a>
</td>
</tr>
<%	} %>
</table>
</body>
</html>