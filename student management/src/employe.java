

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class employe
 */
@WebServlet("/employe")
public class employe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public employe() {
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
		
		//select status
		String statusv=request.getParameter("status");
		
		
		HttpSession hs=request.getSession(false);
		if(hs==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("Login");
			rd.forward(request,response);
		}
		else
		{
			String type=(String)hs.getAttribute("type");
			String uname=(String)hs.getAttribute("uname");//session mathi value access kri
			if(type.equals("employee"))
			{
				try
				{
					Connection con = (Connection) ConnectDB.connect();
					Statement st=con.createStatement();
					String q="update emp set status='"+statusv+"' where uname='"+uname+"'";
					int k=st.executeUpdate(q);
					
					if(k==1)
					{
						pw.print("<script>alert('Status Updated')</script>");
						request.getRequestDispatcher("employeform.html").include(request, response);
					}
					else
					{
						pw.print("<center><h1 style='color:red'>not updated</h1></center>");
						request.getRequestDispatcher("employeform.html").include(request, response);
					}
					
					
				}
				catch(Exception e)
				{
					pw.print(e);
				}
				
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
