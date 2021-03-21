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

import sun.font.CreatedFontTracker;

import com.sun.net.httpserver.HttpsConfigurator;

/**
 * Servlet implementation class account
 */
@WebServlet("/account")
public class account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public account() {
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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");	
		PrintWriter pw = response.getWriter();
		pw.print("<head><style>ul {list-style-type: none;margin: 0;padding: 0;overflow: hidden;background-color: #333;}"
				+ "li {float: left;}"
				+ "li a {display: block;color: white;text-align: center;padding: 14px 16px;text-decoration: none;}"
				+ "li a:hover:not(.active) { background-color: #111;}"
				+ ".active {background-color: #4CAF50;}"
				+ "table {font-size:16px;margin-top:2%;margin-left:7%;border:1px solid black;padding:10px 10px 10px 10px;}"
				+ "h1{margin-top:2%;margin-left:7%;}</style></head>");
		
		
		
		pw.print("<html><ul><li><a href='employeform.html'>Home</a></li><li><a href='Leave.html'>Apply Leave</a></li><li><a href='View_Leave'>View Leave</a></li><li><a class='active' href='account'>Profile</a></li><li><a href='messageEmp'>Message</a></li><li><a href='Logout'>Logout</a></li></ul></html>");
		
		
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		// Create HttpSession Object		
		HttpSession hs = request.getSession(false);//fetch from session
		
		// Check session status , already created or not ? if already not created then redirect on Login.java
		if(hs == null)
		{
			response.sendRedirect("Login");
		}
		else
		{
			String type=(String)hs.getAttribute("type");
			String uname=(String)hs.getAttribute("uname");
			//int a = (int)id.getAttribute("ID");
			//int a=Integer.parseInt(id.getAttribute("ID"));
			try
			{
				Connection con= ConnectDB.connect();
				//String Id = request.getParameter("Id");//get from query string
				if(type.equals("employee"))
				{
					String q = "select * from emp where uname='"+uname+"';";
					Statement st=con.createStatement();
					
					ResultSet rs=st.executeQuery(q);
					if(rs.next())
					{
						
						pw.print("<body style='background-color:    #85c1e9  ;'><center><form action='OperationWithDatabase' method='post'> <div style=width:50%; height: 600%; margin-top: 50px;'><br><h1>Hello,</h1> "
								+ "<table style='text-align:center;  padding: 20px; '>"
								+ "<tr><th>Username</th><td>:</td><td><input type='text' value="+rs.getString(2)+" name='uname' readonly></td></tr>"
								+"<tr><th>Employee Name</th><td>:</td><td><input type='text' value="+rs.getString(3)+" name='ename' readonly></td></tr>"
								+"<tr><th>City</th><td>:</td><td><input type='text' value="+rs.getString(4)+" name='city'></td></tr>"
								+"<tr><th>Date Of birth</th><td>:</td><td><input type='date' value="+rs.getString(5)+" name='dob'></td></tr>");
						if(rs.getString(6).equals("Male"))
						{
							pw.print("<tr><th>Gender</th><td>:</td><td><input type='radio' name='gender' value='Male' checked> Male"
									   + "<input type='radio' name='gender' value='Female'> Female </td></tr>");
						}
						else
						{
							pw.print("<tr><th>Gender</th><td>:</td><td><input type='radio' name='gender' value='Male' > Male"
									   + "<input type='radio' name='gender' value='Female' checked> Female</td></tr>");
						}
						pw.print("<tr><th>Mobile No</th><td>:</td><td><input type='text' value="+rs.getString(7)+" name='dob'></td></tr>");
						pw.print("             <input type='hidden' value='Update' name='OperationType'>"			// Hidden Field for send OperationType (Update Student)
								+ "				<input type='hidden' value='"+rs.getString("Id")+"' name='Id'>"	// Pass employee Id
								+ "				<td><input type='submit' value='Update' class='btn'></td>"			// Update Button
								);
						pw.print("</table></div> </form><center></body>");
						
						
					}
					
				}
			}
			catch(Exception e)
			{
				pw.print(e);
			}
			
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
