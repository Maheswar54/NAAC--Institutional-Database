package com.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/login1")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		System.out.println(username);
		String password =request.getParameter("password");
		try {
			Connection conn;

			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);
			// Create a connection to the database            
			String url = "jdbc:oracle:thin:@localhost:1521/orcl"; 
			conn = DriverManager.getConnection(url,"system","1254");
              Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select *from logins");
			while(rs.next()) {
				System.out.println(rs.getString("username")+"hello");
				if(rs.getString("username").equals(username)) {
					 if(rs.getString("password").equals(password) ){
						 out.print("Successfully logined");
						response.sendRedirect("Admin_Home_page.html");
						 
					 }
					 else {
						 out.print("invalid password");
					 }
					
				}
				else {
					out.print("invalid username");
				}
			}
			
			
			
			
			
		}
		catch(Exception e) {
			out.print(e);
			
	}
		
		
		
	}


}
