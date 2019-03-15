<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<%
	int id = (Integer) request.getAttribute("productId");
	int buffer = (Integer) request.getAttribute("productBuffer");
	int stock = (Integer) request.getAttribute("productStock"); 
%>
<form action="UpdateServlet" method="get">
Product ID:<input type="number" value="<%= id%>" name="id" readonly/><br><br>
Product Buffer:<input type="number" value="<%= buffer%>" name="buffer" readonly/><br><br>
Product Stock:<input type="number" name="stock" value=<%= stock%> /><br><br>
<input type="submit" name="update" value="Update" />
</form>
</body>
</html>