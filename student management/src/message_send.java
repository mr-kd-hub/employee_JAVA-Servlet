
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
 * Servlet implementation class message_send
 */
@WebServlet("/message_send")
public class message_send extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public message_send() {
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
	//	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		// Create HttpSession Object		
		HttpSession hs = request.getSession(false);//fetch from session
		String type=(String)hs.getAttribute("type");
		
		//from employeepage messageEmp.java
		String uname=(String)hs.getAttribute("uname");
		String cmnt=request.getParameter("comment");
		
		//from adminpage messageA.java 
		String comment=request.getParameter("replay");
		String unameA=request.getParameter("username");
		
		if(hs == null)
		{
			response.sendRedirect("Login");
		}
		else
		{
			try
			{
				Connection con=ConnectDB.connect();
				if(type.equals("Admin"))
				{
					//send message to specific user
					//insert message in message table
					String q="insert into message(uname,msg) values(?,?)";
					PreparedStatement pst=con.prepareStatement(q);
					pst.setString(1, unameA);
					pst.setString(2, comment);
					int k=pst.executeUpdate();
					if(k==1)
					{
						pw.print("<script>alert('Message Successfully sent.... ')</script>");
						request.getRequestDispatcher("messageA").include(request, response);
					}
					else
					{
						pw.print("<script>alert('Message not sent.... ')</script>");
						request.getRequestDispatcher("messageA").forward(request, response);
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
