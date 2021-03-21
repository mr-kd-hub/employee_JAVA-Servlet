

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class deletemessage
 */
@WebServlet("/deletemessage")
public class deletemessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletemessage() {
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
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		PrintWriter pw=response.getWriter();
			String uname=request.getParameter("username");		
		HttpSession hs=request.getSession(false);
		if(hs==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("Login");
			rd.forward(request,response);
		}
		else
		{
			try
			{
				Connection con=ConnectDB.connect();
				int id=Integer.parseInt(request.getParameter("Id"));
				//pw.print(id);
				String d="delete from message where mid='"+id+"'";
				Statement st=con.createStatement();
				int k=st.executeUpdate(d);
				if(k==1)
				{
					pw.print("<script>alert('Deleted.....')</script>");
					request.getRequestDispatcher("messageA").include(request, response);
				}
				else
				{
					request.getRequestDispatcher("messageA").include(request, response);
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
