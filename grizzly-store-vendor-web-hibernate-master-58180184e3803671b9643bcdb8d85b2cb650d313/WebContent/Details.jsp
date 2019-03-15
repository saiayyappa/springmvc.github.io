<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="Details.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<% 
		String userName = (String) session.getAttribute("userName");
		int id = (Integer) session.getAttribute("id");
		String role = (String) session.getAttribute("role");
%>
<div id="details">
<h2>PROFILE</h2>
<h4>Edit</h4>
</div>
<h2><%=userName %></h2>
<h3>ID</h3>
<h4><%=id %></h4>
<h3>Designation</h3>
<h4><%=role %></h4>
</body>
</body>
</html>