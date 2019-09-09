package com.Book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookSearch")
public class BookSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement ps;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "sahil";
			String password = "sahil";
			con = DriverManager.getConnection(url, id, password);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		 String Name=request.getParameter("name");
		
		String sql="select Name,Id,Author,Year,Pages from Books where Name='"+Name+"'";
	
		try
		{
		
		 ps=con.prepareStatement(sql);
		 
		 ResultSet rs=ps.executeQuery();
		 while(rs.next())
		 {
			 out.println(rs.getString(1));
			 out.println(rs.getString(2));
			 out.println(rs.getString(3));
			 out.println(rs.getString(4));
			 out.println(rs.getString(5));
		 }

		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}

}
