package com.pack;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Fdps
 */
@WebServlet("/Fdps")
public class Fdps extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fdps() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		response.setContentType("text/html");
 		PrintWriter out=response.getWriter();
 		String year=request.getParameter("year");
 		String dept = request.getParameter("dept");
 		
 	try {
 		Connection conn;

 	      // Load the JDBC driver
 	      String driverName = "oracle.jdbc.driver.OracleDriver";
 	      Class.forName(driverName);

 	      // Create a connection to the database            
 	      String url = "jdbc:oracle:thin:@localhost:1521/orcl"; 

 	      conn = DriverManager.getConnection(url,"system","1254");
 			//System.out.println(st);
 			String query = "select * from FDPSDATA where dept = ? AND year = ?";
 			PreparedStatement ps = conn.prepareStatement(query);
 			ps.setString(1, dept);
 			ps.setString(2, year);
    ResultSet rs = ps.executeQuery();
    if(rs.next()) {
		request.setAttribute("status", "success");
		out.println("<html><head><style>p{font-size: 30px;}");
		out.println("#btn{background-color: brown;color: black;border: 1px solid black;font-size: 20px;padding: 24px;border-radius: 8px;}");
		out.println("img{width: 100px;height: 70px;position: relative;left: 1240px;top: -100px}");
		out.println("body{background-image: linear-gradient(to right, #b6fbff, #83a4d4);font-family: 'Trebuchet MS', sans-serif;overflow-x: hidden;}</style></body>");
		out.println("<body>");
		out.println("<button id='btn'>Back</button>");
		out.println("<p font -size : 30px><center>The Selected Faculty data </p></center>");
		//out.println("<img src='logo.png'");
	    out.println("<hr>");
		out.println("</br><center><table cellspacing='0' cellpadding='5' border='1'>");
		out.println("<tr>");
		out.println("<th>S.No.</th>");
		out.println("<th>Name</th>");
		out.println("<th>Host_Institute</th>");
		out.println("<th>Programme</th>");
		out.println("<th>Year</th>");
		out.println("<th>Department</th>");
		
		out.println("</tr>");
		while(rs.next()) {
			out.println("<tr>");
			//out.println("<td>"+i+"</td>");
			out.println("<td>"+rs.getString("SNO")+"</td>");
			out.println("<td>"+rs.getString("NAME")+"</td>");
			out.println("<td>"+rs.getString("Host_Institute")+"</td>");
			out.println("<td>"+rs.getString("Programme")+"</td>");
			out.println("<td>"+rs.getString("Year")+"</td>");
			out.println("<td>"+rs.getString("Dept")+"</td>");
			
			out.println("</tr>");
		}
		out.println("</table></center><br><hr>");
		out.println("<script>");
		out.println("document.getElementById('btn').addEventListener('click', () => {history.back();});");
		out.println("</script></body></html>");
	}
	else {
		request.setAttribute("status", "failed");
		out.println("<script>");
		out.println("alert('No User Found');");
		out.println("window.open('Home_page.html','_self');");
		out.println("</script>");
	}
	//dispatcher.forward(request, response);
	ps.close();
	conn.close();
}

 	catch (Exception e){
 		System.out.println(e);
 	}
 	}
 	
 	}

