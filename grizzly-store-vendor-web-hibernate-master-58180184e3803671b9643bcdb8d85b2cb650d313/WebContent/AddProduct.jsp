<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<form action="StoreServlet" method="get" >
<input type="text" name="name" placeholder="Enter Product Name" /><br><br>
<input type="text" name="brand" placeholder = "Enter Product Brand" /><br><br>
<input type="text" name="category" placeholder = "Enter Product Category" /><br><br>
<input type="text" name="rating" placeholder = "Enter Product Rating" /><br><br>
<input type="number" name="buffer" placeholder = "Enter Product Buffer" /><br><br>
<input type="number" name="price" placeholder = "Enter Product Price" /><br><br>
<input type="submit" name="AddProducttoDB" value="Add" />
<a href="ListProducts.jsp" >
<input type="button" name="cancel" value="Cancel" />
</a>
</form>
</body>
</html>