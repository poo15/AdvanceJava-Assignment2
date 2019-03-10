package com.nagarro.ImageUtility.UserService;

import java.util.List;

import com.nagarro.ImageUtility.Entity.Image;
import com.nagarro.ImageUtility.Entity.User;

public interface Service {
	
	User login(String userName, String password);
	
	User register(User user);
	
	boolean addBook(Image image);
	
	List<Image> getAllBooks(int userId);
	
	boolean deleteBook(int id);
	
	boolean editBook(Image image);
	
	User findUserName(String userName);
	
	
}
