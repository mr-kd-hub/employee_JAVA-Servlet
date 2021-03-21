

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class View_Leave
 */
@WebServlet("/View_Leave")
public class View_Leave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection con=ConnectDB.connect();

    public View_Leave() {
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
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		PrintWriter pw = response.getWriter();
		
		// Create HttpSession Object		
		HttpSession hs = request.getSession(false);//fetch from session
		
		// Check session status , already created or not ? if already not created then redirect on Login.java
		pw.print("<head><style>ul {list-style-type: none;margin: 0;padding: 0;overflow: hidden;background-color: #333;}"
				+ "li {float: left;}"
				+ "li a {display: block;color: white;text-align: center;padding: 14px 16px;text-decoration: none;}"
				+ "li a:hover:not(.active) { background-color: #111;}"
				+ ".active {background-color: #4CAF50;}"
				+ "table {font-size:16px;margin-top:2%;margin-left:7%;border:1px solid black;padding:10px 10px 10px 10px;}"
				+ "h1{margin-top:2%;margin-left:7%;}</style></head>");
		
		
		
		pw.print("<html><ul><li><a href='employeform.html'>Home</a></li><li><a href='Leave.html'>Apply Leave</a></li><li><a class='active' href='View_Leave'>View Leave</a></li><li><a href='account'>Profile</a></li><li><a href='messageEmp'>Message</a></li><li><a href='Logout'>Logout</a></li></ul></html>");
		
		
		
		
		
		
		
		
		if(hs == null)
		{
			response.sendRedirect("Login");
		}
		else{
			try{
				String name=(String)hs.getAttribute("uname");
				
				
				String q="select id from emp where uname='"+name+"'";
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(q);
				rs.next();
				int id=rs.getInt(1);
				q="select * from tbl_leave where eid='"+id+"'";
				rs=st.executeQuery(q);
				pw.print("<body style='background-color:    #85c1e9  ;'><table style='text-align: center;width: 100%;border-collapse: collapse;border: 1px solid black;'><tr><th style='padding: 15px;height: 50px;border: 1px solid black;'>From Date</th><th style='padding: 15px;height: 50px;border: 1px solid black;'>To Date</th><th style='padding: 15px;height: 50px;border: 1px solid black;'>Reason</th><th style='padding: 15px;height: 50px;border: 1px solid black;'>Result</th></tr>");
				while(rs.next())
				{
					pw.print("<tr><td style='text-align: center; padding: 15px; border: 1px solid black;'>"+rs.getString(4)+"</td><td style='text-align: center; padding: 15px; border: 1px solid black;'>"+rs.getString(5)+"</td><td style='text-align: center; padding: 15px; border: 1px solid black;'>"+rs.getString(6)+"</td>");
					if(rs.getString(7).equals("P"))
					{
						pw.print("<td style='text-align: center; padding: 15px; border: 1px solid black;'>Pending</td>");
					}
					else if(rs.getString(7).equals("R"))
					{
						pw.print("<td style='text-align: center; padding: 15px; border: 1px solid black;'>Rejected</td>");
					}
					else if(rs.getString(7).equals("G"))
					{
						pw.print("<td style='text-align: center; padding: 15px;border: 1px solid black;'>Approved</td>");
					}
				}pw.print("</tr></table></body>");
			}catch(Exception e){}
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
