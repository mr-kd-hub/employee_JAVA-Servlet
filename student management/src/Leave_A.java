

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Leave_A
 */
@WebServlet("/Leave_A")
public class Leave_A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Leave_A() {
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
	Connection con=ConnectDB.connect();
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	//	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
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
					String id=request.getParameter("Id");//from querystring
					String status=request.getParameter("Op");//from querystring
					
					String q="update tbl_leave set status=? where lid=?";
					PreparedStatement pd=con.prepareStatement(q);
					pd.setString(1,status);
					pd.setInt(2,Integer.parseInt(id));
					int n=pd.executeUpdate();
					if(n==1)
					{
						pw.print("<script>alert('Success......')</script>");
						request.getRequestDispatcher("View_Leave_A").forward(request, response);
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
