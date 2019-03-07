package com.nagarro.java.Assignment3.UserService;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.nagarro.java.Assignment3.DAO.UserDaoImpl;
import com.nagarro.java.Assignment3.Entity.Image;
import com.nagarro.java.Assignment3.Entity.User;

public class UserServiceImpl implements Service{

	@Override
	public User login(String userName, String password) {
		System.out.println("In login on service");
		return new UserDaoImpl().login(userName,password);
	}

	public int check(){
		return 2;
	}
	
	@Override
	public boolean addBook(Image image) {
		System.out.println("In add book in server");
		return new UserDaoImpl().addBook(image);
	}

	@Override
	public List<Image> getAllBooks(int userId) {
		return new UserDaoImpl().getAllBooks(userId);
	}

	@Override
	public boolean deleteBook(int id) {
		System.out.println("In Service Delete Book function");
		return new UserDaoImpl().deleteBook(id);
	}

	@Override
	public boolean editBook(int id) {
		System.out.println("In service layer of edit book");
		return new UserDaoImpl().editBook(id);
	}

	@Override
	public User findUserName(String userName) {
		System.out.println("In service of find user");
		return new UserDaoImpl().findUserName(userName);
	}
}
