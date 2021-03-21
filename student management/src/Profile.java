
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
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); //to validate page after browser back button click
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		
		// Create HttpSession Object
		
		HttpSession hs = request.getSession(false);//fetch from session
		
		// Check session status , already created or not ? if already not created then redirect on Login.java
		
		if(hs == null)
		{
			response.sendRedirect("Login");
		}
		
		try
		{
	
			Connection con = ConnectDB.connect();
			
			// Write Select Query
			
			String fetch_students = "select * from emp";
			PreparedStatement pst = con.prepareStatement(fetch_students);			
			ResultSet rs = pst.executeQuery();
			
			String count_student = "select count(*) from emp";
			PreparedStatement pst2 = con.prepareStatement(count_student);			
			ResultSet rs2 = pst2.executeQuery(count_student);			
			rs2.next();
			
			String active_student = "select count(*) from emp where status = 'Active'";
			PreparedStatement pst3 = con.prepareStatement(active_student);			
			ResultSet rs3 = pst3.executeQuery(active_student);			
			rs3.next();
			
		
			
			
			
			
			pw.print( "<html>"
					
					+ "<head><title> admin panele </title>"
					
					+ "<style>"
					
						+ "td,th{padding:14px 30px}"
						//+ "body{font-family:arial;}"
						//+ "table{border:1px solid black;padding:20px;}"
						//+ "a{text-decoration:none;border:1px solid black;padding:10px 10px;}"
						+ "a:hover{color:red;}"
						
					+ "</style>"
					
					+ "</head>"
					
					+ "<body style='background: #5a6457;'>"
					
					+ "<center>"
					
					+ "<h2>Hello, Admin</h2>"
					
					
					+ "<br>"
					//total employee
					+ "<div style='background:#fefefa;float:left;color:black;border:1px solid black;padding:10px 10px;'>Total Employee <h2>"+ rs2.getInt(1) +"</h2></div>"
					
					//total active Employee
					+ "<div style='background:#fefefa;float:left;margin-left:15px;color:black;border:1px solid black;padding:10px 10px;'>Active Employee <h2><a href='SearchResult?Fetch=Active' style=' color:black;'>"+ rs3.getInt(1) +"</a></h2></div>"
				
					//total deactive Employee
					+ "<div style='background:#fefefa;float:left;margin-left:10px;color:black;border:1px solid black;padding:10px 10px;'>Deactive Employee <h2><a href='SearchResult?Fetch=Deactive' style='color:black;'>"+ (rs2.getInt(1)-rs3.getInt(1)) +"</a></h2></div>"
					
										
					//add Employee link
					+ "<div style='clear:both;'></div><div style='float:right; padding:12px;'><a href='OperationForm?Id=Add'>Add Employee</a>"									
					
					//message send
					+"<a href='messageA' style='margin-left:10px;'>Send message</a> "
					
					//view leave link
					+ "<a href='View_Leave_A' style='margin-left:10px;'>View Leave</a>"	
					
					// Logout Link
					+ "<a href='Logout' style='width:100px;height:35px;padding:5px 10px 5px 10px;margin-left:10px;border-radius:22px;'>Log Out</a></div>"
										
					+ "<br><br><br>"
					
					//search Employee
					+ "<div style='float:left;'><form action='SearchResult'><input type='text' name='Fetch' placeholder='Search Employee'><input type='submit' value='Search' style='margin-left:10px;'></form><br></div>"
					
					//generate pdf
					+ "<div style='float:right;'><form action='GeneratePDF'><input type='submit' value='Generate Report' style='margin-left:10px;'></form></div>"
					
					/*+ "<div style='clear:both;'><h2>Employee Details</h2>"*/
					
					+"<br><br><div margin-top:-70px;><center><b>Employees</b></center></div>");
			
			pw.print("<table margin-top:-50px;><tr><th>Id</th>"
						      + "<th>Employee</th>"
							  + "<th>city</th>"
							  + "<th>Date Of Birth</th>"
							  + "<th>Gender</th>"
							  + "<th>Phone</th>"
							  + "<th>Status</th>"
							  + "<th>Update</th>"
							  + "<th>Delete</th></tr>");
			
			while(rs.next())
			{
				// Fetch record
				
				pw.print("<tr><td>"+ rs.getInt(1) + "</td>"
						   + "<td>"+ rs.getString(3)+ "</td>"
						   + "<td>"+ rs.getString(4)+ "</td>"
						   + "<td>"+ rs.getString(5)+ "</td>"
						   + "<td>"+ rs.getString(6)+ "</td>"
						   + "<td>"+ rs.getString(7)+ "</td>"
						   + "<td>"+ rs.getString(8)+ "</td>"
						   + "<td><a href='OperationForm?Id=" + rs.getInt(1) + "'>Update</a></td>"								// Update Link
						   + "<td><a href='OperationWithDatabase?OperationType=Delete&Id=" + rs.getInt(1) + "'>Delete</a></td>"		// Delete Link
						   + "</tr><br><br>");	
			}
			
			pw.print("</table></div></body></html>");
			
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
