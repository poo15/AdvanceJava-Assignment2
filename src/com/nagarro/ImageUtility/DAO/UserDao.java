package com.nagarro.ImageUtility.DAO;

import java.util.List;

import com.nagarro.ImageUtility.Entity.Image;
import com.nagarro.ImageUtility.Entity.User;

public interface UserDao {
	
	User login(String userName, String password);
	
	boolean addBook(Image image);
	
	List<Image> getAllBooks(int id);
	
	boolean deleteBook(int id);
	
	boolean editBook(Image imagr);
	
	User findUserName(String userName);
	
	
}
