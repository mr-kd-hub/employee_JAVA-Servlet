

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		request.getRequestDispatcher("Logout").include(request, response);  
		//RequestDispatcher rd=request.getRequestDispatcher("Logout");rd.include(request, response)
		// Login Form Design
		
		pw.print( "<html>"
				
				+ "<head><title>Employee Login </title>"
				
				+ "<style>"
				
					+ "body{font-family:arial;'}"
					+ "input[type=text], select {padding: 12px 20px;margin: 8px 0; display: inline-block; border: 1px solid #ccc;border-radius: 4px;box-sizing: border-box;}"
					
				+ "</style>"
				
				
				+ "</head>"
				
				+ "<body style='background: linear-gradient(360deg, #01a99c 10%, #0698b1 360%); '>"
				
				+ "<center>"
				+ "<div style='border:3px solid black;margin-top: 10%;width:65%;border-radius:22px;'>"
				+ "<h1>Employee Login</h1>"
				
				+ "	<form action='Check_User' method='post'>"

				+ "		<input type='text' name='uname' placeholder='Username' style='border-radius:22px;padding:5px 10px 5px 10px;background-color: #d6eaf8' required><br><br>"
				
				+ "		<input type='password' name='pass' placeholder='Password' style='border-radius:22px;padding:5px 10px 5px 10px;background-color: "
				+ "#d6eaf8' required>"
				
				+ "		<br><br><input type='submit' value='Login' style='border-radius:22px;width:200px;padding:5px 10px 5px 10px;'>"
				
				+ " <br><br><a href='register.html'>New Employee Register here</a>"
				
				+ "	</form>"
				+ "</div>"
				+ "</center>"
				
				+ " </body>"
				
				);	
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
