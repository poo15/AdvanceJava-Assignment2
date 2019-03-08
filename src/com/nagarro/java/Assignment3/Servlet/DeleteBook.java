package com.nagarro.java.Assignment3.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.java.Assignment3.UserService.UserServiceImpl;

/**
 * Servlet implementation class DeleteBook
 */
@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookDetails = request.getParameter("submit");
		if(bookDetails!=null && bookDetails.contains("edit")){
			String bookId[] = bookDetails.split("-");
			String bookNewName = request.getParameter("bookName");
			if(bookNewName!=null){
				if(new UserServiceImpl().editBook(Integer.parseInt(bookId[1]),bookNewName)){
					System.out.println("Edit done");
				}else{
					System.out.println("Not done");
				}
				
			}
			request.getRequestDispatcher("./dashboard.jsp").forward(request, response);
		}else{
			if(new UserServiceImpl().deleteBook(Integer.parseInt(request.getParameter("bookId")))){
				response.sendRedirect("./dashboard.jsp");
			}else {
				request.setAttribute("deletedStatus", "Book not deleted");
				request.getRequestDispatcher("./dashboard.jsp").forward(request, response);
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

}
