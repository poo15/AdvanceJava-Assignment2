package com.nagarro.ImageUtility.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.bcel.Const;

import com.nagarro.ImageUtility.Constants.Constant;
import com.nagarro.ImageUtility.Entity.User;
import com.nagarro.ImageUtility.Service.Implementation.UserServiceImpl;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
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
		User user = new UserServiceImpl().register(getUser(request));
		if(user!=null){
			session.setAttribute(Constant.USER_OBJECT, user);
			response.sendRedirect(Constant.DASHBOARD);
		}else
		{
			request.setAttribute(Constant.INVALID_USER, Constant.UNIQUE_USERNAME);
			request.getRequestDispatcher(Constant.REGISTER).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static User getUser(HttpServletRequest request){
		User user = new User();
		user.setUserName(request.getParameter(Constant.INPUT_USERNAME));
		user.setPassword(request.getParameter(Constant.INPUT_PASSWORD));
		user.setRecoveryAns(request.getParameter(Constant.RECOVERY_ANS));
		return user;
	}

}
