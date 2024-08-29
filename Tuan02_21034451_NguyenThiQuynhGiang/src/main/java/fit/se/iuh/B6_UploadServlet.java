package fit.se.iuh;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */

@MultipartConfig(
		location = "T:\\WWW_JAVA\\FileSave",
		fileSizeThreshold = 1024 * 1024,// 1MB neu kich thuoc file upload lon hon threshold
		maxFileSize = 1024 * 1024 * 5,//5MB kich thuoc toi da cua file duoc upload
		maxRequestSize = 1024 * 1024 * 10//10MB kich thuoc toi da cho mot request
		)




//@WebServlet("/UploadServlet")
public class B6_UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String upLoadPath = null;

    /**
     * Default constructor. 
     */
    public B6_UploadServlet() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException{
    	super.init();
    	upLoadPath = getServletContext().getInitParameter("upload.path");
    	File uploadDir = new File(upLoadPath);
    	if(!uploadDir.exists()) {
    		uploadDir.mkdirs();
    	}
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			for(Part filePart : request.getParts()) {
				if(filePart == null || filePart.getSubmittedFileName() == null ||
						filePart.getSubmittedFileName().isEmpty()) {
					continue;
				}
				String fileName = filePart.getSubmittedFileName();
				
				InputStream inputStream = filePart.getInputStream();
				Files.copy(inputStream, Paths.get(upLoadPath + File.separator + fileName),
						StandardCopyOption.REPLACE_EXISTING);
				System.out.println(upLoadPath + File.separator + fileName);
			}
			response.getWriter().println("File uploaded successfully !");
		}catch (IOException | ServletException e) {
			// TODO: handle exception
			response.getWriter().println("File uploaded failed" + e.getMessage());
		}
	}

}
