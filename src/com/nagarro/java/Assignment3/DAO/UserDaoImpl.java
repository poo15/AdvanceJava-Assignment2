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
	public boolean addBook(Image image) {
		System.out.println("In add Book on DAO");
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			User us = session.get(User.class, image.getUser().getUser_Id());
			System.out.println("In dao us"+us);
			us.getImagebooks().add(image);
//			double totalSize = getTotalSize(image.getUser().getUser_Id());
//			System.out.println("Total File size :- "+totalSize/Constant.MEGABYTE);
			
			System.out.println("User latest books :- "+us.getImagebooks());
			session.beginTransaction();
			session.persist(image);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		return false;
		}
		return true;
	}
	
	
	private double getTotalSize(int id){
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			User user = session.get(User.class, id);
			String HQL = "SELECT sum(size) FROM Image WHERE user_Id=:userId";
			Query query = session.createQuery(HQL);
			query.setParameter("userId", user.getUser_Id());
			 return  (double) query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<Image> getAllBooks(int id) {
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
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Image image = session.find(Image.class, id);
			session.beginTransaction();
			session.delete(image);
			session.getTransaction().commit();
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean editBook(int id) {
		System.out.println("In Dao layer of edit book");
		return false;
	}

	@Override
	public User findUserName(String userName) {
		System.out.println("In Dao of find user");
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "FROM User WHERE userName=:userName";
			Query<User> query = session.createQuery(HQL,User.class);
			query.setParameter("userName", userName);
			User user = query.uniqueResult();
			if(user!=null){
				return user;
			}
		} catch(Exception e ){
			System.out.println(e.getMessage());
		}
		return null;
	}

}
