package com.nagarro.ImageUtility.Controller;

import java.io.File;
import java.io.IOException;
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
import com.nagarro.ImageUtility.Entity.Image;
import com.nagarro.ImageUtility.Entity.User;
import com.nagarro.ImageUtility.Service.Implementation.UserServiceImpl;


/**
 * Servlet implementation class EditBookController
 */
@WebServlet("/EditBookController")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class EditBookController extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	private final static Logger LOGGER = 
            Logger.getLogger(ImageUploadController.class.getCanonicalName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookDetails = request.getParameter(Constant.INPUT_SUBMIT);
		if(bookDetails!=null && bookDetails.contains(Constant.INPUT_EDIT)){
			String bookId[] = bookDetails.split(Constant.SPLIT_BY);
			String bookNewName = request.getParameter(Constant.IMAGE_NAME);
			if(bookNewName!=null){
				try{
				Part part = request.getPart(Constant.INPUT_FILE);
				String fileUrl = getFileName(part).replace(Constant.REPLACE_BY,Constant.SPLIT_BY);
				String[] arr = fileUrl.split(Constant.SPLIT_BY);
				String fileName = arr[arr.length-1];
				String savePath = Constant.DIRECTORY+File.separator+fileName;
				Image image = getImage(request, part, fileName,Integer.parseInt(bookId[1]));
				new UserServiceImpl().editBook(image);
				}catch(Exception e ){
					System.out.println(e.getMessage());
				}
			}
			request.getRequestDispatcher(Constant.DASHBOARD).forward(request, response);
		}else{
			if(new UserServiceImpl().deleteBook(Integer.parseInt(request.getParameter(Constant.IMAGE_ID)))){
				response.sendRedirect(Constant.DASHBOARD);
			}else {
				request.setAttribute(Constant.DELETED_STATUS, Constant.DELETED_VALUE);
				request.getRequestDispatcher(Constant.DASHBOARD).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Image getImage(HttpServletRequest request, Part part, String fileName, int imageId) {
		Image image = new Image();
		image.setImageId(imageId);
		image.setName(request.getParameter(Constant.IMAGE_NAME));
		image.setSize((int) part.getSize());
		image.setUrl(fileName);
		return image;
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
