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
 * Servlet implementation class messageA
 */
@WebServlet("/messageA")
public class messageA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public messageA() {
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
		
		// Create HttpSession Object
		//response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		HttpSession hs = request.getSession(false);//fetch from session
	
		// Check session status , already created or not ? if already not created then redirect on Login.java
		
		
		if(hs == null)
		{
			response.sendRedirect("Login");
		}
		else
		{
			String type=(String)hs.getAttribute("type");
			
			if(type.equals("Admin"))
			{
				try
				{
					Connection con=ConnectDB.connect();
					//get usernames in groupbox
					String u="select * from users";
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(u);
					pw.print("<a href='Profile' style='float:right;'>Home</a><br>");
					pw.print("<html><body style='background-color:#5a6457;'>"
							+ "<form action='message_send' method='post'>"
							+ "<table>"
							+ "<tr>"
							+ "<th>Username</th><td>:</td>"
							+ "<td>"
							+ "<select name='username'>");
							
							while(rs.next())
							{
								pw.print("<option value="+rs.getString(2)+">"+rs.getString(2)+"</option>");
							}
							
						pw.print("</select>"
							+ "</td></tr>"
							+ "<tr><th>Message</th><th>:</th>"
							+ "<td><textarea rows='4' cols='50' name='replay'>message write here</textarea></td>"
							+"</tr>"
							+ "<tr>"
							+ "<td><input type='submit' value='Send'>"
							+ "<input type='reset' value='Reset'></td>"
							+ "</tr>"
							+ "</table>"
							+ "</form>"
							+ "<br>");
						
						String re="select * from message";
						Statement st1=con.createStatement();
						ResultSet rs1=st1.executeQuery(re);
						pw.print("<table  style='text-align: center;width: 100%;border-collapse: collapse;border: 1px solid black;'>"
								+ "<tr><th style='padding: 15px;height: 50px;border: 1px solid black;'>Username</th><th style='padding: 15px;height: 50px;border: 1px solid black;'>Message</th><th style='padding: 15px;height: 50px;border: 1px solid black;'>Delete Message</th></tr>");
						while(rs1.next())
						{
							pw.print("<tr><td style='text-align: center;padding: 15px;border: 1px solid black;'>"+rs1.getString(2)+"</td><td style='text-align: center;padding: 15px;border: 1px solid black;'>"+rs1.getString(3)+"</td><td style='padding: 15px;height: 50px;border: 1px solid black;'><form action='deletemessage?Id="+rs1.getInt(1)+"' method='post'><input type='submit' value='Delete'></form></td></tr>");
						}
						pw.print("</table></body></html>");
						
						con.close();
				}
				catch(Exception e)
				{
					pw.print(e);
				}
			}
			else
			{
				response.sendRedirect("Login");
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
