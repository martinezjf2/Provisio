package login;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
//		if (password != null && password != "" && email != null && email != "") {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/provisio", "provisio_user", "password");
			Statement stmt = con.createStatement();
//			Resource: https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
			ResultSet rs = stmt.executeQuery("SELECT user_id, first_name, last_name, email, passphrase, points FROM user WHERE email = '" + email + "' AND passphrase = '" + password + "';");
//			Resource: https://javarevisited.blogspot.com/2016/10/how-to-check-if-resultset-is-empty-in-Java-JDBC.html#axzz7rZ0UhPlW
//			Resource: https://www.garron.me/en/bits/mysql-select-multiple-conditions.html
//			String sqlEmail = rs.getString(4);
//			String sqlPassphrase = rs.getString(5);
//			out.print(sqlEmail);
//			if (email == sqlEmail && password == sqlPassphrase){
//				out.println("Successful Login");
////				response.sendRedirect("http://localhost:8080/Jeffrey_provisio/userHompage.jsp");
//			}
			if (rs.next() != false) {
				response.sendRedirect("http://localhost:8080/Jeffrey_provisio/userHompage.jsp");
			} else {
				response.sendRedirect("http://localhost:8080/Jeffrey_provisio/wrongInformationLogin.jsp");
			}
			con.close();
//			response.sendRedirect("http://localhost:8080/Jeffrey_provisio/userHompage.jsp");
		} catch(Exception e) {
			out.println(e);
			response.sendRedirect("http://localhost:8080/Jeffrey_provisio/wrongInformationLogin.jsp");
//			Resource: https://www.javatpoint.com/sendRedirect()-method
//			Resource: https://stackoverflow.com/questions/13621998/how-to-send-redirect-to-jsp-page-in-servlet
			
		}
		
//		Resource on MySQL Connection: https://www.javaguides.net/2019/01/jsp-jdbc-mysql-example.html
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Resource: https://stackoverflow.com/questions/1026846/how-to-redirect-to-login-page-when-session-is-expired-in-java-web-application
		doGet(request, response);
	}

}
