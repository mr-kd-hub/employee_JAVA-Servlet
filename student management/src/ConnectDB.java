import java.sql.DriverManager;
import java.sql.*;
public class ConnectDB {
	public static Connection connect()
	{
	
		try
		{				
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/employee","root",""); // Please Enter Database Name, Username And Password Of Mysql
		
			return con;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
	}
}
