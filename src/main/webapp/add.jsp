
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Employee</title>
</head>
<body>
	<h2>Add New Employee</h2>
	<form action="add" method="post">
		Name: <input type="text" name="name" required><br> Email:
		<input type="email" name="email" required><br>
		Department: <input type="text" name="department" required><br>
		<input type="submit" value="Add Employee">
	</form>
</body>
</html>