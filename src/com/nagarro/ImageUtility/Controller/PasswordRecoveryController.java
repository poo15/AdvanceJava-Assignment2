package com.nagarro.ImageUtility.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.ImageUtility.Constants.Constant;
import com.nagarro.ImageUtility.Entity.User;
import com.nagarro.ImageUtility.Service.Implementation.UserServiceImpl;

/**
 * Servlet implementation class PasswordRecovery
 */
@WebServlet("/PasswordRecoveryController")
public class PasswordRecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static User user=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordRecoveryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Recovery Servlet "+request.getParameter("userName")+" button whick clicked "+request.getParameter("submit"));
		String userName=request.getParameter("userName");
		if(request.getParameter(Constant.INPUT_SUBMIT).equals(Constant.INPUT_SEARCH)){
			user = new UserServiceImpl().findUserName(userName);
			if(userName==Constant.NULL || ( user ==null)){
				request.setAttribute(Constant.INVALID_USER, "Invalid Username ");
			} else{
				request.setAttribute(Constant.INPUT_USERNAME, user.getUserName());
			}
		}else if(request.getParameter(Constant.INPUT_SUBMIT).equals(Constant.INPUT_SUBMIT)){
			String recoveryAns = request.getParameter(Constant.RECOVERY_ANS);
			
			if(recoveryAns.equals(user.getRecoveryAns())){
				request.setAttribute(Constant.INPUT_USERNAME, userName);
				request.setAttribute(Constant.INPUT_PASSWORD, user.getPassword());
			} else{
				request.setAttribute(Constant.RECOVERY_ANS, Constant.INCORRECT_ANS_MSG);
			}

		}
		
		request.getRequestDispatcher(Constant.FORGOT_PASSWORD).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
