package com.nagarro.ImageUtility.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.ImageUtility.Constants.Constant;
import com.nagarro.ImageUtility.Entity.User;
import com.nagarro.ImageUtility.Service.Implementation.UserServiceImpl;
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter(Constant.INPUT_USERNAME);
		String password = request.getParameter(Constant.INPUT_PASSWORD);
		HttpSession session = request.getSession();
		User user = new UserServiceImpl().login(userName,password);
		if(user!=null){
			session.setAttribute(Constant.USER_OBJECT, user);
			response.sendRedirect(Constant.DASHBOARD);
		}else
		{
			request.setAttribute(Constant.INVALID_USER, Constant.INVALID_USER_ERROR);
			request.getRequestDispatcher(Constant.INDEX).forward(request, response);
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
