package com.nagarro.ImageUtility.DAOImplementation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import com.nagarro.ImageUtility.Constants.Constant;
import com.nagarro.ImageUtility.DAO.UserDao;
import com.nagarro.ImageUtility.Entity.Image;
import com.nagarro.ImageUtility.Entity.User;
import com.nagarro.ImageUtility.Util.HibernateUtil;

public class UserDaoImpl implements UserDao{
	private static User user=null;
	@Override
	public User login(String userName, String password) {
		System.out.println("In Dao Layer");
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
		String HQL = "FROM User WHERE userName=:userName AND password=:password";
		Query<User> query = session.createQuery(HQL,User.class);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		user = query.uniqueResult();
		System.out.println("Logined user "+user);
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
			double totalSize = getTotalSize(image.getUser().getUser_Id());
			if(totalSize+image.getSize() > Constant.MAX_FILE_SIZE*10){
				return false;
			}
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
	
	
	private long getTotalSize(int id){
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			User user = session.get(User.class, id);
			String HQL = "SELECT sum(size) FROM Image WHERE user_Id=:userId";
			Query query = session.createQuery(HQL);
			query.setParameter("userId", user.getUser_Id());
			if(query.list().get(0)!=null){
				return  (long) query.list().get(0);
			}
			 
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
	public boolean editBook(Image image) {
		System.out.println("In Dao layer of edit book"+" bookId:- "+image.getImageId()+" book name to be set :- "+image.getName());
		try(Session session= HibernateUtil.getSessionFactory().openSession()){
			Image imageObject = session.find(Image.class, image.getImageId());
			session.beginTransaction();
			imageObject.setName(image.getName());
			if(!image.getUrl().equals("")){
			imageObject.setUrl(image.getUrl());
			}
			System.out.println("In DAO:-  "+image.getUrl());
			imageObject.setSize(image.getSize());
			session.getTransaction().commit();
			return true;
		} catch(Exception e ){
			System.out.println(e.getMessage());
		}
		return false;
	}

//	private Session getSession(){
//		try(Session session= HibernateUtil.getSessionFactory().openSession()){
//			return session;
//		} catch(Exception e ){
//			System.out.println(e.getMessage());
//		}
//		return null;
//	}
	
	@Override
	public User findUserName(String userName) {
		System.out.println("In Dao of find user");
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "FROM User u WHERE userName=:userName";
			Query<User> query = session.createQuery(HQL,User.class);
			query.setParameter("userName", userName);
			User user = query.uniqueResult();
			System.out.println("**************Searched User"+user);
			if(user!=null){
				return user;
			}
		} catch(Exception e ){
			System.out.println(e.getMessage());
		}
		return null;
	}

}
