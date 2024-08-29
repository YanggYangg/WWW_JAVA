package fit.se.iuh;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationForm
 */
@WebServlet("/RegistrationForm")
public class RegistrationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistrationForm() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		
//		 String firstName = request.getParameter("first-name");
//		    String lastName = request.getParameter("last-name");
//		    String username = request.getParameter("username");
//		    String email = request.getParameter("email");
//		    String facebook = request.getParameter("facebook");
//		    String bio = request.getParameter("bio");
//
//		    String html = "<html>"
//		            + "<head><title>Result Page</title></head>"
//		            + "<body>"
//		            + "First Name: " + firstName + "<br>"
//		            + "Last Name: " + lastName + "<br>"
//		            + "Username: " + username + "<br>"
//		            + "Email: " + email + "<br>"
//		            + "Facebook URL: " + facebook + "<br>"
//		            + "Short Bio: " + bio + "<br>"
//		            + "</body>"
//		            + "</html>";
//		    out.println(html);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		 String firstName = request.getParameter("first-name");
		    String lastName = request.getParameter("last-name");
		    String username = request.getParameter("username");
		    String email = request.getParameter("email");
		    String facebook = request.getParameter("facebook");
		    String bio = request.getParameter("bio");

		    String html = "<html>"
		            + "<head><title>Result Page</title></head>"
		            + "<body>"
		            + "First Name: " + firstName + "<br>"
		            + "Last Name: " + lastName + "<br>"
		            + "Username: " + username + "<br>"
		            + "Email: " + email + "<br>"
		            + "Facebook URL: " + facebook + "<br>"
		            + "Short Bio: " + bio + "<br>"
		            + "</body>"
		            + "</html>";
		    out.println(html);
	}

}
