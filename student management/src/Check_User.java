

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Check_User
 */
@WebServlet("/Check_User")
public class Check_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_User() {
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
		PrintWriter pw = response.getWriter();
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		try
		{
			// Get parameter from Login.java
			
			String uname = request.getParameter("uname");
			
			String pass = request.getParameter("pass");
			
			Connection con = (Connection) ConnectDB.connect();
			
			// GET Username And Password From users table
			
			String q = "select * from users where username = (?) and password =md5(?)";
			
			PreparedStatement pst = con.prepareStatement(q);
			
			// Set Parameter
			
			pst.setString(1,uname);
			
			pst.setString(2,pass);
			
			ResultSet rs = pst.executeQuery();
			
			//String U , P;
			
			// Fetch Row
			
			if(rs.next())
			{
				HttpSession hs =request.getSession();
				hs.setAttribute("user", uname);
				
				// Check Username And Password		
				
				if(uname.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin"))
				{		
					// Create HttpSession Object
				hs.setAttribute("type", "Admin");
				
					
					// Create RequestDispatcher Object
					
					RequestDispatcher rd = request.getRequestDispatcher("Profile");
					
				    rd.forward(request, response); // forward() method use	
				}
				else
				{
					hs.setAttribute("type", "employee");
					hs.setAttribute("uname", uname);
					Statement st=con.createStatement();
					String q1= "select eid from users where username='"+uname+"'";
					ResultSet rs1 = st.executeQuery(q1);
					
				
						//pw.print("<a href='employee?ID=+rs1.getInt(1)+'></a>");//sent in query string
						
						
					
				//	<a href='employee?Id=" + rs.getInt(1) + "'>Update</a>
					RequestDispatcher rd = request.getRequestDispatcher("employeform.html");
					rd.forward(request, response); // forward() method use
				}
			}
			else
			{
				// Otherwise display message and redirect on Login.java
				
				pw.print("<center><h3 style='color:red;'>Invalide,Username or Password !!!</h3></center>");
				
				RequestDispatcher rd = request.getRequestDispatcher("Login");
				
				rd.include(request, response); // include() method	
			}
			
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
