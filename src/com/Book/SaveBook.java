package com.Book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveBook")
public class SaveBook extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement ps;
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		

		super.init(config);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "sahil";
			String password = "sahil";
			con = DriverManager.getConnection(url, id, password);
			String sql="insert into Books values(?,?,?,?,?)";
			ps=con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public void destroy() {
		super.destroy();
		try {
		con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();

		String Name = request.getParameter("name");
		String Id = request.getParameter("id");
		String Author = request.getParameter("author");
		String Year = request.getParameter("year");
		String Pages = request.getParameter("pages");
		
		try { 
		ps.setString(1, Name);
		ps.setString(2, Id);
		ps.setString(3, Author);
		ps.setString(4, Year);
		ps.setString(5, Pages);
		ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("Record Added Successfully");

	}

}
