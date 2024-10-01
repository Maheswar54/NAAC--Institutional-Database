package com.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Governing
 */
@WebServlet("/Governing1")
public class Governing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Governing() {
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
		System.out.println(year);
				Connection conn;

		String driverName = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driverName);
		// Create a connection to the database            
		String url = "jdbc:oracle:thin:@localhost:1521/orcl"; 
		conn = DriverManager.getConnection(url,"system","1254");
		String query="";
		if(year.equals("2021")) {
			query = "select * from GOVERNINGBODY";
		}else if(year.equals("2022")) {
			query = "select * from GoverningBody_2022";
		}else {
			query = "select * from GoverningBody_2020";
		}
		Statement st;
		
			st = conn.createStatement();
		
		ResultSet rs;
		
			rs = st.executeQuery(query);
	
		PrintWriter out = response.getWriter();
		int i=1;
		if(rs.next()) {
			request.setAttribute("status", "success");
		//out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Governing Body Web Page</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; }");
        out.println("header { background-color: #333; color: #fff; padding: 1em; }");
        out.println("table { margin: 20px auto; border-collapse: collapse; width: 80%; background-color:palegreen; }");
        out.println("table, th, td { border: 1px solid #333; }");
        out.println("th, td { padding: 10px; }");
       // out.println("select, button { width:100px; margin: 10px; background-color:skyblue; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Governing Body</h1>");
        out.println("</header>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>SNO</th>");
        out.println("<th>NAME</th>");
        out.println("<th>POSITION</th>");
        out.println("</tr>");
        
        
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+i+"</td>");
				//out.println("<td>"+rs.getString("SNO")+"</td>");
				out.println("<td>"+rs.getString("NAME")+"</td>");
				
					out.println("<td>"+rs.getString("POSITION")+"</td>");
				
				out.println("</tr>");
				i=i+1;
			}
		
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
		else {
			request.setAttribute("status", "failed");
			out.println("<script>");
			out.println("alert('No User Found');");
			out.println("window.open('governing.html','_self');");
			out.println("</script>");
		}
		}
		
		catch(Exception e) {
			System.out.println(e);
		}


		
	}
}

