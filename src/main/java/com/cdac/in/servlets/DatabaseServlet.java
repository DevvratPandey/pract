package com.cdac.in.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdac.in.pojo.Employee;

public class DatabaseServlet extends HttpServlet {

	private Connection con;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/messi", "root", "nitin");
			con = conn;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// do something with query
		/*
		 * try { Statement stmt = con.createStatement(); ResultSet rs =
		 * stmt.executeQuery("SELECT * FROM EMPLOYEE"); processResult(rs) } } catch
		 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM EMPLOYEE");
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Employee> employees = processResult(resultSet);
			req.setAttribute("emp", employees);
			req.getRequestDispatcher("/success.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private List<Employee> processResult(ResultSet rs) throws SQLException {
		List<Employee> employeelist = new ArrayList<>();
		while (rs.next()) {
			Employee employee = new Employee();
			employee.setUsername(rs.getString("USER_NAME"));
			employee.setPassword(rs.getString("PASS"));
			employeelist.add(employee);
		}
		return employeelist;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

}
