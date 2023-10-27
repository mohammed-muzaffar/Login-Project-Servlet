package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }
    
    Connection con;
    public void init() {
    	try {
    	ServletContext sc= getServletContext();
    	String s1 = sc.getInitParameter("driver");
    	String s2 = sc.getInitParameter("username");
    	String s3 = sc.getInitParameter("password");
    	String s4 = sc.getInitParameter("url");
    	Class.forName(s1);
    	con = DriverManager.getConnection(s4, s2, s3);
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
			String name = request.getParameter("Username");
			String password = request.getParameter("password");
			PreparedStatement pstmt = con.prepareStatement("select * from userInfo where name = ? and password = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=red text=yellow><h1>");
			if(rs.next()) {
				pw.println("Welcome "+ name);
			} else {
				pw.println("Invalid Username and Password");
			}
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
