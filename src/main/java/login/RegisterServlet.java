package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Connection con;
    public void init() {
    	try {
    		ServletContext sc = getServletContext();
    		String s1 = sc.getInitParameter("driver");
    		String s2 = sc.getInitParameter("url");
    		String s3 = sc.getInitParameter("username");
    		String s4 = sc.getInitParameter("password");
    		Class.forName(s1);
    		con = DriverManager.getConnection(s2, s3, s4);
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		String firstName = request.getParameter("username");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		PreparedStatement pstmt = con.prepareStatement("insert into userInfo values(?,?,?)");
		pstmt.setString(1, firstName);
		pstmt.setString(2, lastName);
		pstmt.setString(3, password);
		pstmt.executeUpdate();
		PrintWriter pw=response.getWriter();
		pw.println("<html><body bgcolor=cyan text=red><center>");
		pw.println("<h1>You Have Registered Succcessfully</h1>");
		pw.println("<a href=login.html>Login</a>");
		pw.println("</center></body></html>");
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
