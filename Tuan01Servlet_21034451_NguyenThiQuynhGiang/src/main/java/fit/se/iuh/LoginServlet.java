package fit.se.iuh;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		//Lay thong tin tu ServletContext (username, password)
		String ten, mk, ms="";
		ten = getServletContext().getInitParameter("tendn");
		mk = getServletContext().getInitParameter("matkhau");
		
		//Lay thong tin tu form client request (uname/pword)
		String usn, pwd;
		usn = request.getParameter("uname");
		pwd = request.getParameter("pword");
		
		if(ten.equalsIgnoreCase(usn) && mk.equalsIgnoreCase(pwd)) 
			ms = "Ban da dang nhap thanh cong !!!";
		else 
			ms = "Dang nhap that bai ! Vui long kiem tra lai thong tin";
		
		out.print(ms);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
