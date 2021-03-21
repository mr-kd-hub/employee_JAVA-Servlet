

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class messageEmp
 */
@WebServlet("/messageEmp")
public class messageEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public messageEmp() {
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
		PrintWriter pw=response.getWriter();
		pw.print("<head><style>ul {list-style-type: none;margin: 0;padding: 0;overflow: hidden;background-color: #333;}"
				+ "li {float: left;}"
				+ "li a {display: block;color: white;text-align: center;padding: 14px 16px;text-decoration: none;}"
				+ "li a:hover:not(.active) { background-color: #111;}"
				+ ".active {background-color: #4CAF50;}"
				+ "table {font-size:16px;margin-top:2%;margin-left:7%;border:1px solid black;padding:10px 10px 10px 10px;}"
				+ "h1{margin-top:2%;margin-left:7%;}</style></head>");
		
		
		
		pw.print("<html><ul><li><a href='employeform.html'>Home</a></li><li><a href='Leave.html'>Apply Leave</a></li><li><a href='View_Leave'>View Leave</a></li><li><a href='account'>Profile</a></li><li><a class='active' href='messageEmp'>Message</a></li><li><a href='Logout'>Logout</a></li></ul></html>");
		
		//response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		// Create HttpSession Object		
		HttpSession hs = request.getSession(false);//fetch from session
		String type=(String)hs.getAttribute("type");
		String uname=(String)hs.getAttribute("uname");
		// Check session status , already created or not ? if already not created then redirect on Login.java
		
		if(hs == null)
		{
			response.sendRedirect("Login");
		}
		else
		{
			if(type.equals("employee"))
			try
			{
				pw.print("<html><body style='background: linear-gradient(360deg, #01a99c 10%, #0698b1 360%);'>");
				Connection con=ConnectDB.connect();
				String q="select * from message where uname='"+uname+"'";
				Statement st=con.createStatement();
				pw.print("<div style='padding:2px;'><center><h1>Messagess</h1>");
				ResultSet rs=st.executeQuery(q);
				while(rs.next())
				{
					pw.print("<form><textarea rows='4' cols='50' readonly'>"+rs.getString(3)+"</textarea><hr></form>");
				}
				pw.print("</center></div></form></body></html>");
				
				
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
