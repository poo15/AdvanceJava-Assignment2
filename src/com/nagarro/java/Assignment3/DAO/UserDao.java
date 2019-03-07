package com.nagarro.java.Assignment3.DAO;

import java.util.List;

import com.nagarro.java.Assignment3.Entity.Image;
import com.nagarro.java.Assignment3.Entity.User;

public interface UserDao {
	
	User login(String userName, String password);
	
	boolean addBook(Image image);
	
	List<Image> getAllBooks(int id);
	
	boolean deleteBook(int id);
	
	boolean editBook(int id);
	
	User findUserName(String userName);
}
