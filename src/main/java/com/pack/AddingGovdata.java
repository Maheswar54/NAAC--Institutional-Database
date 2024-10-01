package com.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddingGovdata
 */
public class AddingGovdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddingGovdata() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			
			String year = request.getParameter("year");
			String name = request.getParameter("name");
			String position = request.getParameter("position");
					Connection conn;

			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);
			// Create a connection to the database            
			String url = "jdbc:oracle:thin:@localhost:1521/orcl"; 
			conn = DriverManager.getConnection(url,"system","1254");
			String query="";
			PrintWriter out=response.getWriter();
			if(year.equals("2021")) {
			  query = "insert into GOVERNINGBODY values(?,?)";
			  PreparedStatement ps = conn.prepareStatement(query);
	 			ps.setString(1, name);
	 			ps.setString(2, position);
	 			ResultSet rs = ps.executeQuery();
	 			ps.close();
	 			out.println("<script>");
	 			out.println("alert('Added Successfully');");
	 			out.println("window.open('Admin_Home_page.html','_self');");
	 			out.println("</script>");
	}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}


}
