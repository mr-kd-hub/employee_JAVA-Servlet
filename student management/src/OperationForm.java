
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
//Update Link
/**
 * Servlet implementation class OperationForm
 */
@WebServlet("/OperationForm")
public class OperationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationForm() {
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
response.setContentType("text/html");
//response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
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
		
		
		pw.print( "<html>"
				
				+ "<head><title> Updation Form </title>"
				
				+ "<style>"
				
					+ "td{padding:10px 10px}"
					+ "body{font-family:arial;}"
					+ "table{border:1px solid black;padding:20px;}"
					+ "a{text-decoration:none;border:1px solid black;padding:10px 10px;}"
					+ "a:hover{color:red;}"
					+ ".btn{padding:10px 20px;}"
					
				+ "</style>"
				
				+ "</head>"
				
				+ "<body style='background-color:#5a6457;'>"
				);
	
		pw.print( "<center>"
				
				+ "<h2>Hii Admin</h2>"
				
				+ "<br>"
				
			);
		pw.print("<a href='Profile'>Home</a>");
			
		String Id = request.getParameter("Id");//get from query string
			
		
			
			ResultSet rs4 = null;
			
			// Add emploee Code
			if(Id.equals("Add"))	//from query string , form profile.java page
			{
			//	pw.print("<a href='Profile'>Home</a>");
				pw.print("<h1>Addd New Employee</h1>");
				
				pw.print("<form action='OperationWithDatabase' method='post'>"     // Redirect OpetationWithDatabase.java
						+ "<table>"
						+ "		<tr>"
						+ "     	<td>Employee Name</td>"
						+ "			<td><input type='text' name='ename' placeholder='Employee Name'></td>"
						+ "		</tr>"
						+"       <tr>"
						+ "     	<td>Username</td>"
						+ "			<td><input type='text' name='uname' placeholder='Username'></td>"
						+ "		</tr>"
						+ "		<tr>"
						+ "     	<td>City</td>"
						+ "			<td><input type='text' name='city' placeholder='city'></td>"
						+ "		</tr>"
						
						+ "		<tr>"
						+ "     	<td>Date Of Birth</td>"
						+ "			<td><input type='date' name='dob'></td>"
						+ "		</tr>"
						
						+ "		<tr>"
						+ "     	<td>Gender</td>"
						+ "			<td><input type='radio' name='gender' value='Male'> Male"
						+ "				<input type='radio' name='gender' value='Female'> Female</td>"
						+ "		</tr>"
						
						+ "		<tr>"
						+ "     	<td>Phone</td>"
						+ "			<td><input type='text' name='phone' placeholder='Phone No'></td>"
						+ "		</tr>"
						
						+ "		<tr>"
						+ "     	<td>Status</td>"
						+ "			<td><select name='status'><option>Active</option><option>Deactive</option></select></td>"
						+ "		</tr>"
											
						+ "		<tr>"
						+ "        <input type='hidden' value='Add' name='OperationType'>"		// Hidden Field for send OperationType (Add Student)
						+ "			<td><input type='submit' value='Add' class='btn'></td>"
						+ "		</tr>"
						
						+ "		<tr></table></form>" );
					
			}
			
			// Update Student Code
			else
			{
			//	pw.print("<a href='Profile'>Home</a>");
					pw.print("<h1>Update emp Id = "+ Id +"</h1>");//heading of page
				
					// Write Select Query
					
					String q = "select * from emp where id = "+ Integer.parseInt(Id) +"";//thid Id is from query string
					
					PreparedStatement pst1 = con.prepareStatement(q);
					
					rs4 = pst1.executeQuery();
				
					rs4.next();
					
					// Set controls value according employe id
					
					pw.print("<form action='OperationWithDatabase' method='post'>"
							+ "<table>"
							+ "		<tr>"
							+ "     	<td>Employee Name</td>"
							+ "			<td><input type='text' name='ename' placeholder='Student Name' value='"+rs4.getString(3)+"'></td>"
							+ "		</tr>"
							
							+ "		<tr>"
							+ "     	<td>City</td>"
							+ "			<td><input type='text' name='city' placeholder='city' value='"+rs4.getString(4)+"'></td>"
							+ "		</tr>"
							
							+ "		<tr>"
							+ "     	<td>Date Of Birth</td>"
							+ "			<td><input type='date' name='dob' value='"+rs4.getString(5)+"'></td>"
							+ "		</tr>"
							
							+ "		<tr>"
							+ "     	<td>Gender</td>");
					
					if(rs4.getString(6).equals("Male"))
					{
						pw.print("<td><input type='radio' name='gender' value='Male' checked> Male"
								   + "<input type='radio' name='gender' value='Female'> Female");
					}
					else
					{
						pw.print("<td><input type='radio' name='gender' value='Male' > Male"
								   + "<input type='radio' name='gender' value='Female' checked> Female");
					}
					
					pw.print("</tr>"
							
							+ "		<tr>"
							+ "     	<td>Phone</td>"
							+ "			<td><input type='text' name='phone' placeholder='Phone No' value='" + rs4.getString(7) + "'></td>"
							+ "		</tr>");
					
					if(rs4.getString(8).equals("Active"))
					{
						pw.print( 	"		<tr>"
									+ "     	<td>Status</td>"
									+ "			<td><select name='status'><option selected>Active</option><option>Deactive</option></select></td>"
									+ "		</tr>");
					}
					else
					{
						pw.print( 	"		<tr>"
									+ "     	<td>Status</td>"
									+ "			<td><select name='status'><option>Active</option><option selected>Deactive</option></select></td>"
									+ "		</tr>");
					}
					
					
					pw.print("		<tr>"
							+ "             <input type='hidden' value='Update' name='OperationType'>"			// Hidden Field for send OperationType (Update Student)
							+ "				<input type='hidden' value='"+rs4.getString("Id")+"' name='Id'>"	// Pass employee Id
							+ "				<td><input type='submit' value='Update' class='btn'></td>"			// Update Button
							+ "		</tr>"
							
							+ "		</table></form>" );
						
			}
		
			pw.print("</center></body></html>");
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
