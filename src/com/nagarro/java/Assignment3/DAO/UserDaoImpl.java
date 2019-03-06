package com.nagarro.java.Assignment3.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.omg.Messaging.SyncScopeHelper;

import com.nagarro.java.Assignment3.Constants.Constant;
import com.nagarro.java.Assignment3.Entity.Image;
import com.nagarro.java.Assignment3.Entity.User;
import com.nagarro.java.Assignment3.Util.HibernateUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public User login(String userName, String password) {
		System.out.println("In Dao Layer");
		User user = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
		String HQL = "FROM User WHERE userName=:userName AND password=:password";
		Query<User> query = session.createQuery(HQL,User.class);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		user = query.uniqueResult();
		if(user!=null){
		System.out.println("Logged In "+user);
		}else
			System.out.println("Invalid Credential");
	} catch(Exception e ){
		System.out.println(e.getMessage());
	}
		return user;
	}

	@Override
	public boolean addBook(String fileName, int size, User user) {
		System.out.println("In add Book on DAO");
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			User us = session.get(User.class, user.getUser_Id());
			System.out.println("In dao us"+us);
			Image image = new Image();
			image.setUrl(Constant.DIRECTORY+fileName);
			image.setSize(size);
			String[] name = fileName.split("\\.");
			image.setName(name[0]);
			us.getImagebooks().add(image);
			System.out.println("User latest books :- "+us.getImagebooks());
			session.beginTransaction();
			session.persist(image);
			//session.update(us);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		return false;
		}
		return true;
	}

	@Override
	public List<Image> getAllBooks(int id) {
		System.out.println("In get All Function");
		List<Image> image = new ArrayList<Image>();
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			User user = session.get(User.class, id);
			 image = (List<Image>) user.getImagebooks();
			 image.forEach(imag-> System.out.println(imag));
		}catch(Exception e){
			e.printStackTrace();
		}
		return image;
	}

	@Override
	public boolean deleteBook(int id) {
		System.out.println("In DAO Delete Function");
		return false;
	}

}
