import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/SearchResult")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        response.setContentType("text/html");		
		PrintWriter pw = response.getWriter();
		
		// Create HttpSession Object		
		HttpSession hs = request.getSession(false);
		
		// Check session status , already created or not ? if already not created then redirect on Login.java
		
		if(hs == null)
		{
			response.sendRedirect("Login");
		}
		
		Connection con = ConnectDB.connect();
		
		try
		{
			String q;
			
			if(request.getParameter("Fetch").equals("Active"))//when click on link of active employee of profile.java
			{												//get from querystring
				q = "select * from emp where status = 'Active'"; 
			}
			else if(request.getParameter("Fetch").equals("Deactive"))//when click on link of deactive employee of profile.java
			{														//get from querystring
				q = "select * from emp where status = 'Deactive'";
			}
			else
			{
				q = "select * from emp where ename like '%"+ request.getParameter("Fetch") +"%'"; //get from textbox with name Fetch of profile.java 
			}
			
			PreparedStatement pst = con.prepareStatement(q);
			
			ResultSet rs = pst.executeQuery();
			
			pw.print( "<html>"
					
					+ "<head><title> Employee Result </title>"
					
					+ "<style>"
					
						+ "td,th{padding:14px 30px}"
						+ "body{font-family:arial;}"
						+ "a:hover{color:red;}"
						
					+ "</style>"
					
					+ "</head>"
					
					+ "<body style='background-color:#5a6457;'>"
					
					+ "<center>"
					
					+ "<br>"
					+ "<br><br>");
			pw.print("<a href='Profile' style='float:right;'>Home</a>"
					+ "<h2>Result</h2><br><br>");
			
			pw.print("<table style='margin-top:-50px;'><tr><th>Id</th>"
						      + "<th>Employee</th>"
							  + "<th>City</th>"
							  + "<th>Date Of Birth</th>"
							  + "<th>Gender</th>"
							  + "<th>Phone</th>"
							  + "<th>Update</th>"
							  + "<th>Delete</th></tr>");
			
			while(rs.next())
			{
				// Fetch record
				
				pw.print("<tr><td>"+ rs.getInt(1) + "</td>"
						   + "<td>"+ rs.getString(2)+ "</td>"
						   + "<td>"+ rs.getString(3)+ "</td>"
						   + "<td>"+ rs.getString(4)+ "</td>"
						   + "<td>"+ rs.getString(5)+ "</td>"
						   + "<td>"+ rs.getString(6)+ "</td>"
						   + "<td><a href='OperationForm?Id=" + rs.getInt(1) + "'>Update</a></td>"								    // Update Link
						   + "<td><a href='OperationWithDatabase?OperationType=Delete&Id=" + rs.getInt(1) + "'>Delete</a></td>"		// Delete Link
						   + "</tr><br><br>");	
			}
			
			pw.print("</table>");
			
			con.close();
		}
		catch(Exception ex)
		{
			pw.print(ex);
		}		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
