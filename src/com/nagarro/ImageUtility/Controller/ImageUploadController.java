package com.nagarro.ImageUtility.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
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
import com.nagarro.ImageUtility.Constants.Constant;
import com.nagarro.ImageUtility.DAOImplementation.UserDaoImpl;
import com.nagarro.ImageUtility.Entity.Image;
import com.nagarro.ImageUtility.Entity.User;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet("/ImageUploadController")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class ImageUploadController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = 
            Logger.getLogger(ImageUploadController.class.getCanonicalName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUploadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Add Book Servlet");
		System.out.println(System.getProperty("user.home"));
		try{
			Part part = request.getPart(Constant.INPUT_FILE);
			String fileUrl = getFileName(part).replace(Constant.REPLACE_BY,Constant.SPLIT_BY);
			String[] arr = fileUrl.split(Constant.SPLIT_BY);
			String fileName = arr[arr.length-1];
			String savePath = System.getProperty("user.home")+Constant.DIRECTORY+fileName;
			part.write(savePath + File.separator);
			User user = (User) request.getSession().getAttribute(Constant.USER_OBJECT);		
			if(new UserDaoImpl().addBook(getImage(request, part, fileName, user))){
				System.out.println("Added");
			}else{
				System.out.println("Not added");
				request.setAttribute(Constant.FILE_SIZE_STAUS, Constant.SIZE_GREATER_10MB); 
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			request.setAttribute(Constant.FILE_SIZE_STAUS, Constant.SIZE_GREATER_1MB);
		}
		request.getRequestDispatcher(Constant.DASHBOARD).forward(request, response);
	}

	/**
	 * @param request
	 * @param part
	 * @param fileName
	 * @param user
	 */
	private Image getImage(HttpServletRequest request, Part part, String fileName, User user) {
		Image image = new Image();
		image.setName(request.getParameter(Constant.IMAGE_NAME));
		image.setSize((int) part.getSize());
		image.setUrl(fileName);
		image.setUser(user);
		return image;
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
