package com.employee.management.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.management.model.Employee;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String department = request.getParameter("department");

		try {
			// JDBC connection setup
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root",
					"Akash@123");
			String query = "INSERT INTO Employee (name, email, department) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, department);
			ps.executeUpdate();
			ps.close();
			con.close();

			response.sendRedirect("list"); // Redirect to list all employees after adding
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Employee> employeeList = new ArrayList<>();

		try {
			// JDBC connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root",
					"Akash@123");
			String query = "SELECT * FROM Employee";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			// Iterate through result set and add employees to the list
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setEmail(rs.getString("email"));
				emp.setDepartment(rs.getString("department"));
				employeeList.add(emp);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Set the list as an attribute and forward to list.jsp
		request.setAttribute("employeeList", employeeList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);
	}

}
