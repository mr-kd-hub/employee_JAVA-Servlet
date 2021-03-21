

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
 * Servlet implementation class Leave_CRUD
 */
@WebServlet("/Leave_CRUD")
public class Leave_CRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Leave_CRUD() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con=ConnectDB.connect();
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
		
		HttpSession hs = request.getSession(false);//fetch from session
		
		// Check session status , already created or not ? if already not created then redirect on Login.java
		
		if(hs == null)
		{
			response.sendRedirect("Login");
		}
		else{
			try{
				String from_date=request.getParameter("from_date");				
				String to_date=request.getParameter("to_date");				
				String reason=request.getParameter("reason");
				
				
				String name=(String)hs.getAttribute("uname"); //get from session
				
				
				String q="select id from emp where uname='"+name+"'";
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(q);
				rs.next();
				int id=rs.getInt(1);//eid
				
				q="insert into tbl_leave(eid,ename,from_Date,to_Date,reason,status) values(?,?,?,?,?,?)";
				PreparedStatement pd=con.prepareStatement(q);
				pd.setInt(1, id);
				pd.setString(2, name);
				pd.setString(3,from_date);
				pd.setString(4, to_date);
				pd.setString(5, reason);
				pd.setString(6,"P");				
				int n=pd.executeUpdate();
				
				if(n==1)
				{
					pw.print("<script>alert('Leave applied....')</script>");
					request.getRequestDispatcher("Leave.html").include(request, response);
					
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
