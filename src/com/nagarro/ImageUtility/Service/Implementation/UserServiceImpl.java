package com.nagarro.ImageUtility.Service.Implementation;

import java.util.List;

import com.nagarro.ImageUtility.DAOImplementation.UserDaoImpl;
import com.nagarro.ImageUtility.Entity.Image;
import com.nagarro.ImageUtility.Entity.User;
import com.nagarro.ImageUtility.UserService.Service;

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
	public boolean editBook(Image image) {
		System.out.println("In service layer of edit book");
		return new UserDaoImpl().editBook(image);
	}

	@Override
	public User findUserName(String userName) {
		System.out.println("In service of find user");
		return new UserDaoImpl().findUserName(userName);
	}

	
}
