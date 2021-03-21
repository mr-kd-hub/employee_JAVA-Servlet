

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class View_Leave_A
 */
@WebServlet("/View_Leave_A")
public class View_Leave_A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection con=ConnectDB.connect();
	public View_Leave_A() {
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
		// TODO Auto-generated method stub
	//	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setContentType("text/html");
				
				PrintWriter pw = response.getWriter();
				
				// Create HttpSession Object
				
				HttpSession hs = request.getSession(false);//fetch from session
				
				// Check session status , already created or not ? if already not created then redirect on Login.java
				pw.print("<body style='background-color:#5a6457;'>");
				
				if(hs == null)
				{
					response.sendRedirect("Login");
				}
				else{
					try{
						String p="P";
						String q="select * from tbl_leave where status='"+p+"'";
						Statement st=con.createStatement();
						ResultSet rs=st.executeQuery(q);
						pw.print("<a href='Profile' style='float:right;'>Home</a>");
						pw.print("<table style='text-align: center;width: 100%;border-collapse: collapse;border: 1px solid black;'><tr><th style='padding: 15px;height: 50px;border: 1px solid black;'>Employee Name</th><th style='padding: 15px;height: 50px;border: 1px solid black;'>From Date</th><th style='padding: 15px;border: 1px solid black;height: 50px;'>To Date</th><th style='padding: 15px;height: 50px;border: 1px solid black;'>Reason</th><th style='padding: 15px;height: 50px;border: 1px solid black;'>Action</th></tr>");
						while(rs.next())
						{
							pw.print("<tr><td style='text-align: center;padding: 15px;border: 1px solid black;'>"+rs.getString(3)+"</td><td style='padding: 15px;border: 1px solid black;'>"+rs.getString(4)+"</td><td style='padding: 15px;border: 1px solid black;'>"+rs.getString(5)+"</td><td style='padding: 15px;border: 1px solid black;'>"+rs.getString(6)+"</td>");
							pw.print("<td style='text-align: center;padding: 15px;border: 1px solid black;'><a href='Leave_A?Id="+rs.getInt(1)+"&Op=G'><input type='submit' name='Submit' value='Grant'/></a>");
							pw.print("<a href='Leave_A?Id="+rs.getInt(1)+"&Op=R'><input type='submit' name='Submit' value='Reject'/></a></td></tr>");
						}pw.print("</table>");
							//con.close();
					}catch(Exception e){pw.print(e);}
					pw.print("</body>");
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
