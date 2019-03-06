package com.nagarro.java.Assignment3.UserService;

import java.util.List;

import com.nagarro.java.Assignment3.Entity.Image;
import com.nagarro.java.Assignment3.Entity.User;

public interface Service {
	
	User login(String userName, String password);
	
	boolean addBook(String fileName,int size,User user);
	
	List<Image> getAllBooks(int userId);
	
	boolean deleteBook(int id);
}
