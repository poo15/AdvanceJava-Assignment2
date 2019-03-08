package com.nagarro.java.Assignment3.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.java.Assignment3.Entity.User;
import com.nagarro.java.Assignment3.UserService.UserServiceImpl;

/**
 * Servlet implementation class PasswordRecovery
 */
@WebServlet("/PasswordRecovery")
public class PasswordRecovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static User user=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordRecovery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Recovery Servlet "+request.getParameter("userName")+" button whick clicked "+request.getParameter("submit"));
		String userName=request.getParameter("userName");
		if(request.getParameter("submit").equals("search")){
			System.out.println("***********In Search if*************");
			user = new UserServiceImpl().findUserName(userName);
			System.out.println("In Seacrch user "+user);
			if(userName=="" || ( user ==null)){
				request.setAttribute("invalidUserName", "Invalid Username ");
			} else{
				request.setAttribute("userName", user.getUserName());
			}
		}else if(request.getParameter("submit").equals("submit")){
			System.out.println("In submit user "+ user.getRecoveryAns());
			System.out.println("*************In submit if**************8");
			String recoveryAns = request.getParameter("recoveryAns");
			
			if(recoveryAns.equals(user.getRecoveryAns())){
				request.setAttribute("userName", userName);
				request.setAttribute("userPassword", user.getPassword());
				System.out.println("In Submit User password "+ user.getPassword());
			} else{
				request.setAttribute("incorrectAns", "Incorrect Ans ");
			}
			
//			if(recoveryAns=="" || (false)){
//				System.out.println("(((((In submit if))))))");
//				request.setAttribute("incorrectAns", "Incorrect Ans ");
//			}else{
//				System.out.println("(((((In submit else))))))");
//				if(recoveryAns.equals(user.getRecoveryAns())){
//					request.setAttribute("userName", userName);
//					request.setAttribute("userPassword", "Pooja");
//				} else{
//					request.setAttribute("incorrectAns", "Incorrect Ans ");
//				}
//			}
		}
		
		request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
