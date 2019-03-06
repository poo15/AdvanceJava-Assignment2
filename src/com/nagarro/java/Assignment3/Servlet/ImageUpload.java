package com.nagarro.java.Assignment3.Servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;

import com.nagarro.java.Assignment3.Constants.Constant;
import com.nagarro.java.Assignment3.Entity.User;
import com.nagarro.java.Assignment3.UserService.UserServiceImpl;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet("/ImageUpload")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class ImageUpload extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = 
            Logger.getLogger(ImageUpload.class.getCanonicalName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Add Book Servlet");
		try{
			Part part = request.getPart("file");
			String fileUrl = getFileName(part).replace("\\","-");
			String[] arr = fileUrl.split("-");
			String fileName = arr[arr.length-1];
			String savePath = Constant.DIRECTORY+File.separator+fileName;
			File fileSaveDir = new File(savePath);
			part.write(savePath + File.separator);
			User user = (User) request.getSession().getAttribute("user");
			if(new UserServiceImpl().addBook(fileName,(int) part.getSize()/1000,user)){
				System.out.println("Added");
			}else
				System.out.println("Not added");
			response.sendRedirect("./dashboard.jsp");
		}catch(Exception e){
			request.setAttribute("sizeExceed", "File Size Exceed");
			request.getRequestDispatcher("./dashboard.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

}
