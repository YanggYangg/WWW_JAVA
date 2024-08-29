package fit.se.iuh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class B7_UploadToDBServlet
 */



//@WebServlet("/B7_UploadToDBServlet")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class B7_UploadToDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int BUFFER_SIZE = 4096;
	private static final String SAVE_DIR = "images";
	private String dbURL = 
	"jdbc:sqlserver://localhost:1433;databaseName=UploadFileServletDB";
	private String dbUser = "sa_1";
	private String dbPass = "sa"; 
    /**
     * Default constructor. 
     */
    public B7_UploadToDBServlet() {
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    InputStream inputStream = null;
	    Part filePart = request.getPart("photo");
	    String fileUploadName = "";

	    if (filePart != null) {
	        fileUploadName = filePart.getSubmittedFileName(); // Sử dụng getSubmittedFileName để lấy tên file gốc
	        inputStream = filePart.getInputStream();
	    }

	    Connection conn = null;
	    String message = null;
	    String filePath = "E:\\WWW_JAVA_2808\\FileSave\\" + fileUploadName;

	    try {
	        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
	        conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

	        String sql = "INSERT INTO contacts (first_name, last_name, photo) values (?, ?, ?)";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, firstName);
	        statement.setString(2, lastName);

	        if (inputStream != null) {
	            statement.setBlob(3, inputStream);
	        }

	        int row = statement.executeUpdate();

	        if (row > 0) {
	            message = "File uploaded and saved into database";
	        }

	        String sql1 = "SELECT photo FROM contacts WHERE first_name=? AND last_name=?";
	        statement = conn.prepareStatement(sql1);
	        statement.setString(1, firstName);
	        statement.setString(2, lastName);
	        ResultSet result = statement.executeQuery();

	        if (result.next()) {
	            Blob blob = result.getBlob("photo");
	            inputStream = blob.getBinaryStream();
	            OutputStream outputStream = new FileOutputStream(filePath);

	            int bytesRead = -1;
	            byte[] buffer = new byte[BUFFER_SIZE];
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }

	            inputStream.close();
	            outputStream.close();

	            message += " and saved to file system at " + filePath;
	        }
	    } catch (SQLException ex) {
	        message = "ERROR: " + ex.getMessage();
	        ex.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }

	        request.setAttribute("Message", message);
	        getServletContext().getRequestDispatcher("/MessageServlet").forward(request, response);
	    }
	}
}

