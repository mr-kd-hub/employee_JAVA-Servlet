

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Servlet implementation class OperationWithDatabase
 */
@WebServlet("/OperationWithDatabase")
public class OperationWithDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationWithDatabase() {
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
response.setContentType("text/html");
//response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		PrintWriter pw = response.getWriter();
		
		// Create HttpSession Object
		
		HttpSession hs = request.getSession(false);
		
		// Check session status , already created or not ? if already not created then redirect on Login.java
				
		if(hs == null)
		{
			response.sendRedirect("Login");
		}
		
		try
		{
			Connection con = ConnectDB.connect();
			
			// Check OperationType Value
			
			
			// This is student delete function
			if(request.getParameter("OperationType").equals("Delete"))//from query string ,it from profoile.java page
			{
				int Id = Integer.parseInt(request.getParameter("Id"));//from query string ,it from profoile.java page
			
				String delete_query = "delete from emp where id = '"+ Id +"'";
				
				Statement st = con.createStatement();
				
				int check = st.executeUpdate(delete_query);
				
				if(check == 1)
				{
					pw.print("<script>alert('Employee Deleted..')</script>");	
					
					RequestDispatcher rq = request.getRequestDispatcher("Profile");
					rq.include(request, response);
				}
				else
				{
					pw.print("<script>alert('Employee Not Deleted Try Again..')</script>");
					
					RequestDispatcher rq = request.getRequestDispatcher("Profile");
					rq.include(request, response);
				}
				
				return;
			}
			
			
			// Get parameters from OperationForm.java
			String ename , city , gender , phone , status,uname;
			
			uname = request.getParameter("uname");			
			ename = request.getParameter("ename");			
			city = request.getParameter("city");			
			LocalDate dob = LocalDate.parse(request.getParameter("dob"), DateTimeFormatter.ISO_DATE);			
			gender = request.getParameter("gender");			
			phone = request.getParameter("phone");			
			status = request.getParameter("status");
			
			
			// This is add student function
			if(request.getParameter("OperationType").equals("Add"))//get from hidden field , from operationform.java page
			{			
					String insert_query = "insert into emp (uname,ename , city , dob , gender , phone , status) values  ('"+uname+"','" + ename + "','" + city + "','" + dob + "' , '" + gender + "' , '" + phone + "' , '" + status + "')";
					
					Statement st = con.createStatement();
					
					int check = st.executeUpdate(insert_query);
					
					if(check == 1)
					{
						pw.print("<script>alert('Employee Inserted..')</script>");		
						
						RequestDispatcher rq = request.getRequestDispatcher("Profile");
						rq.include(request, response);
						
					}
					else
					{
						pw.print("<script>alert('Employee Not Inserted Try Again..')</script>");
						
						RequestDispatcher rq = request.getRequestDispatcher("Profile");
						rq.include(request, response);
						
					}
					
					return;
			}
			
			
			// This is update student function
			if(request.getParameter("OperationType").equals("Update"))//get from hidden field , from OperationForm.java page
			{
					int Id = Integer.parseInt(request.getParameter("Id"));//get from hidden field , from OperationForm.java page
				
					String update_query = "update emp set ename='"+ename+"', city='"+city+"', dob='"+dob+"', gender='"+gender+"', phone='"+phone+"', status='"+status+"' where id = '"+ Id +"'";
					
					Statement st = con.createStatement();
					
					int check = st.executeUpdate(update_query);
					
					if(check == 1)
					{
						pw.print("<script>alert('Employee Updated..')</script>");
						String type=(String)hs.getAttribute("type");
						if(type.equals("employee"))							
						{
							RequestDispatcher rq = request.getRequestDispatcher("account");
							
							rq.include(request, response);
						}
						else
						{
							RequestDispatcher rq = request.getRequestDispatcher("Profile");
							
							rq.include(request, response);
						}
						
					}
					else
					{
						pw.print("<script>alert('Employee Not Updated Try Again..')</script>");
					}			
			}
			
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
