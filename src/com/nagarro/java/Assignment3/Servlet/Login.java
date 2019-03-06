package com.nagarro.java.Assignment3.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.nagarro.java.Assignment3.Entity.User;
import com.nagarro.java.Assignment3.UserService.UserServiceImpl;
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
		
		
		
//		User user = new User();
//		try(Session session = HibernateUtil.getSessionFactory().openSession()){
//		 user.setUserName("pooja15");
//		 user.setPassword("password");
//		 user.setRecoveryAns("123");
//		 session.beginTransaction();
//		 session.persist(user);
//		 session.getTransaction().commit();
//	} catch(Exception e ){
//		System.out.println(e.getMessage());
//	}
		
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		User user = new UserServiceImpl().login(userName,password);
		if(user!=null){
			session.setAttribute("user", user);
			response.sendRedirect("./dashboard.jsp");
		}else
		{
			request.setAttribute("invalidCredentials", "Invalid Username or Password");
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}
		//response.getWriter().append("Served at: "+ userName+" "+password).append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
