
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		//response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String uname=request.getParameter("uname");
		String ename=request.getParameter("ename");
		String city=request.getParameter("city");
		String dob=request.getParameter("dob");
		//String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		String passwd=request.getParameter("passwd");
		String status=request.getParameter("status");
		try{
			Connection con = ConnectDB.connect();
			//Statement st=(Statement) con.createStatement();
			
	
				String q="insert into emp(uname,ename,city,dob,gender,phone,status) values(?,?,?,?,?,?,?)";
				PreparedStatement pd=con.prepareStatement(q);
				pd.setString(1, uname);
				pd.setString(2, ename);
				pd.setString(3, city);
				pd.setString(4, dob);
			    pd.setString(5, gender);
				pd.setString(6, phone);
				pd.setString(7, status);
				int n=pd.executeUpdate();
				
				send_email sd=new send_email();
				sd.send();
				
				if(n==1)
				{
					
					
					
						q="insert into users(username,password) values(?,md5(?))";
						pd=con.prepareStatement(q);
						//pd.setInt(1,rs.getInt(1));
						pd.setString(1, uname);
						pd.setString(2, passwd);
						int m=pd.executeUpdate();
						
						if(m==1)
						{
							
							pw.print("<script>alert('Successful... ')</script>");
							
							RequestDispatcher rd=request.getRequestDispatcher("Login");
							rd.include(request, response);
							
						}
						else
						{
							pw.print("not registered..try again..");
							RequestDispatcher rd=request.getRequestDispatcher("register.html");
							rd.forward(request, response);
						}
				
			}
		}
		catch(SQLIntegrityConstraintViolationException e)
		{
			pw.print("<script>alert('Username already exists!!!!!! ')</script>");
			request.getRequestDispatcher("register.html").include(request, response);
		}
		catch(SQLException e)
		{
			pw.print(e);
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
